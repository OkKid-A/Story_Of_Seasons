package Animaciones;

import InterfazGráfica.VentanaMenuPrincipal;
import Usuario.*;

import javax.swing.*;
import java.awt.*;

public class AnimarHP extends JPanel implements Runnable {
    private int width = 100;
    private Usuario usuario;
    private JLabel vida;

    public AnimarHP(Usuario usuario) {
        this.usuario = usuario;
        this.width = usuario.getHp();
        this.setPreferredSize(new Dimension(100,21));
        this.setLayout(new GridLayout());;
        this.vida = new JLabel("HP");
        this.add(vida);
    }

    public boolean revisarHP(){
        if (usuario.getHp()>0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawRect(0, 0, 100, 20);
        g.fillRect(0, 0, width=usuario.getHp(), 20);
    }

    @Override
    public void run() {
        while (revisarHP()) {
            usuario.setHp(usuario.getHp()-1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
        JOptionPane.showMessageDialog(this,"Has Muerto :c");
        SwingUtilities.windowForComponent(this).dispose();
        usuario.getReportes().grabarDatosPartida(usuario);
        Reinicio reset = new Reinicio(usuario);
        VentanaMenuPrincipal ventanaMenuPrincipal = new VentanaMenuPrincipal(usuario);
    }
}
