package com.zdevs.restaurant_reservation_api.controller;

import com.zdevs.restaurant_reservation_api.dto.response.PaypalCaptureResponse;
import com.zdevs.restaurant_reservation_api.dto.response.PaypalOrderResponse;
import com.zdevs.restaurant_reservation_api.service.CheckoutService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private CheckoutService checkoutService;

    @PostMapping("/paypal/create")
    public PaypalOrderResponse createPaypalOrder(
            @RequestParam Long reservationId,
            @RequestParam String returnUrl,
            @RequestParam String cancelUrl
    ) {
        return checkoutService.createPaypalPaymentUrl(reservationId, returnUrl, cancelUrl);
    }

    @PostMapping("/paypal/capture")
    public PaypalCaptureResponse capturePaypalOrder(@RequestParam String orderId) {
        return checkoutService.capturePaypalPayment(orderId);
    }
}
