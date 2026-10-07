package com.JaehJunior.ecommercebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JaehJunior.ecommercebackend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}