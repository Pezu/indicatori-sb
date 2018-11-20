package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Expense;

public interface ExpensesRepository extends JpaRepository<Expense, Long> {

}