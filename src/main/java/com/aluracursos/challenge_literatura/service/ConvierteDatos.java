package com.aluracursos.challenge_literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Clase que implementa la interfaz IConvierteDatos
public class ConvierteDatos implements IConvierteDatos {

    // Crear una instancia de ObjectMapper para trabajar con JSON
    private ObjectMapper objectMapper = new ObjectMapper();

    // Implementación del método obtenerDatos
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            // Convertir el JSON en un objeto de tipo T usando ObjectMapper
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            // Si ocurre un error durante la conversión, lanzamos una RuntimeException
            throw new RuntimeException(e);
        }
    }
}