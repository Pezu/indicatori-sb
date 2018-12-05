package com.xiia.indicatori.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.xiia.indicatori.domain.Account;
import com.xiia.indicatori.domain.Expense;
import com.xiia.indicatori.domain.Fixed;
import com.xiia.indicatori.domain.FixedHistory;
import com.xiia.indicatori.pojo.ExpenseResponse;
import com.xiia.indicatori.pojo.FixedCreateRequest;
import com.xiia.indicatori.pojo.FixedMoveRequest;
import com.xiia.indicatori.pojo.FixedRequest;
import com.xiia.indicatori.pojo.FixedResponse;
import com.xiia.indicatori.repositories.RepositoryRegistry;

@Service
public class FixedService {
	
	@PersistenceContext
    private EntityManager entityManager;
    private final RepositoryRegistry repositoryRegistry;
    
    public FixedService(final RepositoryRegistry repositoryRegistry, final EntityManager entityManager) {
		this.repositoryRegistry = repositoryRegistry;
		this.entityManager = entityManager;
	}

	public Boolean update(Fixed fixed) {
		Fixed response = repositoryRegistry.getFixedRepository().save(fixed);
		if (response.getId() != null) return true;
		return false;
	}

	public Boolean create(FixedCreateRequest request, Integer user) {
		Fixed fixed = new Fixed(null, request.getCode(), request.getName(), new Account(request.getAccountId(), null, null));
		fixed = repositoryRegistry.getFixedRepository().save(fixed);
		if (fixed.getId() == null) return false;
		return true;
	}

	public FixedResponse getFixed(FixedRequest filter) {
		
		StringBuilder sb = new StringBuilder("select * from fixed where 1 = 1");
		StringBuilder sbCount = new StringBuilder("select count(*) from fixed where 1 = 1");
		
		switch (filter.getFilter()) {
			case "ALL": 
	        	break;
	        case "LIV":
	        	if (filter.getAccount() != 0) {
	        		sb.append(" and account_id =");
	        		sb.append(filter.getAccount());
	        		sbCount.append(" and account_id =");
	        		sbCount.append(filter.getAccount());
	        	} else {
	        		sb.append(" and account_id <> 2 and account_id <> 3");
	        		sbCount.append(" and account_id <> 2 and account_id <> 3");
	        	}
	        	break;
	        case "CST": 
	        	sb.append(" and account_id = 2");
	        	sbCount.append(" and account_id = 2");
	        	break;
	        case "CNS": 
	        	sb.append(" and account_id = 3");
	        	sbCount.append(" and account_id = 3");
	        	break;
	        default:
	        	break;
		} 
		
		
		sb.append(" limit ");
		sb.append(filter.getPageSize());
		sb.append(" offset ");
		sb.append((filter.getPageNo() - 1) * filter.getPageSize());
		
        Query query = entityManager.createNativeQuery(sb.toString(), Fixed.class);
        List<Fixed> fixedList = new ArrayList<Fixed>();
        for (Object fixed : query.getResultList()) {
        	fixedList.add((Fixed)fixed);
        }
        Query queryCount = entityManager.createNativeQuery(sbCount.toString());
        BigInteger count = (BigInteger) queryCount.getSingleResult();
        
        FixedResponse response = new FixedResponse(fixedList, count);
        return response;
	}

	public Boolean move(FixedMoveRequest request, Integer user) {
		FixedHistory history = new FixedHistory(null, new Account(request.getSourceAccountId(), null, null), new Account(request.getDestinationAccountId(), null, null), new Fixed(request.getFixedId(), null, null, null));
		repositoryRegistry.getFixedHistoryRepository().save(history);

		Fixed fixed = repositoryRegistry.getFixedRepository().findById(request.getFixedId()).get();
		fixed.setAccount(new Account(request.getDestinationAccountId(), null, null));
		repositoryRegistry.getFixedRepository().save(fixed);
		
		return true;
	}
    
}
