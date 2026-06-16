package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.*;

import java.time.LocalDateTime;
// Khuon mau cho cac table con
// Cac table nhu Customer, Product, Invoice se extend BaseTenantEntity de auto co cot organization_id de phan biet du lieu theo tenant
@Getter
@Setter
@MappedSuperclass      /*danh dau day la lop cha, khong tao bang trong db*/
@EntityListeners(AuditingEntityListener.class)


public class BaseTenantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name ="organization_id", nullable = false)
    private Long organizationId; // lien ket den bang Organization de phan biet du lieu theo tenant

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
