package com.medivac.indicatori.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.medivac.indicatori.domain.Categorie;
import com.medivac.indicatori.domain.Unit;
import com.medivac.indicatori.repositories.RepositoryRegistry;

@Service
public class CatalogService {
	
    private final RepositoryRegistry repositoryRegistry;
    
    public CatalogService(final RepositoryRegistry repositoryRegistry) {
			this.repositoryRegistry = repositoryRegistry;
			}

	public List<Categorie> getCategories() {
		return repositoryRegistry.getCategoriesRepository().findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	public List<Unit> getUnits() {
		return repositoryRegistry.getUnitsRepository().findAll(new Sort(Sort.Direction.ASC,"name"));

	}

    
}
