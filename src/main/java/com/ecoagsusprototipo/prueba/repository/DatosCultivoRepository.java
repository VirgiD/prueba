package com.ecoagsusprototipo.prueba.repository;

import com.ecoagsusprototipo.prueba.model.ZonaRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatosCultivoRepository extends JpaRepository<ZonaRequest, Long> {
}
