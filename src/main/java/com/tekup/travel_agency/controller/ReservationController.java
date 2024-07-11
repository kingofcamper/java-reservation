package com.tekup.travel_agency.controller;

import com.tekup.travel_agency.model.Reservation;
import com.tekup.travel_agency.model.User;
import com.tekup.travel_agency.model.Voyage;
import com.tekup.travel_agency.repository.ReservationRepository;
import com.tekup.travel_agency.repository.UserRepository;
import com.tekup.travel_agency.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final VoyageRepository voyageRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository,
                                 UserRepository userRepository,
                                 VoyageRepository voyageRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.voyageRepository = voyageRepository;
    }

    // Create a reservation
    @PostMapping("/")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        // Check if user exists
        Optional<User> userOptional = userRepository.findById(reservation.getUser().getId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Check if voyage exists
        Optional<Voyage> voyageOptional = voyageRepository.findById(reservation.getVoyage().getId());
        if (voyageOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Set the user and voyage in the reservation object
        reservation.setUser(userOptional.get());
        reservation.setVoyage(voyageOptional.get());

        // Save the reservation
        Reservation savedReservation = reservationRepository.save(reservation);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);
    }

    // Retrieve all reservations
    @GetMapping("/")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return ResponseEntity.ok(reservations);
    }

    // Retrieve a reservation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        return reservationOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a reservation by ID
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") long id, @RequestBody Reservation updatedReservation) {
        Optional<Reservation> existingReservationOptional = reservationRepository.findById(id);
        if (existingReservationOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Reservation existingReservation = existingReservationOptional.get();

        // Update fields
        Optional<User> userOptional = userRepository.findById(updatedReservation.getUser().getId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Check if voyage exists
        Optional<Voyage> voyageOptional = voyageRepository.findById(updatedReservation.getVoyage().getId());
        if (voyageOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        existingReservation.setUser(userOptional.get());
        existingReservation.setVoyage(voyageOptional.get());

        // Save updated reservation
        Reservation savedReservation = reservationRepository.save(existingReservation);

        return ResponseEntity.ok(savedReservation);
    }

    // Delete a reservation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        reservationRepository.delete(reservationOptional.get());
        return ResponseEntity.noContent().build();
    }
}
