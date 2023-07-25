package com.sun.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.domain.dto.ShelterAddressDbDto;
import com.sun.domain.dto.ShelterRecommendDto;
import com.sun.domain.exception.RedisOperationException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShelterAddressCacheService {

    private static final String CACHE_KEY_SHELTER_ADDRESS = "SHELTER";
    private static final String CACHE_KEY_RECOMMEND = "RECOMMEND";

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private HashOperations<String, String, String> hashOperations;


    @PostConstruct
    public void init() {
        redisTemplate.expire(CACHE_KEY_RECOMMEND, 5, TimeUnit.MINUTES);
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void saveAll(List<ShelterAddressDbDto> shelterAddressDbDtos) {

        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (ShelterAddressDbDto shelterAddressDbDto : shelterAddressDbDtos) {
                    hashOperations.put(CACHE_KEY_SHELTER_ADDRESS, shelterAddressDbDto.getId().toString(), serialize(shelterAddressDbDto));
                }
                return null;
            }
        });
    }

    public List<ShelterAddressDbDto> findAll() {
        List<String> values = hashOperations.values(CACHE_KEY_SHELTER_ADDRESS);
        return values.stream().map(value -> deserialize(value, ShelterAddressDbDto.class)).toList();
    }

    public List<ShelterRecommendDto> findByAddress(String query) {
        String result = hashOperations.get(CACHE_KEY_RECOMMEND, query);
        if (result == null) {
            return Collections.emptyList();
        }
        return deserializeList(result, ShelterRecommendDto.class);
    }

    public void saveRecommendedShelter(String query, List<ShelterRecommendDto> shelterRecommendDtos) {
        hashOperations.put(CACHE_KEY_RECOMMEND, query, serialize(shelterRecommendDtos));
    }

    private String serialize(Object object)  {
        String result = null;
        try{
            result = objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException e){
            log.error("parsing error");
            throw new RedisOperationException("parsing");
        }
        return result;
    }

    private <T> T deserialize(String value, Class<T> dtoClass) {
        T dto = null;
        try {
            dto = objectMapper.readValue(value, dtoClass);
        }  catch (JsonProcessingException e) {
            log.error("parsing error");
            throw new RedisOperationException("parsing");
        }
        return dto;
    }

    private <T> List<T> deserializeList(String value, Class<T> dtoClass) {
        ArrayList<T> dtos = null;
        try {
            dtos = objectMapper.readValue(value, ArrayList.class);
        }  catch (JsonProcessingException e) {
            log.error("parsing error");
            throw new RedisOperationException("parsing");
        }
        return dtos;
    }


}
