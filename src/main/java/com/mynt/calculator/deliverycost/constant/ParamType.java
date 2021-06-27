package com.mynt.calculator.deliverycost.constant;

import com.mynt.calculator.deliverycost.dto.ParcelDto;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public enum ParamType {

    WGT(ParcelDto::getWeight),
    VOL(ParcelDto::getVolume);

    private Function<ParcelDto, Double> parcelValue;

    public Double getParamValue(ParcelDto parcelDto) {
        return parcelValue.apply(parcelDto);
    }
}
