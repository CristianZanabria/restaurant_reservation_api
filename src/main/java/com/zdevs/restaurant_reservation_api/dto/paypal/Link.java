package com.zdevs.restaurant_reservation_api.dto.paypal;


import lombok.Data;

@Data
public class Link {
    private String href;
    private String rel;
    private String method;
}
