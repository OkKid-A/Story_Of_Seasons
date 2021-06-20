package Granero;

import java.util.Objects;

public class listaInventario<K> {
    private K k;
    private int tamano;
    private int numElementosPresentes;
    private K[] lista;

    public listaInventario(int tamano) {
        this.lista = (K[]) new Object[tamano];
        this.numElementosPresentes = 0;
        this.tamano = tamano;
    }

    public void anadirObjetoALista(K k) {
        if (numElementosPresentes == tamano) {
            generarArrayExpandido(k);
        } else {
            lista[numElementosPresentes] = k;
        }
        numElementosPresentes++;
    }

    public K getObjetoDeLista(int index) {
        return lista[index];
    }

    public void generarArrayExpandido(K l) {
        K[] temp = (K[]) new Object[tamano + 1];
        for (int k = 0; k < tamano; k++) {
            temp[k] = lista[k];
        }
        temp[lista.length] = l;
        this.lista = temp;
        tamano++;
    }

    public int getTamano() {
        return tamano;
    }

    public int getNumElementosPresentes() {
        return numElementosPresentes;
    }

    public K[] getLista() {
        return lista;
    }
}
