package com.zdevs.restaurant_reservation_api.repository;

import com.zdevs.restaurant_reservation_api.domain.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DistrictRepository  extends JpaRepository<District, Long> {

    Optional<District> findByName(String name);
}
