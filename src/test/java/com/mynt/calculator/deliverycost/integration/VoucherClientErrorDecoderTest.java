package com.mynt.calculator.deliverycost.integration;

import com.mynt.calculator.deliverycost.exception.BadRequestException;
import com.mynt.calculator.deliverycost.exception.InternalServerErrorException;
import com.mynt.calculator.deliverycost.integration.error.VoucherClientErrorDecoder;
import feign.Request;
import feign.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class VoucherClientErrorDecoderTest {

    @InjectMocks
    private VoucherClientErrorDecoder errorDecoder;

    @Test
    public void test_decode_withStatus400() {
        Exception exception = errorDecoder.decode("methodKey", mockVoucherClientResponse(400));

        assertThat(exception).isInstanceOf(BadRequestException.class);
    }

    @Test
    public void test_decode_withStatus500() {
        Exception exception = errorDecoder.decode("methodKey", mockVoucherClientResponse(500));

        assertThat(exception).isInstanceOf(InternalServerErrorException.class);
    }

    private Response mockVoucherClientResponse(int status) {
        return Response.builder()
                .status(status)
                .request(Request.create(
                        Request.HttpMethod.GET,
                        "url",
                        Collections.emptyMap(),
                        null
                ))
                .body("{\"error\": \"Invalid code\"}".getBytes())
                .build();
    }
}
