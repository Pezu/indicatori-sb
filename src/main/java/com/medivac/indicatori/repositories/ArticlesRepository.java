package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {

}