package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Category;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

}
