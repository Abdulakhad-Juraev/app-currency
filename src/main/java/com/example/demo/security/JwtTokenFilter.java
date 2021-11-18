package com.example.demo.security;

import com.example.demo.service.AuthService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Configuration
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    AuthService authService;

    @Value("${app.jwtSecret}")
    private String kalit;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(httpServletRequest);
            if (StringUtils.hasText(jwt) && tokenValidmi(jwt)) {
                String userId = getUserIdToken(jwt);
                UserDetails authServiceUserById = authService.getUserById(UUID.fromString(userId));
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                authServiceUserById,
                                null,
                                authServiceUserById.getAuthorities()
                        );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            logger.error("Could not set user authentication in security context", e);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public boolean tokenValidmi(String token) {
        try {
            Jwts.parser().setSigningKey(kalit).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid Jwt signature");
        } catch (MalformedJwtException e) {
            logger.error("Invalid Jwt token");
        } catch (ExpiredJwtException e) {
            logger.error("Expired Jwt token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported Jwt token");
        } catch (IllegalArgumentException e) {
            logger.error("Jwt claims strings is empty");
        }
        return false;
    }

    public String getUserIdToken(String tokenUser) {
        return Jwts.parser().setSigningKey(kalit)
                .parseClaimsJws(tokenUser)
                .getBody()
                .getSubject();
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
