package com.mynt.calculator.deliverycost.integration;

import com.mynt.calculator.deliverycost.integration.configuration.VoucherClientConfiguration;
import com.mynt.calculator.deliverycost.integration.dto.VoucherClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "voucherClient",
        url = "${integration.voucher.url}",
        configuration = VoucherClientConfiguration.class
)
@RequestMapping("/voucher")
public interface VoucherClient {

    @GetMapping("/{voucherCode}")
    VoucherClientResponse getVoucherDetails(@PathVariable String voucherCode,
                                            @RequestParam("key") String apikey);
}
