package com.kiosk.kioskbackend.service;

import com.kiosk.kioskbackend.dto.AngebotRequest;
import com.kiosk.kioskbackend.dto.AngebotResponse;
import com.kiosk.kioskbackend.model.Angebot;
import com.kiosk.kioskbackend.repository.AngebotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor
public class AngebotService {

    private final AngebotRepository angebotRepository;

    public List<AngebotResponse> getAlleAngebote() {
        return angebotRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public AngebotResponse neuesAngebotSpeichern(AngebotRequest request) {
        Angebot angebot = mapToEntity(request);
        angebot = angebotRepository.save(angebot);

        return mapToDto(angebot);
    }

    public void angebotLoeschen(Long id) {
        angebotRepository.deleteById(id);
    }

    public AngebotResponse angebotAktualisieren(Long id, AngebotRequest request) {
        Angebot angebot = angebotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Angebot nicht gefunden mit ID: " + id));

        angebot.setTitel(request.getTitel());
        angebot.setBeschreibung(request.getBeschreibung());
        angebot.setBildUrl(request.getImageUrl());

        if (request.getBildBase64() != null && !request.getBildBase64().isEmpty()) {
            angebot.setImageData(Base64.getDecoder().decode(request.getBildBase64()));
        }

        angebot = angebotRepository.save(angebot);
        return mapToDto(angebot);
    }

    // 🔁 Mapper

    public Angebot mapToEntity(AngebotRequest dto) {
        Angebot angebot = new Angebot();
        angebot.setTitel(dto.getTitel());
        angebot.setBeschreibung(dto.getBeschreibung());
        angebot.setBildUrl(dto.getImageUrl());

        if (dto.getBildBase64() != null && !dto.getBildBase64().isEmpty()) {
            angebot.setImageData(Base64.getDecoder().decode(dto.getBildBase64()));
            System.out.println("Empfangenes Base64-Bild (Anfang): " + dto.getBildBase64().substring(0, 30));

        }

        return angebot;
    }

    public AngebotResponse mapToDto(Angebot angebot) {
        String base64 = angebot.getImageData() != null
                ? Base64.getEncoder().encodeToString(angebot.getImageData())
                : null;

        return new AngebotResponse(
                angebot.getId(),
                angebot.getTitel(),
                angebot.getBeschreibung(),
                angebot.getBildUrl(),   // <-- imageUrl
                base64                  // <-- bildBase64
        );
    }
}
