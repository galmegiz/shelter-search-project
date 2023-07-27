package com.sun.domain.service;

import com.sun.domain.dto.ShelterAddressDbDto;
import com.sun.domain.dto.ShelterRecommendDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Service
@ConditionalOnProperty(prefix = "application", name = "mode", havingValue = "standalone")
public class ShelterAddressLocalCacheService implements ShelterAddressCacheService{

    private final ConcurrentMap<String, List<ShelterRecommendDto>> reCommendCache = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        log.info("local cache is initiated");
    }

    @Override
    public void saveAll(List<ShelterAddressDbDto> shelterAddressDbDtos) {

    }

    @Override
    public List<ShelterAddressDbDto> findAll() {
        return null;
    }

    @Override
    public List<ShelterRecommendDto> findByAddress(String query) {
        return reCommendCache.getOrDefault(query, new ArrayList<>());
    }

    @Override
    public void saveRecommendedShelter(String query, List<ShelterRecommendDto> shelterRecommendDtos) {
        reCommendCache.put(query, shelterRecommendDtos);
    }
}
