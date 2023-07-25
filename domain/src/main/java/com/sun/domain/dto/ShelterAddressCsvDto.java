package com.sun.domain.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvDate;
import com.sun.domain.entity.ShelterAddress;
import com.sun.domain.util.coordinate.GeoTransformUtil;
import com.sun.domain.util.csv.LocalDateConverter;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Getter
public class ShelterAddressCsvDto {
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
    private Double x;
    @CsvBindByPosition(position = 27)
    private Double y;
    @CsvBindByPosition(position = 29)
    private String type;

    public static ShelterAddress toEntity(ShelterAddressCsvDto dto){


        Double[] transformed = GeoTransformUtil.transform(dto.getX(), dto.getY());

        return ShelterAddress.builder()
                             .no(dto.getNo())
                             .licenseDate(dto.getLicenseDate())
                             .licenseCancelDate(dto.getLicenseCancelDate())
                             .state(dto.getState())
                             .area(dto.getArea())
                             .address(dto.getAddress())
                             .shelterName(dto.getShelterName())
                             .lastModifiedDate(dto.getLastModifiedDate())
                             .latitude(transformed[0])
                             .longitude(transformed[1])
                             .type(dto.getType())
                             .build();
    }

}
