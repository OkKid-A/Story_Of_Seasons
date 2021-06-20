package Productos;

import Animales.Animal;
import Granero.Repertorio;
import InterfazGráfica.VentanaNuevoProducto;
import Usuario.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NuevoProducto {
    private JPanel ventanaNuevoProductoPanel;
    private JList animalesList;
    private JButton seleccionarAnimalButton;
    private JTable productosAnimalTable;
    private JTextField etiquetaField;
    private JTextField porcentejeProduccionField;
    private JTextField precioField;
    private JTextField hPRecuperadaField;
    private JRadioButton seProduceAlDestazarRadioButton;
    private JRadioButton seProduceSinDestazarRadioButton;
    private JPanel tablaPanel;
    private JScrollPane mm;
    private JScrollPane tabla;
    private NuevoProducto nuevoProductoComponents;
    private Usuario usuario;
    private VentanaNuevoProducto ventanaNuevoProducto;
    private Repertorio repertorio;
    private JButton presupuestarButton;
    private JButton confirmarButton;
    private JTextField precioActualizado;

    public NuevoProducto(Usuario usuario, JButton seleccionarAnimalButton, JList animalesList, JPanel tablaPanel, JTable productosAnimalTable, JPanel ventanaNuevoProductoPanel, JButton presupuestarButton, JButton confirmarButton, JTextField precioActualizado,JTextField precioField,JTextField porcentejeProduccionField) {
        this.precioField = precioField;
        this.porcentejeProduccionField = porcentejeProduccionField;
        this.precioActualizado = precioActualizado;
        this.confirmarButton = confirmarButton;
        this.presupuestarButton = presupuestarButton;
        this.seleccionarAnimalButton = seleccionarAnimalButton;
        this.animalesList = animalesList;
        this.tablaPanel = tablaPanel;
        this.productosAnimalTable = productosAnimalTable;
        this.ventanaNuevoProductoPanel = ventanaNuevoProductoPanel;
        this.usuario = usuario;
        this.repertorio = usuario.getRepertorio();
    }

    public String[] nombresAnimales() {
        String[] nombresAnimales = new String[repertorio.getListaAnimal().getNumElementosPresentes()];
        for (int k = 0; k < nombresAnimales.length; k++) {
            nombresAnimales[k] = repertorio.getListaAnimal().getObjetoDeLista(k).getEspecie();
        }
        return nombresAnimales;
    }

    public static String traducirDestaceBoolean(boolean is) {
        if (is) {
            return "Si";
        } else {
            return "No";
        }
    }

    public JTable productosAnimalTable(int animalIndex) {
        if (animalIndex == -1) {
            return new JTable();
        } else {
            String[] header = new String[]{"Nombre del Producto", "Porcentaje de Produccion", "Precio", "HP que Recupera", "Se necesita Destazar"};
            Producto[] produccion = repertorio.getListaAnimal().getObjetoDeLista(animalIndex).getProduccion();
            String[][] productosData = new String[produccion.length][];
            for (int k = 0; k < productosData.length; k++) {
                productosData[k] = new String[]{produccion[k].getEtiqueta(), String.valueOf(produccion[k].getPorcentajeProduccion()), produccion[k].getPrecio() + "", produccion[k].getHpRecuperada() + "", traducirDestaceBoolean(produccion[k].isProductoDestaze())};
            }
            DefaultTableModel tableModel = new DefaultTableModel(productosData, header) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 1;
                }
            };
            JTable produccionJTable = new JTable(productosData, header);
            produccionJTable.setModel(tableModel);
            return produccionJTable;
        }
    }

    public void setSeleccionarAnimalButtonListener() {
        seleccionarAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animalesList.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(ventanaNuevoProductoPanel, "Selecciona un Animal");
                } else {
                    tablaPanel.setLayout(new FlowLayout());
                    tablaPanel.removeAll();
                    productosAnimalTable = productosAnimalTable(animalesList.getSelectedIndex());
                    mm = new JScrollPane(productosAnimalTable);
                    tablaPanel.add(mm);
                    tablaPanel.revalidate();
                    tablaPanel.repaint();
                    productosAnimalTable.revalidate();
                    ventanaNuevoProductoPanel.revalidate();
                }
            }
        });
    }

    public void setPresupuestarListener() {
        try {
            presupuestarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    precioActualizado.setText(presupuestarPrecio() + "");
                    confirmarButton.setEnabled(true);
                }
            });
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(ventanaNuevoProductoPanel, "Selecciona un Animal");
        }
    }

    public double presupuestarPrecio() throws IndexOutOfBoundsException {
        double promedio = 0;
        Animal animal = usuario.getRepertorio().getListaAnimal().getObjetoDeLista(animalesList.getSelectedIndex());
        for (int k = 0; k < animal.getProduccion().length; k++) {
            promedio = animal.getProduccion()[k].getPrecio() * animal.getProduccion()[k].getPorcentajeProduccion() + promedio;
        }
        promedio = (promedio / (animal.getProduccion().length + 1));
        return ((Integer.valueOf(porcentejeProduccionField.getText())) / promedio);
    }
}
