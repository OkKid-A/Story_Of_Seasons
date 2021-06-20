package InterfazGráfica;

import Animales.Cria;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMercado extends JFrame implements Ventanas {
    private JPanel ventanaMercadoPanel;
    private JTable alimentosAnimalesTable;
    private JTable fertilizantesTable;
    private JTable inventarioTabla;
    private JList alimentosList;
    private JButton comprarAlimentoButton;
    private JButton venderButton;
    private JList productosLista;
    private JList fertilizantesList;
    private JButton comparFertilizantesButton;
    private JScrollPane productosScrollPane;
    private JTable semillasTable;
    private JList semillasList;
    private JButton comprarSemillasButton;
    private JTable criasTable;
    private JButton comprarCriasButton;
    private JList criasList;
    private Usuario usuario;

    public VentanaMercado(Usuario usuario) {
        this.usuario = usuario;
        fixComponents(this, ventanaMercadoPanel);
        setComprarAlimentoButtonListener();
        setComparFertilizantesButtonListener();
        setVenderButtonListener();
        setComprarSemillasListener();
        setComprarCriasButtonListener();
    }

    private void createUIComponents() {
        criasTable = usuario.getGranero().getMercado().displayCantidadCrias(usuario);
        criasList = new JList(usuario.getGranero().getMercado().nombresCrias(usuario));
        alimentosAnimalesTable = usuario.getGranero().displayCantidadAlimentos();
        alimentosList = new JList(usuario.getGranero().getMercado().nombresCrias(usuario));
        fertilizantesTable = usuario.getGranero().displayCantidadFertilizantes();
        inventarioTabla = usuario.getGranero().displayInventarioProductos();
        productosLista = new JList(usuario.getGranero().displayProductos());
        productosScrollPane = new JScrollPane(productosLista);
        productosScrollPane.setPreferredSize(new Dimension(200, 100));
        alimentosList = new JList(usuario.getGranero().displayAlimentos());
        fertilizantesList = new JList(usuario.getGranero().displayFertilizantes());
        semillasTable = usuario.getGranero().getMercado().displaySemillas(usuario);
        semillasList = new JList(usuario.getGranero().getMercado().displayNombresSemillas(usuario));
    }

    private void setComprarAlimentoButtonListener() {
        comprarAlimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alimentosList.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(ventanaMercadoPanel, "No has seleccionado un Alimento.");
                } else if (usuario.getGold() < usuario.getGranero().getMercado().getListaAlimentos().getObjetoDeLista(alimentosList.getSelectedIndex()).getPrecio()) {
                    JOptionPane.showMessageDialog(ventanaMercadoPanel, "No tienes suficiente Oro.");
                } else {
                    usuario.setGold(usuario.getGold() - usuario.getGranero().getMercado().getListaAlimentos().getObjetoDeLista(alimentosList.getSelectedIndex()).getPrecio());
                    usuario.getGranero().setAlimentosPoseidos(usuario.getGranero().getAlimentosPoseidos(alimentosList.getSelectedIndex()) + 1, alimentosList.getSelectedIndex());
                    alimentosAnimalesTable.getModel().setValueAt(usuario.getGranero().getAlimentosPoseidos(alimentosList.getSelectedIndex()), alimentosList.getSelectedIndex(), 4);
                }
            }
        });
    }

    private void setComparFertilizantesButtonListener() {
        comparFertilizantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fertilizantesList.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(ventanaMercadoPanel, "No has seleccionado un Fertilizante.");
                } else if (usuario.getGold() < usuario.getGranero().getMercado().getListaFertilizantes().getObjetoDeLista(fertilizantesList.getSelectedIndex()).getPrecio()) {
                    JOptionPane.showMessageDialog(ventanaMercadoPanel, "No tienes suficiente Oro.");
                } else {
                    usuario.setGold(usuario.getGold() - usuario.getGranero().getMercado().getListaFertilizantes().getObjetoDeLista(fertilizantesList.getSelectedIndex()).getPrecio());
                    usuario.getGranero().setFertilizantesPoseidos(usuario.getGranero().getFertilizantesPoseidos(fertilizantesList.getSelectedIndex()) + 1, fertilizantesList.getSelectedIndex());
                    fertilizantesTable.getModel().setValueAt(usuario.getGranero().getFertilizantesPoseidos(fertilizantesList.getSelectedIndex()), fertilizantesList.getSelectedIndex(), 3);
                }
            }
        });
    }

    private void setVenderButtonListener() {
        venderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productosLista.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(ventanaMercadoPanel, "No has seleccionado un Producto.");
                } else if (usuario.getRepertorio().getListaProductos().getObjetoDeLista(productosLista.getSelectedIndex()).getCantidadExistente() == 0) {
                    JOptionPane.showMessageDialog(ventanaMercadoPanel, "No queda ningun Producto de esta clase.");
                } else {
                    double precio = usuario.getRepertorio().getListaProductos().getObjetoDeLista(productosLista.getSelectedIndex()).getPrecio();
                    usuario.setGold(usuario.getGold() + precio);
                    usuario.getReportes().agregarOroTotal(precio);
                    usuario.getReportes().restarNutricionTotal(usuario.getRepertorio().getListaProductos().getObjetoDeLista(productosLista.getSelectedIndex()).getHpRecuperada());
                    usuario.getRepertorio().getListaProductos().getObjetoDeLista(productosLista.getSelectedIndex()).setCantidadExistente(usuario.getRepertorio().getListaProductos().getObjetoDeLista(productosLista.getSelectedIndex()).getCantidadExistente() - 1);
                    inventarioTabla.getModel().setValueAt(usuario.getRepertorio().getListaProductos().getObjetoDeLista(productosLista.getSelectedIndex()).getCantidadExistente(), productosLista.getSelectedIndex(), 3);
                }
            }
        });
    }

    public void setComprarSemillasListener() {
        comprarSemillasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (semillasList.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(ventanaMercadoPanel, "No has seleccionado una Semilla");
                } else if (usuario.getGold() < usuario.getRepertorio().getListaPlantas().getObjetoDeLista(semillasList.getSelectedIndex()).getSemilla().getPrecio()) {
                    JOptionPane.showMessageDialog(ventanaMercadoPanel, "No tienes suficiente Oro.");
                } else {
                    usuario.getReportes().agregarSemillas(semillasList.getSelectedIndex());
                    usuario.setGold(usuario.getGold() - usuario.getRepertorio().getListaPlantas().getObjetoDeLista(semillasList.getSelectedIndex()).getSemilla().getPrecio());
                    usuario.getRepertorio().getListaPlantas().getObjetoDeLista(semillasList.getSelectedIndex()).getSemilla().setCantidadAlmacenada(usuario.getRepertorio().getListaPlantas().getObjetoDeLista(semillasList.getSelectedIndex()).getSemilla().getCantidadAlmacenada() + 1);
                    semillasTable.getModel().setValueAt(usuario.getRepertorio().getListaPlantas().getObjetoDeLista(semillasList.getSelectedIndex()).getSemilla().getCantidadAlmacenada(), semillasList.getSelectedIndex() , 3);
                }
            }
        });
    }

    public void setComprarCriasButtonListener() {
        comprarCriasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cria cria = usuario.getRepertorio().getListaAnimal().getObjetoDeLista(criasList.getSelectedIndex()).getCria();
                if (criasList.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(ventanaMercadoPanel, "No has seleccionado una Cria");
                } else if (usuario.getGold() < cria.getPrecioCompra()) {
                    JOptionPane.showMessageDialog(ventanaMercadoPanel, "No tienes suficiente Oro.");
                } else {
                    usuario.setGold(usuario.getGold() - cria.getPrecioCompra());
                    usuario.getReportes().agregarCriasCompradas(criasList.getSelectedIndex());
                    cria.setCantidadAlmacenada(cria.getCantidadAlmacenada() + 1);
                    criasTable.getModel().setValueAt(cria.getCantidadAlmacenada(), criasList.getSelectedIndex(), 2);
                }
            }
        });
    }

    @Override
    public void fixComponents(JFrame frame, JComponent component) {
        frame.setContentPane(component);
        frame.setTitle("Mercado");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }


}