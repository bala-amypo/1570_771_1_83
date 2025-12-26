package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long validityInMillis;

    public JwtTokenProvider(String secret, long validityInMillis) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.validityInMillis = validityInMillis;
    }

    public String createToken(String email, String role, Long userId) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        claims.put("userId", userId);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
