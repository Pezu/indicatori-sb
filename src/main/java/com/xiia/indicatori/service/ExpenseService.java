package com.xiia.indicatori.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xiia.indicatori.domain.Percentage;
import com.xiia.indicatori.pojo.PercentageRequest;
import com.xiia.indicatori.repositories.RepositoryRegistry;

@Service
public class ExpenseService {
	
    private final RepositoryRegistry repositoryRegistry;
    
    public ExpenseService(final RepositoryRegistry repositoryRegistry) {
    	this.repositoryRegistry = repositoryRegistry;
	}

	public List<Percentage> getPercentages(PercentageRequest request) {
		return repositoryRegistry.getExpensesRepository().findAllByArticleIdAndChildIdAndParentId(request.getArticleId(), request.getChildId(), request.getParentId());
	}

    
}
