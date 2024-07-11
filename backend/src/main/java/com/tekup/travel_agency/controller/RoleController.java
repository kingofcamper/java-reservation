package com.tekup.travel_agency.controller;

import com.tekup.travel_agency.exception.ResourceNotFoundException;
import com.tekup.travel_agency.model.Role;
import com.tekup.travel_agency.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @PostMapping
    public Role addRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    @GetMapping("{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role with id " + id + " not found"));
//        return ResponseEntity.ok().body(role.get());
        return ResponseEntity.ok(role);
    }

    @PutMapping("{id}")
    public ResponseEntity<Role> updateRole(@PathVariable long id, @RequestBody Role roleDetail) {
        Role updateRole = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role with id " + id + " not found"));

        updateRole.setName(roleDetail.getName());

        roleRepository.save(updateRole);

        return ResponseEntity.ok(updateRole);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role with id " + id + " not found"));
        roleRepository.delete(role);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
