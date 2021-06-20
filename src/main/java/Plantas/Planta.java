package Plantas;

import Productos.Producto;

public abstract class Planta {
    private String especie;
    private int tiempoMarchitar;
    private Semilla semilla;
    private Producto produccion;

    public Planta(String especie, int tiempoMarchitar, Semilla semilla, Producto produccion) {
        this.especie = especie;
        this.tiempoMarchitar = tiempoMarchitar;
        this.semilla = semilla;
        this.produccion = produccion;
    }

    public abstract void correrPlanta();

    public String getEspecie() {
        return especie;
    }

    public int getTiempoMarchitar() {
        return tiempoMarchitar;
    }

    public Semilla getSemilla() {
        return semilla;
    }

    public Producto getProduccion() {
        return produccion;
    }
}
