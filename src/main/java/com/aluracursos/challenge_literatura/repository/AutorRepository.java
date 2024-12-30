package com.aluracursos.challenge_literatura.repository;

import com.aluracursos.challenge_literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a")
    List<Autor> listarTodosLosAutores();

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anhoBuscado AND a.fechaDeFallecimiento >= :anhoBuscado")
    List<Autor> listarAutoresPorAnho(Integer anhoBuscado);


}
