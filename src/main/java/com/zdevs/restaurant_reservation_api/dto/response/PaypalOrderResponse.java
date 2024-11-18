package com.zdevs.restaurant_reservation_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaypalOrderResponse {
    private String paypalUrl;
}
