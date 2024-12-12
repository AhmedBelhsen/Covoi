package com.example.covoiturage.Service;

import com.example.covoiturage.Entity.Reservation;
import com.example.covoiturage.Entity.Ride;
import com.example.covoiturage.Entity.User;
import com.example.covoiturage.Repository.ReservationRepository;
import com.example.covoiturage.Repository.RideRepository;
import com.example.covoiturage.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReservationService {
    private RideRepository rideRepository;
    private ReservationRepository ReservationRepository;
    UserRepository userRepository;

    public List<Reservation> getAllReservations() {
       return ReservationRepository.findAll();
   }

   public Reservation getReservationById(int id) {
       return ReservationRepository.getReferenceById(id);
   }

   public Reservation saveReservation(Reservation reservation) {
       return ReservationRepository.save(reservation);
   }

   public void deleteReservation(Reservation reservation) {
       ReservationRepository.delete(reservation);
   }

    public Reservation makeReservation(int rideId, int passengerId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("Ride not found"));
        User passenger = userRepository.findById(passengerId).orElseThrow(() -> new RuntimeException("Passenger not found"));

        if (ride.getSeatsAvailable() > 0) {
            Reservation reservation = new Reservation();
            reservation.setRide(ride);
            reservation.setPassenger(passenger);
            reservation.setStatus(Reservation.ReservationStatus.EN_ATTENTE);
            return ReservationRepository.save(reservation);
        } else {
            throw new RuntimeException("No available seats on this ride");
        }
    }

    public void cancelReservation(int reservationId) {
        Reservation reservation = ReservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus(Reservation.ReservationStatus.ANNULEE);
        ReservationRepository.save(reservation);

        // Increase available seats in the corresponding ride
        Ride ride = reservation.getRide();
        ride.setSeatsAvailable(ride.getSeatsAvailable() + 1);
        rideRepository.save(ride);
    }
}
