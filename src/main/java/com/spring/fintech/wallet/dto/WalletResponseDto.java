package com.spring.fintech.wallet.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletResponseDto {

    private Integer walletId;
    private Integer userId;
    private Double walletBalance;
    private String status;
    private LocalDate createdAt;

}