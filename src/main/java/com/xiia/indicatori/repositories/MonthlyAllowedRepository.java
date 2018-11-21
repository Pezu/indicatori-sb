package com.xiia.indicatori.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.MonthlyAllowed;

public interface MonthlyAllowedRepository extends JpaRepository<MonthlyAllowed, Long> {
	
	public List<MonthlyAllowed> findAllByMonthlyTypeId(Integer typeId);

}