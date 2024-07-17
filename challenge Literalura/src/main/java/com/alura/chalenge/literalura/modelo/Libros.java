package com.alura.chalenge.literalura.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne()
    private Autores autores;

    private String idiomas;
    private Integer numeroDeDescargas;

    public Libros() {

    }

    public Libros(DatosDeLibro datosDeLibros, Autores autores) {
        this.titulo = datosDeLibros.titulo();
        this.autores = autores;
        this.idiomas = datosDeLibros.idiomas().get(0);
        this.numeroDeDescargas = datosDeLibros.numeroDeDescargas();
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

    public Autores getAutor() {
        return autores;
    }

    public void setAutor(Autores autores) {
        this.autores = autores;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "********** LIBRO **********" + '\n' +
                "Titulo= " + titulo + '\n' +
                "Autor= " + autores.getNombre() + '\n' +
                "Idioma= " + idiomas + '\n' +
                "Número de descargas= " + numeroDeDescargas + '\n' +
                "***************************" + '\n';
    }
}
