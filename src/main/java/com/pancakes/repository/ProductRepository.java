package com.pancakes.repository;

import com.pancakes.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository<T extends Product> extends JpaRepository<T, Long> {

}
