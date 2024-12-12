package com.example.covoiturage.Service;

import com.example.covoiturage.Entity.Ride;
import com.example.covoiturage.Entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.covoiturage.Repository.RideRepository;
import com.example.covoiturage.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class RideService {
    RideRepository rideRepository;
    UserRepository userRepository;

    public List<Ride> findByDestination(String destination) {
        return rideRepository.findByDestination(destination);
    }

    public List<Ride> findByDeparture(String departure) {
        return rideRepository.findByDestination(departure);
    }
    public Optional<Ride> findById(int id) {
        return rideRepository.findById(id);
    }

    public Ride createRide(int driverId, String departure, String destination, BigDecimal price, int seatsAvailable, Timestamp departureTime) {
        Ride ride = new Ride();

        // Find the driver (User) from the database using the ID
        // User driver = userRepository.findById(Math.toIntExact(currentUserId)).orElseThrow(() -> new RuntimeException("User not found"));

        Long currentUserId = getCurrentUserId();

        // Fetch the authenticated user from the database
        User driver = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        ride.setDrive(driver);
        ride.setDeparture(departure);
        ride.setDestination(destination);
        ride.setPrix(price);
        ride.setSeatsAvailable(seatsAvailable);
        ride.setDepartureTime(departureTime);
        return rideRepository.save(ride);
    }

    public List<Ride> getAvailableRides() {
        return rideRepository.findBySeatsAvailableGreaterThan(0);
    }

    public Ride updateRide(int rideId, String departure, String destination, BigDecimal price, int seatsAvailable, Timestamp departureTime) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("Ride not found"));
        ride.setDeparture(departure);
        ride.setDestination(destination);
        ride.setPrix(price);
        ride.setSeatsAvailable(seatsAvailable);
        ride.setDepartureTime(departureTime);
        return rideRepository.save(ride);
    }

    public void deleteRide(int rideId) {
        rideRepository.deleteById(rideId);
    }

    private Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            // Assuming 'email' is used as the username in your security configuration
            String email = ((UserDetails) principal).getUsername();
            return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with email: " + email))
                    .getId();
        } else {
            throw new RuntimeException("Invalid authenticated principal");
        }
    }
}
