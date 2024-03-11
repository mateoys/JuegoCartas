/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegocartas2;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Mateo
 */
public class Carta implements Comparable<Carta> {
     private int indice;
    private static Random r=new Random();

    public Carta() {
        indice = r.nextInt(52)+1;
    }
    public Carta(int indice){
        this.indice=indice;
    }
   
    public int getIndice(){
        return indice;
    }

    public Pinta getPinta() {
        if (indice <= 13) {
            return Pinta.TREBOL;
        } else if (indice <= 26) {
            return Pinta.PICA;
        } else if (indice <= 39) {
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public NombreCarta getNombre() {
        int numero = indice % 13;
        if (numero == 0) {
            numero = 13;
        }
        return NombreCarta.values()[numero - 1];
    }

    public void mostrar(JPanel pnl, int x, int y) {
        String nombreImagen = "/imagenes/carta" + String.valueOf(indice) + ".jpg";

        ImageIcon imagen = new ImageIcon(getClass().getResource(nombreImagen));
        JLabel lbl = new JLabel(imagen);
        lbl.setBounds(x, y, imagen.getIconWidth(), imagen.getIconHeight());

        pnl.add(lbl);

    }
     public int compareTo(Carta otraCarta) {
        return Integer.compare(this.indice, otraCarta.indice);
    }
    
    
}
