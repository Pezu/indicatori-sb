package com.xiia.indicatori.service;

import org.springframework.stereotype.Service;

import com.xiia.indicatori.domain.Fixed;
import com.xiia.indicatori.repositories.RepositoryRegistry;

@Service
public class FixedService {
	
    private final RepositoryRegistry repositoryRegistry;
    
    public FixedService(final RepositoryRegistry repositoryRegistry) {
		this.repositoryRegistry = repositoryRegistry;
	}

	public Boolean update(Fixed fixed) {
		Fixed response = repositoryRegistry.getFixedRepository().save(fixed);
		if (response.getId() != null) return true;
		return false;
	}
    
}
