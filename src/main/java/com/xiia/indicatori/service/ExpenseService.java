package com.xiia.indicatori.service;

import org.springframework.stereotype.Service;

import com.xiia.indicatori.repositories.RepositoryRegistry;

@Service
public class ExpenseService {
	
    private final RepositoryRegistry repositoryRegistry;
    
    public ExpenseService(final RepositoryRegistry repositoryRegistry) {
    	this.repositoryRegistry = repositoryRegistry;
	}

    
}
