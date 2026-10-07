package com.JaehJunior.ecommercebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JaehJunior.ecommercebackend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}