package com.sun.domain.dto;

import com.sun.external.dto.AddressApiResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ClientAddressDto {
    private Double latitude;
    private Double longitude;

    public static ClientAddressDto from(AddressApiResponse.ContentDto contentDto){
        return new ClientAddressDto(contentDto.getLatitude(), contentDto.getLongitude());
    }
}
