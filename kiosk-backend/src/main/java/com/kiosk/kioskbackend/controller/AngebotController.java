package com.kiosk.kioskbackend.controller;

import com.kiosk.kioskbackend.model.Angebot;
import com.kiosk.kioskbackend.service.AngebotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/angebote")
@RequiredArgsConstructor
public class AngebotController {

    private final AngebotService angebotService;

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
}
