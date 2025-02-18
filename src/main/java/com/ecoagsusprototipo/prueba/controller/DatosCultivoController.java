package com.ecoagsusprototipo.prueba.controller;
import com.ecoagsusprototipo.prueba.model.ZonaRequest;
import com.ecoagsusprototipo.prueba.repository.DatosCultivoRepository;
import com.ecoagsusprototipo.prueba.service.IndicadorVegetacionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

import java.util.Map;

@RestController
@RequestMapping("/api/cultivos")
@CrossOrigin(origins = "http://localhost:8080")
public class DatosCultivoController {

    @Autowired
    private IndicadorVegetacionService indicadorVegetacionService;
    @Autowired
    private DatosCultivoRepository datosCultivoRepository;

    @PostMapping("/seleccionar-zona")
    public String seleccionarZona(@RequestParam("file") MultipartFile file, @RequestParam("datosCultivo") String datosCultivoJson) {
        try {
            // Convertir JSON a objeto
            ObjectMapper objectMapper = new ObjectMapper();
            ZonaRequest datosCultivo = objectMapper.readValue(datosCultivoJson, ZonaRequest.class);

            System.out.println("Datos deserializados: " + datosCultivo);

            // Guardar archivo en disco temporalmente
            String tiffPath = "C:/ruta/a/tu/archivo/ndvi.tif";
            File tempFile = new File(tiffPath);
            file.transferTo(tempFile);

            // Calcular NDVI usando el archivo .tif
            Map<String, String> results = indicadorVegetacionService.calculateNDVI(tempFile);

            // Asigna la ruta del archivo TIFF al objeto ZonaRequest
            datosCultivo.setTiffPath(tiffPath);

            System.out.println("Datos recibidos: " + datosCultivo);
            System.out.println("Resultados del NDVI: " + results.get("NDVI"));

            // Guardar los datos en la BD
            datosCultivoRepository.save(datosCultivo);

            return "Zona guardada con éxito y NDVI calculado";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al procesar el archivo y los datos: " + e.getMessage();
        }
    }
}
