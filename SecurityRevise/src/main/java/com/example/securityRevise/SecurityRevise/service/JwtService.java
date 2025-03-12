package com.example.securityRevise.SecurityRevise.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    public static final String SECRET = "TiZSSy4Xek+aPzTi35EKO5Z5eTv9I2uwPTXjUnQhcqri5ZA2FT2vzBQD35ttjB9QLyPp6GDPMFLY+dJgSGOpPrUdlK3Af19/WhCyRzQLLNxuKogBrbYpxheOqEtzxd/Puv7t6DKl2SPj1k98308pK7+HGRaDs9lBOCZDPAlKBAvN50CTKwv8drlIgN9pbzqbTM5R5QYn88Bk26ybYAdcw15elUJMIa+BmjvaKnnocUgBRSM464m2tSA84PdZbiKMF5KoetQNOuAUhw30pj1ZAtBDVfBl5BfGlKJodEJT1dADfp9zWwHGRME3SnWuTwx7xtnGr3nY3fmkRHjvE0ybYbfj+zXc8Kuuh9DKmXcvnM4=";
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
