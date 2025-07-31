package com.kiosk.kioskbackend.service;

import com.kiosk.kioskbackend.model.Angebot;
import com.kiosk.kioskbackend.repository.AngebotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AngebotService {

    private final AngebotRepository angebotRepository;

    public List<Angebot> getAlleAngebote() {
        return angebotRepository.findAll();
    }

    public Angebot neuesAngebotSpeichern(Angebot angebot) {
        return angebotRepository.save(angebot);
    }

    public void angebotLoeschen(Long id) {
        angebotRepository.deleteById(id);
    }

    public Angebot angebotAktualisieren(Long id, Angebot neuesAngebot) {
        Angebot vorhandenesAngebot = angebotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Angebot nicht gefunden mit ID: " + id));

        vorhandenesAngebot.setTitel(neuesAngebot.getTitel());
        vorhandenesAngebot.setBeschreibung(neuesAngebot.getBeschreibung());
        vorhandenesAngebot.setBildUrl(neuesAngebot.getBildUrl());

        return angebotRepository.save(vorhandenesAngebot);
    }
}
