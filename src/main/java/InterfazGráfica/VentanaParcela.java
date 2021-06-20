package InterfazGráfica;

import Animaciones.AnimalHPThread;
import Animales.Animal;
import Animales.AnimalVivo;
import Componentes.animalesEnParcelaPanel;
import Terreno.Parcela;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class VentanaParcela extends JFrame implements Ventanas {
    private JPanel ventanaParcelaPanel;
    private JPanel minimapaPanel;
    private JPanel mostrarAnimalesPanel;
    private JButton agrandarParcelaButton;
    private JButton anadirAnimalButton;
    private JLabel tamanoParcelaLabel;
    private JTable criasTable;
    private JList criasList;
    private JButton procesarParcelaButton;
    private JTable producirAnimalTable;
    private JButton limpiarParcelaButton;
    private JPanel animalTablaPanel;
    private JScrollPane tablaAnimalPane;
    private JButton procesarAlimentosButton;
    private JButton procesarMateriasPrimasButton;
    private JButton procesarConDestaceButton;
    private JPanel granjaPanel;
    private Usuario usuario;
    private Parcela parcela;

    public VentanaParcela(Usuario usuario, JPanel granjaPanel, Parcela parcela) {
        this.parcela = parcela;
        this.usuario = usuario;
        this.granjaPanel = granjaPanel;
        setAgrandarParcelaButtonListener();
        setAnadirAnimalButtonListener();
        setProcesarParcelaButtonListener();
        setProcesarAlimentosButtonListener();
        parcela.setTamanoLabel(tamanoParcelaLabel);
        setProcesarMateriasPrimasButtonListener();
        setProcesarConDestaceButtonListener();
        fixComponents(this, ventanaParcelaPanel);
    }

    @Override
    public void fixComponents(JFrame frame, JComponent component) {
        frame.setContentPane(component);
        frame.setTitle("Parcela");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        tamanoParcelaLabel.setText("Tamano: " + parcela.getTamano());
    }

    private void createUIComponents() {
        if (parcela.getCorralados() == null) {
            producirAnimalTable = new JTable();
            tablaAnimalPane = new JScrollPane(producirAnimalTable);
        } else {
            producirAnimalTable = parcela.getCorralados()[0].productosAnimal();
            tablaAnimalPane = new JScrollPane(producirAnimalTable);

        }
        minimapaPanel = parcela.dibujarMinimapa(usuario);
        criasTable = usuario.getGranero().getMercado().displayCantidadCrias(usuario);
        criasList = new JList(usuario.getGranero().getMercado().nombresCrias(usuario));
        mostrarAnimalesPanel = new animalesEnParcelaPanel(parcela, usuario, this);
    }

    public void setAgrandarParcelaButtonListener() {
        agrandarParcelaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parcela.mostrarPosiblesParcelas(minimapaPanel, usuario, granjaPanel);
            }
        });
    }

    private void setProcesarConDestaceButtonListener(){
        procesarConDestaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int k = 0; k < parcela.getCorralados().length; k++) {
                        if (!parcela.getCorralados()[k].isAnimalMuerto()) {
                            parcela.getCorralados()[0].producirDestace(usuario, producirAnimalTable,ventanaParcelaPanel,parcela,mostrarAnimalesPanel,(JButton) ((JPanel) mostrarAnimalesPanel).getComponent(0));
                        }
                    }
                } catch (NullPointerException f) {
                    JOptionPane.showMessageDialog(ventanaParcelaPanel, "No hay animales Disponible");
                }
            }
        });
    }

    private void setProcesarMateriasPrimasButtonListener(){
        procesarMateriasPrimasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int k = 0; k < parcela.getCorralados().length; k++) {
                        if (parcela.getCorralados()[k].isProduccionLista() && !parcela.getCorralados()[k].isAnimalMuerto()) {
                            parcela.getCorralados()[k].producirAnimalAlimentos(usuario, producirAnimalTable);
                        }
                    }
                } catch (NullPointerException f) {
                    JOptionPane.showMessageDialog(ventanaParcelaPanel, "No hay animales Disponible");
                }
            }
        });
    }

    private void setProcesarParcelaButtonListener() {
        procesarParcelaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    for (int k = 0; k < parcela.getCorralados().length; k++) {
                        if (parcela.getCorralados()[k].isProduccionLista() && !parcela.getCorralados()[k].isAnimalMuerto()) {
                            parcela.getCorralados()[k].producirAnimalAlimentos(usuario, producirAnimalTable);
                        }
                    }
                } catch (NullPointerException f) {
                    JOptionPane.showMessageDialog(ventanaParcelaPanel, "No hay animales Disponible");
                }
            }
        });
    }

    private void setProcesarAlimentosButtonListener(){
        procesarAlimentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int k = 0; k < parcela.getCorralados().length; k++) {
                        if (parcela.getCorralados()[k].isProduccionLista() && !parcela.getCorralados()[k].isAnimalMuerto()) {
                            parcela.getCorralados()[k].producirAnimal(usuario, producirAnimalTable);
                        }
                    }
                } catch (NullPointerException f) {
                    JOptionPane.showMessageDialog(ventanaParcelaPanel, "No hay animales Disponible");
                }
            }
        });
    }

    private void setAnadirAnimalButtonListener() {
        anadirAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (criasList.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(ventanaParcelaPanel, "No has seleccionado una Cria");
                } else {
                    Animal animal = usuario.getRepertorio().getListaAnimal().getObjetoDeLista(criasList.getSelectedIndex());
                    AnimalVivo animalVivo = new AnimalVivo(animal);
                    if (animal.getCria().getCantidadAlmacenada() < 1) {
                        JOptionPane.showMessageDialog(ventanaParcelaPanel, "No tienes de estas Crias");
                    } else if (parcela.getEspacioLibre() < animal.getTamano()) {
                        JOptionPane.showMessageDialog(ventanaParcelaPanel, "No hay suficiente Espacio");
                    } else {
                        animal.getCria().setCantidadAlmacenada(animal.getCria().getCantidadAlmacenada() - 1);
                        animalTablaPanel.setLayout(new BoxLayout(animalTablaPanel, 1));
                        animalTablaPanel.removeAll();
                        producirAnimalTable = animalVivo.productosAnimal();
                        animalTablaPanel.add(producirAnimalTable);
                        tablaAnimalPane = new JScrollPane(producirAnimalTable);
                        animalTablaPanel.add(tablaAnimalPane);
                        animalTablaPanel.revalidate();
                        parcela.anadirAnimal(animalVivo, ventanaParcelaPanel);
                        animalVivo.setAnimarHPAnimal(new AnimalHPThread(usuario, animalVivo));
                        Thread animalThread = new Thread(usuario.getTerrenosThreadGroup(), (Runnable) animalVivo.getAnimarHPAnimal(), "Animal" + animal.getEspecie());
                        animalThread.start();
                        ((animalesEnParcelaPanel) mostrarAnimalesPanel).anadirButton();
                        mostrarAnimalesPanel.repaint();
                        mostrarAnimalesPanel.revalidate();
                        mostrarAnimalesPanel.repaint();
                    }
                }
            }
        });
    }
}
