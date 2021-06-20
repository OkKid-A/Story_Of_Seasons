package Terreno;

import Animales.Animal;
import Animales.AnimalVivo;
import InterfazGráfica.VentanaParcela;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Parcela extends Terreno {
    private JButton parcelaButton;
    private ImageIcon corral;
    private AnimalVivo corralados[];
    private Pradera[] tamanoCorral;
    private double espacioOcupado;
    private int numTerreno;
    private JLabel tamanoLabel;

    public Parcela(Pradera pradera) {
        setIconoButton();
        setTerrenoButton();
        this.tamanoCorral = new Pradera[]{pradera};
        espacioOcupado = 0;
    }

    public Parcela(Pradera[] praderas) {
        setIconoButton();
        setTerrenoButton();
        this.tamanoCorral = praderas;
        espacioOcupado = 0;
    }

    @Override
    public void setThisTerrenoButton(JButton linkedButton) {

    }

    @Override
    public void setTerrenoButton() {
        parcelaButton = new JButton(corral);
        parcelaButton.setPreferredSize(new Dimension(50, 75));
        parcelaButton.setBorder(null);
    }

    @Override
    public void setIconoButton() {
        try {
            this.corral = new ImageIcon(getClass().getClassLoader().getResourceAsStream("Horse-arena-nz_3.png").readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void convertirButtonsAndListeners(Usuario usuario, JPanel panelGranja) {
        for (int k = 0; k < tamanoCorral.length; k++) {
            tamanoCorral[k].cambiarIcono(corral);

            if (tamanoCorral[k].getTerrenoButton().getActionListeners().length == 1) {
                tamanoCorral[k].getTerrenoButton().removeActionListener((tamanoCorral[k].getTerrenoButton().getActionListeners()[0]));
                tamanoCorral[k].setThisListener(usuario, panelGranja, parcelaListener(usuario, panelGranja));
            }
        }
    }

    private ActionListener parcelaListener(Usuario usuario, JPanel panelGranja) {
        ActionListener parcelaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaParcela ventanaParcela = new VentanaParcela(usuario, panelGranja, redundar());
            }
        };
        return parcelaListener;
    }

    public void anadirAnimal(AnimalVivo nuevoAnimal, JPanel jPanel) {
        boolean animalMismoTipo = false;
        if (corralados == null) {
            animalMismoTipo = true;
        } else if (nuevoAnimal.getAnimal() == corralados[0].getAnimal()){
            animalMismoTipo = true;
        }
        if (nuevoAnimal.getAnimal().getTamano() <= getEspacioLibre() && animalMismoTipo) {
            AnimalVivo[] temp;
            int animalesEnCorral;
            if (corralados == null) {
                temp = new AnimalVivo[1];
                animalesEnCorral = 0;
            } else {
                temp = new AnimalVivo[corralados.length + 1];
                animalesEnCorral = corralados.length;
            }
            for (int k = 0; k < animalesEnCorral; k++) {
                temp[k] = corralados[k];
            }
            temp[animalesEnCorral] = nuevoAnimal;
            espacioOcupado = espacioOcupado + nuevoAnimal.getAnimal().getTamano();
            corralados = temp;
        } else if (nuevoAnimal.getAnimal() != corralados[0].getAnimal()) {
            JOptionPane.showMessageDialog(jPanel, "No puedes poner animales de distinta especie en un solo corral");
        } else {
            JOptionPane.showMessageDialog(jPanel, "No tienes suficiente Espacio");
        }
    }


    public void destazarAnimal() {

    }

    public void collectarRecursos() {

    }

    public void setNumeroTerreno(int numTerreno) {
        this.numTerreno = numTerreno;
    }

    @Override
    public void setListener(Usuario usuario, JPanel jpanel) {
        this.parcelaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaParcela ventanaParcela = new VentanaParcela(usuario, jpanel, redundar());
            }
        });
    }

    public void setThisListener(Usuario usuario, JPanel jpanel, JButton buttonChange) {
        buttonChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaParcela ventanaParcela = new VentanaParcela(usuario, jpanel, redundar());
            }
        });
    }

    private Parcela redundar() {
        return this;
    }


    @Override
    public ImageIcon getIconoButton() {
        return null;
    }

    @Override
    public JButton getTerrenoButton() {
        return null;
    }

    public int getNumeroTerreno() {
        return numTerreno;
    }

    public void agrandarCorral(Pradera pradera) {
        Pradera[] temp = new Pradera[tamanoCorral.length + 1];
        for (int k = 0; k < tamanoCorral.length; k++) {
            temp[k] = tamanoCorral[k];
        }
        temp[tamanoCorral.length] = pradera;
        tamanoCorral = temp;
        tamanoLabel.setText("Tamano: " + tamanoCorral.length);
    }

    private boolean perteneceParcela(JButton comparado) {
        boolean pertenece = false;
        for (int k = 0; k < tamanoCorral.length; k++) {
            if (comparado.equals(tamanoCorral[k].getTerrenoButton())) {
                pertenece = true;
                break;
            }
        }
        return pertenece;
    }

    public JPanel dibujarMinimapa(Usuario usuario) {
        int contador = 0;
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout((int) Math.sqrt(usuario.getGranja().getBotonesGranja().length), (int) Math.sqrt(usuario.getGranja().getBotonesGranja().length)));
        for (int k = 0; k < usuario.getGranja().getBotonesGranja().length; k++) {
            if (perteneceParcela(usuario.getGranja().getBotonesGranja()[k])) {
                JButton botonParcela = new JButton(contador + 1 + "");
                botonParcela.setPreferredSize(new Dimension(15, 10));
                jPanel.add(botonParcela);
                contador++;
            } else {
                JButton relleno = new JButton();
                relleno.setVisible(false);
                relleno.setPreferredSize(new Dimension(15, 10));
                jPanel.add(relleno);
            }
        }
        return jPanel;
    }

    public void setTamanoLabel(JLabel tamanoLabel) {
        this.tamanoLabel = tamanoLabel;
    }

    public int esAdyacente(int k, Usuario usuario) {
        int esAdyacente = -1;
        int fila = (int) Math.sqrt(usuario.getGranja().getBotonesGranja().length + 1);
        for (int o = 0; o < usuario.getGranja().getMapa().length; o++) {
            if (usuario.getGranja().getMapa()[o].getClass().getName() == "Terreno.Pradera" && usuario.getGranja().getMapa()[o].getNumeroTerreno() == k) {
                for (int l = 0; l < tamanoCorral.length; l++) {
                    int comparable = tamanoCorral[l].getNumeroTerreno() - k;
                    if (comparable < 0) {
                        comparable = 0 - comparable;
                    }
                    if (comparable == fila || comparable == 1 || comparable == fila + 1 || comparable == fila - 1) {
                        esAdyacente = o;
                        break;
                    }
                }
                break;
            }
        }
        return esAdyacente;
    }

    public void mostrarPosiblesParcelas(JPanel jPanel, Usuario usuario, JPanel granjaPanel) {
        int numMapa = -1;
        for (int k = 0; k < usuario.getGranja().getBotonesGranja().length; k++) {
            numMapa = esAdyacente(k, usuario);
            if (numMapa > -1 && !(((Pradera) usuario.getGranja().getMapa()[numMapa]).isCorral())) {
                jPanel.getComponents()[k].setVisible(true);
                int pasar = k;
                if (((JButton) jPanel.getComponent(k)).getActionListeners().length == 0) {
                    ((JButton) jPanel.getComponents()[k]).addActionListener(minimapaListener(pasar, numMapa, jPanel, usuario, granjaPanel));
                }
            }
        }
    }

    public ActionListener minimapaListener(int pasar, int numMapa, JPanel jPanel, Usuario usuario, JPanel granjaPanel) {
        ActionListener minimapaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((Pradera) usuario.getGranja().getMapa()[numMapa]).isCorral()) {
                    JOptionPane.showMessageDialog(jPanel, "Ya es parte de la parcela");
                } else if (usuario.getGold() > 15) {
                    usuario.setGold(usuario.getGold() - 15);
                    granjaPanel.removeAll();
                    redundar().agrandarCorral((Pradera) usuario.getGranja().getMapa()[numMapa]);
                    ((Pradera) usuario.getGranja().getMapa()[numMapa]).setCorral(true);
                    redundar().convertirButtonsAndListeners(usuario, granjaPanel);
                    usuario.getGranja().dibujarTerrenoAumentado(usuario.getGranja().coleccionarBotonesTerreno(), granjaPanel);
                    granjaPanel.repaint();
                    ((JButton) jPanel.getComponents()[pasar]).setText(tamanoCorral.length + "");
                    limpiarMinimapa(jPanel, usuario);
                } else {
                    JOptionPane.showMessageDialog(jPanel, "No tienes suficiente Oro");
                    limpiarMinimapa(jPanel, usuario);
                }
            }
        };
        return minimapaListener;
    }

    private void limpiarMinimapa(JPanel jPanel, Usuario usuario) {
        jPanel = dibujarMinimapa(usuario);
    }

    public void agrandarCorral(Pradera[] praderas) {
        Pradera[] temp = new Pradera[tamanoCorral.length + praderas.length];
        for (int k = 0; k < tamanoCorral.length; k++) {
            temp[k] = tamanoCorral[k];
        }
        for (int k = 0; k < praderas.length; k++) {
            temp[tamanoCorral.length + k] = praderas[k];
        }
        tamanoCorral = temp;
    }

    public int getTamano() {
        return tamanoCorral.length;
    }

    public double getEspacioLibre() {
        return tamanoCorral.length - espacioOcupado;
    }

    public AnimalVivo[] getCorralados() {
        return corralados;
    }

    public void matarAnimal(AnimalVivo animalVivo) {
        if (corralados.length > 1) {
            int numAnimalVivo = 0;
            for (int k = 0; k < corralados.length; k++) {
                if (corralados[k].equals(animalVivo)) {
                    numAnimalVivo = k;
                    break;
                }
            }
            AnimalVivo[] temp = new AnimalVivo[corralados.length - 1];
            int contador = 0;
            for (int k = 0; k < temp.length; k++) {
                if (k != numAnimalVivo) {
                    temp[contador] = corralados[k];
                    contador++;
                }
            }
            corralados = temp;
        } else {
            corralados = null;
        }
    }

    public void setEspacioOcupado(double espacioOcupado) {
        this.espacioOcupado = espacioOcupado;
    }

    public double getEspacioOcupado() {
        return espacioOcupado;
    }
}
