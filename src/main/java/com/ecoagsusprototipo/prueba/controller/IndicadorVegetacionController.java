package com.ecoagsusprototipo.prueba.controller;


import com.ecoagsusprototipo.prueba.model.ZonaRequest;
import com.ecoagsusprototipo.prueba.service.IndicadorVegetacionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/api/indicadores")
public class IndicadorVegetacionController {

    @Autowired
    private IndicadorVegetacionService service;



    @PostMapping("/seleccionar-zona")
    public String seleccionarZona(@RequestParam("file") MultipartFile file, @RequestParam("datosCultivo") String datosCultivoJson) {
        try {
            // Convertir JSON a objeto
            ObjectMapper objectMapper = new ObjectMapper();
            ZonaRequest datosCultivo = objectMapper.readValue(datosCultivoJson, ZonaRequest.class);

            System.out.println("Datos deserializados: " + datosCultivo); // Verifica los datos deserializados

            // Guardar archivo en disco temporalmente
            File tempFile = File.createTempFile("uploaded", ".tif");
            file.transferTo(tempFile);

            // Calcular NDVI usando el archivo .tif
            Map<String, String> results = IndicadorVegetacionService.calculateNDVI(tempFile);

            // Eliminar archivo temporal
            tempFile.delete();

            System.out.println("Datos recibidos: " + datosCultivo);
            System.out.println("Resultados del NDVI: " + results.get("NDVI"));

            return "Zona guardada con éxito";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al procesar el archivo y los datos: " + e.getMessage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
