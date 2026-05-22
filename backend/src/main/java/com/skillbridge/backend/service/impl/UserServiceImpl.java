package com.skillbridge.backend.service.impl;

import com.skillbridge.backend.dto.request.LoginRequestDTO;
import com.skillbridge.backend.dto.request.UserRegisterRequestDTO;
import com.skillbridge.backend.dto.response.LoginResponseDTO;
import com.skillbridge.backend.dto.response.UserResponseDTO;
import com.skillbridge.backend.entity.User;
import com.skillbridge.backend.exception.UserAlreadyExistsException;
import com.skillbridge.backend.repository.UserRepository;
import com.skillbridge.backend.security.JwtService;
import com.skillbridge.backend.service.UserService;
import com.skillbridge.backend.transformer.UserTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public UserResponseDTO registerUser(UserRegisterRequestDTO requestDTO) {
        if(userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = UserTransformer.toEntity(requestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return UserTransformer.toResponseDTO(savedUser);

    }

    @Override
    public LoginResponseDTO loginUser(LoginRequestDTO requestDTO){
        User user = userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(()-> new RuntimeException("Invalid email or password")
        );

        boolean isPresent = passwordEncoder.matches(requestDTO.getPassword(),user.getPassword());
        if(!isPresent) {
            throw new RuntimeException("Invalid email or password");
        }

        return LoginResponseDTO.builder()
                .message("Successfully logged in")
                .email(user.getEmail())
                .build();
    }
}
