package com.ecoagsusprototipo.prueba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ZonaRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double lat;
    private double lon;
    private double ndvi;
    private int humedad;
    private int temperatura;
    private double phSuelo;
    private String nutrientes;
    private String tiffPath; // Nuevo campo para la ruta del archivo TIFF

    public String getTiffPath() {
        return tiffPath;
    }

    public void setTiffPath(String tiffPath) {
        this.tiffPath = tiffPath;
    }

    @Override
    public String toString() {
        return "ZonaRequest{" +
                "lat=" + lat +
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
