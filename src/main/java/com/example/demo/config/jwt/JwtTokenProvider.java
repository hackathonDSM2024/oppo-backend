package com.example.demo.config.jwt;

import com.example.demo.config.auth.*;
import com.example.demo.controller.dto.*;
import com.example.demo.exceptions.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final CustomUserDetailsService customUserDetailsService;

    public String createAccessToken(String accountId) {
        Date now = new Date();
        return Jwts.builder()
            .setSubject(accountId)
            .claim("type", "access")
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + jwtProperties.accessExpiration() * 1000))
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secret())
            .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims getClaims(String token) {
        try {
            return Jwts
                .parser()
                .setSigningKey(jwtProperties.secret())
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public TokenResponse receiveToken(String accountId) {
        return TokenResponse
            .builder()
            .accessToken(createAccessToken(accountId))
            .build();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.header());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.prefix())
            && bearerToken.length() > jwtProperties.prefix().length() + 1) {
            return bearerToken.substring(7);
        }
        return null;
    }
}