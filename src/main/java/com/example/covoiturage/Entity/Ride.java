package com.example.covoiturage.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table (name="Ride")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ride_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="drive_id",nullable = false)
    private User driver;

    @Column(nullable = false, length = 100)
    private String departure;

    @Column(nullable = false, length = 100)
    private String destination;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @Column(name = "seats_available")
    private int seatsAvailable;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;

    @Column(name = "departure_time")
    private Timestamp departureTime;

    public int getRide_id() {
        return ride_id;
    }

    public void setRide_id(int ride_id) {
        ride_id = ride_id;
    }

    public User getDrive() {
        return driver;
    }

    public void setDrive(User driver) {
        this.driver = driver;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }
}

