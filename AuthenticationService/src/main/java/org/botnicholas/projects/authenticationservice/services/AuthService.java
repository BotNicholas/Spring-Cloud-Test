package org.botnicholas.projects.authenticationservice.services;

import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.botnicholas.projects.authenticationservice.exceptions.BadCredentialsException;
import org.botnicholas.projects.authenticationservice.models.User;
import org.botnicholas.projects.authenticationservice.models.dto.JwtResponse;
import org.botnicholas.projects.authenticationservice.models.dto.RegisterUserDto;
import org.botnicholas.projects.authenticationservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final Map<String, String> jwtRefreshStorage = new HashMap<>();

    @Autowired
    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtResponse register(RegisterUserDto registerUserDto) throws BadCredentialsException {
        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setRole("USER");

        try {
            user = userRepository.save(user);
        } catch (Exception e) {
            throw new BadCredentialsException("Credentials you provided are bad...");
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        jwtRefreshStorage.put(user.getUsername(), refreshToken);

        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse login(RegisterUserDto registerUserDto) throws BadCredentialsException {
        Optional<User> userMb = Optional.ofNullable(userRepository.findByUsername(registerUserDto.getUsername()));

        if (userMb.isEmpty()) {
            throw new BadCredentialsException("Bad credentials!");
        }

        if (!passwordEncoder.matches(registerUserDto.getPassword(), userMb.get().getPassword())) {
            throw new BadCredentialsException("Bad credentials!");
        }

        User user = userMb.get();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        jwtRefreshStorage.put(user.getUsername(), refreshToken);

        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse refreshAccessToken(String refreshToken) {
        if (jwtService.validateRefreshToken(refreshToken)) {
            final Claims refreshClaims = jwtService.getRefreshClaims(refreshToken);
            final String username = refreshClaims.getSubject();

            final String storedRefreshToken = jwtRefreshStorage.get(username);
            if (storedRefreshToken != null && storedRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByUsername(username);
                final String accessToken = jwtService.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refreshAllTokens(String refreshToken) throws AuthException {
        if (jwtService.validateRefreshToken(refreshToken)) {
            final Claims refreshClaims = jwtService.getRefreshClaims(refreshToken);
            final String username = refreshClaims.getSubject();

            final String storedRefreshToken = jwtRefreshStorage.get(username);
            if (storedRefreshToken != null && storedRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByUsername(username);
                final String accessToken = jwtService.generateAccessToken(user);
                final String newRefreshToken = jwtService.generateRefreshToken(user);
                jwtRefreshStorage.put(username, newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid RefreshToken!");
    }
}
