package com.zdevs.restaurant_reservation_api.service;

import com.zdevs.restaurant_reservation_api.domain.entity.District;
import com.zdevs.restaurant_reservation_api.dto.response.DistrictResponseDTO;
import com.zdevs.restaurant_reservation_api.mapper.DistrictMapper;
import com.zdevs.restaurant_reservation_api.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    @Transactional(readOnly = true)
    public List<DistrictResponseDTO> getAllDistricts() {
        List<District> districts = districtRepository.findAll();
        return districtMapper.toResponseDtoList(districts);
    }

}
