package com.alura.chalenge.literalura.modelo;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")

public class Autores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String fechaDeNacimiento;
    private String fechaDeFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> libros;

    public Autores() {
    }

    public Autores(DatosDeAutor datosDeAutor) {
        this.nombre = datosDeAutor.nombre();
        this.fechaDeNacimiento = datosDeAutor.fechaDeNacimiento();
        this.fechaDeFallecimiento = datosDeAutor.fechaDeFallecimiento();
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

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(String fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public List<Libros> getLibro() {
        return libros;
    }

    public void setLibro(List<Libros> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "********** AUTOR ***********" + '\n' +
                "Nombre= " + nombre + '\n' +
                "Fecha De Nacimiento= " + fechaDeNacimiento + '\n' +
                "Fecha De Fallecimiento= " + fechaDeFallecimiento + '\n' +
                "Libros= " + libros.stream().map(Libros::getTitulo).collect(Collectors.toUnmodifiableList()) + '\n' +
                "****************************" + '\n';
    }
}
