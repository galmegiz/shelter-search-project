package com.sun.external.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;

@Configuration
@EnableRetry
@Slf4j
public class RestTemplateConfig {
    @Bean
    @Qualifier("kakaoRestTemplate")
    public RestTemplate kakaoRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .additionalInterceptors(new LogInterceptor("kakao"))
                .build();
    }

    @Bean
    @Qualifier("naverRestTemplate")
    public RestTemplate naverRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .additionalInterceptors(new LogInterceptor("naver"))
                .build();
    }


    static class LogInterceptor implements ClientHttpRequestInterceptor{

        private final String API_NAME;

        public LogInterceptor(String apiName) {
            this.API_NAME = apiName;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            final String threadName = Thread.currentThread().getName();
            printRequest(threadName, request, body);
            ClientHttpResponse response = execution.execute(request, body);
            printResponse(threadName, response);
            return response;
        }

        private void printResponse(String threadName, ClientHttpResponse response) throws IOException {
            String body = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8)).lines()
                    .collect(Collectors.joining("\n"));

            log.info("[{}] {} API Response Status: {}, Headers: {}, Body: {}",  threadName, API_NAME,response.getStatusCode(), response.getHeaders(), body);
        }

        private void printRequest(String threadName, HttpRequest request, byte[] body) {
            log.info("[{}] API Request URI: {}, Method: {}, Header: {}, body: {}",
                    threadName, API_NAME, request.getURI(), request.getMethod(), request.getHeaders(), new String(body, StandardCharsets.UTF_8));
        }


    }
}
