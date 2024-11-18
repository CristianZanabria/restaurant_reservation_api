package com.zdevs.restaurant_reservation_api.domain.entity;


import com.zdevs.restaurant_reservation_api.domain.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reservations")

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @Column(name = "reservation_date", nullable = false)
    private LocalDateTime reservationDate;

    @Column(name = "number_of_people", nullable = false)
    private int numberOfPeople;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReservationStatus status;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    public void calculateTotalAmount() {
        this.totalAmount = this.numberOfPeople * restaurant.getPricePerPerson();
    }

}
