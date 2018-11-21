package com.xiia.indicatori.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Relation;

public interface RelationsRepository extends JpaRepository<Relation, Long> {
	
	public List<Relation> findAllByParentId(Integer typeId);

}