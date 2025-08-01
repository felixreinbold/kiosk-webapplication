package com.kiosk.kioskbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AngebotResponse {
    private Long id;
    private String titel;
    private String beschreibung;
    private String imageUrl;
    private String bildBase64;
}
