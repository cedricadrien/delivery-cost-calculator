package com.mynt.calculator.deliverycost.controller;

import com.mynt.calculator.deliverycost.constant.ResponseCode;
import com.mynt.calculator.deliverycost.dto.BaseResponse;
import com.mynt.calculator.deliverycost.dto.OrderDto;
import com.mynt.calculator.deliverycost.service.DeliveryCostCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deliveries")
public class V1DeliveryCostCalculatorController implements DeliveryCostCalculatorController {

    private final DeliveryCostCalculatorService deliveryCostCalculatorService;

    @Override
    @PostMapping("/calculate")
    public BaseResponse<OrderDto> calculateDeliveryCost(@RequestBody @Valid OrderDto orderRequest) {
        return BaseResponse.<OrderDto>builder()
                .code(ResponseCode.CAL20000)
                .data(deliveryCostCalculatorService.calculateDeliveryCost(orderRequest))
                .build();
    }
}
