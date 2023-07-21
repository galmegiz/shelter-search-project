package com.sun.domain.util;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvDate;
import com.sun.domain.entity.ShelterAddress;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Getter
public class ShelterAddressDto {

    @CsvBindByPosition(position = 0)
    private int no;
    //@CsvDate(value = "yyyy-MM-dd")
    @CsvCustomBindByPosition(position = 5, converter = LocalDateConverter.class)  //인허가일자
    private LocalDate licenseDate;
    //@CsvDate(value = "yyyy-MM-dd")
    @CsvCustomBindByPosition(position = 6, converter = LocalDateConverter.class)
    private LocalDate licenseCancelDate;
    @CsvBindByPosition(position = 8)
    private String state;
    @CsvBindByPosition(position = 16)
    private Double area; //면적(m2)

    @CsvBindByPosition(position =   19) //도로명 주소
    private String address;
    @CsvBindByPosition(position =   21) //사업장명
    private String shelterName;

    @CsvDate("yyyy-MM-dd HH:mm:ss")
    @CsvBindByPosition(position = 22)
    private LocalDateTime lastModifiedDate;

    @CsvBindByPosition(position = 26)
    private String x;
    @CsvBindByPosition(position = 27)
    private String y;
    @CsvBindByPosition(position = 29)
    private String type;

    public static ShelterAddress toEntity(ShelterAddressDto dto){
        return ShelterAddress.builder()
                             .no(dto.getNo())
                             .licenseDate(dto.getLicenseDate())
                             .licenseCancelDate(dto.getLicenseCancelDate())
                             .state(dto.getState())
                             .area(dto.getArea())
                             .address(dto.getAddress())
                             .shelterName(dto.getShelterName())
                             .lastModifiedDate(dto.getLastModifiedDate())
                             .x(dto.getX())
                             .y(dto.getY())
                             .type(dto.getType())
                             .build();
    }
}
