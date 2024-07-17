package com.alura.chalenge.literalura.repo;

import com.alura.chalenge.literalura.modelo.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoresRepo extends JpaRepository<Autores, Long> {
    Autores findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :fecha AND a.fechaDeFallecimiento >= :fecha")
    List<Autores> autorVivoEnDeterminadoAnio(@Param("fecha") String fecha);
}