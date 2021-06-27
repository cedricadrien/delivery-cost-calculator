package com.mynt.calculator.deliverycost.exception;

import com.mynt.calculator.deliverycost.constant.ResponseCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionHelper {

    public static BadRequestException badRequestException(ResponseCode code, String source, String errorMessage) {
        return new BadRequestException(code, "Bad request in " + source, errorMessage);
    }

    public static InternalServerErrorException internalServerException(ResponseCode code, String source, String errorMessage) {
        return new InternalServerErrorException(code, "Internal server error in " + source, errorMessage);
    }

    public static UnprocessableEntityException voucherExpired() {
        return new UnprocessableEntityException(
                ResponseCode.CAL42201,
                "Voucher code expired.",
                "Voucher code is already expired.");
    }

    public static UnprocessableEntityException parcelRejected(ResponseCode responseCode) {
        return new UnprocessableEntityException(responseCode, "Parcel is rejected.", "Parcel is rejected.");
    }
}

