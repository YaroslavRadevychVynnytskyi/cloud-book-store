package com.application.bsuserservice.mapper;

import com.application.bsuserservice.config.MapperConfig;
import com.application.bsuserservice.dto.UserResponseDto;
import com.application.bsuserservice.dto.api.request.UserRegistrationRequestDto;
import com.application.bsuserservice.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toModel(UserRegistrationRequestDto requestDto);

    UserResponseDto toDto(User user);
}
