package com.mynt.calculator.deliverycost.controller.advice;

import com.mynt.calculator.deliverycost.constant.ResponseCode;
import com.mynt.calculator.deliverycost.dto.BaseResponse;
import com.mynt.calculator.deliverycost.exception.BadRequestException;
import com.mynt.calculator.deliverycost.exception.InternalServerErrorException;
import com.mynt.calculator.deliverycost.exception.UnprocessableEntityException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ControllerAdviceTest {

    @InjectMocks
    private ControllerAdvice controllerAdvice;

    @Test
    public void test_handleBadRequestException() {
        BaseResponse<Void> response = controllerAdvice.handleBadRequestException(
                new BadRequestException(ResponseCode.CAL40001, "Bad request", "Bad request"));

        assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("code", ResponseCode.CAL40001)
                .hasFieldOrPropertyWithValue("errorMessage", "Bad request");
    }

    @Test
    public void test_handleUnprocessableEntityException() {
        BaseResponse<Void> response = controllerAdvice.handleUnprocessableEntityException(
                new UnprocessableEntityException(ResponseCode.CAL42201, "Unprocessable entity", "Unprocessable entity"));

        assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("code", ResponseCode.CAL42201)
                .hasFieldOrPropertyWithValue("errorMessage", "Unprocessable entity");
    }

    @Test
    public void test_handleInternalServerErrorException() {
        BaseResponse<Void> response = controllerAdvice.handleInternalServerErrorException(
                new InternalServerErrorException(
                        ResponseCode.CAL50001,
                        "Internal server error",
                        "Internal server error"));

        assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("code", ResponseCode.CAL50001)
                .hasFieldOrPropertyWithValue("errorMessage", "Internal server error");
    }

    @Test
    public void test_handleGeneralException() {
        BaseResponse<Void> response = controllerAdvice.handleGeneralException(new NullPointerException());

        assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("code", ResponseCode.CAL50000);
    }
}
