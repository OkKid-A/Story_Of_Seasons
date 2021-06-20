package Terreno;

import Usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Desierto extends Terreno {
    private ImageIcon arena;
    private JButton desiertoButton;
    private int numTerreno;

    public Desierto() {
        setIconoButton();
        setTerrenoButton();
    }

    public void setTerrenoButton() {
        this.desiertoButton = new JButton(arena);
        desiertoButton.setSize(50, 25);
        desiertoButton.setBorder(null);
    }

    public void setIconoButton() {
        try {
            this.arena = new ImageIcon(getClass().getClassLoader().getResourceAsStream("desierto-semillas.png").readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setNumeroTerreno(int numeroTerreno) {
        this.numTerreno = numeroTerreno;
    }

    public void setThisTerrenoButton(JButton linkedButton) {
        this.desiertoButton = linkedButton;
        linkedButton.setIcon(arena);
        linkedButton.setSize(50, 25);
    }

    public JButton getTerrenoButton() {
        return desiertoButton;
    }

    @Override
    public int getNumeroTerreno() {
        return this.numTerreno;
    }

    @Override
    public void setListener(Usuario usuario,JPanel jPanel) {
        desiertoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(jPanel, "Los Desiertos son casillas incapaces de producir.");
            }
        });
    }

    public ImageIcon getIconoButton() {
        return arena;
    }
}
