package com.aluracursos.modelos;

import java.util.HashMap;

public class Monedas {
    private String tipoMoneda;
    private String cambiarMoneda;
    private HashMap<String, Double> conversionesMoneda;
    private double valorInicial;
    private double conversionInicial;

    public Monedas(MonedaRespuesta datosDeMoneda) {
        this.tipoMoneda = datosDeMoneda.codigo_base();
        this.conversionesMoneda = datosDeMoneda.rango_conversion();
        this.valorInicial = 0;
        this.conversionInicial = 0;
    }

    public double realizaConversion(String cambiarMoneda,double valorInicialAConvertir){
        try {
            this.cambiarMoneda = cambiarMoneda;
            this.valorInicial = valorInicialAConvertir;
            this.conversionInicial = conversionesMoneda.get(cambiarMoneda) * valorInicialAConvertir;
            return this.conversionInicial;
        }catch (Exception e){
            System.out.println("error: " + e);
        }
        return 0;
    }

    public String gettipoMoneda() {
        return tipoMoneda;
    }
    public String getcambiarMoneda() {
        return cambiarMoneda;
    }
    public HashMap<String, Double> getConversionesMoneda() {
        return conversionesMoneda;
    }
    public double getvalorInicial() {
        return valorInicial;
    }
    public double getConversion() {
        return conversionInicial;
    }

    @Override
    public String toString() {
        String valorInicialDeMoneda = String.format("%.2f", this.valorInicial);
        String conversionDeMoneda = String.format("%.2f", this.conversionesMoneda);

        return "El cambio de " + valorInicialDeMoneda + " [" + this.cambiarMoneda + ']' + " a la moneda elegida es  " +
                conversionDeMoneda + " [" + this.cambiarMoneda + "]\n";
    }
}
