package com.ritik.dreamshop.security.jwt;


import com.ritik.dreamshop.security.user.ShopUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${auth.token.jwtSecret}")
    private String jwtSecret;

    @Value("${auth.token.expirationInMils}")
    private int expirationTime;

    public String generateTokenForUser(Authentication authentication){
        ShopUserDetails userPrincipal = ( ShopUserDetails ) authentication.getPrincipal(); // receives the details of the authenticated user.
        List<String> roles = userPrincipal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList(); // return the authorities of the user.
        return Jwts.builder()
                .claims() // claims are piece of information to be included in the token payload
                .add("id", userPrincipal.getId())// adding custom claims
                .add("roles", roles)
                .subject(userPrincipal.getEmail()) // sets the subject, represents the identity of the user, represents the principal(user) to which the token is issued
                .issuedAt(new Date()) // set the issue time to the current date
                .expiration(new Date((new Date()).getTime() + expirationTime)) // set the expiration date
                .and() // closing claim configuration and returns to the main builder context.
                .signWith(key()) // signs the jwt using a cryptographic key returned by the key() method, ensuring the token authenticity.
                .compact(); // finalizes the token construction and coverts it to a compact, URL-safe string representation.
    }

    private SecretKey key() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret); // decoding jwtSecret and converting it to bytes.
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String gerUsernameFromToken(String token){
        return extractClaim(token, Claims::getSubject);

    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String userName = gerUsernameFromToken(token);
            return ( userName.equals(userDetails.getUsername()) && !isTokenExpired(token) );
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
}
