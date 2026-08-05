package com.example.demo.utils;

import com.example.demo.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // Lấy SignKey từ chuỗi Secret
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Hàm tạo Token từ đối tượng User
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        // Đưa role vào Custom Claim
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername()) // Claim 'sub' mặc định chứa username
                .setIssuedAt(new Date(System.currentTimeMillis())) // Thời điểm tạo
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Thời điểm hết hạn
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Ký bằng thuật toán HS256
                .compact();
    }
}