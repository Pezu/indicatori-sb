package com.xiia.indicatori.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Fixed;

public interface FixedRepository extends JpaRepository<Fixed, Integer> {

	List<Fixed> findAllByAccountId(Integer account);

}
