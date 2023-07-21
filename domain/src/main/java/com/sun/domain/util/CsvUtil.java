package com.sun.domain.util;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class CsvUtil {

    public static List<ShelterAddressDto> csvToObject() throws IOException {
        List<ShelterAddressDto> addresses = new CsvToBeanBuilder<ShelterAddressDto>(new FileReader(new ClassPathResource("shelter_list.csv").getFile(),
                                                                                    Charset.forName("EUC-KR")))
                                                .withSkipLines(1)
                                                .withType(ShelterAddressDto.class)
                                                .withFilter(new InvalidAddressFilter())
                                                .build()
                                                .parse();
        addresses.stream().filter(shelterAddressDto -> !shelterAddressDto.getState().equals("영업/정상")).forEach(System.out::println);
        return addresses;
    }
}
