package com.application.bsuserservice.service;

import com.application.bsuserservice.dto.UserResponseDto;
import com.application.bsuserservice.dto.api.request.UserRegistrationRequestDto;
import java.util.Optional;

public interface UserService {
    UserResponseDto save(UserRegistrationRequestDto requestDto);

    Optional<UserResponseDto> findByUsername(String username);

    UserResponseDto findById(Long userId);
}
