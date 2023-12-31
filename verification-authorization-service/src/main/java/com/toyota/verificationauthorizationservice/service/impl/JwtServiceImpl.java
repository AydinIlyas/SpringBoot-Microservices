package com.toyota.verificationauthorizationservice.service.impl;

import com.toyota.verificationauthorizationservice.service.abstracts.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY="4E635166546A576E5A7234753778214125442A472D4B6150645367566B587032";
    @Value("${application.security.jwt.expiration}")
    private long expirationDuration;
    /**
     * Extracts username
     * @param jwt Token
     * @return Username
     */
    @Override
    public String extractUsername(String jwt)
    {
        return extractClaim(jwt,Claims::getSubject);
    }

    /**
     * Extracts token ID
     * @param jwt Token
     * @return Token ID
     */
    @Override
    public String extractTokenId(String jwt) {
        Claims claims=extractAllClaims(jwt);
        return claims.get("jti").toString();
    }


    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    /**
     * Extracts all claims
     * @param token Token
     * @return Claims
     */
    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * @return Signing key
     */
    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generates token
     * @param userDetails User details
     * @return Token
     */
    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims=Map.of("jti",UUID.randomUUID().toString());
        return generateToken(claims,userDetails);
    }

    /**
     * Generates jwt token
     * @param extraClaims Extra Claims
     * @param userDetails User information
     * @return Token
     */
    private String generateToken(
            Map<String,Object> extraClaims, UserDetails userDetails
    )
    {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationDuration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Checks if token is valid
     * @param jwt Token
     * @param userDetails User information
     * @return boolean: if token is valid or not.
     */
    @Override
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username=extractUsername(jwt);
        return (username.equals(userDetails.getUsername()))&&!isTokenExpired(jwt);
    }

    /**
     * Checks if token is expired
     * @param token Token
     * @return  boolean
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts expiration date
     * @param token token
     * @return  Date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }
}
