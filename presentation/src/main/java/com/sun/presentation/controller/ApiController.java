package com.sun.presentation.controller;

import com.sun.domain.dto.ClientAddressDto;
import com.sun.domain.dto.ShelterRecommendDto;
import com.sun.domain.service.AddressSearchService;
import com.sun.domain.service.ShelterAddressManageService;
import com.sun.domain.service.ShelterRecommendService;

import com.sun.presentation.constant.ResponseCode;
import com.sun.presentation.dto.Response;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ApiController {


    private final ShelterAddressManageService shelterAddressService;
    private final ShelterRecommendService shelterRecommendService;
    private final AddressSearchService addressSearchService;


    @GetMapping("/search-coordinate")
    public ResponseEntity<Response> search(String query) {
        return ResponseEntity.ok(Response.success(addressSearchService.searchAddressInfo(query)));
    }



    @GetMapping("/recommend-shelter")
    public ResponseEntity<Response> recommendShelter(String query) {
        ClientAddressDto clientAddress = addressSearchService.searchClientAddress(query);
        List<ShelterRecommendDto> shelterRecommendDtos = shelterRecommendService.recommendShelter(clientAddress);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success(shelterRecommendDtos));
    }
}
