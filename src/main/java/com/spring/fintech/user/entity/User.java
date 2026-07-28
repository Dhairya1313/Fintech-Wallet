package com.spring.fintech.user.entity;

import java.time.LocalDate;

import com.spring.fintech.wallet.entity.Wallet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	@Column(nullable = false, unique = true, length = 64)
	private String userName;
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	@Column(nullable = false, length = 20)
	private String password;
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	private LocalDate createdAt;
	
	@OneToOne(cascade = CascadeType.ALL) 
	@JoinColumn(name="wallet_id",unique=true)  
	private Wallet wallet;

}
