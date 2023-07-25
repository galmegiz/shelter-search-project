package com.sun.presentation.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    SUCCESS(HttpStatus.OK, "1000", "성공"),
    COORDINATE_NOT_FOUND(HttpStatus.NOT_FOUND, "2001", "요청하신 주소에 대한 좌표를 찾을 수 없습니다.");

    private HttpStatus httpStatus;
    private String code;
    private String desc;

    ResponseCode(HttpStatus httpStatus, String code, String desc) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.desc = desc;
    }
}
