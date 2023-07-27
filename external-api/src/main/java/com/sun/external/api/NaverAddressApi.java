package com.sun.external.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.external.dto.AddressApiResponse;
import com.sun.external.dto.NaverApiResponse;
import com.sun.external.properties.ExternalApiProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
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
@Order(2)
public class NaverAddressApi implements ExternalAddressApi{

    private static final String PARAM_KEY_QUERY = "query";
    private static final String PARAM_KEY_PAGE = "page";
    private static final String PARAM_KEY_SIZE = "count";
    private static final String HEADER_KEY_ID = "X-NCP-APIGW-API-KEY-ID";
    private static final String HEADER_KEY = "X-NCP-APIGW-API-KEY";

    @Qualifier("naverRestTemplate")
    private final RestTemplate naverRestTemplate;
    private final ExternalApiProps apiProperty;
    private final ObjectMapper objectMapper;
    @Retryable(retryFor = {HttpClientErrorException.class}, maxAttempts = 3, recover = "recover")
    @Override
    public AddressApiResponse searchAddress(String query, Integer page, Integer size) {
        URI uri = UriComponentsBuilder.fromHttpUrl(apiProperty.getNaver().getUri())
                                      .queryParam(PARAM_KEY_QUERY, query)
                                      .queryParam(PARAM_KEY_PAGE, page)
                                      .queryParam(PARAM_KEY_SIZE, size)
                                      .build()
                                      .encode()
                                      .toUri();
        RequestEntity request = RequestEntity.get(uri)
                                             .header(HEADER_KEY_ID, apiProperty.getNaver().getKeyId())
                                             .header(HEADER_KEY, apiProperty.getNaver().getKey())
                                             .build();
        ResponseEntity<String> response = naverRestTemplate.exchange(request, String.class);

        try {
            return objectMapper.readValue(response.getBody(), NaverApiResponse.class);
        } catch (JsonProcessingException e) {
            log.error("External Api response parsing error", e);
            throw new RuntimeException(e);
        }
    }

    @Recover
    public AddressApiResponse recover(HttpClientErrorException e, String query, Integer page, Integer size) {
        log.error("All request to Naver Api is failed, query = {}, error response = {}", query, e.getMessage());
        return null;
    }
}
