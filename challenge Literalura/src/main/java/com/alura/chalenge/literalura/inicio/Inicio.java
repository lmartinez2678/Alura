package com.alura.chalenge.literalura.inicio;

import com.alura.chalenge.literalura.modelo.*;
import com.alura.chalenge.literalura.modelo.DatosDeLibro;
import com.alura.chalenge.literalura.repo.AutoresRepo;
import com.alura.chalenge.literalura.repo.LibroRepo;
import com.alura.chalenge.literalura.servicio.ConsumirApi;
import com.alura.chalenge.literalura.servicio.ConvertirDatos;

import java.util.*;

public class Inicio {
    private static final String URL_BASE = "https://gutendex.com/books/";
    final private ConsumirApi ConsumirApi = new ConsumirApi();
    final private ConvertirDatos conversor = new ConvertirDatos();
    final private Scanner teclado = new Scanner(System.in);
    private List<Autores> autores;
    private List<Libros> libros;
    final private LibroRepo libroRepo;
    final private AutoresRepo autoresRepo;

    public Inicio(LibroRepo libroRepo, AutoresRepo autoresRepo) {
        this.libroRepo = libroRepo;
        this.autoresRepo = autoresRepo;
    }

    public void mostrarMenu() {

        var puntos = -10;
        try {
            while (puntos != 0) {
                var menu = """
                        Esta es tu biblioteca digital, del menú escoge la opción que buscas.
                        *******************************************************************
                                            
                        1) Buscar libro por TITULO
                        2) Listar LIBROS registrados
                        3) Listar AUTORES registrados
                        4) Listar AUTORES vivos en un determinado año
                        5) Listar LIBROS por IDIOMA
                                            
                        0) Salir
                                            
                        Por favor ingrese la opción deseada:
                        *****************************************************************
                        """;
                System.out.println(menu);
                puntos = teclado.nextInt();
                teclado.nextLine();

                switch (puntos) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listaDeLibrosRegistrados();
                        break;
                    case 3:
                        listaDeAutoresRegistrados();
                        break;
                    case 4:
                        listaDeAutoresVivosEnDeterminadoAnio();
                        break;
                    case 5:
                        listaDeLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación. Muchas gracias por utilizarla.");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                        break;

                }
            }
        } catch (InputMismatchException e) {
            System.out.println("ERROR! Por favor vuelva a ejecutar el programa e ingrese un número de opción válido.");
        }
    }

    private Datos buscarDatosDeLibros() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var libro = teclado.nextLine();
        var json = ConsumirApi.obtenerDatos(URL_BASE + "?search=" + libro.replace(" ", "+"));
        return conversor.obtenerDatos(json, Datos.class);
    }

    private Libros agregarLibroBD(DatosDeLibro datosDeLibros, Autores autores) {
        Libros libro = new Libros(datosDeLibros, autores);
        return libroRepo.save(libro);

    }

    private void buscarLibroPorTitulo() {
        Datos datos = buscarDatosDeLibros();

        if (!datos.resultados().isEmpty()) {
            DatosDeLibro datosDeLibros = datos.resultados().get(0);
            DatosDeAutor datosDeAutor = datosDeLibros.autor().get(0);
            Libros libroBuscado = LibroRepo.findByTituloIgnoreCase(datosDeLibros.titulo());

            if (libroBuscado != null) {
                System.out.println(libroBuscado);
                System.out.println("El libro ya existe en la base de datos. No se puede volver a registrar.");

            } else {
                Autores autorBuscado = autoresRepo.findByNombreIgnoreCase(datosDeAutor.nombre());

                if (autorBuscado == null) {
                    Autores autores = new Autores(datosDeAutor);
                    autoresRepo.save(autores);
                    Libros libro = agregarLibroBD(datosDeLibros, autores);
                    System.out.println(libro);
                } else {
                    Libros libros = agregarLibroBD(datosDeLibros, autorBuscado);
                    System.out.println(libros);
                }
            }
        } else {
            System.out.println("El libro buscado no se encuentra. Pruebe con otro.");
        }
    }

    private void listaDeLibrosRegistrados() {
        libros = libroRepo.findAll();
        if (!libros.isEmpty()) {
            libros.forEach(System.out::println);
        } else {
            System.out.println("No hay ningún Libro registrado.");
        }
    }

    private void listaDeAutoresRegistrados() {
        autores = autoresRepo.findAll();
        if (!autores.isEmpty()) {
            autores.forEach(System.out::println);
        } else {
            System.out.println("No hay ningún Autor registrado");
        }
    }

    private void listaDeAutoresVivosEnDeterminadoAnio() {
        System.out.println("Ingrese el año en el que quiere saber los autores vivos: ");
        String fecha = teclado.nextLine();
        try {
            List<Autores> autoresVivosEnCiertaFecha = autoresRepo.autorVivoEnDeterminadoAnio(fecha);
            if (!autoresVivosEnCiertaFecha.isEmpty()) {
                autoresVivosEnCiertaFecha.forEach(System.out::println);
            } else {
                System.out.println("No existen Autores vivos en esos años.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void listaDeLibrosPorIdioma() {
        System.out.println("""
                1) Español (ES)
                2) Inglés (EN)
                3) Francés (FR)
                4) Portugués (PT)
                5) Regresar al menú principal
                                
                Por favor, ingrese el número de opción para elegir el idioma de los libros a consultar:
                """);
        int opcion;
        opcion = teclado.nextInt();
        teclado.nextLine();
        switch (opcion) {
            case 1:
                libros = LibroRepo.findByIdiomasContaining("es");
                if (!libros.isEmpty()) {
                    libros.forEach(System.out::println);
                } else {
                    System.out.println("No hay ningún libro registrado en Español.");
                }
                break;
            case 2:
                libros = LibroRepo.findByIdiomasContaining("en");
                if (!libros.isEmpty()) {
                    libros.forEach(System.out::println);
                } else {
                    System.out.println("No hay ningún libro registrado en Inglés.");
                }
                break;
            case 3:
                libros = LibroRepo.findByIdiomasContaining("fr");
                if (!libros.isEmpty()) {
                    libros.forEach(System.out::println);
                } else {
                    System.out.println("No hay ningún libro registrado en Francés.");
                }
                break;
            case 4:
                libros = LibroRepo.findByIdiomasContaining("pt");
                if (!libros.isEmpty()) {
                    libros.forEach(System.out::println);
                } else {
                    System.out.println("No hay ningún libro registrado en Portugués.");
                }
                break;
            case 5:
                mostrarMenu();
                break;
            default:
                System.out.println("La opción seleccionada no es válida.");
        }
    }
}