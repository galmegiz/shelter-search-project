package com.sun.domain.util.csv;

import com.opencsv.bean.CsvToBeanFilter;
import org.springframework.util.ObjectUtils;

public class CsvInputFilter implements CsvToBeanFilter {

    @Override
    public boolean allowLine(String[] line) {
        //미사용 시설 삭제
        if(!ObjectUtils.nullSafeEquals(line[7], "01")){
            return false;
        }

        if(ObjectUtils.isEmpty(line[26]) || ObjectUtils.isEmpty(line[27])){
            return false;
        }
        return true;
    }
}
