package com.sun.domain.service;

import com.sun.domain.entity.ShelterAddress;
import com.sun.domain.repository.ShelterAddressRepository;
import com.sun.domain.util.CsvUtil;
import com.sun.domain.util.ShelterAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShelterAddressService {

    private final ShelterAddressRepository addressRepository;

    public void save() throws IOException {
        List<ShelterAddressDto> dtos = CsvUtil.csvToObject();
        List<ShelterAddress> entities = dtos.stream().map(ShelterAddressDto::toEntity).toList();
        addressRepository.saveAll(entities);
    }
}
