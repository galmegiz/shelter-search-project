package com.sun.domain.service;

import com.sun.domain.dto.AddressInfoDto;
import com.sun.domain.dto.ClientAddressDto;
import com.sun.domain.exception.CoordinateNotFoundException;
import com.sun.external.dto.AddressApiResponse;
import com.sun.external.service.ExternalAddressApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AddressSearchService {
    private final ExternalAddressApiService externalAddressApiService;

    public AddressInfoDto searchAddressInfo(String query) {
        AddressApiResponse apiResponse = externalAddressApiService.searchAddress(query);

        if (Objects.isNull(apiResponse)) {
            log.error("No search result from API");
            throw new CoordinateNotFoundException(query);
        }

        return AddressInfoDto.fromApiResponse(apiResponse);
    }

    public ClientAddressDto searchClientAddress(String query){
        AddressApiResponse apiResponse = externalAddressApiService.searchAddress(query);

        if (Objects.isNull(apiResponse) || CollectionUtils.isEmpty(apiResponse.getContents())) {
            log.error("No search result from API");
            throw new CoordinateNotFoundException(query);
        }

        //clientAddress api 응답
        AddressApiResponse.ContentDto clientAddressInfo = apiResponse.getContents().get(0);
        return ClientAddressDto.from(clientAddressInfo);
    }

}
