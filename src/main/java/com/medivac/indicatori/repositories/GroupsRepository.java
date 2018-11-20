package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Group, Long> {

}