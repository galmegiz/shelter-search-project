package com.sun.presentation.controller;

import com.sun.domain.service.ShelterAddressManageService;
import com.sun.presentation.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final ShelterAddressManageService shelterAddressManageService;

    @GetMapping("/save")
    public ResponseEntity<Response> saveShelterInfo() throws IOException {
        shelterAddressManageService.save();
        return ResponseEntity.status(HttpStatus.CREATED).body(Response.success("Saving shelter info success!"));
    }
}
