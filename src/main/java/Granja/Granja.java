package Granja;

import Granero.*;
import Limpieza.ColaCosechas;
import Terreno.*;
import Usuario.Usuario;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class Granja {
    private Terreno[] mapa;
    private ColaCosechas cosechas;
    private JButton[] botonesGranja;
    private boolean enProcesoCompra;

    public Granja() {
        enProcesoCompra = false;
        generarTerrenosInicio();
        coleccionarBotonesTerreno();
        cosechas = new ColaCosechas(mapa);
    }

    public void generarTerrenosInicio() {
        this.mapa = new Terreno[25];
        Random aleatorio = new Random();
        for (int k = 0; k < 25; k++) {
            int terrenoAEscoger = aleatorio.nextInt(20);
            if (terrenoAEscoger < 5) {
                mapa[k] = new Desierto();
            } else if (terrenoAEscoger < 12) {
                mapa[k] = new Lago();
            } else {
                mapa[k] = new Pradera();
            }
        }
    }

    public void anadirTerreno(JButton linkedButton) {
        Random aleatorio = new Random();
        int terrenoAEscoger = aleatorio.nextInt(20);
        Terreno terrenoAnadir;
        if (terrenoAEscoger < 5) {
            terrenoAnadir = new Desierto();
        } else if (terrenoAEscoger < 12) {
            terrenoAnadir = new Lago();
        } else {
            terrenoAnadir = new Pradera();
        }
        Terreno[] temp = new Terreno[mapa.length + 1];
        for (int k = 0; k < mapa.length; k++) {
            temp[k] = mapa[k];
        }
        temp[mapa.length] = terrenoAnadir;
        mapa = temp;
        mapa[mapa.length - 1].setThisTerrenoButton(linkedButton);
        try {
            mapa[mapa.length - 1].setNumeroTerreno(Integer.parseInt(linkedButton.getText()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static Terreno escogerTerreno() throws IOException {
        Terreno terrenoEscogido;
        Random claseTerreno = new Random();
        int clase = claseTerreno.nextInt(20);
        if (clase < 5) {
            terrenoEscogido = new Desierto();
        } else if (clase < 12) {
            terrenoEscogido = new Lago();
        } else {
            terrenoEscogido = new Pradera();
        }
        return terrenoEscogido;
    }

    public JButton[] coleccionarBotonesTerreno() {
        JButton[] terrenosComoBotones = new JButton[mapa.length];
        for (int k = 0; k < mapa.length; k++) {
            terrenosComoBotones[k] = mapa[k].getTerrenoButton();
        }
        this.botonesGranja = terrenosComoBotones;
        return terrenosComoBotones;
    }

    public Terreno[] getMapa() {
        return mapa;
    }

    public JButton[] dibujarTerrenoAumentado(JButton[] TerrenoAIncrementar, JPanel terrenoPanel) {
        int columnasNuevas = ((int) Math.sqrt(mapa.length)) + 2;
        terrenoPanel.setLayout(new GridLayout(columnasNuevas, columnasNuevas));
        JButton[] terrenoCuadriculabuttonsExtra = new JButton[columnasNuevas * columnasNuevas];
        int contador = 0;
        for (int k = 0; k < TerrenoAIncrementar.length; k++) {
            if (TerrenoAIncrementar[k] != null) {
                terrenoPanel.remove(TerrenoAIncrementar[k]);
            }
        }
        for (int k = 0; k < terrenoCuadriculabuttonsExtra.length; k++) {
            if (k < columnasNuevas || ((k + 1) % columnasNuevas) == 0 || ((k + 1) % columnasNuevas) == 1 || k > (columnasNuevas * (columnasNuevas - 1))) {
                terrenoCuadriculabuttonsExtra[k] = new JButton(String.valueOf(k));
                terrenoPanel.add(terrenoCuadriculabuttonsExtra[k]);
                terrenoCuadriculabuttonsExtra[k].setVisible(false);
            } else {
                terrenoCuadriculabuttonsExtra[k] = TerrenoAIncrementar[contador];
                mapa[contador].setNumeroTerreno(k);
                terrenoPanel.add(terrenoCuadriculabuttonsExtra[k]);
                contador++;
            }
        }
        botonesGranja = terrenoCuadriculabuttonsExtra;
        return terrenoCuadriculabuttonsExtra;
    }

    public JButton[] redrawTerrenos(JButton[] terrenoACopiar, JPanel terrenoPanel) {
        JButton[] terrenosCopiados = new JButton[terrenoACopiar.length];
        for (int k = 0; k < terrenoACopiar.length; k++) {
            if (terrenoACopiar[k] == null) {
            } else {
                terrenosCopiados[k] = mapa[k].getTerrenoButton();
                terrenoPanel.add(terrenosCopiados[k]);
            }
        }
        botonesGranja = terrenosCopiados;
        return terrenosCopiados;
    }

    public void mostrarTerrenosALaVenta(Usuario usuario, JPanel jPanel) {
        for (int k = 0; k < botonesGranja.length; k++) {
            if (botonesGranja[k].isVisible()) {
                botonesGranja[k].setVisible(false);
                if (enProcesoCompra) {
                    botonesGranja[k].removeActionListener(botonesGranja[k].getActionListeners()[0]);
                }
            } else if (!botonesGranja[k].isVisible()) {
                botonesGranja[k].setVisible(true);
                if (!enProcesoCompra && botonesGranja[k].getActionListeners().length == 0) {
                    botonesGranja[k].addActionListener(comprarTerrenoListener(botonesGranja[k], usuario, jPanel));
                }
            }
        }
        enProcesoCompra = !enProcesoCompra;
    }

    private ActionListener comprarTerrenoListener(JButton linkedButton, Usuario usuario, JPanel jPanel) {
        ActionListener comprarTerreno = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuario.getGold() < 20) {
                    JOptionPane.showMessageDialog(jPanel, "No tienes Suficiente Oro");
                } else {
                    usuario.setGold(usuario.getGold() -20);
                    anadirTerreno(linkedButton);
                    linkedButton.setText(null);
                    linkedButton.setBorder(null);
                    linkedButton.removeActionListener(linkedButton.getActionListeners()[0]);
                    mapa[mapa.length -1].setListener(usuario,jPanel);
                    linkedButton.setVisible(false);
                    mostrarTerrenosALaVenta(usuario,jPanel);
                }
            }
        };
        return comprarTerreno;
    }

    public void setListenersGranja(Usuario usuario, JPanel panel) {
        for (int k = 0; k < mapa.length; k++) {
            mapa[k].setListener(usuario, panel);
        }
    }

    public void setMapa(Terreno mapa, int index) {
        this.mapa[index] = mapa;
    }

    public ColaCosechas getCosechas() {
        return cosechas;
    }

    public JButton[] getBotonesGranja() {
        return botonesGranja;
    }
}
