package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

}