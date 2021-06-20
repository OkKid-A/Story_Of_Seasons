package Plantas;

public class Semilla {
    private double precio;
    private String planta;
    private int tiempoCosecha;
    private int semillasPorCasilla;
    private int cantidadAlmacenada;

    public Semilla(double precio, String planta, int tiempoCosecha, int semillasPorCasilla) {
        this.precio = precio;
        this.planta = planta;
        this.tiempoCosecha = tiempoCosecha;
        this.semillasPorCasilla = semillasPorCasilla;
        this.cantidadAlmacenada = 0;
    }

    public int getCantidadAlmacenada() {
        return cantidadAlmacenada;
    }

    public double getPrecio() {
        return precio;
    }

    public String getPlanta() {
        return planta;
    }

    public int getTiempoCosecha() {
        return tiempoCosecha;
    }

    public int getSemillasPorCasilla() {
        return semillasPorCasilla;
    }

    public void setCantidadAlmacenada(int cantidadAlmacenada) {
        this.cantidadAlmacenada = cantidadAlmacenada;
    }

    public String[] datosDeEstaSemilla(){
        String[] datos = new String[]{this.planta,this.precio+"",this.semillasPorCasilla+"",this.cantidadAlmacenada+""};
        return datos;
    }
}
