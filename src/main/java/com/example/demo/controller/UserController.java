package com.example.demo.controller;

import com.example.demo.config.jwt.*;
import com.example.demo.controller.dto.*;
import com.example.demo.domain.*;
import com.example.demo.exceptions.*;
import lombok.*;
import org.springframework.security.core.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public TokenResponse userLogin(@RequestBody LoginRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
            .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }
        return jwtTokenProvider.receiveToken(user.getAccountId());
    }

    @GetMapping
    public UserResponse getUser() {
        User user = getCurrentUser();
        return UserResponse.builder()
            .accountId(user.getAccountId())
            .build();
    }

    public User getCurrentUser() {
        var accountId = SecurityContextHolder.getContext()
            .getAuthentication()
            .getName();
        return userRepository.findByAccountId(accountId).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
