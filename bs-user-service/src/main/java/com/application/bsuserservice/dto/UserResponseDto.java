package com.application.bsuserservice.dto;

import com.application.bsuserservice.model.Role;
import java.util.Set;

public record UserResponseDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String shippingAddress,
        Set<Role> roles
) {
}
