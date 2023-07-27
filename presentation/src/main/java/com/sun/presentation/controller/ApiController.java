package com.sun.presentation.controller;

import com.sun.domain.dto.ClientAddressDto;
import com.sun.domain.dto.ShelterRecommendDto;
import com.sun.domain.service.AddressSearchService;
import com.sun.domain.service.ShelterAddressManageService;
import com.sun.domain.service.ShelterRecommendService;

import com.sun.presentation.constant.ResponseCode;
import com.sun.presentation.dto.Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class ApiController {


    private final ShelterRecommendService shelterRecommendService;
    private final AddressSearchService addressSearchService;


    @GetMapping("/search-coordinate")
    public ResponseEntity<Response> search(@RequestParam(required =true) String query) throws MissingServletRequestParameterException {
        if (!StringUtils.hasLength(query.trim())) {
            throw new MissingServletRequestParameterException("query", "string", true);
        }
        return ResponseEntity.ok(Response.success(addressSearchService.searchAddressInfo(query)));
    }



    @GetMapping("/recommend-shelter")
    public ResponseEntity<Response> recommendShelter(@RequestParam(required =true) String query) throws MissingServletRequestParameterException {
        if (!StringUtils.hasLength(query.trim())) {
            throw new MissingServletRequestParameterException("query", "string", true);
        }
        List<ShelterRecommendDto> shelterRecommendDtos = shelterRecommendService.recommendShelter(query);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success(shelterRecommendDtos));
    }
}
