package com.sun.presentation.dto;

import com.sun.presentation.constant.ResponseCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Response<T> {
    private ResponseCode responseCode;
    private T data;


    public static <T> Response<T> success(T data){
        return new Response<>(ResponseCode.SUCCESS, data);
    }

    public static <Void> Response<Void> fail(ResponseCode responseCode) {
        return new Response<>(responseCode, null);
    }

    public static <String> Response<String> fail(ResponseCode responseCode, String query) {
        return new Response<>(responseCode, query);
    }

}
