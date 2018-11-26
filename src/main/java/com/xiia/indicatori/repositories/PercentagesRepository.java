package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Expense;
import com.xiia.indicatori.domain.Percentage;

public interface PercentagesRepository extends JpaRepository<Percentage, Long> {

}