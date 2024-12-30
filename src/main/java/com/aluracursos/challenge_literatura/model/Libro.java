package com.aluracursos.challenge_literatura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;

    private String idiomas;
    private Double numeroDeDescargas;

    public Libro() {}

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.autores = datosLibros.autor().stream()
                .map(datosAutor -> {
                    Autor autor = new Autor(datosAutor);
                    autor.setLibro(this);
                    return autor;
                })
                .collect(Collectors.toList());
        this.idiomas = String.join(", ", datosLibros.idiomas());
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        String autorNombre = autores.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining(", "));

        return "-----LIBRO-----\n" +
                "Titulo: " + titulo + '\n' +
                "Autor: " + (autorNombre.isEmpty() ? "Sin autor" : autorNombre) + '\n' +
                "Idiomas: " + (idiomas != null ? String.join(", ", idiomas) : "Sin idiomas") + '\n' +
                "Descargas: " + (numeroDeDescargas != null ? numeroDeDescargas : "Sin datos") + '\n';
    }


}


