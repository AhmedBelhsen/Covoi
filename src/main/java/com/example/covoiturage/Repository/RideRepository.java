package com.example.covoiturage.Repository;

import com.example.covoiturage.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Integer> {
    List<Ride> findBySeatsAvailableGreaterThan(int seatsAvailable);
    List<Ride> findByDestination (String destination);
    List<Ride> findbyDeparture (String departure);
    
}
