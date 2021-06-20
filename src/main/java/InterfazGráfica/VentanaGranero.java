package InterfazGráfica;

import Usuario.Usuario;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaGranero extends JFrame implements Ventanas {
    private JList seleccionarProductoLista;
    private JTable inventarioTabla;
    private JLabel graneroInfoLabel1;
    private JButton comerButton;
    private JPanel listaPanel;
    private JPanel tablaPanel;
    private JPanel mercadoExistenciaPanel;
    private JTable alimentosAnimalesTable;
    private JTable fertilizantesTable;
    private JScrollPane productoScrollPane;
    private JTable semillasDisponiblesTable;
    private JTable criasTable;
    private Usuario usuario;

    public VentanaGranero(Usuario usuario) {
        this.usuario = usuario;
        fixComponents(this, listaPanel);
        setSeleccionarProductoListaListener();
        setComerListener();
    }

    @Override
    public void fixComponents(JFrame frame, JComponent component) {
        frame.setContentPane(component);
        frame.setTitle("Granero");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void setComerListener() {
        comerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (seleccionarProductoLista.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(listaPanel, "No has seleccionado un producto.");
                }  else if (usuario.getHp() == 100) {
                    JOptionPane.showMessageDialog(listaPanel,"No puedes curarte mas.");
                } else if ((100 - usuario.getHp()) < usuario.getRepertorio().getListaProductos().getObjetoDeLista(seleccionarProductoLista.getSelectedIndex()).getHpRecuperada()) {
                    int curacion = 100 - usuario.getHp();
                    usuario.getReportes().agregarVidaCuradaTotal(curacion);
                    JOptionPane.showMessageDialog(listaPanel,"Te has curado: " + (curacion));
                    usuario.setHp(100);
                    inventarioTabla.getModel().setValueAt(usuario.getRepertorio().getListaProductos().getObjetoDeLista(seleccionarProductoLista.
                            getSelectedIndex()).getCantidadExistente(), seleccionarProductoLista.getSelectedIndex() , 3);
                }else {
                    int curacion = usuario.getGranero().curarUsuario(seleccionarProductoLista.getSelectedIndex(), listaPanel);
                    usuario.setHp(curacion + usuario.getHp());
                    usuario.getReportes().agregarVidaCuradaTotal(curacion);
                    inventarioTabla.getModel().setValueAt(usuario.getRepertorio().getListaProductos().getObjetoDeLista(seleccionarProductoLista.
                            getSelectedIndex()).getCantidadExistente(), seleccionarProductoLista.getSelectedIndex(), 3);

                }
            }
        });
    }

    public void setSeleccionarProductoListaListener() {
        seleccionarProductoLista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (seleccionarProductoLista.getSelectedIndex() == -1) {
                        comerButton.setEnabled(false);
                    } else {
                        comerButton.setEnabled(true);
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        inventarioTabla = usuario.getGranero().displayInventarioProductos();
        seleccionarProductoLista = new JList(usuario.getGranero().displayProductos());
        criasTable = usuario.getGranero().getMercado().displayCantidadCrias(usuario);
        alimentosAnimalesTable = usuario.getGranero().displayCantidadAlimentos();
        fertilizantesTable = usuario.getGranero().displayCantidadFertilizantes();
        productoScrollPane = new JScrollPane(seleccionarProductoLista);
        productoScrollPane.setPreferredSize(new Dimension(200, 100));
        semillasDisponiblesTable = usuario.getGranero().getMercado().displaySemillas(usuario);
    }
}
