package com.mynt.calculator.deliverycost.controller.advice;

import com.mynt.calculator.deliverycost.constant.ResponseCode;
import com.mynt.calculator.deliverycost.constant.Severity;
import com.mynt.calculator.deliverycost.dto.BaseResponse;
import com.mynt.calculator.deliverycost.exception.BadRequestException;
import com.mynt.calculator.deliverycost.exception.InternalServerErrorException;
import com.mynt.calculator.deliverycost.exception.UnprocessableEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Void> handleBadRequestException(BadRequestException e) {
        log.warn(e.getMessage());
        return buildExceptionResponse(e.getResponseCode(), e.getErrorMessage(), Severity.WARN);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public BaseResponse<Void> handleUnprocessableEntityException(UnprocessableEntityException e) {
        log.warn(e.getMessage());
        return buildExceptionResponse(e.getResponseCode(), e.getErrorMessage(), Severity.WARN);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Void> handleInternalServerErrorException(InternalServerErrorException e) {
        log.error(e.getMessage());
        return buildExceptionResponse(e.getResponseCode(), e.getErrorMessage(), Severity.ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Void> handleArgsNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        return buildExceptionResponse(ResponseCode.CAL40000,
                String.format("%s %s",
                        e.getBindingResult().getFieldError().getField(),
                        e.getBindingResult().getFieldError().getDefaultMessage()),
                Severity.WARN);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Void> handleGeneralException(Exception e) {
        log.error(e.getMessage());
        return buildExceptionResponse(ResponseCode.CAL50000, e.getMessage(), Severity.ERROR);
    }

    private BaseResponse<Void> buildExceptionResponse(ResponseCode code, String message, Severity severity) {
        return BaseResponse.<Void>builder()
                .code(code)
                .errorMessage(message)
                .severity(severity)
                .build();
    }
}
