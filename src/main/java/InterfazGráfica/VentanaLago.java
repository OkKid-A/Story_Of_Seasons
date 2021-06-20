package InterfazGráfica;

import Animaciones.AnimarPecesVivos;
import Terreno.Lago;
import Threads.LagoThread;
import Usuario.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaLago extends JFrame implements Ventanas {
    private JPanel ventanaLagoPanel;
    private JLabel pecesVivosLabel;
    private JButton limpiarBarcoPesquero;
    private JButton comprarBarcoButton;
    private JLabel hayBarcoPesqueroLabel;
    private JLabel oroUsuarioLabel;
    private Lago lago;
    private Usuario usuario;
    private Thread peces;
    private Thread lake;

    public VentanaLago(Usuario usuario, Lago lago) {
        this.usuario = usuario;
        this.lago = lago;
        setLimpiarBarcoPesqueroListener();
        setComprarBarcoButtonListener();
        cierreListener();
        disableButtons();
        fixComponents(this, ventanaLagoPanel);
        this.peces = new Thread((Runnable) pecesVivosLabel);
        peces.start();
        this.lake = new Thread(usuario.getTerrenosThreadGroup(), new LagoThread(this.usuario,this.lago), "pesca");
        decidirSiCrearThread();
        hayBarcoPesqueroLabel.setText(lago.hayBarcoPesquero());
        oroUsuarioLabel.setText("Oro: " + usuario.getGold());
    }

    private void createUIComponents() {
        this.hayBarcoPesqueroLabel = new JLabel(lago.hayBarcoPesquero());
        this.oroUsuarioLabel = new JLabel();
        this.pecesVivosLabel = new AnimarPecesVivos(lago, ventanaLagoPanel);
    }

    @Override
    public void fixComponents(JFrame frame, JComponent component) {
        frame.setContentPane(component);
        frame.setTitle("Lago");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void setLimpiarBarcoPesqueroListener() {
        limpiarBarcoPesquero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lago.removerBarco();
                hayBarcoPesqueroLabel.setText(lago.hayBarcoPesquero());
                limpiarBarcoPesquero.setEnabled(false);
                comprarBarcoButton.setEnabled(true);
            }
        });
    }

    private void setComprarBarcoButtonListener() {
        comprarBarcoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuario.getGold() < 50) {
                    JOptionPane.showMessageDialog(ventanaLagoPanel, "No tienes suficiente Oro");
                } else {
                    lago.comprarBarcoPesquero();
                    usuario.setGold(usuario.getGold() - 50);
                    limpiarBarcoPesquero.setEnabled(true);
                    comprarBarcoButton.setEnabled(false);
                    hayBarcoPesqueroLabel.setText(lago.hayBarcoPesquero());
                    oroUsuarioLabel.setText("Oro: " + usuario.getGold());
                }
            }
        });
    }

    private void disableButtons() {
        if (lago.isLagoConBotePesquero()) {
            comprarBarcoButton.setEnabled(false);
            limpiarBarcoPesquero.setEnabled(true);
        } else {
            comprarBarcoButton.setEnabled(true);
            limpiarBarcoPesquero.setEnabled(false);
        }
    }

    private void cierreListener() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                peces.stop();
            }
        });
    }

    private void decidirSiCrearThread() {
        if (!lago.isTieneThread()) {
            lago.setTieneThread(true);
            lake.start();
        }
    }
}
