package com.mynt.calculator.deliverycost.integration.error;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mynt.calculator.deliverycost.constant.ResponseCode;
import com.mynt.calculator.deliverycost.exception.ExceptionHelper;
import com.mynt.calculator.deliverycost.integration.dto.VoucherClientResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class VoucherClientErrorDecoder implements ErrorDecoder {

    private final Default defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            VoucherClientResponse voucherResponse = mapper.readValue(
                    IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8.name()),
                    VoucherClientResponse.class);

            switch (response.status()) {
                case 400:
                    return ExceptionHelper.badRequestException(
                            ResponseCode.CAL40001, "Bad request in voucher client.", voucherResponse.getError());
                case 500:
                    return ExceptionHelper.internalServerException(
                            ResponseCode.CAL50001,
                            "Internal server error in voucher client.",
                            voucherResponse.getError());
                default:
                    return defaultErrorDecoder.decode(methodKey, response);
            }
        } catch (IOException e) {
            log.error("Failed to read VoucherClient response.");
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }
}
