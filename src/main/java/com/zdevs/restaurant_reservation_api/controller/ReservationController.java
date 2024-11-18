package com.zdevs.restaurant_reservation_api.controller;

import com.zdevs.restaurant_reservation_api.dto.request.ReservationRequestDTO;
import com.zdevs.restaurant_reservation_api.dto.response.ReservationResponseDTO;
import com.zdevs.restaurant_reservation_api.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@RequestBody @Validated ReservationRequestDTO reservationRequestDTO) {
        ReservationResponseDTO reservationResponse = reservationService.createReservation(reservationRequestDTO);
        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id) {
        ReservationResponseDTO reservationResponse = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservationResponse);
    }


    @GetMapping("/my-reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getMyReservations() {
        List<ReservationResponseDTO> reservations = reservationService.getReservationsByClientId();
        return ResponseEntity.ok(reservations);
    }


}
