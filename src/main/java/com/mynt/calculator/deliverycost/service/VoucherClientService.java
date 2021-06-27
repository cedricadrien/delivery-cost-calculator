package com.mynt.calculator.deliverycost.service;

import com.mynt.calculator.deliverycost.dto.VoucherDto;

public interface VoucherClientService {

    VoucherDto getVoucherDiscount(String voucherCode);
}
