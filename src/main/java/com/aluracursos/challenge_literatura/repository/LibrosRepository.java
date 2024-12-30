package com.aluracursos.challenge_literatura.repository;

import com.aluracursos.challenge_literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l")
    List<Libro> mostrarLibrosRegistrados();

    Optional<Libro> findByTituloContainsIgnoreCase(String nombreLibro);

    @Query("SELECT l FROM Libro l WHERE l.idiomas LIKE %:idiomaElegido%")
    List<Libro> listarLibrosPorIdioma(@Param("idiomaElegido") String idiomaElegido);

    @Query("SELECT l FROM Libro l JOIN l.autores a WHERE a.nombre LIKE %:autorABuscar%")
    List<Libro> listarLibrosPorAutor(@Param("autorABuscar") String autorABuscar);

    Optional<Libro> findByTitulo(String titulo);
}

