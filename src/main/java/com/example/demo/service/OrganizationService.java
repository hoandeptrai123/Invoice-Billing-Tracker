package com.example.demo.service;
import com.example.demo.dto.OrganizationRequestDTO;
import com.example.demo.dto.OrganizationResponseDTO;
import java.util.List;
public interface OrganizationService {
    OrganizationResponseDTO createOrganization(OrganizationRequestDTO request);
    // OrganizationResponseDTO getOrganizationById(Long id);
    List<OrganizationResponseDTO> getAllOrganizations();
    // OrganizationResponseDTO updateOrganization(Long id, OrganizationRequestDTO request);
    // void deleteOrganization(Long id);
}
