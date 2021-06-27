package com.mynt.calculator.deliverycost.exception;

import com.mynt.calculator.deliverycost.constant.ResponseCode;
import lombok.Getter;

@Getter
public class InternalServerErrorException extends RuntimeException {

    private final String errorMessage;
    private final ResponseCode responseCode;

    public InternalServerErrorException(ResponseCode responseCode, String message, String errorMessage) {
        super(message);
        this.responseCode = responseCode;
        this.errorMessage = errorMessage;
    }
}
