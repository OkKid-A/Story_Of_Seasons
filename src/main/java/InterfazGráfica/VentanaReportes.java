package InterfazGráfica;

import Usuario.Usuario;

import javax.swing.*;
import java.awt.*;

public class VentanaReportes extends JFrame implements Ventanas {
    private JPanel ventanaReportesPanel;
    private JTable partidasDataTable;
    private JTable animalesDataTable;
    private JTable plantasDataTable;
    private Usuario usuario;

    public VentanaReportes(Usuario usuario){
        this.usuario = usuario;
        partidasDataTable = usuario.getReportes().dibujarDatosPartida();
        animalesDataTable = usuario.getReportes().dibujarDatosAnimales(usuario);
        plantasDataTable = usuario.getReportes().dibujarDatosPlantas(usuario);
        fixComponents(this,ventanaReportesPanel);
    }



    @Override
    public void fixComponents(JFrame frame, JComponent component) {
        frame.setContentPane(component);
        frame.setTitle("Reportes");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        partidasDataTable = usuario.getReportes().dibujarDatosPartida();
        animalesDataTable = usuario.getReportes().dibujarDatosAnimales(usuario);
        plantasDataTable = usuario.getReportes().dibujarDatosPlantas(usuario);
    }
}
