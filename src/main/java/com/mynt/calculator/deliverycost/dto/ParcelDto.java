package com.mynt.calculator.deliverycost.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ParcelDto {

    @NotNull
    private Double height;

    @NotNull
    private Double width;

    @NotNull
    private Double length;

    @NotNull
    private Double weight;

    public Double getVolume() {
        return this.width * this.length * this.height;
    }
}
