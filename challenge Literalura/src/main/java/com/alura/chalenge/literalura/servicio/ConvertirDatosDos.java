package com.alura.chalenge.literalura.servicio;

public interface ConvertirDatosDos {
    <T> T obtenerDatos(String json, Class<T> clase);
}