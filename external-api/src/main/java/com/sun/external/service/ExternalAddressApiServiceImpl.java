package com.sun.external.service;

import com.sun.shleter.api.ExternalAddressApi;
import com.sun.shleter.dto.AddressApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalAddressApiServiceImpl implements ExternalAddressApiService{

    private final ExternalAddressApi externalAddressApi;
    @Override
    public AddressApiResponse searchAddress(String query, String analyzeType, Integer page, Integer size) {
        return externalAddressApi.searchAddress(query, analyzeType, page, size);
    }
}
