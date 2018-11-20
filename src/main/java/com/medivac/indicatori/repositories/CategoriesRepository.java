package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categorie, Long> {

}
