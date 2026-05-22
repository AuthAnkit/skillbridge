package com.skillbridge.backend.controller;

import com.skillbridge.backend.dto.request.LoginRequestDTO;
import com.skillbridge.backend.dto.request.UserRegisterRequestDTO;
import com.skillbridge.backend.dto.response.LoginResponseDTO;
import com.skillbridge.backend.dto.response.UserResponseDTO;
import com.skillbridge.backend.entity.User;
import com.skillbridge.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        UserResponseDTO userResponseDTO = userService.registerUser(userRegisterRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public   ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO requestDTO) {
        return ResponseEntity.ok(userService.loginUser(requestDTO));
    }
}
