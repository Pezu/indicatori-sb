package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {

}
