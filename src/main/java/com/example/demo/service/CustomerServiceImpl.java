package com.example.demo.service;

import com.example.demo.dto.CustomerRequestDTO;
import com.example.demo.dto.CustomerResponseDTO;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.security.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO request) {
        // Lấy ID công ty từ thẻ Token đang đăng nhập
        Long currentOrgId = TenantContext.getCurrentTenant();

        Customer customer = new Customer();
        customer.setOrganizationId(currentOrgId); // Chốt chặt khách hàng này thuộc về công ty nào
        customer.setCustomerType(request.getCustomerType());
        customer.setCompanyName(request.getCompanyName());
        customer.setContactName(request.getContactName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setTaxCode(request.getTaxCode());
        customer.setBillingAddress(request.getBillingAddress());
        customer.setShippingAddress(request.getShippingAddress());
        customer.setNotes(request.getNotes());

        Customer savedCustomer = customerRepository.save(customer);
        return mapToDTO(savedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        Long currentOrgId = TenantContext.getCurrentTenant();
        
        // Chỉ quét danh sách khách hàng của đúng công ty đang đăng nhập
        return customerRepository.findByOrganizationId(currentOrgId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Entity -> DTO
    private CustomerResponseDTO mapToDTO(Customer customer) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(customer.getId());
        dto.setCustomerType(customer.getCustomerType());
        dto.setCompanyName(customer.getCompanyName());
        dto.setContactName(customer.getContactName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        return dto;
    }
}