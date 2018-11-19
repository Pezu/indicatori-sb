package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

}