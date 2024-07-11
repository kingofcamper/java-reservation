package com.tekup.travel_agency.repository;

import com.tekup.travel_agency.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}