package com.application.bsuserservice.controller;

import com.application.bsuserservice.dto.UserResponseDto;
import com.application.bsuserservice.dto.api.request.UserLoginRequestDto;
import com.application.bsuserservice.dto.api.request.UserRegistrationRequestDto;
import com.application.bsuserservice.dto.api.response.JwtResponseDto;
import com.application.bsuserservice.service.UserService;
import com.application.bsuserservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<JwtResponseDto> register(
            @RequestBody UserRegistrationRequestDto requestDto) {
        UserResponseDto user = userService.save(requestDto);

        String jwt = jwtUtil.generateToken(user.email());
        return ResponseEntity.ok(new JwtResponseDto(jwt));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> authenticate(@RequestBody UserLoginRequestDto requestDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password()));

        String token = jwtUtil.generateToken(authenticate.getName());

        return ResponseEntity.ok(new JwtResponseDto(token));
    }
}
