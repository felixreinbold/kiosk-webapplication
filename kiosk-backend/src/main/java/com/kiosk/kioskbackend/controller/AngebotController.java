package com.kiosk.kioskbackend.controller;

import com.kiosk.kioskbackend.dto.AngebotRequest;
import com.kiosk.kioskbackend.dto.AngebotResponse;
import com.kiosk.kioskbackend.service.AngebotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/angebote")
@RequiredArgsConstructor
public class AngebotController {

    private final AngebotService angebotService;

    @GetMapping
    public ResponseEntity<List<AngebotResponse>> getAlleAngebote() {
        return ResponseEntity.ok(angebotService.getAlleAngebote());
    }

    @PostMapping
    public ResponseEntity<AngebotResponse> neuesAngebot(@RequestBody AngebotRequest request) {
        return ResponseEntity.ok(angebotService.neuesAngebotSpeichern(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AngebotResponse> aktualisiereAngebot(@PathVariable Long id,
                                                               @RequestBody AngebotRequest request) {
        return ResponseEntity.ok(angebotService.angebotAktualisieren(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> loescheAngebot(@PathVariable Long id) {
        angebotService.angebotLoeschen(id);
        return ResponseEntity.noContent().build();
    }
}
