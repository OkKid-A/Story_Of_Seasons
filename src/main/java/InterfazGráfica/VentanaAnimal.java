package InterfazGráfica;

import Animales.AnimalOmnivoro;
import Animales.AnimalVivo;
import Componentes.AlimentosLista;
import Componentes.AlimentosTabla;
import Componentes.TiempoProduccionLabel;
import Terreno.Parcela;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAnimal extends JFrame implements Ventanas {
    private Usuario usuario;
    private AnimalVivo animalVivo;
    private JPanel ventanaAnimalPanel;
    private JPanel vidaAnimalPanel;
    private JButton producirButton;
    private JButton destazarButton;
    private JLabel edadLabel;
    private JTable alimentosTable;
    private JList alimentosList;
    private JButton alimentarButton;
    private JTable produccionTable;
    private JLabel tiempoParaProducirLabel;
    private Parcela parcela;
    private JPanel animalesButtons;
    private JButton animalButton;

    public VentanaAnimal(Usuario usuario, AnimalVivo animalVivo, Parcela parcela, JPanel animalesButtons, JButton animalButton) {
        this.usuario = usuario;
        this.animalVivo = animalVivo;
        this.parcela = parcela;
        this.animalesButtons = animalesButtons;
        this.animalButton = animalButton;
        fixComponents(this, ventanaAnimalPanel);
        setAlimentarButtonListener();
        setProucirButtonListener();
        setDestazarButton();
    }


    @Override
    public void fixComponents(JFrame frame, JComponent component) {
        frame.add(component);
        frame.setTitle("Animal");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        edadLabel.setText(animalVivo.etapaCriaVivo());
        animalVivo.setEdadLabel(edadLabel);
    }

    private void createUIComponents() {
        alimentosTable = (new AlimentosTabla(animalVivo.getAnimal(), usuario)).tipoTable();
        alimentosList = (new AlimentosLista(usuario, animalVivo.getAnimal())).tipoLista(animalVivo.getAnimal());
        vidaAnimalPanel = animalVivo.getAnimarHPAnimal();
        produccionTable = animalVivo.productosAnimal();
        tiempoParaProducirLabel = new TiempoProduccionLabel();
        animalVivo.setTiempoProduccionLabel((TiempoProduccionLabel) tiempoParaProducirLabel);
    }

    private void setAlimentarButtonListener() {
        alimentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int curar = 0;
                if (alimentosList.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(ventanaAnimalPanel, "No has seleccionado un Alimento");
                } else if (usuario.getGranero().getAlimentosPoseidos(alimentosList.getSelectedIndex()) <= 0) {
                    JOptionPane.showMessageDialog(ventanaAnimalPanel, "No tienes este Alimento");
                } else {
                    if (animalVivo.getAnimal() instanceof AnimalOmnivoro) {
                        int k = 3;
                        if ((100 - animalVivo.gethPAnimal()) < usuario.getGranero().getMercado().getListaAlimentos().getObjetoDeLista(alimentosList.getSelectedIndex() + k).getNutricion()) {
                            animalVivo.sethPAnimal(100);
                        } else {
                            animalVivo.sethPAnimal(animalVivo.gethPAnimal() + usuario.getGranero().getMercado().getListaAlimentos().getObjetoDeLista(alimentosList.getSelectedIndex() + k).getNutricion());
                        }
                        usuario.getGranero().setAlimentosPoseidos(usuario.getGranero().getAlimentosPoseidos(alimentosList.getSelectedIndex() + k) - 1, alimentosList.getSelectedIndex() + k);
                    } else {
                        if ((100 - animalVivo.gethPAnimal()) < usuario.getGranero().getMercado().getListaAlimentos().getObjetoDeLista(alimentosList.getSelectedIndex()).getNutricion()) {
                            animalVivo.sethPAnimal(100);
                        } else {
                            animalVivo.sethPAnimal(animalVivo.gethPAnimal() + usuario.getGranero().getMercado().getListaAlimentos().getObjetoDeLista(alimentosList.getSelectedIndex()).getNutricion());
                        }
                        usuario.getGranero().setAlimentosPoseidos(usuario.getGranero().getAlimentosPoseidos(alimentosList.getSelectedIndex()) - 1, alimentosList.getSelectedIndex());
                    }
                }
            }
        });
    }

    private void setProucirButtonListener() {
        producirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animalVivo.isProduccionLista()) {
                    JOptionPane.showMessageDialog(ventanaAnimalPanel, "Aun no puedes procesar este Animal");
                } else if (animalVivo.isAnimalMuerto()){
                    JOptionPane.showMessageDialog(ventanaAnimalPanel,"Este animal Esta Muerto");
                } else {
                    animalVivo.producirAnimal(usuario, produccionTable);
                    produccionTable = animalVivo.productosAnimal();
                    ventanaAnimalPanel.repaint();
                    redundar().repaint();
                    animalVivo.setProduccionLista(false);
                }
            }
        });
    }

    private void setDestazarButton() {
        destazarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animalVivo.isAnimalMuerto()) {
                    JOptionPane.showMessageDialog(ventanaAnimalPanel, "Ya esta muerto");
                } else {
                    animalVivo.producirDestace(usuario, produccionTable, ventanaAnimalPanel, parcela,animalesButtons,animalButton);
                    redundar().dispose();
                }
            }
        });
    }

    private JFrame redundar() {
        return this;
    }
}
