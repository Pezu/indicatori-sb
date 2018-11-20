package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Group;

public interface GroupsRepository extends JpaRepository<Group, Long> {

}