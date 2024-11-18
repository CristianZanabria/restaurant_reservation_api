package com.zdevs.restaurant_reservation_api.mapper;

import com.zdevs.restaurant_reservation_api.domain.entity.Restaurant;
import com.zdevs.restaurant_reservation_api.dto.response.RestaurantResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RestaurantMapper {

    private final ModelMapper modelMapper;

    public RestaurantResponseDTO toRestaurantDto(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantResponseDTO.class);
    }
    public List<RestaurantResponseDTO> toRestaurantDtoList(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(this:: toRestaurantDto)
                .toList();
    }
}
