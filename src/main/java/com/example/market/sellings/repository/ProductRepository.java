package com.example.market.sellings.repository;

import com.example.market.sellings.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
