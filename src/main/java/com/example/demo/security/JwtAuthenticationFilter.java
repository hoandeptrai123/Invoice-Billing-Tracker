package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 1. Lay token từ header cua request
            String token = getJwtFromRequest(request);

            // 2. check token 
            if (StringUtils.hasText(token) && jwtUtil.validateToken(token)) {
                
                // Lay email và organizationId ra
                String email = jwtUtil.getEmailFromToken(token);
                Long orgId = jwtUtil.getOrganizationIdFromToken(token);

                // Gán orgId vào TenantContext để các hàm Query Database dùng
                TenantContext.setCurrentTenant(orgId);

                // Báo cho Spring Security biết là "Người này đã hợp lệ, cho qua"
                UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
                // Nếu có lỗi gì trong quá trình giải mã token, hãy in ra để dễ debug
                System.err.println("❌ Lỗi khi giải mã token: " + e.getMessage());
            e.printStackTrace();
        }

        // 3. Cho phép request đi tiếp vào Controller
        filterChain.doFilter(request, response);

        // 4. BẮT BUỘC: Xóa dữ liệu sau khi xong request để không bị rò rỉ sang user khác
        TenantContext.clear();
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7).trim(); // Cắt chữ "Bearer " để lấy lõi token
        }
        return null;
    }
}