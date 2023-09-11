package com.Davidson.EncryptionSystem.service;

import com.Davidson.EncryptionSystem.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
<<<<<<< Updated upstream
import java.util.*;
=======
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
>>>>>>> Stashed changes
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTService {

    private final String SECRET_KEY = "5367566B59703373357638792F423F4528482B4D6251655468576D5A71347437";

    @Autowired
    private UserRepository userRepository;

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsFunction){
        final Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
<<<<<<< Updated upstream
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
=======
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
>>>>>>> Stashed changes
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractUsername(token);
<<<<<<< Updated upstream
        return (username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token) && !this.blackList.contains(token));
=======
        return username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token);
>>>>>>> Stashed changes
    }

    public String refreshToken(UserDetails userDetails){
        return generateToken(userDetails);
    }
<<<<<<< Updated upstream

    private List<String> blackList = new ArrayList<>();
    public void addToBlacklist(String jti) {
        this.blackList.add(jti);
    }
=======
>>>>>>> Stashed changes
}
