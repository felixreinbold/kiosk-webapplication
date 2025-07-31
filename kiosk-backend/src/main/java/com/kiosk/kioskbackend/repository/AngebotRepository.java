package com.kiosk.kioskbackend.repository;

import com.kiosk.kioskbackend.model.Angebot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AngebotRepository extends JpaRepository<Angebot, Long> {
}
