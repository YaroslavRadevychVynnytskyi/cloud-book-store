package com.application.bsuserservice.service.impl;

import com.application.bsuserservice.dto.UserResponseDto;
import com.application.bsuserservice.dto.api.request.UserRegistrationRequestDto;
import com.application.bsuserservice.mapper.UserMapper;
import com.application.bsuserservice.model.Role;
import com.application.bsuserservice.model.User;
import com.application.bsuserservice.repo.RoleRepository;
import com.application.bsuserservice.repo.UserRepository;
import com.application.bsuserservice.service.UserService;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto save(UserRegistrationRequestDto requestDto) {
        User user = userMapper.toModel(requestDto);
        user.setRoles(Set.of(roleRepository.findByName(Role.RoleName.USER)));

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public Optional<UserResponseDto> findByUsername(String username) {
        User user = userRepository.findByEmail(username);
        return Optional.ofNullable(userMapper.toDto(user));
    }

    @Override
    public UserResponseDto findById(Long userId) {
        return userMapper.toDto(userRepository.findById(userId).orElseThrow());
    }
}
