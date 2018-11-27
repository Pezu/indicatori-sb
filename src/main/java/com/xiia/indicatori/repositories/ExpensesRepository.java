package com.xiia.indicatori.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Expense;
import com.xiia.indicatori.domain.Percentage;

public interface ExpensesRepository extends JpaRepository<Expense, Long> {

}