package com.pancakes.service;

import com.pancakes.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerService<T extends Product> {

    private final TaskScheduler taskScheduler;
    private final ProductService<T> productService;
    private ScheduledFuture<?> scheduledFuture;
    private int interval;

    @Autowired
    public SchedulerService(ProductService<T> productService) {
        this.productService = productService;
        this.taskScheduler = new ThreadPoolTaskScheduler();
        ((ThreadPoolTaskScheduler)this.taskScheduler).initialize();
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void scheduleCall() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(false);
        }

        CountDownLatch latch = new CountDownLatch(1);
        scheduledFuture = taskScheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.println("Fetching products... " + LocalDateTime.now());
                productService.getProducts();
            } finally {
                latch.countDown();
            }
        }, Duration.ofMinutes(interval));

        try {
            latch.await();
        } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }

    public void stopScheduler() {
        System.out.println("Stopped at " + LocalDateTime.now());
        if (scheduledFuture != null)
            scheduledFuture.cancel(false);
    }
}
