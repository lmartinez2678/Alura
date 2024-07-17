package com.aluracursos.modelos;

import java.util.HashMap;

public class TipoDeConversiones {
    private HashMap<Integer, String[]> conversionMoneda = new HashMap<>();

    public TipoDeConversiones() {
        this.conversionMoneda.put(1, new String[]{"USD","ARS"});
        this.conversionMoneda.put(2, new String[]{"ARS","USD"});
        this.conversionMoneda.put(3, new String[]{"USD","BRL"});
        this.conversionMoneda.put(4, new String[]{"BRL","USD"});
        this.conversionMoneda.put(5, new String[]{"USD","COP"});
        this.conversionMoneda.put(6, new String[]{"COP","USD"});
        this.conversionMoneda.put(7, new String[]{"USD","MXN"});
        this.conversionMoneda.put(8, new String[]{"MXN","USD"});
    }


    public HashMap<Integer, String[]> getconversionsMoneda() {
        return conversionMoneda;
    }
}
