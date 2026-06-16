package com.example.demo.service;
import com.example.demo.dto.OrganizationRequestDTO;
import com.example.demo.dto.OrganizationResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.example.demo.repository.OrganizationRepository;
import com.example.demo.entity.Organization;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class OrganizationServiceIml implements OrganizationService {

    private final OrganizationRepository organizationRepository;


    @Override
    public OrganizationResponseDTO createOrganization(OrganizationRequestDTO requestDTO) {
        //1. Chuyen DTO sang Entity de luu vao database
            Organization organization = new Organization();
            organization.setName(requestDTO.getName());
            organization.setTaxCode(requestDTO.getTaxCode());
            organization.setEmail(requestDTO.getEmail());
            organization.setPhone(requestDTO.getPhone());
            organization.setAddress(requestDTO.getAddress());
            organization.setStatus("ACTIVE");
        //2. Luu vao database
            Organization saveOrg = organizationRepository.save(organization);
        //3. Chuyen Entity sang DTO de tra ve client
            return mapToResponseDTO(saveOrg);
        // throw new UnsupportedOperationException("Unimplemented method 'createOrganization'");
    }
    @Override
    public List<OrganizationResponseDTO> getAllOrganizations() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getAllOrganizations'");
        return organizationRepository.findAll().stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }
    //ham helper` de chuyen doi Entity sang DTO
    private OrganizationResponseDTO mapToResponseDTO(Organization organization){
        OrganizationResponseDTO responseDTO = new OrganizationResponseDTO();
        responseDTO.setId(organization.getId());
        responseDTO.setName(organization.getName());
        responseDTO.setEmail(organization.getEmail());
        responseDTO.setStatus(organization.getStatus());
        responseDTO.setCreatedAt(organization.getCreatedAt());
        return responseDTO;
    }
    
}
