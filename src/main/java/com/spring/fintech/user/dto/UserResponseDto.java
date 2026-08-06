package com.spring.fintech.user.dto;

import java.time.LocalDate;

import com.spring.fintech.wallet.dto.WalletResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
	 private Integer userId;
	    private String userName;
	    private String email;
	    private String status;
	    private LocalDate createdAt;
	    private WalletResponseDto wallet;
}
