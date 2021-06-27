package com.mynt.calculator.deliverycost.service;

import com.mynt.calculator.deliverycost.dto.VoucherDto;
import com.mynt.calculator.deliverycost.exception.ExceptionHelper;
import com.mynt.calculator.deliverycost.integration.VoucherClient;
import com.mynt.calculator.deliverycost.integration.dto.VoucherClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class V1VoucherClientService implements VoucherClientService {

    private final VoucherClient voucherClient;
    private static final String KEY = "apikey";

    @Override
    public VoucherDto getVoucherDiscount(String voucherCode) {
        VoucherClientResponse voucherResponse = voucherClient.getVoucherDetails(voucherCode, KEY);

        if (Objects.nonNull(voucherResponse.getExpiry()) && LocalDate.now().isAfter(voucherResponse.getExpiry())) {
            throw ExceptionHelper.voucherExpired();
        }

        return VoucherDto.builder()
                .code(voucherCode)
                .discount(voucherResponse.getDiscount())
                .build();
    }
}
