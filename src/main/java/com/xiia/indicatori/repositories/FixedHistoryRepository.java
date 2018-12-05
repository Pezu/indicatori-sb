package com.xiia.indicatori.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Fixed;
import com.xiia.indicatori.domain.FixedHistory;

public interface FixedHistoryRepository extends JpaRepository<FixedHistory, Integer> {

}
