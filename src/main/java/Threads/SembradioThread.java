package Threads;

import Componentes.TiempoProduccionLabel;
import Terreno.Sembradio;
import Usuario.*;

public class SembradioThread implements Runnable {
    private Sembradio siembra;
    private Usuario usuario;
    private int tiempoEnCrecimiento;
    private TiempoProduccionLabel jLabel;
    private int ciclosPlanta;

    public SembradioThread(Sembradio siembra, Usuario usuario, TiempoProduccionLabel jLabel) {
        this.siembra = siembra;
        this.usuario = usuario;
        this.jLabel = jLabel;
        this.tiempoEnCrecimiento = siembra.getPlanta().getSemilla().getTiempoCosecha();
        siembra.setTiempoCrecimiento(tiempoEnCrecimiento);
    }

    @Override
    public void run() {
        siembra.getLabel().setValue("Tiempo Faltante Para Cosechar:");
        while (tiempoEnCrecimiento > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tiempoEnCrecimiento--;
            siembra.getLabel().setValue("Tiempo Faltante para Cosechar: " + tiempoEnCrecimiento);
        }
        tiempoEnCrecimiento = siembra.getPlanta().getTiempoMarchitar();
        siembra.setSiembraMadura(true);
        usuario.getGranja().getCosechas().anadir(siembra);
        while (tiempoEnCrecimiento > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tiempoEnCrecimiento--;
            siembra.getLabel().setValue("Te quedan " + tiempoEnCrecimiento + " Segundos para Cosechar");
        }
        siembra.setSiembraMuerta(true);
        siembra.getLabel().setValue("Tu Cultivo se ha Marchitado");
    }

    public int getTiempoEnCrecimiento() {
        return tiempoEnCrecimiento;
    }
}
