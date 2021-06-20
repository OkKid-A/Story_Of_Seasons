package Terreno;

public class BarcoPesquero {
    private int pescaDiaria;
    private int oroProducido;
    private double precio;

    public BarcoPesquero() {
        this.pescaDiaria = 1;
        this.oroProducido = 3;
        this.precio = 50;
    }

    public int getPescaDiaria() {
        return pescaDiaria;
    }

    public int getOroProducido() {
        return oroProducido;
    }

    public double getPrecio() {
        return precio;
    }
}
