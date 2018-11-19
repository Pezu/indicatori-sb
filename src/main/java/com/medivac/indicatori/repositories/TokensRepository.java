package com.medivac.indicatori.repositories;

import com.medivac.indicatori.domain.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokensRepository extends JpaRepository<Tokens, Long> {

}