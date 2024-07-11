package com.tekup.travel_agency.controller;

import com.tekup.travel_agency.exception.ResourceNotFoundException;
import com.tekup.travel_agency.model.Role;
import com.tekup.travel_agency.model.Voyage;
import com.tekup.travel_agency.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/voyages")
public class VoyageController {

    @Autowired
    private VoyageRepository voyageRepository;

    @GetMapping
    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    @PostMapping
    public Voyage addVoyage(@RequestBody Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    @GetMapping("{id}")
    public ResponseEntity<Voyage> getVoyageById(@PathVariable long id) {
        Voyage voyage = voyageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Voyage with id " + id + " not found"));
//        return ResponseEntity.ok().body(role.get());
        return ResponseEntity.ok(voyage);
    }

    @PutMapping("{id}")
    public ResponseEntity<Voyage> updateRole(@PathVariable long id, @RequestBody Role voyageDetail) {
        Voyage updateVoyage = voyageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Voyage with id " + id + " not found"));

        updateVoyage.setName(voyageDetail.getName());

        voyageRepository.save(updateVoyage);

        return ResponseEntity.ok(updateVoyage);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable long id) {
        Voyage voyage = voyageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Voyage with id " + id + " not found"));

        voyageRepository.delete(voyage);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
