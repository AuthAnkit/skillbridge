package com.skillbridge.backend.dto.response;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class LoginResponseDTO {

    private String message;
    private String email;
    private String token;
}
