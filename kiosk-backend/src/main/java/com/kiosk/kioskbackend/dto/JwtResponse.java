package com.kiosk.kioskbackend.dto; // oder passe dein Package an

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
