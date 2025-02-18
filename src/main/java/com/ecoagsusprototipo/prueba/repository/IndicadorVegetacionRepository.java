package com.ecoagsusprototipo.prueba.repository;


import com.ecoagsusprototipo.prueba.model.IndicadorVegetacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IndicadorVegetacionRepository extends JpaRepository<IndicadorVegetacion, Long> {
    List<IndicadorVegetacion> findByTipoAndFechaBetween(String tipo, LocalDate startDate, LocalDate endDate);
}
