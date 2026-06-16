package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Tắt chống giả mạo request để dễ test POST
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Tạm thời cho phép qua cửa hết, không cần login
            );
        return http.build();
    }
}

// Spring Security đang chặn toàn bộ hệ thống bằng mật khẩu tự sinh. Nếu bạn dùng .http gọi thử API bây giờ sẽ bị báo lỗi 401 Unauthorized.

// Để test được luồng CRUD này, bạn cần tắt tạm tính năng chặn API. Hãy tạo một thư mục tên là config, sau đó tạo file SecurityConfig.java bên trong: