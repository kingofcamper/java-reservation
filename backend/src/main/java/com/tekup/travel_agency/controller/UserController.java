package com.tekup.travel_agency.controller;

import com.tekup.travel_agency.exception.ResourceNotFoundException;
import com.tekup.travel_agency.model.User;
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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " not found"));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userDetail) {
        User updateUser = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " not found"));

        updateUser.setFirstName(userDetail.getFirstName());
        updateUser.setLastName(userDetail.getLastName());
        updateUser.setEmail(userDetail.getEmail());

        userRepository.save(updateUser);

        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " not found"));
        userRepository.delete(user);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
