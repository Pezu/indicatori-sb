package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Token;

public interface TokensRepository extends JpaRepository<Token, Long> {
	
	public Token findOneByToken(String token);
	
}