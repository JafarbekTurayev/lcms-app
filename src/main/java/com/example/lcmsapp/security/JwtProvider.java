package com.example.lcmsapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    //kalit token yasash login phone username email
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 86400))
                .signWith(SignatureAlgorithm.HS512, "SUPPERAPIKEY")
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .setSigningKey("SUPPERAPIKEY")
                .parseClaimsJws(token)
                .getBody().getSubject();
    }




}
