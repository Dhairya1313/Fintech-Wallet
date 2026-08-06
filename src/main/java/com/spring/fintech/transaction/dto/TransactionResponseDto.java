package com.spring.fintech.transaction.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {

    private Integer transactionId;
    private Integer senderWalletId;
    private Integer receiverWalletId;
    private Double amount;
    private String type;
    private String status;
    private LocalDateTime createdAt;

}
