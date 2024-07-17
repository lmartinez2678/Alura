package com.alura.forohub.forohub.curso;

import com.alura.forohub.forohub.Modelo.curso;

public record DatosRespuestaCurso(String nombre, String categoria) {

    public DatosRespuestaCurso(curso curso) {
        this(curso.getNombre(), curso.getCategoria());
    }
}
