package com.zdevs.restaurant_reservation_api.mapper;

import com.zdevs.restaurant_reservation_api.domain.entity.District;
import com.zdevs.restaurant_reservation_api.dto.response.DistrictResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hibernate.Hibernate.map;

@Component
@RequiredArgsConstructor
public class DistrictMapper {

    private final ModelMapper modelMapper;

    public DistrictResponseDTO toResponseDto(District district) {
        return modelMapper.map(district, DistrictResponseDTO.class);
    }
    public List<DistrictResponseDTO> toResponseDtoList(List<District> districts) {
        return districts.stream()
        .map(this :: toResponseDto)
        .toList();

    }
}
