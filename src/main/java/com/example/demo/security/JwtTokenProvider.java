package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwtSecret}")
    private String kalit;

    @Value("${app.jwtExpirationInMs}")
    private Long expireTime;

    public String generateToken(User user) {
        Date amalQilishMuddati = new Date(new Date().getTime() + expireTime);
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(amalQilishMuddati)
                .signWith(SignatureAlgorithm.HS512, kalit)
                .compact();
    }
}
