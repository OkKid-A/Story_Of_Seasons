package Granero;

import Animales.Animal;
import Animales.AnimalHerviboro;
import Animales.AnimalOmnivoro;
import Animales.Cria;
import Plantas.Planta;
import Plantas.PlantaFruta;
import Plantas.PlantaGrano;
import Plantas.Semilla;
import Productos.Producto;

import java.util.List;

public class Repertorio {
    private int noProductos;
    private int noPlantas;
    private int noAnimales;
    private listaInventario<Animal> listaAnimal;
    private listaInventario<Producto> listaProductos;
    private listaInventario<Planta> listaPlantas;

    public Repertorio() {
        this.noProductos = 7;
        this.noPlantas = 2;
        this.noAnimales = 2;
        registrarProductosPredet();
        registrarAnimalesPredet();
        registrarPlantasPrede();
    }

    public Producto[] separarProductosAnimal(String especie) {
        int cantidadProductos = 0;
        int numProducto = 0;
        for (int k = 0; k < listaProductos.getNumElementosPresentes(); k++) {
            if (this.listaProductos.getObjetoDeLista(k).getProductor().compareTo(especie) == 0) {
                cantidadProductos++;
            }
        }
        Producto[] productosPropios = new Producto[cantidadProductos];
        for (int k = 0; k < listaProductos.getNumElementosPresentes(); k++) {
            if (this.listaProductos.getObjetoDeLista(k).getProductor().compareTo(especie) == 0) {
                productosPropios[numProducto] = listaProductos.getObjetoDeLista(k);
                numProducto++;
            }
        }
        return productosPropios;
    }

    public void registrarAnimalesPredet() {
        this.listaAnimal = new listaInventario<Animal>(noAnimales);
        listaAnimal.anadirObjetoALista(new AnimalHerviboro(2, "Vaca", separarProductosAnimal("Vaca"),  new Cria("Ternero",50,60),""));
        listaAnimal.getObjetoDeLista(0).setIconoAnimal("450_1000.jpg");
        listaAnimal.anadirObjetoALista(new AnimalOmnivoro(0.5, "Gallina", separarProductosAnimal("Gallina"), new Cria("Pollito",10,10),""));
        listaAnimal.getObjetoDeLista(1).setIconoAnimal("gallina.jpg");
    }

    public void registrarProductosPredet() {
        this.listaProductos = new listaInventario<Producto>(this.noProductos);
        listaProductos.anadirObjetoALista(new Producto(10, 0.25, 0, "Cuero", "Vaca", true,-15));
        listaProductos.anadirObjetoALista(new Producto(5, 0.75, 0, "Carne de Res", "Vaca", true,20));
        listaProductos.anadirObjetoALista(new Producto(2, 1, 0, "Leche", "Vaca", false,5));
        listaProductos.anadirObjetoALista(new Producto(3, 1, 0, "Carne de Pollo", "Gallina", true,10));
        listaProductos.anadirObjetoALista(new Producto(1, 1, 0, "Huevos", "Gallina", false,3));
        listaProductos.anadirObjetoALista(new Producto(15, 1, 0, "Granos de Maiz", "Maiz", true,30));
        listaProductos.anadirObjetoALista(new Producto(5, 1, 0, "Manzanas", "Manzanero", false,10));
        listaProductos.anadirObjetoALista(new Producto(3,1,0,"Carne de Pescado","Pez",true,10));
    }

    public void registrarPlantasPrede() {
        this.listaPlantas = new listaInventario<Planta>(noPlantas);
        listaPlantas.anadirObjetoALista(new PlantaGrano("Maiz", 60, new Semilla(5, "Maiz", 10,8),listaProductos.getObjetoDeLista(5)));
        listaPlantas.anadirObjetoALista(new PlantaFruta("Manzanero", 300, new Semilla(20, "Manzana", 100,5),listaProductos.getObjetoDeLista(6)));
    }

    public void registrarProductosPorAnimal() {
        for (int k = 0; k < noAnimales; k++) {
            separarProductosAnimal(listaAnimal.getObjetoDeLista(k).getEspecie());
        }
    }

    public listaInventario<Producto> getListaProductos() {
        return listaProductos;
    }

    public listaInventario<Animal> getListaAnimal() {
        return listaAnimal;
    }

    public listaInventario<Planta> getListaPlantas() {
        return listaPlantas;
    }

}