package com.zdevs.restaurant_reservation_api.controller;

import com.zdevs.restaurant_reservation_api.domain.entity.District;
import com.zdevs.restaurant_reservation_api.dto.response.DistrictResponseDTO;
import com.zdevs.restaurant_reservation_api.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/districts")
public class DistrictController {
    private final DistrictService districtService;

    @GetMapping
    public ResponseEntity<List<DistrictResponseDTO>> getAllDistricts() {
        List<DistrictResponseDTO> districts = districtService.getAllDistricts();
        return  ResponseEntity.ok(districts);

    }
}
