package com.pancakes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Product {

    @Id
    @JsonProperty("id")
    private Long id;

    private String title;
    private String published_at;
    private String created_at;
    private String type;
    private String vendor;
    private double price;
    private double price_min;
    private double price_max;
    private boolean available;

    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
    private List<Variants> variants;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice_min() {
        return price_min;
    }

    public void setPrice_min(double price_min) {
        this.price_min = price_min;
    }

    public double getPrice_max() {
        return price_max;
    }

    public void setPrice_max(double price_max) {
        this.price_max = price_max;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Variants> getVariants() {
        return variants;
    }

    public void setVariants(List<Variants> variants) {
        this.variants = variants;
    }

    public Long getProduct_id() {
        return id;
    }

    public void setProduct_id(Long id) {
        this.id = id;
    }
}
