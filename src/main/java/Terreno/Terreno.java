package Terreno;

import Usuario.Usuario;

import javax.swing.*;
import java.awt.image.BufferedImage;

abstract public class Terreno {
    private JButton terrenoButton;
    private ImageIcon icono;
    private int numeroTerreno;

    public Terreno() {
    }

    public abstract void setThisTerrenoButton(JButton linkedButton);

    public abstract void setTerrenoButton();

    public abstract void setIconoButton();

    public abstract void setNumeroTerreno(int numeroTerreno);

    public abstract void setListener(Usuario usuario,JPanel jpanel);

    public abstract ImageIcon getIconoButton();

    public abstract JButton getTerrenoButton();

    public abstract int getNumeroTerreno();

    public ImageIcon getIcono() {
        return icono;
    }

}
