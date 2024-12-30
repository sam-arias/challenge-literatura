package com.aluracursos.challenge_literatura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    // Método que obtiene datos desde una URL (API) y devuelve la respuesta como un String
    public String obtenerDatos(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            // Enviar la solicitud HTTP y obtener la respuesta como una cadena
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Obtener el cuerpo de la respuesta (el contenido de la API en formato JSON)
        String json = response.body();
        return json;
    }
}
