package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.MonthlyType;

public interface MonthlyTypeRepository extends JpaRepository<MonthlyType, Long> {

}