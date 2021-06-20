package InterfazGráfica;

import Componentes.NuevaPlantaListeners;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaNuevaPlanta extends JFrame implements Ventanas, ActionListener {
    private JPanel ventanaNuevaPlantaPanel;
    private JTextField especieField;
    private JTextField semillasCasillaField;
    private JTextField precioSemillasField;
    private JTextField etiquetaField;
    private JRadioButton plantaDeGranoRadioButton;
    private JRadioButton plantaDeFrutoRadioButton;
    private JTextField precioProductoField;
    private JTextField hpRecuperadaField;
    private JButton presupuestarButton;
    private JButton confirmarButton;
    private JTextField tiempoCrecimientoField;
    private Usuario usuario;
    private boolean plantaGrano;

    public VentanaNuevaPlanta(Usuario usuario) {
        this.usuario = usuario;
        precioSemillasField.setEditable(false);
        fixComponents(this, ventanaNuevaPlantaPanel);
        ButtonGroup tipoPlanta = new ButtonGroup();
        tipoPlanta.add(plantaDeFrutoRadioButton);
        tipoPlanta.add(plantaDeGranoRadioButton);
        plantaDeGranoRadioButton.setSelected(true);
        setPresupuestarButtonListener();
        confirmarButton.setEnabled(false);
    }

    public void setPresupuestarButtonListener() {
        NuevaPlantaListeners listeners = new NuevaPlantaListeners(precioProductoField, hpRecuperadaField, ventanaNuevaPlantaPanel, especieField, semillasCasillaField, precioSemillasField, etiquetaField, tiempoCrecimientoField, plantaGrano,plantaDeGranoRadioButton,usuario,confirmarButton,this);
        presupuestarButton.addActionListener(listeners.presupuestarListener());
        confirmarButton.addActionListener(listeners.confirmarListener(usuario));
        plantaDeGranoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarButton.setEnabled(false);
            }
        });
        plantaDeFrutoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarButton.setEnabled(false);
            }
        });
    }

    @Override
    public void fixComponents(JFrame frame, JComponent component) {
        frame.setContentPane(component);
        frame.setTitle("Nueva Planta");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (plantaDeFrutoRadioButton.isSelected()) {
            plantaGrano = false;
        } else if (plantaDeGranoRadioButton.isSelected()) {
            plantaGrano = true;
        }
    }
}
