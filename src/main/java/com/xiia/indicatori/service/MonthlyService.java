package com.xiia.indicatori.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xiia.indicatori.domain.Unit;
import com.xiia.indicatori.repositories.RepositoryRegistry;

@Service
public class MonthlyService {
	
    private final RepositoryRegistry repositoryRegistry;
    
    public MonthlyService(final RepositoryRegistry repositoryRegistry) {
    	this.repositoryRegistry = repositoryRegistry;
	}

	public List<Unit> getMonthlyAllowedUnits(Integer typeId) {
		
		this.repositoryRegistry.getMonthlyAllowedRepository().findAllByMonthlyTypeId(typeId);
		
		return null;

	}

    
}
