package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokensRepository extends JpaRepository<Token, Long> {
	
	public Token findOneByToken(String token);
	
}