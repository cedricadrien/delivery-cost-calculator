package com.mynt.calculator.deliverycost.integration.configuration;

import com.mynt.calculator.deliverycost.integration.error.VoucherClientErrorDecoder;
import feign.Logger;
import org.springframework.context.annotation.Bean;

public class VoucherClientConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public VoucherClientErrorDecoder errorDecoder() {
        return new VoucherClientErrorDecoder();
    }
}
