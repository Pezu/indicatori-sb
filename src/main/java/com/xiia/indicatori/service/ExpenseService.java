package com.xiia.indicatori.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.xiia.indicatori.domain.Expense;
import com.xiia.indicatori.domain.Percentage;
import com.xiia.indicatori.domain.Relation;
import com.xiia.indicatori.pojo.ExpenseRequest;
import com.xiia.indicatori.pojo.ExpenseResponse;
import com.xiia.indicatori.pojo.SplitPercentage;
import com.xiia.indicatori.repositories.RepositoryRegistry;

@Service
public class ExpenseService {
	
	@PersistenceContext
    private EntityManager entityManager;
    private final RepositoryRegistry repositoryRegistry;
    
    public ExpenseService(final RepositoryRegistry repositoryRegistry, final EntityManager entityManager) {
    	this.repositoryRegistry = repositoryRegistry;
    	this.entityManager = entityManager;
	}

	public List<SplitPercentage> getPercentages(Integer article, Integer unit) {
		List<Percentage> percentages = repositoryRegistry.getPercentageRepository().findAllByArticleIdAndParentId(article, unit);
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		for(Percentage percentage : percentages) {
			map.put(percentage.getChildId(), percentage.getValue());
		}
		
		List<Relation> relationList = repositoryRegistry.getRelationsRepository().findAllByParentId(unit);
		List<SplitPercentage> response = new ArrayList<SplitPercentage>();
		
		for (Relation relation : relationList) {
			response.add(new SplitPercentage(relation.getChild().getId(), 
											relation.getChild().getName(), 
											article, 
											0d, 
											zeroIfNull(map.get(relation.getChild().getId())), 
											true, 
											unit));
		}
		
		return response;
		
	}
	
	public Double zeroIfNull(Double d) {
		return d == null ? 0d : d;
	}

	public Boolean insertExpense(Expense expense) {
		Expense response = repositoryRegistry.getExpensesRepository().save(expense);
		if (response.getId() != null) return true;
		return false;
	}

	public void deleteExpense(Long expenseId) {
		repositoryRegistry.getExpensesRepository().deleteById(expenseId);
	}

	public List<Expense> getAllExpenses() {
		return repositoryRegistry.getExpensesRepository().findAll();
	}
	public ExpenseResponse getAllExpenses(ExpenseRequest filter) {
		StringBuilder sb = new StringBuilder("select * from expenses where month = '");
		StringBuilder sbCount = new StringBuilder("select count(*) from expenses where month = '");
		sb.append(filter.getMonth());
		sb.append("'");
		sbCount.append(filter.getMonth());
		sbCount.append("'");
		if (filter.getUnitId() != null) {
			sb.append(" and unit_id = ");
			sb.append(filter.getUnitId());
			sbCount.append(" and unit_id = ");
			sbCount.append(filter.getUnitId());
		}
		if (filter.getGroupId() != null) {
			sb.append(" and group_id = ");
			sb.append(filter.getGroupId());
			sbCount.append(" and group_id = ");
			sbCount.append(filter.getGroupId());
		}
		if (filter.getCategoryId() != null) {
			sb.append(" and category_id = ");
			sb.append(filter.getCategoryId());
			sbCount.append(" and category_id = ");
			sbCount.append(filter.getCategoryId());
		}
		if (filter.getArticleId() != null) {
			sb.append(" and article_id = ");
			sb.append(filter.getArticleId());
			sbCount.append(" and article_id = ");
			sbCount.append(filter.getArticleId());
		}
		if (filter.getRoot().intValue() == 0) {
			sb.append(" and parent_id is null");
			sbCount.append(" and parent_id is null");
		}
		if (filter.getRoot().intValue() == 1) {
			sb.append(" and parent_id is not null");
			sbCount.append(" and parent_id is not null");
		}
		if (filter.getSplit().intValue() == 0) {
			sb.append(" and split_id is null");
			sbCount.append(" and split_id is null");
		}
		if (filter.getSplit().intValue() == 1) {
			sb.append(" and split_id is not null");
			sbCount.append(" and split_id is not null");
		}
		sb.append(" limit ");
		sb.append(filter.getPageSize());
		sb.append(" offset ");
		sb.append((filter.getPageNo() - 1) * filter.getPageSize());
        Query query = entityManager.createNativeQuery(sb.toString(), Expense.class);
        List<Expense> expenses = new ArrayList<Expense>();
        for (Object expense : query.getResultList()) {
        	expenses.add((Expense)expense);
        }
        Query queryCount = entityManager.createNativeQuery(sbCount.toString());
        BigInteger count = (BigInteger) queryCount.getSingleResult();
        
        ExpenseResponse response = new ExpenseResponse(expenses, count);
        return response;
    }
}
