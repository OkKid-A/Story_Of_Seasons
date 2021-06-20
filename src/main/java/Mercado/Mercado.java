package Mercado;

import Granero.listaInventario;
import Usuario.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Mercado {
    public static final String[] CARAC_ALIMENTOS = new String[]{"Producto Alimenticio", "Vida que Recupera", "Precio (Oro)", "Clase de Alimento"};
    public static final String[] CARAC_FERTILIZANTES = new String[]{"Fertilizante", "Fertilidad que mejora", "Precio (Oro)"};
    private listaInventario<AlimentoAnimal> listaAlimentos;
    private listaInventario<Fertilizante> listaFertilizantes;

    public Mercado() {
        registrarAlimentosGanado();
        registrarFertilizantes();
    }

    public void registrarAlimentosGanado() {
        listaAlimentos = new listaInventario<AlimentoAnimal>(3);
        listaAlimentos.anadirObjetoALista(new AlimentoAnimal(10, 2, "Pasto", true));
        listaAlimentos.anadirObjetoALista(new AlimentoAnimal(25, 5, "Heno", true));
        listaAlimentos.anadirObjetoALista(new AlimentoAnimal(50, 8, "Hierbas", true));
        listaAlimentos.anadirObjetoALista(new AlimentoAnimal(10, 2, "Granillo", false));
        listaAlimentos.anadirObjetoALista(new AlimentoAnimal(25, 5, "Legumbres", false));
        listaAlimentos.anadirObjetoALista(new AlimentoAnimal(50, 8, "Cereales", false));
    }

    public void registrarFertilizantes() {
        listaFertilizantes = new listaInventario<Fertilizante>(3);
        listaFertilizantes.anadirObjetoALista(new Fertilizante(25, 2, "Foliar Nitro Xtend"));
        listaFertilizantes.anadirObjetoALista(new Fertilizante(50, 5, "Nitrogenado Nobrico"));
        listaFertilizantes.anadirObjetoALista(new Fertilizante(100, 12, "Organico Blatt Pure"));
        ;
    }

    public String[][] displayAlimentosData() {
        String[][] alimentosDatos = new String[7][];
        alimentosDatos[0] = CARAC_ALIMENTOS;
        alimentosDatos[0] = new String[]{"Alimento", "Vida que recupera", "Precio", "Clase de alimento"};
        for (int k = 0; k < 6; k++) {
            alimentosDatos[k + 1] = new String[]{listaAlimentos.getObjetoDeLista(k).getNombreAlimento(), listaAlimentos.getObjetoDeLista(k).getNutricion() + "",
                    listaAlimentos.getObjetoDeLista(k).getPrecio() + "", listaAlimentos.getObjetoDeLista(k).isHerviboro()};
        }
        return alimentosDatos;
    }

    public String[][] displayFertilizantesData() {
        String[][] fertilizantesDatos = new String[4][];
        fertilizantesDatos[0] = CARAC_FERTILIZANTES;
        for (int k = 0; k < 3; k++) {
            fertilizantesDatos[k + 1] = new String[]{listaFertilizantes.getObjetoDeLista(k).getNombreFertilizante(), listaFertilizantes.getObjetoDeLista(k).getFertilidadAumentada() + "",
                    listaFertilizantes.getObjetoDeLista(k).getPrecio() + ""};
        }
        return fertilizantesDatos;
    }

    public JTable displaySemillas(Usuario usuario) {
        String[][] semillasDatos = new String[usuario.getRepertorio().getListaPlantas().getNumElementosPresentes()][];
        String[] semillasDatosHeader = new String[]{"Semilla de:", "Precio", "Semillas Necesarias por Sembradio", "Semillas en Inventario"};
        for (int k = 0; k < semillasDatos.length; k++) {
            semillasDatos[k] = usuario.getRepertorio().getListaPlantas().getObjetoDeLista(k).getSemilla().datosDeEstaSemilla();
        }
        return getjTable(semillasDatos, semillasDatosHeader);
    }

    public JTable displayCantidadCrias(Usuario usuario) {
        String[][] criasDatos = new String[usuario.getRepertorio().getListaAnimal().getNumElementosPresentes()][];
        String[] criasDatosHeader = new String[]{"Especie", "Precio", "Crias Guardadas"};
        for (int k = 0; k < criasDatos.length; k++) {
            criasDatos[k] = usuario.getRepertorio().getListaAnimal().getObjetoDeLista(k).getCria().datosCria();
        }
        return getjTable(criasDatos, criasDatosHeader);
    }

    public String[] nombresCrias(Usuario usuario) {
        String[] nombresCria = new String[usuario.getRepertorio().getListaAnimal().getNumElementosPresentes()];
        for (int k = 0; k < nombresCria.length; k++) {
            nombresCria[k] = usuario.getRepertorio().getListaAnimal().getObjetoDeLista(k).getCria().getCriaNombre();
        }
        return nombresCria;
    }

    public static JTable getjTable(String[][] datosFilas, String[] datosHeader) {
        DefaultTableModel tableModel = new DefaultTableModel(datosFilas, datosHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable semillasTabla = new JTable(datosFilas, datosHeader);
        semillasTabla.setModel(tableModel);
        return semillasTabla;
    }

    public String[] displayNombresSemillas(Usuario usuario) {
        String[] nombres = new String[usuario.getRepertorio().getListaPlantas().getNumElementosPresentes()];
        for (int k = 0; k < usuario.getRepertorio().getListaPlantas().getNumElementosPresentes(); k++) {
            nombres[k] = "Semilla de: " + usuario.getRepertorio().getListaPlantas().getObjetoDeLista(k).getSemilla().getPlanta();
        }
        return nombres;
    }

    public listaInventario<AlimentoAnimal> getListaAlimentos() {
        return listaAlimentos;
    }

    public listaInventario<Fertilizante> getListaFertilizantes() {
        return listaFertilizantes;
    }
}
