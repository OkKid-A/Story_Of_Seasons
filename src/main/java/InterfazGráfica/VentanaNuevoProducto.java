package InterfazGráfica;

import Productos.NuevoProducto;
import Usuario.Usuario;

import javax.swing.*;

public class VentanaNuevoProducto extends JFrame implements Ventanas {
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
    private JButton presupuestarButton;
    private JButton confirmarButton;
    private JTextField precioActualizadoField;
    private JScrollPane tabla;
    private NuevoProducto nuevoProductoComponents;
    private Usuario usuario;
    private VentanaNuevoAnimal ventanaNuevoAnimal;

    public VentanaNuevoProducto(Usuario usuario) {
        this.usuario = usuario;
        this.nuevoProductoComponents = new NuevoProducto(usuario, seleccionarAnimalButton, animalesList, tablaPanel,
                productosAnimalTable, ventanaNuevoProductoPanel, presupuestarButton ,confirmarButton, precioActualizadoField,precioField,porcentejeProduccionField);
        ButtonGroup tipoProducto = new ButtonGroup();
        tipoProducto.add(seProduceAlDestazarRadioButton);
        tipoProducto.add(seProduceSinDestazarRadioButton);
        seProduceSinDestazarRadioButton.setSelected(true);
        precioActualizadoField.setEditable(false);
        confirmarButton.setEnabled(false);
        fixComponents(this, ventanaNuevoProductoPanel);
        setListeners();

        this.nuevoProductoComponents.setSeleccionarAnimalButtonListener();
        this.nuevoProductoComponents.setPresupuestarListener();
    }


    private void createUIComponents() {this.nuevoProductoComponents = new NuevoProducto(usuario, seleccionarAnimalButton, animalesList, tablaPanel,
            productosAnimalTable, ventanaNuevoProductoPanel, presupuestarButton ,confirmarButton, precioActualizadoField,precioField,porcentejeProduccionField);
        this.animalesList = new JList(nuevoProductoComponents.nombresAnimales());
        this.productosAnimalTable = nuevoProductoComponents.productosAnimalTable(-1);
    }

    private void setListeners(){
        this.nuevoProductoComponents = new NuevoProducto(usuario, seleccionarAnimalButton, animalesList, tablaPanel,
                productosAnimalTable, ventanaNuevoProductoPanel, presupuestarButton ,confirmarButton, precioActualizadoField,precioField,porcentejeProduccionField);
        this.nuevoProductoComponents.setSeleccionarAnimalButtonListener();
        this.nuevoProductoComponents.setPresupuestarListener();
        this.animalesList = new JList(nuevoProductoComponents.nombresAnimales());
        this.productosAnimalTable = nuevoProductoComponents.productosAnimalTable(-1);
    }

    @Override
    public void fixComponents(JFrame frame, JComponent component) {
        frame.add(component);
        frame.setTitle("Nuevo Producto");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }


    private JFrame redundar() {
        return this;
    }
}
