package com.example.demo.controller;

import com.example.demo.security.TenantContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getCurrentUser() {
        // 1. Lấy email của người đang cầm thẻ từ SecurityContext
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getPrincipal().toString();

        // 2. Lấy ID công ty mà thẻ này đang gán vào từ TenantContext
        Long orgId = TenantContext.getCurrentTenant();

        // 3. Đóng gói kết quả để trả về cho file .http
        Map<String, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("organizationId", orgId);
        response.put("message", "Thẻ từ hợp lệ. Chào mừng bạn qua cổng!");

        return ResponseEntity.ok(response);
    }
}