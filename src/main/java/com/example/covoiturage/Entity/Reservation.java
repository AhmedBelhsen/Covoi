package com.example.covoiturage.Entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ride_id")
    private Ride ride;  // Foreign Key to Ride

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    private User passenger;  // Foreign Key to User (passenger)

    @Column(name = "reservation_date", updatable = false, insertable = false)
    private Timestamp reservationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status;

    public enum ReservationStatus {
        EN_ATTENTE, CONFIRMEE, ANNULEE
    }

     public Reservation() {}

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public Timestamp getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Timestamp reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
