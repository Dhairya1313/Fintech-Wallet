package com.spring.fintech.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.fintech.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	@Query("SELECT t FROM Transaction t WHERE t.receiverWallet.walletId = :walletId OR t.serderWallet.walletId = :walletId ORDER BY t.createdAt DESC")
	public Page<Transaction> findTransactionsByWalletId(Integer walletId, Pageable pageable);  
}
