package com.mynt.calculator.deliverycost.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VoucherClientResponse {

    private String code;
    private Float discount;
    private LocalDate expiry;
    private String error;
}
