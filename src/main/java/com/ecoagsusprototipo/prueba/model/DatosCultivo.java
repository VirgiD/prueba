package com.ecoagsusprototipo.prueba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DatosCultivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Añadir el campo id si no está presente
    private double lat;
    private double lon;
    private double ndvi;
    private int humedad;
    private int temperatura;
    private double phSuelo;
    private String nutrientes;
    private String tiffPath; // Nuevo campo para la ruta del archivo TIFF

    @Override
    public String toString() {
        return "DatosCultivo{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", ndvi=" + ndvi +
                ", humedad=" + humedad +
                ", temperatura=" + temperatura +
                ", phSuelo=" + phSuelo +
                ", nutrientes='" + nutrientes + '\'' +
                ", tiffPath='" + tiffPath + '\'' +
                '}';
    }
    }
