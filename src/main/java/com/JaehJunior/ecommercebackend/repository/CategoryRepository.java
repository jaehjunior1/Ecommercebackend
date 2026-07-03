package com.JaehJunior.ecommercebackend.repository;

import com.JaehJunior.ecommercebackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}