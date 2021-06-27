package com.mynt.calculator.deliverycost.dto;

import com.mynt.calculator.deliverycost.constant.ResponseCode;
import com.mynt.calculator.deliverycost.constant.Severity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BaseResponse<T> {

    private ResponseCode code;
    private T data;
    private String errorMessage;
    private Severity severity;
}
