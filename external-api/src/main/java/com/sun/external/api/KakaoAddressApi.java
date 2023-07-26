package com.sun.external.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.external.dto.AddressApiResponse;
import com.sun.external.dto.KakaoApiResponse;
import com.sun.external.properties.ExternalApiProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(1)
public class KakaoAddressApi implements ExternalAddressApi {

    private static final String PARAM_KEY_QUERY = "query";
    private static final String PARAM_KEY_ANALYZE_TYPE = "analyze_type";
    private static final String PARAM_KEY_PAGE = "page";
    private static final String PARAM_KEY_SIZE = "size";

    @Qualifier("kakaoRestTemplate")
    private final RestTemplate kakaoRestTemplate;
    private final ExternalApiProps apiProperty;
    private final ObjectMapper objectMapper;
    @Retryable(retryFor = {HttpClientErrorException.class}, maxAttempts = 3, recover = "recover")
    @Override
    public AddressApiResponse searchAddress(String query, Integer page, Integer size) {
        URI uri = UriComponentsBuilder.fromHttpUrl(apiProperty.getKakao().getUri())
                                      .queryParam(PARAM_KEY_QUERY, query)
                                      .queryParam(PARAM_KEY_PAGE, page)
                                      .queryParam(PARAM_KEY_SIZE, size)
                                      .build()
                                      .encode()
                                      .toUri();
        RequestEntity request = RequestEntity.get(uri)
                .header(HttpHeaders.AUTHORIZATION, apiProperty.getKakao().getAuthKey())
                .build();
        ResponseEntity<String> response = kakaoRestTemplate.exchange(request, String.class);

        try {
            return objectMapper.readValue(response.getBody(), KakaoApiResponse.class);
        } catch (JsonProcessingException e) {
            log.error("External Api response parsing error", e);
            throw new RuntimeException(e);
        }
    }



    @Recover
    public AddressApiResponse recover(HttpClientErrorException e, String query, Integer page, Integer size) {
        log.error("All request to Kakao Api is failed, query = {}, error response = {}", query, e.getMessage());
        return null;
    }
}
