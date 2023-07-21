package com.sun.domain.service;

import com.sun.domain.dto.ShelterAddressDbDto;
import com.sun.domain.entity.ShelterAddress;
import com.sun.domain.repository.ShelterAddressRepository;
import com.sun.domain.util.csv.CsvUtil;
import com.sun.domain.dto.ShelterAddressCsvDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShelterAddressManageService {

    private final ShelterAddressRepository addressRepository;

    public void save() throws IOException {
        List<ShelterAddressCsvDto> dtos = CsvUtil.csvToObject();
        List<ShelterAddress> entities = dtos.stream().map(ShelterAddressCsvDto::toEntity).toList();
        addressRepository.saveAll(entities);
    }

    @Transactional(readOnly = true)
    public List<ShelterAddressDbDto> getAllAddresses() {
        return addressRepository.findAll().stream().map(ShelterAddressDbDto::fromEntity).toList();
    }
}
