package com.xiia.indicatori.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Account;

public interface AccountsRepository extends JpaRepository<Account, Integer> {

}