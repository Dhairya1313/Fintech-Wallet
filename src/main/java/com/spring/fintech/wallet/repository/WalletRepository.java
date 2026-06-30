package com.spring.fintech.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.fintech.wallet.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>{

}
