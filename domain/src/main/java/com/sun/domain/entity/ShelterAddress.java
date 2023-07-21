package com.sun.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ShelterAddress extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_address_id")
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

    @Builder
    private ShelterAddress(int no, LocalDate licenseDate, LocalDate licenseCancelDate, String state, Double area, String address, String shelterName, LocalDateTime lastModifiedDate, Double longitude, Double latitude, String type) {
        this.no = no;
        this.licenseDate = licenseDate;
        this.licenseCancelDate = licenseCancelDate;
        this.state = state;
        this.area = area;
        this.address = address;
        this.shelterName = shelterName;
        this.lastModifiedDate = lastModifiedDate;
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
    }
}
