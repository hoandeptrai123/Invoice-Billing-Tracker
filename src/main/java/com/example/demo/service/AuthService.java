package com.example.demo.service;

import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.RegisterRequestDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng!");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword())); // Mã hóa trước khi lưu
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setOrganizationId(request.getOrganizationId()); 
        user.setStatus("ACTIVE");

        userRepository.save(user);
        return new AuthResponseDTO(null, "Đăng ký thành công!");
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại!"));

        // So sánh mật khẩu người dùng nhập với mật khẩu đã mã hóa trong DB
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Sai mật khẩu!");
        }

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        // Sinh Token
        String token = jwtUtil.generateToken(user);
        return new AuthResponseDTO(token, "Đăng nhập thành công!");
    }
}