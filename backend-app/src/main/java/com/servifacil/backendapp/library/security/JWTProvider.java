package com.servifacil.backendapp.library.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-seconds}")
    private  long jwtExpirationDate;


    public String generateToken(String username){

        long expirationTime = jwtExpirationDate * 1_000;
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + expirationTime);

        Map<String, Object> extra = new HashMap<>();

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .addClaims(extra)
                .signWith(key())
                .compact();
        return token;
    }

    public String getUsername(String token){
        return getClaim(token, Claims::getSubject);
    }


    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }


    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsername(token);
        return ((username).equals(userDetails.getUsername()) && !isTokenExpired(token));

//        try{
//            Jwts.parserBuilder()
//                    .setSigningKey(key())
//                    .build()
//                    .parse(token);
//            return true;
//        } catch (MalformedJwtException e) {
//            logger.error("Invalid JWT token: {}", e.getMessage());
//        } catch (ExpiredJwtException e) {
//            logger.error("JWT token is expired: {}", e.getMessage());
//        } catch (UnsupportedJwtException e) {
//            logger.error("JWT token is unsupported: {}", e.getMessage());
//        } catch (IllegalArgumentException e) {
//            logger.error("JWT claims string is empty: {}", e.getMessage());
//        }
//        return false;
    }
    public Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }

    private  <T> T getClaim(String token, Function<Claims, T> claimsFunction) {
        final  Claims claims = this.getAllClaims(token);
        return claimsFunction.apply(claims);
    }

    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

}
