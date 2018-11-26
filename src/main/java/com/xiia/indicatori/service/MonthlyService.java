package com.xiia.indicatori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.xiia.indicatori.domain.Monthly;
import com.xiia.indicatori.domain.MonthlyAllowed;
import com.xiia.indicatori.domain.Unit;
import com.xiia.indicatori.pojo.MonthlyUpdate;
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

	public List<Monthly> getMonthlyByMonthAndTypeId(String month, Integer typeId) {
		return repositoryRegistry.getMonthlyRepository().findAllByMonthAndTypeId(month, typeId);
		
	}

	public Boolean update(List<MonthlyUpdate> list) {
		
		try {
			for (MonthlyUpdate update : list) {
				Monthly monthly = repositoryRegistry.getMonthlyRepository()
													.findOneByMonthAndTypeIdAndUnitId(update.getMonth(), 
																						update.getTypeId(),
																						update.getUnitId());
				
				repositoryRegistry.getMonthlyRepository().save(new Monthly(monthly == null ? null : monthly.getId(), 
																			update.getMonth(), 
																			update.getUnitId(), 
																			update.getTypeId(),
																			update.getValue()));
			}
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	
    
}
