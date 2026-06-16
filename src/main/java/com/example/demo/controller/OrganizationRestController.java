package com.example.demo.controller;

import com.example.demo.dto.OrganizationRequestDTO;
import com.example.demo.dto.OrganizationResponseDTO;
import com.example.demo.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationRestController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationResponseDTO> createOrg(@RequestBody OrganizationRequestDTO request) {
        OrganizationResponseDTO response = organizationService.createOrganization(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDTO>> getAllOrgs() {
        return ResponseEntity.ok(organizationService.getAllOrganizations());
    }
}