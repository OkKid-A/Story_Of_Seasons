package Animaciones;

import Terreno.Lago;

import javax.swing.*;

public class AnimarPecesVivos extends JLabel implements Runnable {
    private final String PECES = "Peces en el lago: ";
    private Lago lago;
    private JPanel jPanel;

    public AnimarPecesVivos(Lago lago, JPanel jPanel) {
        super();
        this.lago = lago;
        this.jPanel = jPanel;
        this.setText(PECES + lago.getPecesVivos());
    }


    @Override
    public void run() {
        while (true) {
            this.setText(PECES + lago.getPecesVivos());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
