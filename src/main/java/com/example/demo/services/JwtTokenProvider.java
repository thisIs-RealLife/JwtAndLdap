package com.example.demo.services;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final AuthenticationService authenticationService;

    private String secretKey = "secret";
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public JwtTokenProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public String createToken(String login, String password){
        Authentication authentication = authenticationService.authenticate(login, password);
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("role", authentication.getAuthorities());
        Date date = new Date();
        Date expiration = new Date(date.getTime() + 1000 * 60 * 60 * 10);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.ES256, secretKey)
                .compact();
    }

    public boolean validityToken(String token) throws Exception {
        try {
            Jws<Claims> claimsJwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJwt.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException exception) {
            throw new Exception("Jwt token invalid");
        }
    }

    public Authentication getAuthentication(String token){
        return null;
    }

    public String getEmail(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader(header);
    }
}
