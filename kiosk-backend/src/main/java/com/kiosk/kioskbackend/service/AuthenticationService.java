
package com.kiosk.kioskbackend.service;

import com.kiosk.kioskbackend.dto.ChangePasswordRequest;
import com.kiosk.kioskbackend.dto.JwtResponse;
import com.kiosk.kioskbackend.dto.LoginRequest;
import com.kiosk.kioskbackend.model.User;
import com.kiosk.kioskbackend.repository.UserRepository;
import com.kiosk.kioskbackend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void sendResetKeyToAdmin() {
        // Admin holen (es gibt ja nur einen)
        User admin = userRepository.findByUsername("admin")
                .orElseThrow(() -> new RuntimeException("Admin nicht gefunden"));

        // Zufälligen Reset-Key generieren
        String resetKey = UUID.randomUUID().toString().substring(0, 8);
        admin.setResetKey(passwordEncoder.encode(resetKey));
        admin.setResetKeyCreatedAt(LocalDateTime.now());

        userRepository.save(admin);

        // Simulierter E-Mail-Versand (z. B. Log oder Konsole)
        System.out.println("📬 Reset-Key wurde an Admin versendet:");
        System.out.println("🔑 Dein temporäres Passwort lautet: " + resetKey);
    }

    public JwtResponse authenticate(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Normales Passwort prüfen
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return buildJwtResponse(user);
        }

        // Wenn Passwort nicht passt → Reset-Key prüfen (und gültig?)
        if (user.getResetKey() != null &&
                passwordEncoder.matches(request.getPassword(), user.getResetKey()) &&
                user.getResetKeyCreatedAt() != null &&
                user.getResetKeyCreatedAt().isAfter(LocalDateTime.now().minusMinutes(15))) {

            return buildJwtResponse(user);
        }

        throw new BadCredentialsException("Falsche Zugangsdaten");
    }

    private JwtResponse buildJwtResponse(User user) {
        String token = jwtService.generateToken(user.getUsername());
        return new JwtResponse(token);
    }

    public void changePassword(String username, ChangePasswordRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BadCredentialsException("Altes Passwort ist falsch");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setResetKey(null);
        user.setResetKeyCreatedAt(null);
        userRepository.save(user);
    }

}
