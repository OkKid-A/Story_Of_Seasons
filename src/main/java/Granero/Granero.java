package Granero;

import Animales.Animal;
import Mercado.Mercado;
import Productos.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static Mercado.Mercado.getjTable;

public class Granero {
    private Repertorio repertorio;
    private Mercado mercado;
    private int[] fertilizantesPoseidos;
    private int[] alimentosPoseidos;

    public Granero(Repertorio repertorio, Mercado mercado) {
        this.repertorio = repertorio;
        this.mercado = mercado;
        this.alimentosPoseidos = new int[]{0, 0, 0, 0, 0, 0};
        this.fertilizantesPoseidos = new int[]{0, 0, 0};
    }

    public boolean graneroVacio(int numObjeto) {
        if (repertorio.getListaProductos().getObjetoDeLista(numObjeto).getCantidadExistente() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public String traducirDestaze(int k) {
        if (repertorio.getListaProductos().getObjetoDeLista(k).isProductoDestaze()) {
            return "Si";
        } else {
            return "No";
        }
    }

    public String[] displayProductos() {
        String nombresProductos[] = new String[repertorio.getListaProductos().getNumElementosPresentes()];
        for (int k = 0; k < repertorio.getListaProductos().getNumElementosPresentes(); k++) {
            nombresProductos[k] = repertorio.getListaProductos().getObjetoDeLista(k).getEtiqueta();
        }
        return nombresProductos;
    }

    public int curarUsuario(int numObjeto, JPanel frame) {
        if (repertorio.getListaProductos().getObjetoDeLista(numObjeto).getCantidadExistente() <= 0) {
            JOptionPane.showMessageDialog(frame, "No hay productos de este tipo en tu granero.");
            return 0;
        } else {
            int hpCurada = repertorio.getListaProductos().getObjetoDeLista(numObjeto).getHpRecuperada();
            repertorio.getListaProductos().getObjetoDeLista(numObjeto).setCantidadExistente(repertorio.getListaProductos().getObjetoDeLista(numObjeto).getCantidadExistente() - 1);
            JOptionPane.showMessageDialog(frame, "Has recuperado: " + hpCurada);
            return hpCurada;
        }
    }

    public JTable displayInventarioProductos() {
        String[] nombresColumnas = {"Producto", "Precio", "Porcentaje de la Produccion", "Cantidad en Existencia",
                "HP+", "¿Es producto de Destace o Cosecha?"};
        String[][] datosEnFilas = new String[repertorio.getListaProductos().getNumElementosPresentes()][6];
        for (int k = 0; k < repertorio.getListaProductos().getNumElementosPresentes(); k++) {
            datosEnFilas[k] = new String[]{repertorio.getListaProductos().getObjetoDeLista(k).getEtiqueta(),
                    repertorio.getListaProductos().getObjetoDeLista(k).getPrecio() + "", repertorio.getListaProductos().getObjetoDeLista(k).getPorcentajeProduccion() * 100 + "%",
                    repertorio.getListaProductos().getObjetoDeLista(k).getCantidadExistente() + "", repertorio.getListaProductos().getObjetoDeLista(k).getHpRecuperada() + "",
                    traducirDestaze(k)};
        }
        return getjTable(datosEnFilas, nombresColumnas);
    }

    public String [] displayAlimentos() {
        String[] nombresAlimentos = new String[6];
        for (int k = 0;k < 6;k++){
            nombresAlimentos[k] = mercado.getListaAlimentos().getObjetoDeLista(k).getNombreAlimento();
        }
        return nombresAlimentos;
    }

    public JTable displayCantidadAlimentos() {
        String[][] alimentosDataExten = new String[6][];
        String [] alimentosDataHeader = new String[]{mercado.CARAC_ALIMENTOS[0], mercado.CARAC_ALIMENTOS[1], mercado.CARAC_ALIMENTOS[2], mercado.CARAC_ALIMENTOS[3], "Cantidad en Existencia"};
        for (int k = 0; k < 6; k++) {
            alimentosDataExten[k] = new String[]{mercado.getListaAlimentos().getObjetoDeLista(k).getNombreAlimento(), mercado.getListaAlimentos().getObjetoDeLista(k).getNutricion() + "",
                    mercado.getListaAlimentos().getObjetoDeLista(k).getPrecio() + "", mercado.getListaAlimentos().getObjetoDeLista(k).isHerviboro(), alimentosPoseidos[k] + ""};
        }
        return getjTable(alimentosDataExten, alimentosDataHeader);
    }

    public String[] displayFertilizantes(){
        String[] nombresFertilizantes = new String[3];
        for (int k = 0;k < 3;k++){
            nombresFertilizantes[k] = mercado.getListaFertilizantes().getObjetoDeLista(k).getNombreFertilizante();
        }
        return nombresFertilizantes;
    }

    public JTable displayCantidadFertilizantes() {
        String[][] fertilizantesDataExtend = new String[3][];
        String[] fertilizantesDataHeader = new String[]{mercado.CARAC_FERTILIZANTES[0], mercado.CARAC_FERTILIZANTES[1], mercado.CARAC_FERTILIZANTES[2], "Cantidad en Existencia"};
        for (int k = 0; k < 3; k++) {
            fertilizantesDataExtend[k] = new String[]{mercado.getListaFertilizantes().getObjetoDeLista(k).getNombreFertilizante(), mercado.getListaFertilizantes().getObjetoDeLista(k).getFertilidadAumentada() + "",
                    mercado.getListaFertilizantes().getObjetoDeLista(k).getPrecio() + "", fertilizantesPoseidos[k] + ""};
        }
        return getjTable(fertilizantesDataExtend, fertilizantesDataHeader);
    }

    public Mercado getMercado() {
        return mercado;
    }

    public int getFertilizantesPoseidos(int numFertilizante) {
        return fertilizantesPoseidos[numFertilizante];
    }

    public void setAlimentosPoseidos(int alimentosPoseidos, int index) {
        this.alimentosPoseidos[index] = alimentosPoseidos;
    }

    public int getAlimentosPoseidos(int numAlimento) {
        return alimentosPoseidos[numAlimento];
    }

    public void setFertilizantesPoseidos(int fertilizantesPoseidos,int index) {
        this.fertilizantesPoseidos[index] = fertilizantesPoseidos;
    }

    public String[] displayNombresSemillas(){
        String[] nombresSemillas = new String[repertorio.getListaPlantas().getNumElementosPresentes()];
        for (int k = 0;k < nombresSemillas.length;k++){
            nombresSemillas[k] = repertorio.getListaPlantas().getObjetoDeLista(k).getSemilla().getPlanta();
        }
        return nombresSemillas;
    }

    public int[] getAlimentosPoseidos() {
        return alimentosPoseidos;
    }

    public int[] getFertilizantesPoseidos() {
        return fertilizantesPoseidos;
    }
}


