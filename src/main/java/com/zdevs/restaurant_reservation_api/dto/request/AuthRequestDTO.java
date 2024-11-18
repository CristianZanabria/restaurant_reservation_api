package com.zdevs.restaurant_reservation_api.dto.request;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String password;
}
