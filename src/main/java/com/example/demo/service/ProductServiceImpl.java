package com.example.demo.service;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.security.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        Long currentOrgId = TenantContext.getCurrentTenant();

        Product product = new Product();
        product.setOrganizationId(currentOrgId);
        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setType(request.getType());
        product.setUnit(request.getUnit());
        product.setUnitPrice(request.getUnitPrice());
        product.setTaxRate(request.getTaxRate());
        product.setActive(request.getActive() != null ? request.getActive() : true);

        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        Long currentOrgId = TenantContext.getCurrentTenant();
        
        return productRepository.findByOrganizationId(currentOrgId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ProductResponseDTO mapToDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setSku(product.getSku());
        dto.setName(product.getName());
        dto.setType(product.getType());
        dto.setUnitPrice(product.getUnitPrice());
        dto.setActive(product.getActive());
        return dto;
    }
}