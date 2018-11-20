package com.medivac.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medivac.indicatori.domain.Unit;

public interface UnitsRepository extends JpaRepository<Unit, Long> {

}
