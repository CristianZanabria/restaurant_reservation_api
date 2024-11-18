package com.zdevs.restaurant_reservation_api.dto.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderCaptureResponse {
    private String status;

    @JsonProperty("purchase_units")
    private List<PurchaseUnit> purchaseUnits;

}
