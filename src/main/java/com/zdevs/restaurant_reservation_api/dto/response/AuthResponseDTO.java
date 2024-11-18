package com.zdevs.restaurant_reservation_api.dto.response;

import lombok.Data;

@Data
public class AuthResponseDTO {

    private String token;
    private UserProfileResponseDTO user;
}
