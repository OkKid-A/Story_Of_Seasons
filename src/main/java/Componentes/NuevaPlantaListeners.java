package Componentes;

import Plantas.PlantaFruta;
import Plantas.PlantaGrano;
import Plantas.Semilla;
import Productos.Producto;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NuevaPlantaListeners {
    private JTextField precioProductoField;
    private JTextField hpRecuperadaField;
    private JPanel ventanaNuevaPlantaPanel;
    private JTextField especieField;
    private JTextField semillasCasillaField;
    private JTextField precioSemillasField;
    private JTextField etiquetaField;
    private JTextField tiempoCrecimientoField;
    private boolean plantaGrano;
    private JRadioButton plantaGranoButton;
    private Usuario usuario;
    private double costo;
    private JButton confirmarButton;
    private JFrame frame;

    public NuevaPlantaListeners(JTextField precioProductoField, JTextField hpRecuperadaField, JPanel ventanaNuevaPlantaPanel, JTextField especieField, JTextField semillasCasillaField, JTextField precioSemillasField, JTextField etiquetaField, JTextField tiempoCrecimientoField, boolean plantaGrano, JRadioButton plantaGranoButton, Usuario usuario, JButton confirmarButton,JFrame frame) {
        this.precioProductoField = precioProductoField;
        this.hpRecuperadaField = hpRecuperadaField;
        this.ventanaNuevaPlantaPanel = ventanaNuevaPlantaPanel;
        this.especieField = especieField;
        this.semillasCasillaField = semillasCasillaField;
        this.precioSemillasField = precioSemillasField;
        this.etiquetaField = etiquetaField;
        this.tiempoCrecimientoField = tiempoCrecimientoField;
        this.plantaGrano = plantaGrano;
        this.plantaGranoButton = plantaGranoButton;
        this.usuario = usuario;
        this.confirmarButton = confirmarButton;
        this.frame = frame;
    }

    public ActionListener presupuestarListener() {
        ActionListener presupuestar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (estanFieldsLlenos() && plantaGranoButton.isSelected()) {
                    costo = ((Integer.valueOf(precioProductoField.getText()) + Integer.valueOf(hpRecuperadaField.getText())) / 3);
                    precioSemillasField.setText(costo + "");
                    confirmarButton.setEnabled(true);
                } else if (estanFieldsLlenos() && !plantaGranoButton.isSelected()) {
                    costo = Integer.valueOf(precioProductoField.getText()) + Integer.valueOf(hpRecuperadaField.getText());
                    precioSemillasField.setText(costo + "");
                    confirmarButton.setEnabled(true);
                }
            }
        };
        return presupuestar;
    }

    public ActionListener confirmarListener(Usuario usuario) {
        ActionListener confirmar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (estanFieldsLlenos() && plantaGranoButton.isSelected()) {
                    double d = Double.parseDouble(precioProductoField.getText());
                    Producto productoPlanta = new Producto(d, 1, 0, etiquetaField.getText(), especieField.getText(), true, Integer.valueOf(hpRecuperadaField.getText()));
                    usuario.getRepertorio().getListaProductos().anadirObjetoALista(productoPlanta);
                    usuario.getRepertorio().getListaPlantas().anadirObjetoALista(new PlantaGrano(especieField.getText(), 60, new Semilla(costo, especieField.getText(), 60, Integer.valueOf(semillasCasillaField.getText())),
                            productoPlanta));
                    JOptionPane.showMessageDialog(ventanaNuevaPlantaPanel,"La planta ha sido agregada");
                    frame.dispose();
                } else if (estanFieldsLlenos() && !plantaGranoButton.isSelected()) {
                    double d = Double.parseDouble(precioProductoField.getText());
                    Producto productoPlanta = new Producto(d, 1, 0, etiquetaField.getText(), especieField.getText(), true, Integer.valueOf(hpRecuperadaField.getText()));
                    usuario.getRepertorio().getListaProductos().anadirObjetoALista(productoPlanta);
                    usuario.getRepertorio().getListaPlantas().anadirObjetoALista(new PlantaFruta(especieField.getText(), 60, new Semilla(costo, especieField.getText(), 60, Integer.valueOf(semillasCasillaField.getText())),
                            productoPlanta));
                    JOptionPane.showMessageDialog(ventanaNuevaPlantaPanel,"La planta ha sido agregada");
                    frame.dispose();
                }
            }
        };
        usuario.getReportes().expandirDatosPlantas();
        return confirmar;
    }

    private boolean estanFieldsLlenos() {
        if (etiquetaField.getText() != null && especieField.getText() != null && isTextFieldIntValid(semillasCasillaField) && isTextFieldIntValid(hpRecuperadaField) && isTextFieldDoubleValid(precioProductoField)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isTextFieldIntValid(JTextField field) {
        try {
            Integer.valueOf(field.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ventanaNuevaPlantaPanel, "El numero escrito no es valido");
            return false;
        }
        if (field.getText() != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isTextFieldDoubleValid(JTextField field) {
        try {
            double d = Double.parseDouble(field.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ventanaNuevaPlantaPanel, "El numero escrito no es valido");
            return false;
        }
        if (field.getText() != null) {
            return true;
        } else {
            return false;
        }
    }
}
