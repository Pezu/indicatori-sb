package com.xiia.indicatori.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Category;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

	List<Category> findAllByGroupId(Integer group);

}
