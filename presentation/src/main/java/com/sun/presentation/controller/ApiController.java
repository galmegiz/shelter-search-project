package com.sun.presentation.controller;

import com.sun.domain.dto.ClientAddressDto;
import com.sun.domain.dto.ShelterRecommendDto;
import com.sun.domain.service.AddressSearchService;
import com.sun.domain.service.ShelterAddressManageService;
import com.sun.domain.service.ShelterRecommendService;

import lombok.RequiredArgsConstructor;
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


    @GetMapping("/test")
    public ResponseEntity get(String query) {
        return ResponseEntity.ok(addressSearchService.searchAddressInfo(query));
    }

    @GetMapping("/save")
    public String save() throws IOException {
        shelterAddressService.save();
        return "success";
    }

    @GetMapping("/search")
    public ResponseEntity search(String query) {
        ClientAddressDto clientAddress = addressSearchService.searchClientAddress(query).orElseThrow(() -> {throw new RuntimeException();});
        List<ShelterRecommendDto> shelterRecommendDtos = shelterRecommendService.recommendShelter(clientAddress);
        return ResponseEntity.ok(shelterRecommendDtos);
    }
}
