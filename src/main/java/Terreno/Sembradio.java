package Terreno;

import Componentes.TiempoProduccionLabel;
import InterfazGráfica.VentanaSembradio;
import Plantas.Planta;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class Sembradio extends Terreno {
    private ImageIcon siembraNormal;
    private ImageIcon siembraNotif;
    private JButton sembradioButton;
    private int fertilidad;
    private int numTerreno;
    private int vecesCosechada;
    private int tiempoCrecimiento;
    private Planta planta;
    private boolean siembraMuerta;
    private boolean siembraMadura;
    private TiempoProduccionLabel label;
    private Thread thread;

    public Sembradio(int fertilidad, Planta planta, JPanel panelGranja) {
        this.fertilidad = fertilidad;
        this.planta = planta;
        label = new TiempoProduccionLabel();
        siembraMadura = false;
        siembraMuerta = false;
        setIconoButton();
        setTerrenoButton();
    }

    @Override
    public void setThisTerrenoButton(JButton linkedButton) {

    }

    @Override
    public void setTerrenoButton() {
        this.sembradioButton = new JButton(siembraNormal);
        sembradioButton.setSize(50, 25);
        sembradioButton.setBorder(null);
    }

    @Override
    public void setIconoButton() {
        try {
            siembraNormal = new ImageIcon(getClass().getClassLoader().getResourceAsStream("Sembradio.png").readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ImageIcon getIconoButton() {
        return null;
    }

    @Override
    public JButton getTerrenoButton() {
        return sembradioButton;
    }

    @Override
    public void setListener(Usuario usuario, JPanel jpanel) {
        this.sembradioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaSembradio ventana = new VentanaSembradio(redundar(),usuario);
            }
        });
    }

    public void setNumeroTerreno(int numTerreno) {
        this.numTerreno = numTerreno;
    }

    public int getNumeroTerreno() {
        return numTerreno;
    }

    public Planta getPlanta() {
        return planta;
    }

    public int getFertilidad() {
        return fertilidad;
    }

    public int getTiempoCrecimiento() {
        return tiempoCrecimiento;
    }

    public void setSiembraMadura(boolean siembraMadura) {
        this.siembraMadura = siembraMadura;
    }

    public void setSiembraMuerta(boolean siembraMuerta) {
        this.siembraMuerta = siembraMuerta;
    }

    public void setTiempoCrecimiento(int tiempoCrecimiento) {
        this.tiempoCrecimiento = tiempoCrecimiento;
    }

    public void setLabel(TiempoProduccionLabel label) {
        this.label = label;
    }

    public TiempoProduccionLabel getLabel() {
        return label;
    }

    private Sembradio redundar(){
        return this;
    }

    public void setLabelMarchito(TiempoProduccionLabel label){
        if (siembraMadura){
            label.setValue("La Cosecha esta Marchita");
        }
    }

    public void producirCosecha(JPanel panel){
        Random random = new Random();
        int cosecha = ((this.fertilidad*(random.nextInt(4)+1)));
        this.planta.getProduccion().setCantidadExistente(cosecha + planta.getProduccion().getCantidadExistente());
        JOptionPane.showMessageDialog(panel, "Cosechaste " + cosecha + " " + planta.getProduccion().getEtiqueta());
    }

    public boolean isSiembraMadura() {
        return siembraMadura;
    }

    public boolean isSiembraMuerta() {
        return siembraMuerta;
    }

    public void setFertilidad(int fertilidad) {
        this.fertilidad = fertilidad;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String plantaEnSiembra(){
        if (planta == null){
            return "Cultivo vacio";
        } else {
            return planta.getEspecie();
        }
    }

    public void setVecesCosechada(int vecesCosechada) {
        this.vecesCosechada = vecesCosechada;
    }

    public int getVecesCosechada() {
        return vecesCosechada;
    }
}