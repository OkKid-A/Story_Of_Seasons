package InterfazGráfica;

import Componentes.TiempoProduccionLabel;
import Plantas.Planta;
import Terreno.Pradera;
import Terreno.Sembradio;
import Threads.SembradioThread;
import Usuario.Usuario;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSembrar extends JFrame implements Ventanas {
    private Usuario usuario;
    private Pradera pradera;
    private Pradera[] praderasASembrar;
    private JPanel ventanaSembrar;
    private JTable semillasTable;
    private JList seleccionSemillasList;
    private JButton sembrarCoreButton;
    private JLabel semillasNecesariasLabel;
    private int praderasSeleccionadas;
    private JPanel panelGranja;
    private Thread cultivo;
    private int cambio;
    private TiempoProduccionLabel labelPasada;

    public VentanaSembrar(Usuario usuario, Pradera pradera, JPanel panelGranja) {
        this.usuario = usuario;
        this.pradera = pradera;
        this.panelGranja = panelGranja;
        this.praderasSeleccionadas = 1;
        fixComponents(this, ventanaSembrar);
        setSeleccionSemillasListener();
        setSembrarCoreButtonListener();
    }

    public VentanaSembrar(Usuario usuario, Pradera[] praderasASembrar) {
        this.usuario = usuario;
        this.praderasASembrar = praderasASembrar;
        this.praderasSeleccionadas = praderasASembrar.length;
        fixComponents(this, ventanaSembrar);
        setSeleccionSemillasListener();
        setSembrarCoreButtonListener();
    }


    private void createUIComponents() {
        semillasTable = usuario.getGranero().getMercado().displaySemillas(usuario);
        seleccionSemillasList = new JList(usuario.getGranero().displayNombresSemillas());
    }

    @Override
    public void fixComponents(JFrame frame, JComponent component) {
        frame.setContentPane(component);
        frame.setTitle("Sembrar");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(new Dimension(400, 150));
        frame.setLocationRelativeTo(null);
    }

    private void setSeleccionSemillasListener() {
        seleccionSemillasList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (seleccionSemillasList.getSelectedIndex() < 0) {
                        sembrarCoreButton.setEnabled(false);
                    } else {
                        semillasNecesariasLabel.setText("Semillas necesarias: " + (usuario.getRepertorio().getListaPlantas().getObjetoDeLista(
                                seleccionSemillasList.getSelectedIndex()).getSemilla().getSemillasPorCasilla() * praderasSeleccionadas));
                        sembrarCoreButton.setEnabled(true);
                    }
                }
            }
        });
    }

    private void setSembrarCoreButtonListener() {
        sembrarCoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Planta planta = usuario.getRepertorio().getListaPlantas().getObjetoDeLista(seleccionSemillasList.getSelectedIndex());
                if (planta.getSemilla().getCantidadAlmacenada() >= planta.getSemilla().getSemillasPorCasilla()) {
                    usuario.getReportes().agregarPraderasSembradas(seleccionSemillasList.getSelectedIndex());
                    cambio = pradera.encontrarPradera(usuario);
                    usuario.getGranja().setMapa(new Sembradio(pradera.getFertilidad(), planta, panelGranja), cambio);
                    panelGranja.removeAll();
                    usuario.getGranja().dibujarTerrenoAumentado(usuario.getGranja().coleccionarBotonesTerreno(), panelGranja);
                    labelPasada = new TiempoProduccionLabel();
                    ((Sembradio)usuario.getGranja().getMapa()[cambio]).setLabel(labelPasada);
                    cultivo = new Thread(usuario.getTerrenosThreadGroup(),new SembradioThread((Sembradio) usuario.getGranja().getMapa()[cambio],usuario, ((Sembradio)usuario.getGranja().getMapa()[cambio]).getLabel()), "siembra" + cambio);
                    ((Sembradio)usuario.getGranja().getMapa()[cambio]).setThread(cultivo);
                    cultivo.start();
                    usuario.getGranja().getMapa()[cambio].setListener(usuario,panelGranja);
                    planta.getSemilla().setCantidadAlmacenada(planta.getSemilla().getCantidadAlmacenada() - planta.getSemilla().getSemillasPorCasilla());
                    dispose();
                } else{
                    JOptionPane.showMessageDialog(ventanaSembrar,"No tienes suicientes semillas.");
                }
            }
        });
    }
}
