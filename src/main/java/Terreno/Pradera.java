package Terreno;

import InterfazGráfica.VentanaPradera;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class Pradera extends Terreno {
    private JButton praderaButton;
    private ImageIcon grama;
    private int fertilidad;
    private int numTerreno;
    private boolean isCorral;
    private ActionListener praderaListener;

    public Pradera() {
        super();
        setIconoButton();
        setTerrenoButton();
        randomizarFertilidad();
        isCorral = false;
    }

    public void setTerrenoButton() {
        this.praderaButton = new JButton(grama);
        this.praderaButton.setPreferredSize(new Dimension(50, 25));
        this.praderaButton.setBorder(null);
    }

    public void setIconoButton() {
        try {
            this.grama = new ImageIcon(getClass().getClassLoader().getResourceAsStream("pradera.png").readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cambiarIcono(ImageIcon iconoNuevo){
        this.praderaButton.setIcon(iconoNuevo);
    }



    public void setThisListener(Usuario usuario,JPanel jPanel, ActionListener nuevoListener){
        this.praderaButton.removeActionListener(praderaListener);
        this.praderaButton.addActionListener(nuevoListener);
    }

    @Override
    public void setNumeroTerreno(int numeroTerreno) {
        this.numTerreno = numeroTerreno;
    }

    @Override
    public void setListener(Usuario usuario, JPanel jpanel) {
        this.praderaButton.addActionListener(createListener(usuario,jpanel));
    }

    private ActionListener createListener(Usuario usuario, JPanel jpanel){
        ActionListener praderaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPradera ventanaPradera = new VentanaPradera(usuario, redundar(), jpanel);
            }
        };
        this.praderaListener = praderaListener;
        return praderaListener;
    }



    private void randomizarFertilidad() {
        Random fert = new Random();
        this.fertilidad = fert.nextInt(5) + 1;
    }

    @Override
    public void setThisTerrenoButton(JButton linkedButton) {
        this.praderaButton = linkedButton;
        linkedButton.setIcon(grama);
        linkedButton.setSize(50, 25);
    }

    public JButton getTerrenoButton() {
        return praderaButton;
    }

    @Override
    public int getNumeroTerreno() {
        return this.numTerreno;
    }

    public ImageIcon getIconoButton() {
        return grama;
    }

    public int getFertilidad() {
        return fertilidad;
    }

    private Pradera redundar() {
        return this;
    }

    public int encontrarPradera(Usuario usuario) {
        int numeroPradera = 0;
        for (int k = 0; k < usuario.getGranja().getMapa().length; k++) {
            if (usuario.getGranja().getMapa()[k] == this) {
                numeroPradera = k;
                break;
            }
        }
        return numeroPradera;
    }

    public void setCorral(boolean corral) {
        isCorral = corral;
    }

    public boolean isCorral() {
        return isCorral;
    }
}

