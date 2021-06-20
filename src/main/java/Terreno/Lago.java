package Terreno;

import InterfazGráfica.VentanaLago;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Lago extends Terreno implements Runnable {
    private int pecesVivos;
    private int numTerreno;
    private ImageIcon agua;
    private ImageIcon iconoLagoBarco;
    private JButton lagoButton;
    private boolean lagoConBotePesquero;
    private BarcoPesquero barcoEnLago;
    private boolean tieneThread;

    public Lago() {
        this.pecesVivos = 100;
        lagoConBotePesquero = false;
        setIconoButton();
        setTerrenoButton();
        this.tieneThread = false;
    }

    public void comprarBarcoPesquero() {
        this.barcoEnLago = new BarcoPesquero();
    }

    public boolean lagoVacio() {
        if (this.pecesVivos <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setTerrenoButton() {
        this.lagoButton = new JButton(agua);
        lagoButton.setSize(50, 25);
        lagoButton.setBorder(null);

    }

    public void setIconoButton() {
        try {
            this.agua = new ImageIcon(getClass().getClassLoader().getResourceAsStream("laguna-chicabal-5-Fotografía-Jhonathan-F-560x577.png").readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setNumeroTerreno(int numeroTerreno) {
        this.numTerreno = numeroTerreno;
    }

    @Override
    public void setListener(Usuario usuario, JPanel jpanel) {
        this.lagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaLago ventanaLago = new VentanaLago(usuario, redundar());
            }
        });
    }

    public ImageIcon getIconoButton() {
        return agua;
    }

    public JButton getTerrenoButton() {
        return lagoButton;
    }

    @Override
    public int getNumeroTerreno() {
        return this.numTerreno;
    }

    public void setThisTerrenoButton(JButton linkedButton) {
        this.lagoButton = linkedButton;
        linkedButton.setIcon(agua);
        linkedButton.setSize(50, 25);
    }

    public boolean isLagoConBotePesquero() {
        if (this.barcoEnLago == null) {
            return false;
        } else {
            return true;
        }
    }

    public String hayBarcoPesquero() {
        if (isLagoConBotePesquero()) {
            return "El Lago cuenta con un barco Pesquero";
        } else {
            return "El lago esta desocupado";
        }
    }

    public void cambiarIcono() {

    }

    public void removerBarco() {
        this.barcoEnLago = null;
    }

    public void pescarPecesVivos() {
        this.pecesVivos = pecesVivos - 1;
    }

    public int getPecesVivos() {
        return pecesVivos;
    }

    public Lago redundar() {
        return this;
    }

    public BarcoPesquero getBarcoEnLago() {
        return barcoEnLago;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (pecesVivos < 100 && !isLagoConBotePesquero()) {
                this.pecesVivos++;
            } else if (this.pecesVivos == 100 && !isLagoConBotePesquero()) {
                setIconoButton();
            } else if (this.pecesVivos > 0 && isLagoConBotePesquero()) {
                this.pecesVivos--;
            } else {
            }
        }
    }

    public boolean isTieneThread() {
        return tieneThread;
    }

    public void setTieneThread(boolean tieneThread) {
        this.tieneThread = tieneThread;
    }

    public void repoblarLago() {
        this.pecesVivos = pecesVivos + 1;
    }
}
