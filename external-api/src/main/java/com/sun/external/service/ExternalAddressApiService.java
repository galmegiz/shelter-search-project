package com.sun.external.service;


import com.sun.external.dto.AddressApiResponse;

public interface ExternalAddressApiService {
    AddressApiResponse searchAddress(String query, String analyzeType, Integer page, Integer size);
    AddressApiResponse searchAddress(String query);
}
