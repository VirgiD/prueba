package com.ecoagsusprototipo.prueba.service;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class IndicadorVegetacionService {

        public static Map<String, String> calculateNDVI(File file) throws Exception {
            Map<String, String> results = new HashMap<>();

            // Leer imagen TIFF
            GridCoverage2DReader reader = new GeoTiffReader(file);
            GridCoverage2D coverage = reader.read(null);
            Raster raster = coverage.getRenderedImage().getData();

            // Obtener dimensiones
            int width = raster.getWidth();
            int height = raster.getHeight();

            // NDVI
            double[][] ndvi = new double[width][height];

            // Recorrer cada pixel y calcular índices
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    double red = raster.getSampleDouble(x, y, 0);
                    double nir = raster.getSampleDouble(x, y, 1);

                    // NDVI
                    ndvi[x][y] = (nir - red) / (nir + red);
                }
            }

            results.put("NDVI", "NDVI calculado con éxito.");

            return results;
        }
    public static class TiffGenerator {

        public void generarTiffDeSalida(double[][] data, String outputPath, String tipoIndicador) {
            try {
                int width = data[0].length;
                int height = data.length;

                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
                WritableRaster raster = bufferedImage.getRaster();

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        raster.setSample(x, y, 0, data[y][x]);
                    }
                }

                File outputfile = new File(outputPath);
                File directorio = outputfile.getParentFile();

                // Verificar y crear el directorio si no existe
                if (!directorio.exists()) {
                    directorio.mkdirs();
                }

                ImageIO.write(bufferedImage, "tif", outputfile);
                System.out.println("Archivo TIFF generado exitosamente para " + tipoIndicador + " en " + outputPath);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error al generar el archivo TIFF de salida: " + e.getMessage());
            }
        }
    }
    }

