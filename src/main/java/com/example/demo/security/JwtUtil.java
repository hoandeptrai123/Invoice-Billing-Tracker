package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Khóa bí mật (Trong thực tế sẽ để ở file properties)
    // Dùng một chuỗi cố định để không bị đổi mỗi khi restart server
    private final String SECRET_KEY = "DayLaMotChuoiBaoMatCucKyDaiVaPhucTapChoHeThongSaaS2026";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    // Thời gian sống của Token: 1 ngày (86400000 ms)
    private final long jwtExpirationMs = 86400000;

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("organizationId", user.getOrganizationId()) // Gắn luôn orgId vào Token!
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key)
                .compact();
    }

    //Lay email tu token
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //lay organizationId tu token
    public Long getOrganizationIdFromToken(String token) {
        Object orgIdObj = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().get("organizationId");
                
        if (orgIdObj != null) {
            return Long.valueOf(orgIdObj.toString()); // Chuyển qua chuỗi rồi mới ép về Long
        }
        return null;
    }

    // Kiem tra token hop le, chua het han, chua bi thay doi
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Token không hợp lệ (hết hạn, bị thay đổi, v.v.)
            System.err.println("❌ Máy quét thẻ báo lỗi: " + e.getMessage());
            return false;
        }
    }
}