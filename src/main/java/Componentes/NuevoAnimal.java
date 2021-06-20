package Componentes;

import Granero.Repertorio;
import Productos.Producto;
import Usuario.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static Productos.NuevoProducto.traducirDestaceBoolean;

public class NuevoAnimal {
    private JRadioButton animalOmnivoboroRadioButton;
    private JRadioButton animalHerviboroRadioButton;
    private JTextField especieField;
    private JTextField tamanoField;
    private JRadioButton iconoGenericaRadioButton;
    private JRadioButton imagenEspecificaRadioButton;
    private JTextField pathIconoField;
    private JTextField nombreCriaField;
    private JTextField tiempoCrecimientoField;
    private JTextField precioCriasField;
    private JButton presupuestarButton;
    private JTable productosAnimalTable;
    private JButton agregarProductoButton;
    private JButton button1;
    private Usuario usuario;
    private Repertorio repertorio;
    private Producto[] produccion;

    public NuevoAnimal(Usuario usuario) {
        this.usuario = usuario;
        this.repertorio = usuario.getRepertorio();
        produccion = new Producto[0];
    }



    public JTable productosAnimalTable() {
        String[] header = new String[]{"Nombre del Producto", "Porcentaje de Produccion", "Precio", "HP que Recupera", "Se necesita Destazar"};
        String[][] productosData = new String[produccion.length][];
        for (int k = 0; k < productosData.length; k++) {
            productosData[k] = new String[]{produccion[k].getEtiqueta(), String.valueOf(produccion[k].getPorcentajeProduccion()), produccion[k].getPrecio() + "", produccion[k].getHpRecuperada() + "", traducirDestaceBoolean(produccion[k].isProductoDestaze())};
        }
        DefaultTableModel tableModel = new DefaultTableModel(productosData, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable produccionJTable = new JTable(productosData, header);
        produccionJTable.setModel(tableModel);
        return produccionJTable;
    }
}
