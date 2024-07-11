package com.tekup.travel_agency.repository;

import com.tekup.travel_agency.model.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {
}
