package com.sun.domain.service;

import com.sun.domain.dto.ShelterAddressDbDto;
import com.sun.domain.entity.ShelterAddress;
import com.sun.domain.repository.ShelterAddressRepository;
import com.sun.domain.util.csv.CsvUtil;
import com.sun.domain.dto.ShelterAddressCsvDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ShelterAddressManageService {

    private final ShelterAddressRepository addressRepository;
    private final ShelterAddressCacheService shelterAddressCacheService;

    public void save() throws IOException {
        addressRepository.deleteAll();

        List<ShelterAddressCsvDto> dtos = CsvUtil.csvToObject();
        List<ShelterAddress> entities = dtos.stream().map(ShelterAddressCsvDto::toEntity).toList();
        addressRepository.saveAll(entities);
        //redis 이용 성능 이점이 없어 기능 제거
        //List<ShelterAddressDbDto> dbDtos = entities.stream().map(ShelterAddressDbDto::fromEntity).toList();
        //shelterAddressCacheService.saveAll(dbDtos);
    }

    @Transactional(readOnly = true)
    public List<ShelterAddressDbDto> getAllAddresses() {
        /* cache사용 시 성능상 이점이 없었음
        List<ShelterAddressDbDto> cacheResult = shelterAddressCacheService.findAll();
        if (!CollectionUtils.isEmpty(cacheResult)) {
            log.info("cache");
            return cacheResult;
        }*/

        List<ShelterAddressDbDto> shelterAddressDbDtos = addressRepository.findAll().stream().map(ShelterAddressDbDto::fromEntity).toList();
        return shelterAddressDbDtos;
    }
}
