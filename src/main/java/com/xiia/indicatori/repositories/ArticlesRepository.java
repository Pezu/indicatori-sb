package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Article;

public interface ArticlesRepository extends JpaRepository<Article, Long> {

}