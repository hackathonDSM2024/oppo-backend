package com.example.demo.config.auth;

import com.example.demo.domain.*;
import com.example.demo.domain.User;
import com.example.demo.exceptions.*;
import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) {
        User user = studentRepository.findByAccountId(accountId)
            .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        return new CustomUserDetails(user.getAccountId());
    }
}