package com.zdevs.restaurant_reservation_api.dto.response;

import com.zdevs.restaurant_reservation_api.domain.enums.Role;
import lombok.Data;

@Data
public class UserProfileResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
