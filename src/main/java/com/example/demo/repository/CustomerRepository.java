package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    // Lấy danh sách khách hàng của một công ty
    List<Customer> findByOrganizationId(Long organizationId);

    // Tìm chính xác một khách hàng thuộc về một công ty (dùng cho update/delete sau này)
    Optional<Customer> findByIdAndOrganizationId(Long id, Long organizationId);
}