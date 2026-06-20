package com.pm.authservice.controller;

import com.pm.authservice.dto.LoginRequestDTO;
import com.pm.authservice.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Operation(summary = "Generate login token for user")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Validated(Default.class) @RequestBody LoginRequestDTO loginRequestDTO){
        return null;
    }
}
