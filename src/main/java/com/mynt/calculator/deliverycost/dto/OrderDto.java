package com.mynt.calculator.deliverycost.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDto {

    @Valid
    @NotNull
    private ParcelDto parcel;
    private VoucherDto voucher;
    private Double deliveryCost;
}
