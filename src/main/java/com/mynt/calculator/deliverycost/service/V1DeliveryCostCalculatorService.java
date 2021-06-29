package com.mynt.calculator.deliverycost.service;

import com.mynt.calculator.deliverycost.constant.ResponseCode;
import com.mynt.calculator.deliverycost.dto.OrderDto;
import com.mynt.calculator.deliverycost.dto.ParcelDto;
import com.mynt.calculator.deliverycost.dto.VoucherDto;
import com.mynt.calculator.deliverycost.exception.ExceptionHelper;
import com.mynt.calculator.deliverycost.model.Condition;
import com.mynt.calculator.deliverycost.model.CostExpression;
import com.mynt.calculator.deliverycost.model.Rule;
import com.mynt.calculator.deliverycost.repository.RuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class V1DeliveryCostCalculatorService implements DeliveryCostCalculatorService {

    private final RuleRepository ruleRepository;
    private final VoucherClientService voucherClientService;

    @Override
    public OrderDto calculateDeliveryCost(OrderDto orderRequest) {
        List<Rule> rules = ruleRepository.getAllByOrderByPriorityAsc();

        Double deliveryCost = rules.stream()
                .filter(rule -> evaluateCondition(orderRequest.getParcel(), rule.getCondition())
                        && rule.getIsActive())
                .peek(rule -> log.info("Parcel falls under {}", rule.getRuleName()))
                .map(rule -> executeCostExpression(orderRequest.getParcel(), rule.getCostExpression()))
                .findFirst()
                .orElseThrow(() -> ExceptionHelper.parcelRejected(ResponseCode.CAL42202));

        if (Objects.isNull(orderRequest.getVoucher())) {
            return OrderDto.builder()
                    .deliveryCost(deliveryCost)
                    .build();
        }

        VoucherDto voucher = voucherClientService
                .getVoucherDiscount(orderRequest.getVoucher().getCode());

        return OrderDto.builder()
                .voucher(voucher)
                .deliveryCost(calculateNetCost(deliveryCost, voucher.getDiscount()))
                .build();
    }

    private boolean evaluateCondition(ParcelDto parcel, Condition condition) {
        return condition.getRelationalOperationId().getOperationCode()
                .evaluate(
                        condition.getParamType().getParamCode().getParamValue(parcel),
                        condition.getOperandValue());
    }

    private Double executeCostExpression(ParcelDto parcel, CostExpression costExpression) {
        if (Objects.isNull(costExpression)) {
            throw ExceptionHelper.parcelRejected(ResponseCode.CAL42203);
        }

        return costExpression.getArithmeticOperation().getOperationCode()
                .execute(
                        costExpression.getParamType().getParamCode().getParamValue(parcel),
                        costExpression.getOperandValue());
    }

    private Double calculateNetCost(Double gross, Float discount) {
        double netCost = gross - discount;

        //return zero if net is negative
        return netCost < 0 ? 0 : netCost;
    }
}
