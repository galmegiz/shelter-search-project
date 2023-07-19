package com.sun.shleter.service;

import com.sun.shleter.dto.AddressApiResponse;

public interface ExternalAddressApiService {
    AddressApiResponse searchAddress(String query, String analyzeType, Integer page, Integer size);
}
