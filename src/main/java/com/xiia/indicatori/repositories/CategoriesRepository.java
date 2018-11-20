package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Categorie;

public interface CategoriesRepository extends JpaRepository<Categorie, Long> {

}
