package com.sun.domain.util;

import com.opencsv.bean.CsvToBeanFilter;

public class InvalidAddressFilter implements CsvToBeanFilter {
    @Override
    public boolean allowLine(String[] line) {
       return "18".equals(line[9]);
    }
}
