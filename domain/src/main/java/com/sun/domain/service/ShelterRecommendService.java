package com.sun.domain.service;

import com.sun.domain.dto.ClientAddressDto;
import com.sun.domain.dto.ShelterRecommendDto;
import com.sun.domain.util.distance.DistanceCalculateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class ShelterRecommendService {

    private final AddressSearchService addressSearchService;
    private final ShelterAddressManageService shelterAddressManageService;
    private final ShelterAddressCacheService shelterAddressCacheService;
    private final DistanceCalculateUtil distanceCalculateUtil;

    public List<ShelterRecommendDto> recommendShelter(String query){
        List<ShelterRecommendDto> results = shelterAddressCacheService.findByAddress(query);

        if (!CollectionUtils.isEmpty(results)) {
            log.info("Recommended Shelter Info In Cache");
            return results;
        }

        ClientAddressDto clientAddressDto = addressSearchService.searchClientAddress(query);
        results = recommendShortestPathShelter(clientAddressDto, 10);
        shelterAddressCacheService.saveRecommendedShelter(query, results);
        return results;
    }

    private List<ShelterRecommendDto> recommendShortestPathShelter(ClientAddressDto clientAddressDto, int maxCount) {
        if(Objects.isNull(clientAddressDto)) return Collections.emptyList();

        return shelterAddressManageService.getAllAddresses()
                                          .stream()
                                          .map(shelterAddressDto -> ShelterRecommendDto.builder()
                                                                                        .id(shelterAddressDto.getId())
                                                                                        .no(shelterAddressDto.getNo())
                                                                                        .shelterName(shelterAddressDto.getShelterName())
                                                                                        .address(shelterAddressDto.getAddress())
                                                                                        .state(shelterAddressDto.getState())
                                                                                        .area(shelterAddressDto.getArea())
                                                                                        .lastModifiedDate(shelterAddressDto.getLastModifiedDate())
                                                                                        .url("aaa")
                                                                                        .distance(distanceCalculateUtil.calculateDistance(clientAddressDto.getLatitude(), clientAddressDto.getLongitude(), shelterAddressDto.getLatitude(), shelterAddressDto.getLongitude()))
                                                                                        .build()
                                                )
                                          .filter(shelterRecommendDto -> shelterRecommendDto.getDistance() <= 10.0) // 10km 이내 대피소로 한정
                                          .sorted(Comparator.comparing(ShelterRecommendDto::getDistance))
                                          .limit(maxCount)
                                          .toList();

    }

}
