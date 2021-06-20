package Animales;

import Productos.Producto;

import javax.swing.*;
import java.io.IOException;

public class Animal {
    private double tamano;
    private int etapaVejez;
    private int cantidadProductos;
    private String especie;
    private Producto[] produccion;
    private Cria cria;
    private ImageIcon iconoAnimal;
    private String direccionImagen;

    public Animal(double tamano, String especie, Producto[] produccion, Cria cria, String direccionImagen) {
        this.direccionImagen = direccionImagen;
        this.tamano = tamano;
        this.especie = especie;
        this.produccion = produccion;
        this.cria = cria;
        this.cantidadProductos = produccion.length;
        convertirIcono();
    }

    public String getEspecie() {
        return especie;
    }

    public Cria getCria() {
        return this.cria;
    }

    public void setIconoAnimal(String ubicacion) {
        try {
            this.iconoAnimal = new ImageIcon(getClass().getClassLoader().getResourceAsStream(ubicacion).readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageIcon getIconoAnimal() {
        return iconoAnimal;
    }

    private void convertirIcono() {
        try {
            this.iconoAnimal = new ImageIcon(direccionImagen);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Producto[] getProduccion() {
        return produccion;
    }

    public double getTamano() {
        return tamano;
    }


}
