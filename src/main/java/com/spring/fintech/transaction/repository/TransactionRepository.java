package com.spring.fintech.transaction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.fintech.transaction.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	Page<Transaction> findBySenderWallet_WalletIdOrReceiverWallet_WalletId(Integer senderWalletId,Integer receiverWalletId,Pageable pageable);
}
