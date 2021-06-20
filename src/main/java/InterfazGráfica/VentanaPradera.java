package InterfazGráfica;

import Terreno.Parcela;
import Terreno.Pradera;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPradera extends JFrame implements Ventanas {
    private JPanel ventanaPraderaPanel;
    private JLabel fertilidadLabel;
    private JLabel oroUsuarioLabel;
    private JButton sembrarPraderaJButton;
    private JButton convertirEnCorralButton;
    private JPanel panelGranja;
    private Usuario usuario;
    private Pradera pradera;

    public VentanaPradera(Usuario usuario, Pradera pradera, JPanel panelGranja) {
        this.usuario = usuario;
        this.pradera = pradera;
        this.panelGranja = panelGranja;
        fixComponents(this, ventanaPraderaPanel);
        setConvertirEnCorralListener();
        setSembrarPraderaListener();
    }

    private void createUIComponents() {
        this.fertilidadLabel = new JLabel();
        oroUsuarioLabel = new JLabel();
    }

    @Override
    public void fixComponents(JFrame frame, JComponent component) {
        frame.setContentPane(component);
        frame.setTitle("Pradera");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        fertilidadLabel.setText("Fertilidad: " + pradera.getFertilidad());
        oroUsuarioLabel.setText("Tu oro: " + usuario.getGold());
    }

    public void setConvertirEnCorralListener() {
        convertirEnCorralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuario.getGold() >=15) {
                    usuario.setGold(usuario.getGold() - 15);
                    panelGranja.removeAll();
                    Parcela parcela = new Parcela(pradera);
                    pradera.setCorral(true);
                    parcela.convertirButtonsAndListeners(usuario, panelGranja);
                    usuario.getGranja().dibujarTerrenoAumentado(usuario.getGranja().coleccionarBotonesTerreno(), panelGranja);
                    panelGranja.repaint();
                    dispose();
                }
            }
        });
    }

    public void setSembrarPraderaListener() {
        sembrarPraderaJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaSembrar ventanaSembrarIndividual = new VentanaSembrar(usuario, pradera, panelGranja);
                dispose();
            }
        });
    }
}
