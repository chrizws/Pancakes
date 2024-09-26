package com.pancakes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Variants {

    @Id
    @JsonProperty("id")
    private Long var_id;

    @ManyToOne
    private Product product;

    private String title;
    private String name;
    private String option1;
    private String option2;
    private double price;
    private boolean available;
    private LocalDateTime last_updated;


    public Long getVar_id() {
        return var_id;
    }

    public void setVar_id(Long var_id) {
        this.var_id = var_id;
    }

    public LocalDateTime getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(LocalDateTime last_updated) {
        this.last_updated = last_updated;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
