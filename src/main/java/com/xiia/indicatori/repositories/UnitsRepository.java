package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Unit;

public interface UnitsRepository extends JpaRepository<Unit, Long> {

}
