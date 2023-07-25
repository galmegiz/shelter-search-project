package com.sun.presentation.exception;

import com.sun.presentation.constant.ResponseCode;
import lombok.Getter;

@Getter
public class PresentationException extends RuntimeException{
    ResponseCode responseCode;

    public PresentationException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
