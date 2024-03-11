/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegocartas2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author Mateo
 */
public class Jugador {
    public int TOTAL_CARTAS = 10;

    private Carta[] cartas;

    public void repartir() {
        cartas = new Carta[TOTAL_CARTAS];
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = new Carta();
        }
        
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        Arrays.sort(cartas);
        
        for (int i = 0; i < cartas.length; i++) {
            cartas[i].mostrar(pnl, 5 + i * 40, 5);
        }
        
        pnl.repaint();
    }

    public String getGrupos() {
        String mensaje = "No hay grupos";

        int[] contadores = new int[NombreCarta.values().length];

        for (int i = 0; i < cartas.length; i++) {
            contadores[cartas[i].getNombre().ordinal()]++;
        }

        int totalGrupos = 0;
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                totalGrupos++;
            }
        }
        if (totalGrupos > 0) {
            mensaje = "Los grupos encontrados fueron:\n";
            for (int i = 0; i < contadores.length; i++) {
                if (contadores[i] >= 2) {
                    mensaje += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
                }
            }
        }
        return mensaje;
    }
    
   public String Escalera() {
    Arrays.sort(cartas); // ordenar el vector en orden ascendente 
    int cont = 1;
    Pinta tipoEscalera = null;
    String mensaje = "";
    
    for (int i = 1; i < cartas.length; i++) {
        if ((cartas[i].getIndice() == cartas[i - 1].getIndice() + 1) && cartas[i].getPinta() == cartas[i - 1].getPinta()) {
            cont++;
            tipoEscalera = cartas[i].getPinta();
        } else {
            if (cont > 1) {
                mensaje += String.format("Las escalera encontradas fueron:\n %s de %s\n", Grupo.values()[cont], tipoEscalera);
            }
            cont = 1;
        }
    }
    
    if (cont > 1) {
        mensaje += String.format("Las escalera encontradas fueron:\n muj%s de %s\n", Grupo.values()[cont], tipoEscalera);
    }

    if (mensaje.isEmpty()) {
        mensaje = "No hay escaleras";
    }

    return mensaje;
}


    

public String Puntaje() {
    int puntajeTotal = 0; // Variable para almacenar el puntaje total de las cartas individuales
    boolean[] cartasAgrupadas = new boolean[TOTAL_CARTAS]; // Array para marcar las cartas agrupadas

    // Identificar cartas agrupadas
    for (int i = 0; i < cartas.length; i++) {
        Carta cartaActual = cartas[i];
        NombreCarta nombreCarta = cartaActual.getNombre();

        // Verificar si la carta actual está en un grupo
        boolean esGrupo = false;
        for (int j = i + 1; j < cartas.length; j++) {
            if (!cartasAgrupadas[j] && cartas[j].getNombre() == nombreCarta) {
                cartasAgrupadas[j] = true;
                esGrupo = true;
            }
        }

        // Marcar la carta actual como agrupada si está en un grupo
        if (esGrupo) {
            cartasAgrupadas[i] = true;
        }
    }

    // Identificar cartas en escaleras
    for (int i = 1; i < cartas.length; i++) {
        if (cartas[i].getIndice() == cartas[i - 1].getIndice() + 1 && cartas[i].getPinta() == cartas[i - 1].getPinta()) {
            cartasAgrupadas[i] = true; // Marcar cartas en escalera como agrupadas
            cartasAgrupadas[i - 1] = true;
        }
    }

    // Calcular puntaje de las cartas individuales
    for (int i = 0; i < cartas.length; i++) {
        if (!cartasAgrupadas[i]) {
            int puntajeCarta;
            NombreCarta nombreCarta = cartas[i].getNombre();
            switch (nombreCarta) {
                case AS:
                case JACK:
                case QUEEN:
                case KING:
                    puntajeCarta = 10; // A, J, Q, K valen 10 puntos
                    break;
                default:
                    puntajeCarta = nombreCarta.ordinal() + 1; // Valor numérico de la carta
                    break;
            }
            puntajeTotal += puntajeCarta; // Sumar el puntaje de la carta individual al puntaje total
        }
    }

    // Crear mensaje de salida
    StringBuilder mensaje = new StringBuilder("El puntaje total de las cartas individuales es: ");
    mensaje.append(puntajeTotal);
    return mensaje.toString();
}


}
   

