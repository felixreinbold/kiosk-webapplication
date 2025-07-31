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
}
