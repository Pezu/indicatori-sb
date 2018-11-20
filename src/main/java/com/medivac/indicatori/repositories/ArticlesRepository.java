package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<Article, Long> {

}