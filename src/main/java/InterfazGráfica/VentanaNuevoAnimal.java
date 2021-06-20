package InterfazGráfica;

import Componentes.NuevoAnimal;
import Productos.NuevoProducto;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaNuevoAnimal extends JFrame implements Ventanas{
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
    private NuevoAnimal nuevoAnimal;

    public VentanaNuevoAnimal(Usuario usuario){
        this.nuevoAnimal = new NuevoAnimal(usuario);
        this.usuario = usuario;
        precioCriasField.setEditable(false);
    }



    private void createUIComponents() {

    }

    @Override
    public void fixComponents(JFrame frame, JComponent component) {

    }
}
