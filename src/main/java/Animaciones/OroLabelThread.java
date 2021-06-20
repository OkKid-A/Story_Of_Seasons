package Animaciones;

import Usuario.Usuario;

import javax.swing.*;

public class OroLabelThread extends JLabel implements Runnable{
    private Usuario usuario;

    public OroLabelThread(Usuario usuario){
        super();
        this.usuario = usuario;
        this.setText("Tu oro: " + usuario.getGold());
    }
    @Override
    public void run() {
        while (true) {
            this.setText("Tu oro: " + usuario.getGold());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
