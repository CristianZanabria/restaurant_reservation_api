package com.zdevs.restaurant_reservation_api.service;

import com.zdevs.restaurant_reservation_api.domain.entity.Reservation;
import com.zdevs.restaurant_reservation_api.dto.paypal.OrderCaptureResponse;
import com.zdevs.restaurant_reservation_api.dto.paypal.OrderResponse;
import com.zdevs.restaurant_reservation_api.dto.response.PaypalCaptureResponse;
import com.zdevs.restaurant_reservation_api.dto.response.PaypalOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CheckoutService {
    private PaypalService paypalService;
    private ReservationService reservationService;

    public PaypalOrderResponse createPaypalPaymentUrl(Long reservationId, String returnUrl, String cancelUrl) {
        OrderResponse orderResponse = paypalService.createOrder(reservationId, returnUrl, cancelUrl);

        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getHref();

        return new PaypalOrderResponse(paypalUrl);
    }

    public PaypalCaptureResponse capturePaypalPayment(String orderId) {
        OrderCaptureResponse orderCaptureResponse = paypalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaypalCaptureResponse paypalCaptureResponse = new PaypalCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().getFirst().getReferenceId();
            Reservation reservation = reservationService.confirmReservationPayment(Long.parseLong(purchaseIdStr));
            paypalCaptureResponse.setReservationId(reservation.getId());
        }
        return paypalCaptureResponse;
    }


}
