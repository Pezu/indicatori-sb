package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense, Long> {

}