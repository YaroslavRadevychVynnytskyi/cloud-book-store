package com.application.bsuserservice.dto.api.request;

public record UserRegistrationRequestDto(
        String email,
        String password,
        String firstName,
        String lastName,
        String shippingAddress
) {
}
