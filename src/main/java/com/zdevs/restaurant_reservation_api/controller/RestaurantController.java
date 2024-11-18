package com.zdevs.restaurant_reservation_api.controller;

import com.zdevs.restaurant_reservation_api.dto.response.RestaurantResponseDTO;
import com.zdevs.restaurant_reservation_api.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/page")
    public ResponseEntity<Page<RestaurantResponseDTO>> getAllRestaurant(
            @PageableDefault(size = 5)Pageable pageable){
        Page<RestaurantResponseDTO> restaurants = restaurantService.getAllRestaurants(pageable);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/page/districts")
    public ResponseEntity<Page<RestaurantResponseDTO>> findByDistrictName(
            @RequestParam String districtName,
            @PageableDefault(sort = "name", size = 5) Pageable pageable){
        Page<RestaurantResponseDTO> restaurants = restaurantService.getRestaurantByDistrictName( districtName, pageable);
        return ResponseEntity.ok(restaurants);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(@PathVariable Long id) {
        RestaurantResponseDTO restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }


}
