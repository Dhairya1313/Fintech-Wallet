package com.spring.fintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.fintech.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>{

}
