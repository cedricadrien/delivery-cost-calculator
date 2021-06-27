package com.mynt.calculator.deliverycost.service;

import com.mynt.calculator.deliverycost.dto.VoucherDto;
import com.mynt.calculator.deliverycost.exception.UnprocessableEntityException;
import com.mynt.calculator.deliverycost.integration.VoucherClient;
import com.mynt.calculator.deliverycost.integration.dto.VoucherClientResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class VoucherClientServiceTest {

    @Mock
    private VoucherClient voucherClient;

    @InjectMocks
    private V1VoucherClientService voucherClientService;

    private static final LocalDate NOW = LocalDate.now();
    private static final String VOUCHER_CODE = "MYNT";

    @Test
    public void test_getVoucherDiscount_thenVoucherExpired() {
        doReturn(VoucherClientResponse.builder()
                .discount(12f)
                .expiry(NOW.minusDays(1))
                .build())
                .when(voucherClient).getVoucherDetails(anyString(), anyString());

        assertThatThrownBy(() -> voucherClientService.getVoucherDiscount(VOUCHER_CODE))
                .isInstanceOf(UnprocessableEntityException.class)
                .hasMessage("Voucher code expired.");
    }

    @Test
    public void test_getVoucherDiscount_thenSuccess() {
        doReturn(VoucherClientResponse.builder()
                .discount(12f)
                .expiry(NOW.plusMonths(1))
                .build())
                .when(voucherClient).getVoucherDetails(anyString(), anyString());

        VoucherDto response = voucherClientService.getVoucherDiscount(VOUCHER_CODE);

        assertThat(response)
                .isNotNull()
                .hasFieldOrPropertyWithValue("code", VOUCHER_CODE)
                .hasFieldOrPropertyWithValue("discount", 12f);
    }
}
