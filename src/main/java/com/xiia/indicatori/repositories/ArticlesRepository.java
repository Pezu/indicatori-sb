package com.xiia.indicatori.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Article;

public interface ArticlesRepository extends JpaRepository<Article, Long> {

	List<Article> findAllByCategoryId(Integer category);

}