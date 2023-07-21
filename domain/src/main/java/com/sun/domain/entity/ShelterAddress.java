package com.sun.domain.entity;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvDate;
import com.sun.domain.util.LocalDateConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
    private String x;
    private String y;
    private String type;

    @Builder
    private ShelterAddress(int no, LocalDate licenseDate, LocalDate licenseCancelDate, String state, Double area, String address, String shelterName, LocalDateTime lastModifiedDate, String x, String y, String type) {
        this.no = no;
        this.licenseDate = licenseDate;
        this.licenseCancelDate = licenseCancelDate;
        this.state = state;
        this.area = area;
        this.address = address;
        this.shelterName = shelterName;
        this.lastModifiedDate = lastModifiedDate;
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
