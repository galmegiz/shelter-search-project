package com.sun.external.api;

import com.sun.external.dto.AddressApiResponse;

public interface ExternalAddressApi {
    AddressApiResponse searchAddress(String query, String analyzeType, Integer page, Integer size);
}
