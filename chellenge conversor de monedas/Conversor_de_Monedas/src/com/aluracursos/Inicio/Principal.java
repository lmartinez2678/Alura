package com.aluracursos.Inicio;


import com.aluracursos.modelos.HistoricoConversiones;
import com.aluracursos.modelos.Monedas;
import com.aluracursos.modelos.RevisarConversiones;
import com.aluracursos.modelos.TipoDeConversiones;

import java.util.HashMap;
import java.util.Scanner;

public class Principal {
    static String llaveApi = "3ef26dd54c274ba4290e8119";
    static HashMap<Integer, String[]> tipoConversiones = new TipoDeConversiones().getconversionsMoneda();
    static RevisarConversiones consulta = new RevisarConversiones();
    static String[] valores;
    static String monedaACambiar = "";
    static String cambioDeMoneda = "";
    static HistoricoConversiones historialConversiones = new HistoricoConversiones();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opciones = 0; //Elección del usuario
        double valorCambio = 0; 
        System.out.println("SConversion de Monedas --- Alura Cursos (Challenge)");
        System.out.println("Selecciones dentro de las opciones la moneda a convertir por favor .....");
        while (true) {
            try {
                menu(false);
                opciones = scanner.nextInt();
                if (opciones < 1 || opciones >=10) {
                    break;
                }
                if(opciones == 9){
                    historialConversiones.muestraHistoricoConversiones();
                    continue;
                }

                valores = tipoConversiones.get(opciones);
                monedaACambiar = valores[0];
                cambioDeMoneda = valores[1];

                var json = new RevisarConversiones().mirarConversion(llaveApi,monedaACambiar);
                Monedas monedas = new Monedas(json);
                System.out.println("Digite la cantidad a convertir: ");
                valorCambio = scanner.nextDouble();
                monedas.realizaConversion(cambioDeMoneda,valorCambio);
                historialConversiones.agregaConversion(monedas);
                System.out.println(monedas);
            } catch (Exception e) {
                System.out.println("Mal Mal Mal, por favor ingrese un número del menú correcto");
                scanner.nextLine();
            }
        }
        menu(true);
    }

    public static void menu(boolean finalizar){
        if (finalizar){
            System.out.print("Saliendo del Sistema........");
        }else{
            System.out.print(
                    """
                            1.- De Dollar Americano a Peso Argentino.
                            2.- De Peso Argentino a Dollar Americano.
                            3.- De Dollar Americano a Real Brasileño.
                            4.- De Real Brasileño a Dollar Americano.
                            5.- De Dollar Americano a Peso Colombiano.
                            6.- De Peso Colombiano a Dollar Americano.
                            7.- De Dollar Americano a Peso Mexicano.
                            8.- De Peso Mexicano a Dollar Americano.
                            9.- Viendo Nuestras COnversiones Históricas
                            10.- Salir
                            Elige una del 1 al 10: 
                            
                            
                            """
            );
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        }
    }
}
