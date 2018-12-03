package com.xiia.indicatori.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.xiia.indicatori.domain.Expense;
import com.xiia.indicatori.domain.Monthly;
import com.xiia.indicatori.domain.MonthlyAllowed;
import com.xiia.indicatori.domain.Percentage;
import com.xiia.indicatori.domain.Relation;
import com.xiia.indicatori.pojo.ExpenseRequest;
import com.xiia.indicatori.pojo.ExpenseResponse;
import com.xiia.indicatori.pojo.SplitChild;
import com.xiia.indicatori.pojo.SplitRequest;
import com.xiia.indicatori.pojo.SplitDetails;
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

	public SplitDetails getSplitDetails(SplitRequest request) {
		
		List<SplitChild> children;
		switch(request.getSplitCode()) 
        { 
            case "PRC": 
            	children = splitPercent(request.getArticleId(), request.getParentUnitId(), request.getMonth());
            case "SRF": 
            	children = splitSurface(request.getArticleId(), request.getParentUnitId(), request.getMonth()); 
            case "MAN": 
            	children = splitCustom(request.getArticleId(), request.getParentUnitId(), request.getMonth()); 
            case "HSD": 
            	children = splitDays(request.getArticleId(), request.getParentUnitId(), request.getMonth()); 
            case "NON": 
            	children = dontSplit(request.getArticleId(), request.getParentUnitId(), request.getMonth()); 
            default: 
            	children = splitCustom(request.getArticleId(), request.getParentUnitId(), request.getMonth()); 
        } 
		
		SplitDetails response = new SplitDetails(children, request.getParentUnitId(), request.getExpenseId(), request.getSplitId(), request.getSplitCode(), request.getMonth(), request.getArticleId(), request.getCategoryId(), request.getGroupId(), true);
		return response;
		
	}
	
	private List<SplitChild> dontSplit(Integer article, Integer parent, String month) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<SplitChild> splitDays(Integer article, Integer parent, String month) {
		
		List<Monthly> monthlyList = repositoryRegistry.getMonthlyRepository().findAllByMonthAndTypeId(month, 1);
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		for(Monthly monthly : monthlyList) {
			map.put(monthly.getUnitId(), monthly.getValue());
		}
		
		List<Expense> expenses = repositoryRegistry.getExpensesRepository().findAllByArticleIdAndParentIdAndMonth(article, parent, month);
		Map<Integer, Double> mapValues = new HashMap<Integer, Double>();
		for(Expense expense : expenses) {
			mapValues.put(expense.getUnitId(), expense.getAmount());
		}
 		
		List<Relation> relationList = repositoryRegistry.getRelationsRepository().findAllByParentId(parent);
		List<SplitChild> children = new ArrayList<SplitChild>();
		
		for (Relation relation : relationList) {
			children.add(new SplitChild(relation.getChild().getId(), 
										relation.getChild().getName(), 
										zeroIfNull(mapValues.get(relation.getChild().getId())), 
										zeroIfNull(map.get(relation.getChild().getId())), 
										true));
			
		}
		
		return children;
	}

	private List<SplitChild> splitCustom(Integer article, Integer parent, String month) {
		List<Expense> expenses = repositoryRegistry.getExpensesRepository().findAllByArticleIdAndParentIdAndMonth(article, parent, month);
		Map<Integer, Double> mapValues = new HashMap<Integer, Double>();
		for(Expense expense : expenses) {
			mapValues.put(expense.getUnitId(), expense.getAmount());
		}
 		
		List<Relation> relationList = repositoryRegistry.getRelationsRepository().findAllByParentId(parent);
		List<SplitChild> children = new ArrayList<SplitChild>();
		
		for (Relation relation : relationList) {
			children.add(new SplitChild(relation.getChild().getId(), 
										relation.getChild().getName(), 
										zeroIfNull(mapValues.get(relation.getChild().getId())), 
										null, 
										true));
			
		}
		
		return children;
	}

	private List<SplitChild> splitSurface(Integer article, Integer parent, String month) {
		
		List<Expense> expenses = repositoryRegistry.getExpensesRepository().findAllByArticleIdAndParentIdAndMonth(article, parent, month);
		Map<Integer, Double> mapValues = new HashMap<Integer, Double>();
		for(Expense expense : expenses) {
			mapValues.put(expense.getUnitId(), expense.getAmount());
		}
 		
		List<Relation> relationList = repositoryRegistry.getRelationsRepository().findAllByParentId(parent);
		List<SplitChild> children = new ArrayList<SplitChild>();
		
		for (Relation relation : relationList) {
			children.add(new SplitChild(relation.getChild().getId(), 
										relation.getChild().getName(), 
										zeroIfNull(mapValues.get(relation.getChild().getId())), 
										zeroIfNull(relation.getChild().getArea()), 
										true));
			
		}
		
		return children;
	}

	private List<SplitChild> splitPercent(Integer article, Integer parent, String month) {
		
		List<Percentage> percentages = repositoryRegistry.getPercentageRepository().findAllByArticleIdAndParentId(article, parent);
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		for(Percentage percentage : percentages) {
			map.put(percentage.getChildId(), percentage.getValue());
		}
		
		List<Expense> expenses = repositoryRegistry.getExpensesRepository().findAllByArticleIdAndParentIdAndMonth(article, parent, month);
		Map<Integer, Double> mapValues = new HashMap<Integer, Double>();
		for(Expense expense : expenses) {
			mapValues.put(expense.getUnitId(), expense.getAmount());
		}
 		
		List<Relation> relationList = repositoryRegistry.getRelationsRepository().findAllByParentId(parent);
		List<SplitChild> children = new ArrayList<SplitChild>();
		
		for (Relation relation : relationList) {
			children.add(new SplitChild(relation.getChild().getId(), 
										relation.getChild().getName(), 
										zeroIfNull(mapValues.get(relation.getChild().getId())), 
										zeroIfNull(map.get(relation.getChild().getId())), 
										true));
			
		}
		
		return children;
	}

	public Double zeroIfNull(Double d) {
		return d == null ? 0d : d;
	}

	public Boolean insertExpense(Expense expense) {
		expense.setId(UUID.randomUUID().toString());
		repositoryRegistry.getExpensesRepository().save(expense);
		return true;
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

	public Boolean isHsdAvailable(Integer unitId) {
		
		List<MonthlyAllowed> allowedList = repositoryRegistry.getMonthlyAllowedRepository().findAllByMonthlyTypeId(1);
		List<Integer> allowedIds = allowedList.stream().map(o -> o.getUnit().getId()).distinct().collect(Collectors.toList());
		
		List<Relation> relationList = repositoryRegistry.getRelationsRepository().findAllByParentId(unitId);
		List<Integer> children = relationList.stream().map(o -> o.getChild().getId()).collect(Collectors.toList());
		
		for (Integer child : children) {
			if (!allowedIds.contains(child)) return false;
		}
		
		return true;
	}

	public Boolean createSplit(SplitDetails request) {

		Expense parentExpense = repositoryRegistry.getExpensesRepository().findOneById(request.getExpenseId());
		
		for (SplitChild split : request.getChildren()) {
			StringBuilder sb = new StringBuilder();
			
			Expense expense = new Expense(sb.toString(), split.getUnitId(), request.getArticleId(), request.getGroupId(), request.getCategoryId(), request.getMonth(), split.getValue(), false, null, parentExpense.getId(), parentExpense.getOriginalParentId() == null ? parentExpense.getId() : parentExpense.getOriginalParentId(), parentExpense.getDescription());
			repositoryRegistry.getExpensesRepository().save(expense);
		}
		
		parentExpense.setSplitId(request.getSplitId());
		repositoryRegistry.getExpensesRepository().save(parentExpense);
		
		return true;
	}
	public Boolean deleteSplit(String originalParentId) {
		return true;
	}
}
