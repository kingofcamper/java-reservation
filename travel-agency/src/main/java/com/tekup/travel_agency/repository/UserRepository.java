package com.tekup.travel_agency.repository;

import com.tekup.travel_agency.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // all crud database methods
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
//
//    Optional<UserEntity> findByUsername(String username);
//    Boolean existsByUsername(String username);
}
