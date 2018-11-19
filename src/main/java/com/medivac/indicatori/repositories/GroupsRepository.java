package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Groups, Long> {

}