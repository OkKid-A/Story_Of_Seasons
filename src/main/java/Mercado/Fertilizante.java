package Mercado;

public class Fertilizante {
    private double precio;
    private int fertilidadAumentada;
    private String nomreFertilizante;

    public Fertilizante(int precio, int fertilidadAumentada, String nomreFertilizante) {
        this.precio = precio;
        this.fertilidadAumentada = fertilidadAumentada;
        this.nomreFertilizante = nomreFertilizante;
    }

    public double getPrecio() {
        return precio;
    }

    public int getFertilidadAumentada() {
        return fertilidadAumentada;
    }

    public String getNombreFertilizante() {
        return nomreFertilizante;
    }
}
