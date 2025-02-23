package org.botnicholas.projects.authenticationservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRefreshRequest {
    private String refreshToken;
}
