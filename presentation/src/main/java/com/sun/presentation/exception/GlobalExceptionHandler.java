package com.sun.presentation.exception;

import com.sun.domain.exception.CoordinateNotFoundException;
import com.sun.presentation.constant.ResponseCode;
import com.sun.presentation.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CoordinateNotFoundException.class)
    public ResponseEntity<Response> CoordinateNotFoundExHandler(CoordinateNotFoundException ex) {
        log.error("API 검색 결과 {}에 대한 좌표결과가 없음", ex.getSearchQuery(), ex);
        Response response = Response.fail(ResponseCode.COORDINATE_NOT_FOUND, "query=" + ex.getSearchQuery());
        return ResponseEntity.status(response.getResponseCode().getHttpStatus()).body(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Response> CoordinateNotFoundExHandler(MissingServletRequestParameterException ex) {
        log.error("API 검색 결과 {}에 대한 좌표결과가 없음", ex);
        Response response = Response.fail(ResponseCode.REQUEST_VALIDATION_ERROR);
        return ResponseEntity.status(response.getResponseCode().getHttpStatus()).body(response);
    }
}
