package com.spring.fintech.wallet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequestDto {

    @NotNull(message = "User ID is required.")
    private Integer userId;

}
