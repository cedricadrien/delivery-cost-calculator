package com.mynt.calculator.deliverycost.controller;

import com.mynt.calculator.deliverycost.constant.ResponseCode;
import com.mynt.calculator.deliverycost.dto.BaseResponse;
import com.mynt.calculator.deliverycost.dto.OrderDto;
import com.mynt.calculator.deliverycost.service.DeliveryCostCalculatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryCostCalculatorControllerTest {

    @Mock
    private DeliveryCostCalculatorService deliveryCostCalculatorService;

    @InjectMocks
    private V1DeliveryCostCalculatorController deliveryCostCalculatorController;

    @Test
    public void test_calculateDeliveryCost_thenSuccess() {
        Double deliveryCost = 10d;
        doReturn(OrderDto.builder()
                .deliveryCost(deliveryCost)
                .build())
                .when(deliveryCostCalculatorService).calculateDeliveryCost(any());

        BaseResponse<OrderDto> response = deliveryCostCalculatorController.calculateDeliveryCost(new OrderDto());

        assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("code", ResponseCode.CAL20000);

        assertThat(response.getData())
                .isNotNull()
                .hasFieldOrPropertyWithValue("deliveryCost", deliveryCost);
    }
}
