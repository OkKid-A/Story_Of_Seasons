package Animales;

import Componentes.TiempoProduccionLabel;
import Componentes.animalesEnParcelaPanel;
import InterfazGráfica.VentanaParcela;
import Mercado.Mercado;
import Terreno.Parcela;
import Usuario.Usuario;

import javax.swing.*;

import java.util.Random;

import static Mercado.Mercado.getjTable;
import static Productos.NuevoProducto.traducirDestaceBoolean;

public class AnimalVivo {
    private Animal animal;
    private int hPAnimal;
    private JPanel animarHPAnimal;
    private boolean produccionLista;
    private boolean animalMuerto;
    private TiempoProduccionLabel tiempoProduccionLabel;
    private int etapaAnimal;
    private JLabel edadLabel;

    public AnimalVivo(Animal animal) {
        this.hPAnimal = 100;
        this.animal = animal;
        etapaAnimal = 0;
        this.edadLabel = new JLabel();
        animalMuerto = false;
        tiempoProduccionLabel = new TiempoProduccionLabel();
    }

    public Animal getAnimal() {
        return animal;
    }

    public int gethPAnimal() {
        return hPAnimal;
    }

    public void sethPAnimal(int hPAnimal) {
        this.hPAnimal = this.hPAnimal + hPAnimal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public boolean isProduccionLista() {
        return produccionLista;
    }

    public int getEtapaAnimal() {
        return etapaAnimal;
    }

    public void setAnimarHPAnimal(JPanel animarHPAnimal) {
        this.animarHPAnimal = animarHPAnimal;
    }

    public JPanel getAnimarHPAnimal() {
        return animarHPAnimal;
    }

    public String etapaCriaVivo() {
        String etapa = "";
        switch (etapaAnimal) {
            case 0:
                etapa = ("Etapa: " + animal.getCria().getCriaNombre());
                break;
            case 1:
                etapa = (animal.getEspecie() + " Adulto");
                break;
            case 2:
                etapa = (animal.getEspecie() + "Viejo");
        }
        return etapa;
    }

    public void setEtapaAnimal(int etapaAnimal) {
        this.etapaAnimal = etapaAnimal;
    }

    public void setEdadLabel(JLabel edadLabel) {
        this.edadLabel = edadLabel;
    }

    public JLabel getEdadLabel() {
        return edadLabel;
    }

    public void setProduccionLista(boolean produccionLista) {
        this.produccionLista = produccionLista;
    }

    public JTable productosAnimal() {
        String[] header = new String[]{"Producto", "Cantidad Almacenada", "Se produce al destazar"};
        String[][] datosProductos = new String[animal.getProduccion().length][];
        for (int k = 0; k < animal.getProduccion().length; k++) {
            datosProductos[k] = new String[]{animal.getProduccion()[k].getEtiqueta(), animal.getProduccion()[k].getCantidadExistente() + "", traducirDestaceBoolean(animal.getProduccion()[k].isProductoDestaze())};
        }
        return getjTable(datosProductos, header);
    }

    public TiempoProduccionLabel getTiempoProduccionLabel() {
        return tiempoProduccionLabel;
    }

    public void setTiempoProduccionLabel(TiempoProduccionLabel tiempoProduccionLabel) {
        this.tiempoProduccionLabel = tiempoProduccionLabel;
    }

    public void producirAnimal(Usuario usuario, JTable jTable) {
        Random random = new Random();
        int base = random.nextInt(10) + 1;
        for (int k = 0; k < animal.getProduccion().length; k++) {
            if (!animal.getProduccion()[k].isProductoDestaze()) {
                int producido = (int) (((hPAnimal / 10) + base) * ampliarProduccionPorEdad() * animal.getProduccion()[k].getPorcentajeProduccion());
                animal.getProduccion()[k].setCantidadExistente(producido + animal.getProduccion()[k].getCantidadExistente());
                jTable.setValueAt(animal.getProduccion()[k].getCantidadExistente(), k, 1);
                usuario.getReportes().agregarNuticionTotal(producido*animal.getProduccion()[k].getHpRecuperada());
            }
        }
    }

    public void producirAnimalAlimentos(Usuario usuario,JTable jTable){
        Random random = new Random();
        boolean tieneAlimentos = false;
        int base = random.nextInt(10) + 1;
        for (int k = 0; k < animal.getProduccion().length; k++) {
            if (animal.getProduccion()[k].getHpRecuperada()<=0){
                tieneAlimentos = true;
            }
        }
        if (tieneAlimentos) {
            producirAnimal(usuario,jTable);
        }
    }

    public void producirAnimalMateriasPrimas(Usuario usuario,JTable jTable){
        Random random = new Random();
        boolean tieneMateriasPrimas = false;
        int base = random.nextInt(10) + 1;
        for (int k = 0; k < animal.getProduccion().length; k++) {
            if (animal.getProduccion()[k].getHpRecuperada()<=0){
                tieneMateriasPrimas = true;
            }
        }
        if (tieneMateriasPrimas) {
            producirAnimal(usuario,jTable);
        }
    }

    public void producirDestace(Usuario usuario, JTable jTable, JPanel jPanel, Parcela parcela, JPanel buttonsGranja, JButton animalButton) {
        Random random = new Random();
        int base = random.nextInt(10) + 1;
        if (isProduccionLista()) {
            producirAnimal(usuario, jTable);
        }
        for (int k = 0; k < animal.getProduccion().length; k++) {
            if (animal.getProduccion()[k].isProductoDestaze()) {
                int producido = (int) (((hPAnimal / 10) + base) * ampliarProduccionPorEdad() * animal.getProduccion()[k].getPorcentajeProduccion());
                animal.getProduccion()[k].setCantidadExistente(producido + animal.getProduccion()[k].getCantidadExistente());
                jTable.setValueAt(animal.getProduccion()[k], k, 2);
                usuario.getReportes().agregarNuticionTotal(producido*animal.getProduccion()[k].getHpRecuperada());
            }
        }
        parcela.setEspacioOcupado(parcela.getEspacioOcupado() - animal.getTamano());
        usuario.getReportes().agregarAnimalesDestazados(usuario.getReportes().buscarAnimal(usuario,animal));
        animalMuerto = true;
        JOptionPane.showMessageDialog(jPanel, "El animal ha Muerto");
        parcela.matarAnimal(this);
        buttonsGranja.remove(animalButton);
        buttonsGranja.revalidate();
        buttonsGranja.repaint();
    }

    private double ampliarProduccionPorEdad() {
        double ampliar = 0;
        switch (etapaAnimal) {
            case 0:
                ampliar = 1;
                break;
            case 1:
                ampliar = 2;
                break;
            case 2:
                ampliar = 0.8;
                break;
        }
        return ampliar;
    }

    public void setAnimalMuerto(boolean animalMuerto) {
        this.animalMuerto = animalMuerto;
    }

    public boolean isAnimalMuerto() {
        return animalMuerto;
    }
}
