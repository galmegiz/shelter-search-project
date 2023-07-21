package com.sun.domain.util.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import com.sun.domain.dto.ShelterAddressCsvDto;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class CsvUtil {

    public static List<ShelterAddressCsvDto> csvToObject() throws IOException {
        List<ShelterAddressCsvDto> addresses = new CsvToBeanBuilder<ShelterAddressCsvDto>(new FileReader(new ClassPathResource("shelter_list.csv").getFile(),
                                                                                    Charset.forName("EUC-KR")))
                .withSkipLines(1)
                .withType(ShelterAddressCsvDto.class)
                .withFilter(new CsvInputFilter()) // x, y좌표 이상한 데이터 삭제 예시) 경상북도 청송군 청송읍 금월로
                .build()
                .parse();
        return addresses;
    }
}
