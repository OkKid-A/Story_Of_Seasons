package Productos;

import Animales.Animal;

public class Producto {
    private double precio;
    private double porcentajeProduccion;
    private int cantidadExistente;
    private int hpRecuperada;
    private String etiqueta;
    private String productor;
    private boolean productoDestaze;

    public Producto(double precio, double porcentajeProduccion, int cantidadExistente, String etiqueta, String productor, boolean productoDestaze,int hpRecuperada) {
        this.precio = precio;
        this.porcentajeProduccion = porcentajeProduccion;
        this.cantidadExistente = cantidadExistente;
        this.etiqueta = etiqueta;
        this.productor = productor;
        this.productoDestaze = productoDestaze;
        this.hpRecuperada = hpRecuperada;
    }

    public void setCantidadExistente(int cantidadExistente) {
        this.cantidadExistente = cantidadExistente;
    }

    public int getCantidadExistente() {
        return cantidadExistente;
    }

    public String getProductor() {
        return productor;
    }

    public double getPrecio() {
        return precio;
    }

    public double getPorcentajeProduccion() {
        return porcentajeProduccion;
    }

    public int getHpRecuperada() {
        return hpRecuperada;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public boolean isProductoDestaze() {
        return productoDestaze;
    }
}
