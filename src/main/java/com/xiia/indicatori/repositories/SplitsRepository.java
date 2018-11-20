package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Split;

public interface SplitsRepository extends JpaRepository<Split, Long> {

}