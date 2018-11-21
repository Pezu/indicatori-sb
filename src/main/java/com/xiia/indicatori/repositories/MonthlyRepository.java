package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Monthly;

public interface MonthlyRepository extends JpaRepository<Monthly, Long> {

}