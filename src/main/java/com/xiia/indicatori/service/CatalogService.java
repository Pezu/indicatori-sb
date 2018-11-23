package com.xiia.indicatori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xiia.indicatori.domain.*;
import com.xiia.indicatori.repositories.RepositoryRegistry;

@Service
public class CatalogService {
	
    private final RepositoryRegistry repositoryRegistry;
    
    public CatalogService(final RepositoryRegistry repositoryRegistry) {
			this.repositoryRegistry = repositoryRegistry;
			}

	public List<Category> getCategories() {
		return repositoryRegistry.getCategoriesRepository().findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	public List<Unit> getUnits() {
		return repositoryRegistry.getUnitsRepository().findAll(new Sort(Sort.Direction.ASC,"name"));
	}
	
	public List<MonthlyType> getMonthlyType() {
		return repositoryRegistry.getMonthlyTypeRepository().findAll();
	}
	
//   banuiesc ca trebuie mutat
//	public List<Unit> getChildUnits(Integer parentId) {
//		
//		List<Relation> relationList = this.repositoryRegistry.getRelationsRepository().findAllByParentId(parentId);
//		List<Unit> units = new ArrayList<Unit>();
//		
//		for (Relation relation : relationList) {
//			units.add(relation.getChild());
//		}
//		
//		return units;
//		
//	}
    
}
