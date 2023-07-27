package com.sun.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ShelterRecommendDto {
    private Long id;
    private int no;
    private String shelterName;
    private String state;
    private Double area;
    private String address;
    private LocalDateTime lastModifiedDate;
    private Double distance;
}
