package com.xiia.indicatori.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Expense;

public interface ExpensesRepository extends JpaRepository<Expense, Long> {

	public List<Expense> findAllByArticleIdAndParentIdAndMonth(Integer articleId, Integer parentId, String month);

	public Expense findOneById(String id);
}