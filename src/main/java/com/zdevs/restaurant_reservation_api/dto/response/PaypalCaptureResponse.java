package com.zdevs.restaurant_reservation_api.dto.response;

import lombok.Data;

@Data
public class PaypalCaptureResponse {
    private boolean completed;
    private Long reservationId;
}
