package com.xiia.indicatori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xiia.indicatori.domain.MonthlyAllowed;
import com.xiia.indicatori.domain.Unit;
import com.xiia.indicatori.repositories.RepositoryRegistry;

@Service
public class MonthlyService {
	
    private final RepositoryRegistry repositoryRegistry;
    
    public MonthlyService(final RepositoryRegistry repositoryRegistry) {
    	this.repositoryRegistry = repositoryRegistry;
	}

	public List<Unit> getMonthlyAllowedUnits(Integer typeId) {
		
		List<MonthlyAllowed> allowedList = this.repositoryRegistry.getMonthlyAllowedRepository().findAllByMonthlyTypeId(typeId);
		List<Unit> units = new ArrayList<Unit>();
		
		for (MonthlyAllowed allowed : allowedList) {
			units.add(allowed.getUnit());
		}
		
		return units;
		
	}
	
	
    
}
