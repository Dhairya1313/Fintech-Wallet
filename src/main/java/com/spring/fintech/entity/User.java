package com.spring.fintech.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue()
	private int userId;
	@Column(nullable = false, unique = true, length = 64)
	private String userName;
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	@Column(nullable = false, length = 20)
	private String password;
	private String status;
	private LocalDate creadtedAt;
}
