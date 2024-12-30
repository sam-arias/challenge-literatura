package com.aluracursos.challenge_literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;

    @ManyToOne()
    @JoinColumn(name = "libro_id")
    private Libro libro;


    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = (datosAutor.fechaDeNacimiento() != null) ? Integer.valueOf(datosAutor.fechaDeNacimiento()) : null;
        this.fechaDeFallecimiento = (datosAutor.fechaDeFallecimiento() != null) ? Integer.valueOf(datosAutor.fechaDeFallecimiento()) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "-----AUTOR-----\n" +
                "Nombre: " + nombre + '\n' +
                "Fecha de nacimiento: " + fechaDeNacimiento + '\n' +
                "Fecha de Fallecimiento: " + fechaDeFallecimiento + '\n' +
                "Libro: " + (libro != null ? libro.getTitulo() : "Sin libro") + '\n';
    }


}
