package com.aluracursos.modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HistoricoConversiones {
    private ArrayList <Monedas> ListadoDeConversiones;
    private ArrayList <Integer> numeroConversiones;
    private ArrayList <String> fechaHoraEnCurso;
    DateTimeFormatter tipoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static int contador = 0;

    public HistoricoConversiones() {
        this.ListadoDeConversiones = new ArrayList<>();
        this.numeroConversiones = new ArrayList<>();
        this.fechaHoraEnCurso = new ArrayList<>();
    }

    public void agregaConversion(Monedas moneda){
        LocalDateTime fechaYHora = LocalDateTime.now();
        String fechaHoraFormateada = fechaYHora.format(tipoFecha);
        fechaHoraEnCurso.add(fechaHoraFormateada);
        this.ListadoDeConversiones.add(moneda);
        ++contador;
        numeroConversiones.add(contador);
    }

    public void muestraHistoricoConversiones(){
        if (!(ListadoDeConversiones.isEmpty())) {
            for (int i = 0; i < contador; i++) {
                Monedas moneda = ListadoDeConversiones.get(i);
                String valorDeMoneda = String.format("%.2f", moneda.getvalorInicial());
                String conversionFormateada = String.format("%.2f", moneda.getConversion());
                String elemento = fechaHoraEnCurso.get(i) + " Conversión " + numeroConversiones.get(i) + ": " +
                        valorDeMoneda + " [" + moneda.gettipoMoneda() + "] =>> " +
                        conversionFormateada + " [" + moneda.getcambiarMoneda() + "]";
                System.out.println(elemento);
            }
            System.out.println();
        }else {
            System.out.println("No hay registros de conversiones hasta ahora\n");
        }
    }
}
