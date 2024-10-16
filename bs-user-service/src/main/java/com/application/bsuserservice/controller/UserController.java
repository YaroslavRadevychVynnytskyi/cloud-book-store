package com.application.bsuserservice.controller;

import com.application.bsuserservice.dto.UserResponseDto;
import com.application.bsuserservice.dto.api.request.UserRegistrationRequestDto;
import com.application.bsuserservice.model.User;
import com.application.bsuserservice.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRegistrationRequestDto requestDto) {
        return ResponseEntity.ok(userService.save(requestDto));
    }

    @PostMapping("/get-by-username")
    public ResponseEntity<Optional<UserResponseDto>> getByUsername(
            @RequestBody UserRegistrationRequestDto requestDto) {
        return ResponseEntity.ok(userService.findByUsername(requestDto.email()));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getInfo(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(userService.findById(user.getId()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/secured")
    public ResponseEntity<String> secured() {
        return ResponseEntity.ok("Hello to admin from secured!");
    }
}
