package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter; // Inject Filter vào đây

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/api/organizations").permitAll() // Vẫn mở cửa cho Login/Register
                .anyRequest().authenticated() // TẤT CẢ các API còn lại đều phải CÓ THẺ mới được vào
            )
            // Nhét chú bảo vệ vào trước chốt kiểm tra mặc định của Spring
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); 
            
        return http.build();
    }
}

// Spring Security đang chặn toàn bộ hệ thống bằng mật khẩu tự sinh. Nếu bạn dùng .http gọi thử API bây giờ sẽ bị báo lỗi 401 Unauthorized.

// Để test được luồng CRUD này, bạn cần tắt tạm tính năng chặn API. Hãy tạo một thư mục tên là config, sau đó tạo file SecurityConfig.java bên trong: