package com.kiosk.kioskbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AngebotRequest {
    private String titel;
    private String beschreibung;
    private String imageUrl;
    private String bildBase64;
}