package com.sun.external.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.external.dto.AddressApiResponse;
import com.sun.external.dto.KakaoApiResponse;
import com.sun.external.properties.ExternalApiProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Component
public class KakaoAddressApi implements ExternalAddressApi {

    private static final String PARAM_KEY_QUERY = "query";
    private static final String PARAM_KEY_ANALYZE_TYPE = "analyze_type";
    private static final String PARAM_KEY_PAGE = "page";
    private static final String PARAM_KEY_SIZE = "size";

    private final RestTemplate restTemplate;
    private final ExternalApiProps apiProperty;
    private final ObjectMapper objectMapper;
    @Override
    public AddressApiResponse searchAddress(String query, String analyzeType, Integer page, Integer size) {
        URI uri = UriComponentsBuilder.fromHttpUrl(apiProperty.getKakao().getUri())
                                      .queryParam(PARAM_KEY_QUERY, query)
                                      .queryParam(PARAM_KEY_ANALYZE_TYPE, analyzeType)
                                      .queryParam(PARAM_KEY_PAGE, page)
                                      .queryParam(PARAM_KEY_SIZE, size)
                                      .build()
                                      .encode()
                                      .toUri();
        RequestEntity request = RequestEntity.get(uri)
                .header(HttpHeaders.AUTHORIZATION, apiProperty.getKakao().getAuthKey())
                .build();

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        try {
            return objectMapper.readValue(response.getBody(), KakaoApiResponse.class);
        } catch (JsonProcessingException e) {
            log.error("External Api response parsing error", e);
            throw new RuntimeException(e);
        }
    }
}
