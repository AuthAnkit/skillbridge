package com.skillbridge.backend.service;

import com.skillbridge.backend.dto.request.UserRegisterRequestDTO;
import com.skillbridge.backend.dto.response.UserResponseDTO;
import com.skillbridge.backend.exception.UserAlreadyExistsException;

public interface UserService {
    UserResponseDTO registerUser(UserRegisterRequestDTO requestDTO);
}
