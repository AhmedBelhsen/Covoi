package com.example.covoiturage.Repository;

import com.example.covoiturage.Entity.Reservation;
import com.example.covoiturage.Entity.Ride;
import com.example.covoiturage.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByPassenger(User passenger);
    Reservation findByRideAndPassenger(Ride ride, User passenger);
}
