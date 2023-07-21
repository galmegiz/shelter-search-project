package com.sun.domain.dto;

import com.sun.domain.entity.ShelterAddress;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ShelterAddressDbDto {
    private Long id;
    private int no;
    private LocalDate licenseDate;
    private LocalDate licenseCancelDate;
    private String state;
    private Double area;
    private String address;
    private String shelterName;
    private LocalDateTime lastModifiedDate;
    private Double longitude;
    private Double latitude;
    private String type;

    public static ShelterAddressDbDto fromEntity(ShelterAddress entity) {
        return ShelterAddressDbDto.builder()
                .id(entity.getId())
                .no(entity.getNo())
                .licenseDate(entity.getLicenseDate())
                .licenseCancelDate(entity.getLicenseCancelDate())
                .state(entity.getState())
                .area(entity.getArea())
                .address(entity.getAddress())
                .shelterName(entity.getShelterName())
                .lastModifiedDate(entity.getLastModifiedDate())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .type(entity.getType())
                .build();
    }

}
