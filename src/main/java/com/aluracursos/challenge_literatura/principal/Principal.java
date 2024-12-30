package com.aluracursos.challenge_literatura.principal;

import com.aluracursos.challenge_literatura.model.Autor;
import com.aluracursos.challenge_literatura.model.Datos;
import com.aluracursos.challenge_literatura.model.DatosLibros;
import com.aluracursos.challenge_literatura.model.Libro;
import com.aluracursos.challenge_literatura.repository.AutorRepository;
import com.aluracursos.challenge_literatura.repository.LibrosRepository;
import com.aluracursos.challenge_literatura.service.ConsumoAPI;
import com.aluracursos.challenge_literatura.service.ConvierteDatos;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "http://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    private List<Libro> libros;
    private List<Autor> autores;

    private LibrosRepository librosRepository;
    private AutorRepository autorRepositoy;

    public Principal(LibrosRepository librosRepository, AutorRepository autorRepository) {
        this.librosRepository = librosRepository;
        this.autorRepositoy = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    •••••THE LITERATURA'S BOOKS•••••
                    1 - Buscar libro por titulo 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Mostrar datos del libro por titulo
                    7 - Buscar libro por autor
                    8 - Mostrar datos de un autor
                    
                    0 - Salir
                    """;
            System.out.println("\n" + menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    guardarLibroEnBaseDeDatos();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresPorAnho();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    buscarLibrosPorTitulo();
                    break;
                case 7:
                    listarLibrosPorAutor();
                    break;
                case 8:
                    mostrarDatosDelAutor();
                    break;
                case 0:
                    System.out.println("•••••Cerrando la aplicación•••••\n");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private Datos getDatosLibro() {
        System.out.println("Ingrese el nombre del libro a buscar:");
        var nombreLibro = teclado.nextLine().toLowerCase();
        System.out.println("URL construida: " + URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        System.out.println(json);
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);
        return datos;
    }

    public void guardarLibroEnBaseDeDatos() {
        try {
            Datos datosLibro = getDatosLibro();

            Optional<DatosLibros> librosOptional = datosLibro.resultados().stream().findFirst();

            if (librosOptional.isPresent()) {
                DatosLibros datosLibros = librosOptional.get();
                Libro libro = new Libro(datosLibros);

                // Verificar si el libro ya existe en la base de datos
                Optional<Libro> libroExistente = librosRepository.findByTitulo(libro.getTitulo());

                if (libroExistente.isPresent()) {
                    System.out.println("\nEl libro buscado ya se encuentra en la base de datos");
                } else {
                    // Asignar autores al libro
                    List<Autor> autores = datosLibros.autor().stream()
                            .map(datosAutor -> {
                                Autor autor = new Autor(datosAutor);
                                autor.setLibro(libro);
                                return autor;
                            })
                            .collect(Collectors.toList());

                    libro.setAutores(autores);

                    // Guardar el libro en la base de datos
                    librosRepository.save(libro);
                    System.out.println("\nEl libro ha sido guardado en la base de datos.");
                }
            } else {
                System.out.println("\nNo se encontraron resultados para el libro buscado.");
            }
        } catch (Exception e) {
            System.out.println("\nError al guardar el libro en la base de datos: " + e.getMessage());
        }
    }

    public void listarLibrosRegistrados() {
        libros = librosRepository.mostrarLibrosRegistrados();
        //libros = librosRepository.findAll();

        libros.forEach(System.out::println);
    }

    public void listarAutoresRegistrados() {
        autores = autorRepositoy.listarTodosLosAutores();
        if(!autores.isEmpty()) {
            autores.stream().forEach(System.out::println);
        } else {
            System.out.println("No hay autores registrados en la base de datos");
        }
    }

    public void listarAutoresPorAnho() {
        Integer fechaAutores = null;
        while (fechaAutores == null) {
            try {
                System.out.println("Ingrese el año del autor/es que deseas buscar: ");
                fechaAutores = teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Asegúrate de introducir un número entero.");
                teclado.next();
            }
        }

        List<Autor> autoresPorAnho = autorRepositoy.listarAutoresPorAnho(fechaAutores);
        if (!autoresPorAnho.isEmpty()) {
            autoresPorAnho.stream().forEach(System.out::println);
        } else {
            System.out.println("No se encontraron autores en la fecha especificada.");
        }

    }

    private void listarLibrosPorIdioma() {
        var idiomas = """
                Ingrese el idioma para buscar los libros:
                es -> Epañol
                en -> Inglés
                fr -> Frances
                pt -> Portugues
                
                Digite otro código:""";

        System.out.println(idiomas);
        var idiomaElegido = teclado.nextLine();

        libros = librosRepository.listarLibrosPorIdioma(idiomaElegido);

        if (!libros.isEmpty()) {
            libros.forEach(System.out::println);
        } else {
            System.out.println("No hay libros registrados en ese idioma: '" + idiomaElegido + "'");
        }
    }

    public void buscarLibrosPorTitulo() {
        System.out.println("Ingrese el nombre del libro a buscar");
        var nombreLibro = teclado.nextLine();

        Optional<Libro> libroEncontrado = librosRepository.findByTituloContainsIgnoreCase(nombreLibro);

        if (libroEncontrado.isPresent()) {
            System.out.println(libroEncontrado.get());
        } else {
            System.out.println("El libro '" + nombreLibro + "' no esta registrado en la base de datos");
        }
    }

    public void listarLibrosPorAutor() {
        System.out.println("Ingrese el nombre del autor: ");
        var autorABuscar = teclado.nextLine();

        libros = librosRepository.listarLibrosPorAutor(autorABuscar);

        if (!libros.isEmpty()) {
            libros.forEach(System.out::println);
        } else {
            System.out.println("El autor no se encuentra registrados en la base de datos");
        }
    }

    public void mostrarDatosDelAutor() {
        System.out.println("Ingresa el nombre del Autor:");
        var nombreAutor = teclado.nextLine();

        Optional<Autor> autorBuscado = autorRepositoy.findByNombreIgnoreCase(nombreAutor);

        if (autorBuscado.isPresent()) {
            System.out.println(autorBuscado.get());
        } else {
            System.out.println("El autor '" + nombreAutor + "' no esta registrado en la base de datos");
        }
    }

}


