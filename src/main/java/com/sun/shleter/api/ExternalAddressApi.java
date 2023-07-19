package com.sun.shleter.api;

import com.sun.shleter.dto.AddressApiResponse;

public interface ExternalAddressApi {
    AddressApiResponse searchAddress(String query, String analyzeType, Integer page, Integer size);
}
