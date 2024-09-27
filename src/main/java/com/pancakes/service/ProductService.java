package com.pancakes.service;

import com.pancakes.model.Product;
import com.pancakes.model.Variants;
import com.pancakes.notification.Email;
import com.pancakes.proxy.NotificationProxy;
import com.pancakes.proxy.PancakeProxy;
import com.pancakes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService<T extends Product> {

    private PancakeProxy pancakeProxy;
    private ProductRepository<T> repository;
    private T products;
    private StringBuilder sb;
    private LocalTime time = LocalTime.now();

    @Autowired
    public ProductService(PancakeProxy pancakeProxy, ProductRepository<T> repository) {
        this.pancakeProxy = pancakeProxy;
        this.repository = repository;
    }

    public List<T> getProducts() {

        products = (T)pancakeProxy.getProducts();
        sb = new StringBuilder();

        if (isAvailable(products)) {
            List<Variants> var = getAvailableVariants(products);

            var.stream().forEach(e -> sb.append(e.getName())
                    .append("\t\t").append(e.getOption1()).append(" / ").append(e.getOption2()).append("\n"));

            sendEmail();
        }

        saveProducts(products);

        return List.of(products);
    }

    private void sendEmail() {

        LocalTime now = LocalTime.now();

        if (time.until(now, ChronoUnit.MINUTES) > 10) {
            System.out.println(sb);

            NotificationProxy email = new Email();
            email.send(sb);
            time = now;
        }
    }

    public String getAvailableProductsAsString() {
        return sb.toString();
    }

    private void saveProducts(T p) {

        repository.save(p);
    }

    public List<T> getProductList() {
        return List.of(products);
    }

    private List<Variants> getAvailableVariants(Product p) {
        List<Variants> variants = p.getVariants().stream()
                .filter(Variants::isAvailable)
                .collect(Collectors.toList());

        return variants;
    }

    private boolean isAvailable(T p) {

        AtomicInteger count = new AtomicInteger();

        List<Variants> variants = p.getVariants().stream()
                .peek(e -> {
                    if (e.isAvailable()) {
                        e.setLast_updated(LocalDateTime.now());
                        count.getAndIncrement();
                    }
                })
                .toList();

        p.setVariants(variants);

        return count.get() > 0;
    }
}
