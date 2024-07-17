package com.aluracursos.modelos;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

    public class RevisarConversiones {
        public MonedaRespuesta mirarConversion(String key, String tipoDeMoneda) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/" + key + "/latest/" + tipoDeMoneda))
                    .build();

            try {
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                return new Gson().fromJson(response.body(), MonedaRespuesta.class);
            } catch (Exception e) {
                throw new RuntimeException("El tipo de Moneda no se reconoce");
            }
        }
    }
