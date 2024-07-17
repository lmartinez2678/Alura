package com.alura.forohub.forohub.Respuesta;

import com.ForoAlura.ForoAlura.Modelo.Respuesta;

public record DatosListadoRespuesta(Long id, String mensaje, String topico, String autor) {

    public DatosListadoRespuesta(Respuesta respuesta) {
        this(respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().gettitulo(),
                respuesta.getAutor().getNombre());
    }
}