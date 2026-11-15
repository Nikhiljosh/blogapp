package com.example.blogpr.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String appSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private long milliseconds;


    public String generateToken(Authentication authentication){

        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + milliseconds))
                .signWith(getKey())
                .compact();

    }

    public String getUserNameFromToken(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token){

        try {
            Jwts.parser()
                    .verifyWith((SecretKey) getKey())
                    .build()
                    .parse(token);
            return true;
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    private Key getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(appSecret));
    }
}
