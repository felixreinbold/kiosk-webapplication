// Datei: AuthController.java
package com.kiosk.kioskbackend.controller;

import com.kiosk.kioskbackend.dto.ChangePasswordRequest;
import com.kiosk.kioskbackend.dto.LoginRequest;
import com.kiosk.kioskbackend.dto.LoginRequest;
import com.kiosk.kioskbackend.dto.JwtResponse;
import com.kiosk.kioskbackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    // 🔐 Login-Endpunkt
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        JwtResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    // 📧 Reset-Key an Admin-Mail senden
    @PostMapping("/reset")
    public ResponseEntity<String> sendResetKey() {
        authService.sendResetKeyToAdmin();
        return ResponseEntity.ok("Reset-Key wurde an die Admin-Mail gesendet.");
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestBody ChangePasswordRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        authService.changePassword(userDetails.getUsername(), request);
        return ResponseEntity.ok("Passwort erfolgreich geändert");
    }
}