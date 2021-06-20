package InterfazGráfica;

import Componentes.TiempoProduccionLabel;
import Plantas.Planta;
import Terreno.Sembradio;
import Threads.SembradioThread;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSembradio extends JFrame implements Ventanas {
    private JPanel ventanaSembradioPanel;
    private JLabel plantaSiembraLabel;
    private JLabel tiempoCosechaLabel;
    private JLabel fertilidadLabel;
    private JButton fertilizarButton;
    private JButton limpiarButton;
    private JButton cosecharButton;
    private JList fertilizantesList;
    private JTable fertilizantesTable;
    private JButton sembrarButton;
    private JList selecSemillaList;
    private JTable semillasTable;
    private Sembradio siembra;
    private Usuario usuario;

    public VentanaSembradio(Sembradio siembra, Usuario usuario) {
        this.siembra = siembra;
        this.usuario = usuario;
        fixComponents(this, ventanaSembradioPanel);
        setCosecharButtonListener();
        setFertilizarButton();
        setLimpiarButtonListener();
        disableButtons();
        tiempoCosechaLabel.setText("Tiempo Faltante para la Cosecha:");
        setSembrarButton();
    }

    @Override
    public void fixComponents(JFrame frame, JComponent component) {
        frame.setContentPane(component);
        frame.setTitle("Cultivo");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        fertilidadLabel.setText("Fertilidad: " + siembra.getFertilidad());
        siembra.setLabelMarchito((TiempoProduccionLabel) tiempoCosechaLabel);
        plantaSiembraLabel.setText(siembra.plantaEnSiembra());
    }

    private void createUIComponents() {
        semillasTable = usuario.getGranero().getMercado().displaySemillas(usuario);
        selecSemillaList = new JList(usuario.getGranero().displayNombresSemillas());
        tiempoCosechaLabel = siembra.getLabel();
        siembra.getLabel().setValue("Tiempo Faltante para la Cosecha");
        fertilizantesList = new JList(usuario.getGranero().displayFertilizantes());
        fertilizantesTable = usuario.getGranero().displayCantidadFertilizantes();
    }

    private void setCosecharButtonListener() {
        cosecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (siembra.isSiembraMadura() && siembra == usuario.getGranja().getCosechas().getTop()) {
                    usuario.getGranja().getCosechas().atender(usuario, ventanaSembradioPanel);
                    cosecharButton.setEnabled(false);
                    siembra.getThread().stop();
                    sembrarButton.setEnabled(true);
                    if ("Plantas.PlantaFruta" == siembra.getPlanta().getClass().getName() && !siembra.isSiembraMuerta()) {
                        siembra.setVecesCosechada(siembra.getVecesCosechada() + 1);
                        if (siembra.getVecesCosechada() <= 5) {
                            siembra.setThread(new Thread(usuario.getTerrenosThreadGroup(), new SembradioThread(
                                    siembra, usuario, (TiempoProduccionLabel) tiempoCosechaLabel), "siembra" + siembra.getNumeroTerreno()));
                            siembra.getThread().start();
                            siembra.setLabel((TiempoProduccionLabel) tiempoCosechaLabel);
                        } else {
                            tiempoCosechaLabel.setText("Tu planta ha muerto por vejez");
                        }
                    } else {
                        tiempoCosechaLabel.setText("No hay cosecha");
                        siembra.setPlanta(null);
                    }
                } else {
                    JOptionPane.showMessageDialog(ventanaSembradioPanel, "No puedes Cosechar en este Momento, o Hay otra cosecha mas antigua que esta");
                }
            }
        });
    }

    private void setFertilizarButton() {
        fertilizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fertilizantesList.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(ventanaSembradioPanel, "No has seleccionado un fertilizante.");
                } else if (usuario.getGranero().getFertilizantesPoseidos(fertilizantesList.getSelectedIndex()) <= 0) {
                    JOptionPane.showMessageDialog(ventanaSembradioPanel, "No tienes ese Fertilizante.");
                } else {
                    siembra.setFertilidad(siembra.getFertilidad() + usuario.getGranero().getMercado().getListaFertilizantes().getObjetoDeLista(fertilizantesList.getSelectedIndex()).getFertilidadAumentada());
                    usuario.getGranero().setFertilizantesPoseidos(usuario.getGranero().getFertilizantesPoseidos(fertilizantesList.getSelectedIndex()) - 1, fertilizantesList.getSelectedIndex());
                    fertilizantesTable.getModel().setValueAt(usuario.getGranero().getFertilizantesPoseidos(fertilizantesList.getSelectedIndex()), fertilizantesList.getSelectedIndex() + 1, 3);
                    fertilidadLabel.setText("Fertilidad " + siembra.getFertilidad());
                }
            }
        });
    }

    private void setLimpiarButtonListener() {
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                siembra.setPlanta(null);
                plantaSiembraLabel.setText("Siembra Vacia");
                siembra.getThread().stop();
                cosecharButton.setEnabled(false);
                limpiarButton.setEnabled(false);
            }
        });
    }

    private void disableButtons() {
        if (siembra.getPlanta() == null) {
            cosecharButton.setEnabled(false);
            sembrarButton.setEnabled(true);
        } else {
            sembrarButton.setEnabled(false);
        }
    }

    private void setSembrarButton() {
        sembrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Planta planta = usuario.getRepertorio().getListaPlantas().getObjetoDeLista(selecSemillaList.getSelectedIndex());
                    if (siembra.getPlanta() == null && planta.getSemilla().getCantidadAlmacenada() >= planta.getSemilla().getSemillasPorCasilla()) {
                        planta.getSemilla().setCantidadAlmacenada(planta.getSemilla().getCantidadAlmacenada() - planta.getSemilla().getSemillasPorCasilla());
                        siembra.setPlanta(planta);
                        siembra.setSiembraMadura(false);
                        siembra.setSiembraMuerta(false);
                        siembra.setVecesCosechada(0);
                        Thread cultivo = new Thread(usuario.getTerrenosThreadGroup(), new SembradioThread(siembra, usuario, siembra.getLabel()), "Siembra");
                        siembra.setThread(cultivo);
                        cultivo.start();
                    }
                } catch (IndexOutOfBoundsException f) {
                    JOptionPane.showMessageDialog(ventanaSembradioPanel, "Selecciona una Semilla");
                }
            }
        });
    }
}
