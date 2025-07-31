package com.kiosk.kioskbackend.controller;

import com.kiosk.kioskbackend.dto.ChangePasswordRequest;
import com.kiosk.kioskbackend.model.Angebot;
import com.kiosk.kioskbackend.service.AngebotService;
import com.kiosk.kioskbackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kiosk.kioskbackend.security.JwtService;

import java.util.List;

@RestController
@RequestMapping("/api/angebote")
@RequiredArgsConstructor
public class AngebotController {

    private final AngebotService angebotService;
    private final AuthenticationService authService;

    @GetMapping
    public List<Angebot> getAlleAngebote() {
        return angebotService.getAlleAngebote();
    }

    @PostMapping
    public Angebot neuesAngebot(@RequestBody Angebot angebot) {
        return angebotService.neuesAngebotSpeichern(angebot);
    }

    @DeleteMapping("/{id}")
    public void loescheAngebot(@PathVariable Long id) {
        angebotService.angebotLoeschen(id);
    }

    @PutMapping("/{id}")
    public Angebot aktualisiereAngebot(@PathVariable Long id, @RequestBody Angebot angebot) {
        return angebotService.angebotAktualisieren(id, angebot);
    }
}
