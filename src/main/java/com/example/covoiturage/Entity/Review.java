package com.example.covoiturage.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table (name="Review")
public class Review {
    @Id
    @GeneratedValue( strategy =GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ride_id")
    private Ride ride;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="passenger_id")
    private User passenger;

    @Column(name="rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "review_date", updatable = false, insertable = false)
    private Timestamp reviewDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate() {
        this.reviewDate = new Timestamp(System.currentTimeMillis());
    }
}

