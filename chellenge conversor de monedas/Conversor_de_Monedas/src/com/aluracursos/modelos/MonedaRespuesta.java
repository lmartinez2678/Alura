package com.aluracursos.modelos;

import java.util.HashMap;

public record MonedaRespuesta(String codigo_base, HashMap<String, Double> rango_conversion) {
}
