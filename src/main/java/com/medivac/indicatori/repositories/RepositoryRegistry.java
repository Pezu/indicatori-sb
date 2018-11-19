package com.medivac.indicatori.repositories;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class RepositoryRegistry {

    private final CategoriesRepository categoriesRepository;
    private final UnitsRepository unitsRepository;
 
}
