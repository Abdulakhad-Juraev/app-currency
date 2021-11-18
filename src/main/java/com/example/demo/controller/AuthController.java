package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.JwtToken;
import com.example.demo.payload.LoginDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.CurrentUser;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import com.example.demo.utils.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    private HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String token = jwtTokenProvider.generateToken(user);
            return ResponseEntity
                    .status(200)
                    .body(new ApiResponse(MessageConst.LOGIN_SUCCESS, true, new JwtToken(token)));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new ApiResponse(MessageConst.LOGIN_ERROR, false));
        }
    }

    @GetMapping("/me")
    public HttpEntity<?> getUserMe(@CurrentUser User user) {
        return ResponseEntity.ok(user);
    }



}
