package Componentes;

import Animales.Animal;
import Animales.AnimalVivo;
import InterfazGráfica.VentanaAnimal;
import InterfazGráfica.VentanaParcela;
import InterfazGráfica.Ventanas;
import Terreno.Parcela;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class animalesEnParcelaPanel extends JPanel {
    private Parcela parcela;
    private Usuario usuario;
    private VentanaParcela parcelaW;

    public animalesEnParcelaPanel(Parcela parcela, Usuario usuario, VentanaParcela parcelaW) {
        super();
        this.parcela = parcela;
        this.usuario = usuario;
        this.parcelaW = parcelaW;
        redundar().setLayout(new BoxLayout(this, 1));
        drawAnimalesButtons();
        redundar().setVisible(true);
    }

    public void anadirButton() {
        redundar().removeAll();
        drawAnimalesButtons();
    }

    private void drawAnimalesButtons() {
        int lenght;
        if (parcela.getCorralados() == null) {
            lenght = 0;
        } else {
            lenght = parcela.getCorralados().length;
        }
        System.out.println(lenght);
        for (int k = 0; k < lenght; k++) {
            int resize = (int) (Math.sqrt(parcela.getCorralados()[k].getAnimal().getTamano() * 100));
            AnimalVivo animalVivo = parcela.getCorralados()[k];
            JButton animalButton = new JButton(new ImageIcon(((parcela.getCorralados()[k].getAnimal().getIconoAnimal()).getImage().getScaledInstance(resize + 20, resize + 20, Image.SCALE_SMOOTH))));
            animalButton.setPreferredSize(new Dimension(resize, resize));
            animalButton.setBorder(null);
            animalButton.setVisible(true);
            animalButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    VentanaAnimal ventanaAnimal = new VentanaAnimal(usuario, animalVivo, parcela, redundar(), animalButton);
                }
            });
            redundar().add(animalButton);
            redundar().revalidate();
            parcelaW.revalidate();
        }
    }

    private ComponentAdapter resizeInstruction(ImageIcon icon) {
        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();

        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        ComponentAdapter resizeInstruction = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                JButton btn = (JButton) e.getComponent();
                Dimension size = btn.getSize();
                Insets insets = btn.getInsets();
                size.width -= insets.left + insets.right;
                size.height -= insets.top + insets.bottom;
                if (size.width > size.height) {
                    size.width = -1;
                } else {
                    size.height = -1;
                }
                Image scaled = bi.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(scaled));
            }
        };
        return resizeInstruction;
    }


    private JPanel redundar() {
        return this;
    }
}
