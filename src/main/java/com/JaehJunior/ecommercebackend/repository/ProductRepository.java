package com.JaehJunior.ecommercebackend.repository;

import com.JaehJunior.ecommercebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}