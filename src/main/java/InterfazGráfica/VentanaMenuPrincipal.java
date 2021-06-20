package InterfazGráfica;

import Granja.Granja;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaMenuPrincipal extends JFrame {
    public static final String TITLE = "Story of Seasons 2d";
    private JFormattedTextField bienvenidoFormattedTextField;
    private JPanel MenuPrincipalPanel;
    private JButton salirButton;
    private JLabel bienvenidoJLabel;
    private JButton nuevaPlantaButton;
    private JButton nuevaPartidaButton;
    private JButton nuevoAnimalButton;
    private JButton nuevoProductobutton;
    private JButton reportesButton;
    private Usuario usuario;

    public VentanaMenuPrincipal(Usuario usuario) {
        this.usuario = usuario;
        fixComponents();
        setSalirButton();
        setNuevaPartidaButton();
        setNuevaPlantaButtonListener();
        setNuevoProductoButtonListener();
        setReportesButton();
    }

    private void fixComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(MenuPrincipalPanel);
        this.setSize(400, 600);
        this.setTitle(TITLE);
    }

    private void setNuevoProductoButtonListener(){
        nuevoProductobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
VentanaNuevoProducto ventanaNuevoProducto = new VentanaNuevoProducto(usuario);
            }
        });
    }

    private void setSalirButton() {
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void setNuevaPartidaButton() {
        nuevaPartidaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaGranja granja = new VentanaGranja(usuario);
                usuario.getReportes().getTimer().start();
                dispose();
            }
        });
    }

    private void setNuevaPlantaButtonListener(){
        nuevaPlantaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
VentanaNuevaPlanta ventanaNuevaPlanta = new VentanaNuevaPlanta(usuario);
            }
        });
    }

    private void setReportesButton(){
        reportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
VentanaReportes ventanaReportes = new VentanaReportes(usuario);
            }
        });
    }

    public JPanel getIntro() {
        return MenuPrincipalPanel;
    }
}
