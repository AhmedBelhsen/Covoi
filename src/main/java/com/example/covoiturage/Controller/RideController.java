package com.example.covoiturage.Controller;

import com.example.covoiturage.Entity.Ride;
import com.example.covoiturage.Service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    private RideService rideService;


    // Get all available rides
    @GetMapping("/availableRides")
    public ResponseEntity<List<Ride>> getAvailableRides() {
        List<Ride> rides = rideService.getAvailableRides();
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    // Get a ride by ID
    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable int id) {
        Optional<Ride> ride = rideService.findById(id);
        return ride.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Find rides by destination
    @GetMapping("/destination")
    public ResponseEntity<List<Ride>> findRidesByDestination(@RequestParam String destination) {
        List<Ride> rides = rideService.findByDestination(destination);
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    // Find rides by departure location
    @GetMapping("/departure")
    public ResponseEntity<List<Ride>> findRidesByDeparture(@RequestParam String departure) {
        List<Ride> rides = rideService.findByDeparture(departure);
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    // Create a new ride
    @PostMapping
    public ResponseEntity<Ride> createRide(
            @RequestParam String departure,
            @RequestParam String destination,
            @RequestParam BigDecimal price,
            @RequestParam int seatsAvailable,
            @RequestParam Timestamp departureTime) {
        try {
            Ride ride = rideService.createRide(0, departure, destination, price, seatsAvailable, departureTime);
            return new ResponseEntity<>(ride, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing ride
    @PutMapping("/{id}")
    public ResponseEntity<Ride> updateRide(
            @PathVariable int id,
            @RequestParam String departure,
            @RequestParam String destination,
            @RequestParam BigDecimal price,
            @RequestParam int seatsAvailable,
            @RequestParam Timestamp departureTime) {
        try {
            Ride updatedRide = rideService.updateRide(id, departure, destination, price, seatsAvailable, departureTime);
            return new ResponseEntity<>(updatedRide, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a ride
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable int id) {
        try {
            rideService.deleteRide(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
