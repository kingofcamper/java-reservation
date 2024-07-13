package com.tekup.travel_agency.controller;

import com.tekup.travel_agency.exception.ResourceNotFoundException;
import com.tekup.travel_agency.model.UserEntity;
import com.tekup.travel_agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " not found"));
        return ResponseEntity.ok(userEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable long id, @RequestBody UserEntity userEntityDetail) {
        UserEntity updateUserEntity = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " not found"));

        updateUserEntity.setFirstName(userEntityDetail.getFirstName());
        updateUserEntity.setLastName(userEntityDetail.getLastName());
        updateUserEntity.setEmail(userEntityDetail.getEmail());

        userRepository.save(updateUserEntity);

        return ResponseEntity.ok(updateUserEntity);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " not found"));
        userRepository.delete(userEntity);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
