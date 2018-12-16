package com.xiia.indicatori.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Expense;

public interface ExpensesRepository extends JpaRepository<Expense, String> {

	public List<Expense> findAllByParentId(String parentId);

	public Expense findOneById(String id);

	public List<Expense> findAllByUnitIdIn(List<Integer> children);
	
}