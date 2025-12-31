package com.example.repetitivecrud.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.secret.key}") String secretKeyString) {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * 토큰 만들기
     */
    public String createToken(Long memberId, String memberEmail, String memberName) {

        // 2. 데이터 준비
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * 60 * 60); // 만료시간: 1시간

        // 3. 토큰 만들기
        String jwt = Jwts.builder()
                .issuer("jwt.basic.com")
                .subject(memberId.toString())
                .expiration(expiration)
                .issuedAt(now)
                .claim("email", memberEmail)
                .claim("name", memberName)
                .signWith(secretKey)
                .compact();
        return jwt;
    }
}