package com.sun.external.service;


import com.sun.external.api.ExternalAddressApi;
import com.sun.external.dto.AddressApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalAddressApiServiceImpl implements ExternalAddressApiService{

    private final List<ExternalAddressApi> externalAddressApi;
    @Override
    public AddressApiResponse searchAddress(String query, String analyzeType, Integer page, Integer size) {
        for (ExternalAddressApi addressApi : externalAddressApi) {
            AddressApiResponse result = addressApi.searchAddress(query, page, size);
            if (result != null && !CollectionUtils.isEmpty(result.getContents())) {
                return result;
            }
        }
        return null;
    }

    @Override
    public AddressApiResponse searchAddress(String query) {
        return searchAddress(query, null, null, null);
    }
}
