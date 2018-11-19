package com.medivac.indicatori.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.medivac.indicatori.domain.Categories;
import com.medivac.indicatori.domain.Units;
import com.medivac.indicatori.repositories.RepositoryRegistry;

@Service
public class CatalogService {
	
    private final RepositoryRegistry repositoryRegistry;
    
    public CatalogService(final RepositoryRegistry repositoryRegistry) {
			this.repositoryRegistry = repositoryRegistry;
			}

	public List<Categories> getCategories() {
		return repositoryRegistry.getCategoriesRepository().findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	public List<Units> getUnits() {
		return repositoryRegistry.getUnitsRepository().findAll(new Sort(Sort.Direction.ASC,"name"));

	}

    
}
