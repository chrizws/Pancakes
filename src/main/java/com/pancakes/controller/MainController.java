package com.pancakes.controller;

import com.pancakes.model.Product;
import com.pancakes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.*;


@Controller
public class MainController {

    private final ProductService<Product> productService;

    @Autowired
    public MainController(ProductService<Product> productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<Product> products = productService.getProducts();

        if (products != null) {
            model.addAttribute("product", products);
            model.addAttribute("variants", products.get(0).getVariants());
            model.addAttribute("lastFetch", LocalDateTime.now());

        }

        return "index";
    }

}
