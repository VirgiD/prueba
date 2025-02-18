package com.ecoagsusprototipo.prueba;

import com.ecoagsusprototipo.prueba.service.IndicadorVegetacionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.io.File;

@SpringBootApplication
public class
PruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
		ejecutarGeneracionTiff();
	}

	private static void ejecutarGeneracionTiff() {
		// Asegúrate de asignar una ruta válida a esta variable
		String generarTiffDeSalida = "ruta/a/tu/archivo/ndvi.tif"; // Asegúrate de que la ruta es válida
		File archivo = new File(generarTiffDeSalida);
		File directorio = archivo.getParentFile();

		if (!directorio.exists()) {
			directorio.mkdirs();
		}

		IndicadorVegetacionService.TiffGenerator tiffGenerator = new IndicadorVegetacionService.TiffGenerator();

		// Ejemplo de datos NDVI
		double[][] ndviData = {
				{0.1, 0.2, 0.3},
				{0.4, 0.5, 0.6},
				{0.7, 0.8, 0.9}
		};

		tiffGenerator.generarTiffDeSalida(ndviData, generarTiffDeSalida, "NDVI");
	}
	String generarTiffDeSalida = System.getProperty("java.io.tmpdir") + "/ndvi.tif";




}


