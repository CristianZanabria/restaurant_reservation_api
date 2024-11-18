package com.zdevs.restaurant_reservation_api.service;

import com.zdevs.restaurant_reservation_api.domain.entity.Restaurant;
import com.zdevs.restaurant_reservation_api.dto.response.RestaurantResponseDTO;
import com.zdevs.restaurant_reservation_api.exception.ResourceNotFoundException;
import com.zdevs.restaurant_reservation_api.mapper.RestaurantMapper;
import com.zdevs.restaurant_reservation_api.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;


    @Transactional(readOnly = true)
    public Page<RestaurantResponseDTO> getAllRestaurants(Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable);
        return restaurants.map(restaurantMapper::toRestaurantDto);
    }

    @Transactional(readOnly = true)
    public Page<RestaurantResponseDTO> getRestaurantByDistrictName(String districtName, Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findByDistrictName(districtName, pageable);
        return restaurants.map(restaurantMapper::toRestaurantDto);
    }

    public RestaurantResponseDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id" + id ));
        return restaurantMapper.toRestaurantDto(restaurant);
    }
}
