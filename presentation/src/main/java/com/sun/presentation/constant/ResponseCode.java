package com.sun.presentation.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    SUCCESS(HttpStatus.OK, "1000", "성공"),
    REQUEST_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "4001", "요청 입력값에 오류가 있습니다"),
    COORDINATE_NOT_FOUND(HttpStatus.NOT_FOUND, "4002", "요청하신 주소에 대한 좌표를 찾을 수 없습니다."),
    UNKNOWN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5001", "알 수 없는 서버 에러가 발생했습니다");

    private HttpStatus httpStatus;
    private String code;
    private String desc;

    ResponseCode(HttpStatus httpStatus, String code, String desc) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.desc = desc;
    }
}
