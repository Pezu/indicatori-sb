package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

	public User findOneByUsernameAndPassword(String username, String password);
	
}