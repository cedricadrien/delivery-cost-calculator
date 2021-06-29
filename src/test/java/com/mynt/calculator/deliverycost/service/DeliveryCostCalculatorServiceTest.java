package com.mynt.calculator.deliverycost.service;

import com.mynt.calculator.deliverycost.constant.ArithmeticOperation;
import com.mynt.calculator.deliverycost.constant.ParamType;
import com.mynt.calculator.deliverycost.constant.RelationalOperation;
import com.mynt.calculator.deliverycost.dto.OrderDto;
import com.mynt.calculator.deliverycost.dto.ParcelDto;
import com.mynt.calculator.deliverycost.dto.VoucherDto;
import com.mynt.calculator.deliverycost.exception.UnprocessableEntityException;
import com.mynt.calculator.deliverycost.model.*;
import com.mynt.calculator.deliverycost.repository.RuleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryCostCalculatorServiceTest {

    @Mock
    private VoucherClientService voucherClientService;

    @Mock
    private RuleRepository ruleRepository;

    @InjectMocks
    private V1DeliveryCostCalculatorService deliveryCostCalculatorService;

    private static final String VOUCHER_CODE = "MYNT";
    private static final Float VOUCHER_DISCOUNT = 10f;

    @Test
    public void test_calculateDeliveryCost_thenParcelNoneMatchRule() {
        doReturn(createRules()).when(ruleRepository).getAllByOrderByPriorityAsc();

        OrderDto orderRequest = OrderDto.builder()
                .parcel(ParcelDto.builder()
                        .length(20d)
                        .width(20d)
                        .height(20d)
                        .weight(5d)
                        .build())
                .build();

        assertThatThrownBy(() -> deliveryCostCalculatorService.calculateDeliveryCost(orderRequest))
                .isInstanceOf(UnprocessableEntityException.class)
                .hasMessage("Parcel is rejected.");
    }

    @Test
    public void test_calculateDeliveryCost_thenParcelRejected() {
        doReturn(createRules()).when(ruleRepository).getAllByOrderByPriorityAsc();

        OrderDto orderRequest = OrderDto.builder()
                .parcel(ParcelDto.builder()
                        .length(20d)
                        .width(20d)
                        .height(20d)
                        .weight(5d)
                        .build())
                .build();

        assertThatThrownBy(() -> deliveryCostCalculatorService.calculateDeliveryCost(orderRequest))
                .isInstanceOf(UnprocessableEntityException.class)
                .hasMessage("Parcel is rejected.");
    }

    @Test
    public void test_calculateDeliveryCost_withVoucher_thenSuccess() {
        doReturn(createRules()).when(ruleRepository).getAllByOrderByPriorityAsc();
        doReturn(VoucherDto.builder()
                .code(VOUCHER_CODE)
                .discount(VOUCHER_DISCOUNT)
                .build())
                .when(voucherClientService).getVoucherDiscount(anyString());

        OrderDto response = deliveryCostCalculatorService.calculateDeliveryCost(OrderDto.builder()
                .parcel(ParcelDto.builder()
                        .length(20d)
                        .width(20d)
                        .height(20d)
                        .weight(11d)
                        .build())
                .voucher(VoucherDto.builder()
                        .code(VOUCHER_CODE)
                        .build())
                .build());

        verify(voucherClientService).getVoucherDiscount(anyString());
        assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("deliveryCost", 210d);
        assertThat(response.getVoucher())
                .isNotNull()
                .hasFieldOrPropertyWithValue("code", VOUCHER_CODE)
                .hasFieldOrPropertyWithValue("discount", VOUCHER_DISCOUNT);
    }

    @Test
    public void test_calculateDeliveryCost_withoutVoucher_thenSuccess() {
        doReturn(createRules()).when(ruleRepository).getAllByOrderByPriorityAsc();

        OrderDto response = deliveryCostCalculatorService.calculateDeliveryCost(OrderDto.builder()
                .parcel(ParcelDto.builder()
                        .length(20d)
                        .width(20d)
                        .height(20d)
                        .weight(20d)
                        .build())
                .build());

        verifyZeroInteractions(voucherClientService);
        assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("deliveryCost", 400d);
        assertThat(response.getVoucher()).isNull();
    }

    private List<Rule> createRules() {
        return Arrays.asList(
                Rule.builder()
                        .isActive(true)
                        .condition(Condition.builder()
                                .paramType(ParamTypeCodelist.builder()
                                        .paramCode(ParamType.WGT)
                                        .build())
                                .relationalOperationId(RelationalOperationCodelist.builder()
                                        .operationCode(RelationalOperation.GT)
                                        .build())
                                .operandValue(10d)
                                .build())
                        .costExpression(CostExpression.builder()
                                .paramType(ParamTypeCodelist.builder()
                                        .paramCode(ParamType.WGT)
                                        .build())
                                .arithmeticOperation(ArithmeticOperationCodelist.builder()
                                        .operationCode(ArithmeticOperation.MUL)
                                        .build())
                                .operandValue(20d)
                                .build())
                        .build(),
                Rule.builder()
                        .isActive(true)
                        .condition(Condition.builder()
                                .paramType(ParamTypeCodelist.builder()
                                        .paramCode(ParamType.VOL)
                                        .build())
                                .relationalOperationId(RelationalOperationCodelist.builder()
                                        .operationCode(RelationalOperation.LT)
                                        .build())
                                .operandValue(1500d)
                                .build())
                        .build()
        );
    }
}
