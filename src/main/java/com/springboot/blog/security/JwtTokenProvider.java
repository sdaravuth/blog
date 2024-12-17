package com.springboot.blog.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springboot.blog.exception.BlogAPIException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationMilliseconds;

    @PostConstruct
    public void logProperties() {
        System.out.println("JWT Secret: " + jwtSecret);
        System.out.println("JWT Expiration: " + jwtExpirationMilliseconds);
    }

    //generate JWT token (utility method)
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currecntDate = new Date();
        Date expireDate = new Date(currecntDate.getTime() + jwtExpirationMilliseconds);

        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret) );
    }

    //get username from JWT token
    public String getUsername(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    //validate JWT token
    public boolean validateToken(String token){
        try {
            Jwts.parser()
            .verifyWith((SecretKey) key())
            .build()
            .parse(token);
        
            return true; 
        } catch (MalformedJwtException e) {
            // TODO: handle exception
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch(ExpiredJwtException expiredJwtException){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch(UnsupportedJwtException unsupportedJwtException){
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupport JWT token");
        } catch(IllegalArgumentException illegalArgumentException){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT CLAIMS STIRNG IS NULL OR EMPTY");
        }

    }
}
