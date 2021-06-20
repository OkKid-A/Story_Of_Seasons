package Limpieza;

import Terreno.Sembradio;
import Terreno.Terreno;
import Usuario.Usuario;

import javax.swing.*;
import java.util.Random;

public class ColaCosechas {
    private Sembradio[] arrayCola;
    private Sembradio top;
    private int tamanoCola;
    private Usuario usuario;
    private int elementosPresentes;
    private Terreno[] mapa;

    public ColaCosechas(Terreno[] mapa) {
        this.tamanoCola = 0;
        this.elementosPresentes = 0;
        this.mapa = mapa;
        initArrayCola();
    }

    private void initArrayCola() {
        tamanoCola = 0;
        arrayCola = new Sembradio[tamanoCola];
    }

    public void anadir(Sembradio siembra) {
        if (elementosPresentes < tamanoCola) {
            arrayCola[elementosPresentes] = siembra;
        } else {
            Sembradio[] temp = new Sembradio[tamanoCola + 1];
            for (int l = 0; l < tamanoCola; l++) {
                temp[l] = arrayCola[l];
            }
            temp[tamanoCola] = siembra;
            arrayCola = temp;
            tamanoCola++;
        }
        elementosPresentes++;
        top = arrayCola[0];
        System.out.println(elementosPresentes + "" + "");
    }

    public void atender(Usuario usuario, JPanel jPanel) {
        if (elementosPresentes > 0) {
            tamanoCola--;
            Cosechar(usuario, arrayCola[0], jPanel);
            Sembradio[] temp = new Sembradio[tamanoCola];
            for (int l = 0; l < tamanoCola; l++) {
                temp[l] = arrayCola[l + 1];
            }
            arrayCola = temp;
            elementosPresentes--;
            System.out.println(elementosPresentes + "");
        }
    }

    public int getElementosPresentes() {
        return elementosPresentes;
    }

    private void Cosechar(Usuario usuario, Sembradio siembra, JPanel jPanel) {
        Random random = new Random();
        int cosecha = ((siembra.getFertilidad() * (random.nextInt(4) + 1)));
        siembra.getPlanta().getProduccion().setCantidadExistente(cosecha + siembra.getPlanta().getProduccion().getCantidadExistente());
        JOptionPane.showMessageDialog(jPanel, "Cosechaste " + cosecha + " " + siembra.getPlanta().getProduccion().getEtiqueta());
        siembra.getThread().stop();
        usuario.getReportes().agregarNuticionTotal(cosecha * siembra.getPlanta().getProduccion().getHpRecuperada());
    }

    public Sembradio getTop() {
        if (arrayCola.length > 0) {
            top = arrayCola[0];
            return top;
        } else {
            return null;
        }
    }
}

