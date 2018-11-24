package com.xiia.indicatori.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Monthly;

public interface MonthlyRepository extends JpaRepository<Monthly, Long> {

	public List<Monthly> findAllByMonthAndTypeId(String month, Integer typeId);

	public Monthly findOneByMonthAndTypeIdAndUnitId(String month, Integer typeId, Integer unitId);
	
}