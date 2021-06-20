package InterfazGráfica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Animaciones.AnimarHP;
import Animaciones.OroLabelThread;
import Usuario.*;

public class VentanaGranja extends JFrame implements Ventanas {
    private JPanel granjaPanel;
    private JPanel terrenopanel;
    private JButton cosecharButton;
    private JButton mercadoButton;
    private JButton GraneroButton;
    private JButton comprarTerrenoButton;
    private JPanel AnimarHPPanel;
    private JPanel exteriorPanel;
    private JButton salirButton;
    private JLabel oroLabel;
    private JLabel nicknameLabel;
    private JButton[] TerrenoCuadriculabuttons;
    private Usuario usuario;

    public VentanaGranja(Usuario usuario) {
        this.usuario = usuario;
        setGraneroButton();
        setMercadoButton();
        setCosecharButton();
        nicknameLabel = new JLabel(this.usuario.getNickname());
        nicknameLabel.setText(this.usuario.getNickname());
        usuario.getGranja().dibujarTerrenoAumentado(usuario.getGranja().getBotonesGranja(), terrenopanel);
        usuario.getGranja().setListenersGranja(usuario, terrenopanel);
        AnimarHPPanel = new AnimarHP(usuario);
        Thread animarhp = new Thread(usuario.getTerrenosThreadGroup(),(Runnable) AnimarHPPanel,"AnimacionHP");
        Ventanas.fixComponents(this, AnimarHPPanel);
        Ventanas.fixComponents(this, granjaPanel);
        animarhp.start();
        setSalirButtonListener();
        setComprarTerrenoButtonListener();
        Thread oroThread = new Thread(usuario.getTerrenosThreadGroup(),(Runnable) oroLabel,"Oro");
        oroThread.start();
    }

    public void setGraneroButton() {
        GraneroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaGranero granero = new VentanaGranero(usuario);
            }
        });
    }

    public void setMercadoButton() {
        mercadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaMercado mercado = new VentanaMercado(usuario);
            }
        });
    }

    private void setCosecharButton() {
        cosecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuario.getGranja().getCosechas().getTop() == null) {
                    JOptionPane.showMessageDialog(exteriorPanel, "No hay Nada que Cosechar aun");
                } else {
                    usuario.getGranja().getCosechas().getTop().getThread().stop();
                    usuario.getGranja().getCosechas().atender(usuario, exteriorPanel);
                }
            }
        });
    }

    @Override
    public void fixComponents(JFrame frame, JComponent component) {

    }

    private void setSalirButtonListener() {
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaMenuPrincipal ventanaMenuPrincipal = new VentanaMenuPrincipal(usuario);
                usuario.getReportes().grabarDatosPartida(usuario);
                usuario.getTerrenosThreadGroup().stop();
                Reinicio reinicio = new Reinicio(usuario);
                dispose();
            }
        });
    }

    private void setComprarTerrenoButtonListener() {
        comprarTerrenoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuario.getGranja().getMapa().length == usuario.getGranja().getBotonesGranja().length) {
                    usuario.getGranja().dibujarTerrenoAumentado(usuario.getGranja().getBotonesGranja(),terrenopanel);
                }
                usuario.getGranja().mostrarTerrenosALaVenta(usuario, terrenopanel);
            }
        });
    }

    private void createUIComponents() {
        oroLabel = new OroLabelThread(usuario);
        OroLabelThread oroLabelThread = new OroLabelThread(usuario);
        Thread oroThred = new Thread(usuario.getTerrenosThreadGroup(),oroLabelThread,"Oro");
        oroThred.start();
        oroLabel = oroLabelThread;
        nicknameLabel = new JLabel(this.usuario.getNickname());
        nicknameLabel.setText(this.usuario.getNickname());
        nicknameLabel.revalidate();
    }
}
