package com.example.demo.service;

import com.example.demo.dto.OrganizationRequestDTO;
import com.example.demo.dto.OrganizationResponseDTO;
import com.example.demo.entity.Organization;
import com.example.demo.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationResponseDTO createOrganization(OrganizationRequestDTO request) {
        Organization org = new Organization();
        org.setName(request.getName());
        org.setTaxCode(request.getTaxCode());
        org.setEmail(request.getEmail());
        org.setPhone(request.getPhone());
        org.setAddress(request.getAddress());
        org.setLogoUrl(request.getLogoUrl());
        org.setCurrencyCode(request.getCurrencyCode() != null ? request.getCurrencyCode() : "VND"); // Default VND
        org.setTimezone(request.getTimezone() != null ? request.getTimezone() : "Asia/Ho_Chi_Minh"); // Default Timezone
        org.setStatus("ACTIVE");

        Organization savedOrg = organizationRepository.save(org);
        
        OrganizationResponseDTO response = new OrganizationResponseDTO();
        response.setId(savedOrg.getId());
        response.setName(savedOrg.getName());
        response.setEmail(savedOrg.getEmail());
        response.setCreatedAt(savedOrg.getCreatedAt());
        return response;
    }
}