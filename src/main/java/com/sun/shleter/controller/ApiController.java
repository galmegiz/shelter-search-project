package com.sun.shleter.controller;

import com.sun.shleter.service.ExternalAddressApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ApiController {

    private final ExternalAddressApiService externalAddressApiService;


    @GetMapping("test")
    public ResponseEntity get(String query) {
        return ResponseEntity.ok(externalAddressApiService.searchAddress(query, null, null, null));
    }
}
