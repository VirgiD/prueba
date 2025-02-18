document.addEventListener('DOMContentLoaded', function() {
    var map = L.map('map').setView([-34.6037, -58.3816], 5);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors'
    }).addTo(map);

    var drawnItems = new L.FeatureGroup();
    map.addLayer(drawnItems);

    var drawControl = new L.Control.Draw({
        edit: {
            featureGroup: drawnItems
        },
        draw: {
            polygon: true,
            rectangle: false,
            circle: false,
            polyline: false,
            marker: false,
            circlemarker: false
        }
    });
    map.addControl(drawControl);

    map.on(L.Draw.Event.CREATED, function(event) {
        var layer = event.layer;
        drawnItems.addLayer(layer);

        var coordinates = layer.getLatLngs()[0].map(function(latlng) {
            return { lat: latlng.lat, lon: latlng.lng };
        });

        console.log("Coordenadas del polígono:", coordinates);

        window.selectedCoords = coordinates;
    });

    document.getElementById("guardarZona").addEventListener("click", function() {
        var tiffFile = document.getElementById("tiffFile").files[0];

        if (!window.selectedCoords || !tiffFile) {
            alert("Selecciona una zona en el mapa y sube un archivo .tif.");
            return;
        }

        let datosCultivo = {
            lat: window.selectedCoords[0].lat,
            lon: window.selectedCoords[0].lon,
            ndvi: (Math.random() * 1).toFixed(2),
            humedad: Math.floor(Math.random() * 100),
            temperatura: Math.floor(Math.random() * 40),
            phSuelo: (Math.random() * (8 - 5) + 5).toFixed(2),
            nutrientes: JSON.stringify({ nitrogeno: Math.floor(Math.random() * 100), fosforo: Math.floor(Math.random() * 100), potasio: Math.floor(Math.random() * 100) })
        };

        console.log("Datos a enviar:", datosCultivo);

        var formData = new FormData();
        formData.append("file", tiffFile);
        formData.append("datosCultivo", new Blob([JSON.stringify(datosCultivo)], { type: "application/json" }));

        fetch("http://localhost:8080/api/cultivos/seleccionar-zona", {
            method: "POST",
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            alert("Zona guardada con éxito y NDVI calculado: " + JSON.stringify(data));
        })
        .catch(error => console.error("Error al guardar zona:", error));
    });
});

