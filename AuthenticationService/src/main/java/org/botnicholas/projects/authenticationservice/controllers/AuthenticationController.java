package org.botnicholas.projects.authenticationservice.controllers;

import jakarta.security.auth.message.AuthException;
import org.botnicholas.projects.authenticationservice.exceptions.BadCredentialsException;
import org.botnicholas.projects.authenticationservice.models.dto.JwtRefreshRequest;
import org.botnicholas.projects.authenticationservice.models.dto.JwtResponse;
import org.botnicholas.projects.authenticationservice.models.dto.RegisterUserDto;
import org.botnicholas.projects.authenticationservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthService authService;

    @Autowired
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@RequestBody RegisterUserDto registerUserDto) throws BadCredentialsException {
        JwtResponse jwtResponse = authService.register(registerUserDto);

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody RegisterUserDto registerUserDto) throws BadCredentialsException {
        JwtResponse jwtResponse = authService.login(registerUserDto);

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refresh-access")
    public ResponseEntity<JwtResponse> refreshAccess(@RequestBody JwtRefreshRequest refreshRequest) {
        JwtResponse jwtResponse = authService.refreshAccessToken(refreshRequest.getRefreshToken());

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refresh-all")
    public ResponseEntity<JwtResponse> refreshAll(@RequestBody JwtRefreshRequest refreshRequest) throws AuthException {
        JwtResponse jwtResponse = authService.refreshAllTokens(refreshRequest.getRefreshToken());

        return ResponseEntity.ok(jwtResponse);
    }
}
