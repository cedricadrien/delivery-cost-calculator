package com.mynt.calculator.deliverycost.service;

import com.mynt.calculator.deliverycost.dto.OrderDto;

public interface DeliveryCostCalculatorService {

    OrderDto calculateDeliveryCost(OrderDto orderRequest);
}
