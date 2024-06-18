package com.tekup.travel_agency.repository;

import com.tekup.travel_agency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // all crud database methods
}
