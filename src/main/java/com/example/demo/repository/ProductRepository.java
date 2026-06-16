package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Tìm tất cả sản phẩm thuộc về một công ty cụ thể
    List<Product> findByOrganizationId(Long organizationId);

    // Tìm 1 sản phẩm cụ thể của một công ty (để tránh sửa/xóa nhầm đồ của công ty khác)
    Optional<Product> idAndOrganizationId(Long id, Long organizationId);
}