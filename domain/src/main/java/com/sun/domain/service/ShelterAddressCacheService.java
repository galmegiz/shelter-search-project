package com.sun.domain.service;

import com.sun.domain.dto.ShelterAddressDbDto;
import com.sun.domain.dto.ShelterRecommendDto;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;

import java.util.Collections;
import java.util.List;

public interface ShelterAddressCacheService {
    String CACHE_KEY_SHELTER_ADDRESS = "SHELTER";
    String CACHE_KEY_RECOMMEND = "RECOMMEND";
    void saveAll(List<ShelterAddressDbDto> shelterAddressDbDtos);
    List<ShelterAddressDbDto> findAll();
    List<ShelterRecommendDto> findByAddress(String query);
    void saveRecommendedShelter(String query, List<ShelterRecommendDto> shelterRecommendDtos);

}
