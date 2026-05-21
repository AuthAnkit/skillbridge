package com.skillbridge.backend.transformer;

import com.skillbridge.backend.dto.request.UserRegisterRequestDTO;
import com.skillbridge.backend.dto.response.UserResponseDTO;
import com.skillbridge.backend.entity.User;

public class UserTransformer {
    public static User toEntity(UserRegisterRequestDTO requestDTO) {
        return User.builder()
                .name(requestDTO.getName())
                .email(requestDTO.getEmail())
                .password(requestDTO.getPassword())
                .build();
    }

    public static UserResponseDTO toResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
