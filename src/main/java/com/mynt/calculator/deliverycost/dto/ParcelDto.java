package com.mynt.calculator.deliverycost.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ParcelDto {

    @NotNull
    @Min(0)
    private Double height;

    @NotNull
    @Min(0)
    private Double width;

    @NotNull
    @Min(0)
    private Double length;

    @NotNull
    @Min(0)
    private Double weight;

    public Double getVolume() {
        return this.width * this.length * this.height;
    }
}
