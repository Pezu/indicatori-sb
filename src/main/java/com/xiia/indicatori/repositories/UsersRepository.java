package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.User;

public interface UsersRepository extends JpaRepository<User, Long> {

	public User findOneByUsernameAndPassword(String username, String password);
	
}