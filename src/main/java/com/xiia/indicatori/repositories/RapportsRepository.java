package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Rapport;

public interface RapportsRepository extends JpaRepository<Rapport, Long> {

}