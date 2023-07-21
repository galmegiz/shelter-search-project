package com.sun.external.service;


import com.sun.external.api.ExternalAddressApi;
import com.sun.external.dto.AddressApiResponse;
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

    @Override
    public AddressApiResponse searchAddress(String query) {
        return searchAddress(query, null, null, null);
    }
}
