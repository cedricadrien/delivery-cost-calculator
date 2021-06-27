package com.mynt.calculator.deliverycost.controller;

import com.mynt.calculator.deliverycost.dto.BaseResponse;
import com.mynt.calculator.deliverycost.dto.OrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Delivery Cost Calculator")
public interface DeliveryCostCalculatorController {

    @ApiOperation(value = "Calculates parcel's delivery cost", notes = "Calculates parcel's delivery cost")
    BaseResponse<OrderDto> calculateDeliveryCost(OrderDto orderRequest);
}
