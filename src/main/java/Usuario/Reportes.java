package Usuario;

import Animales.Animal;

import javax.swing.*;
import java.util.Random;

import static Mercado.Mercado.getjTable;

public class Reportes implements Runnable {
    private int[] criasCompradas;
    private int[] animalesDestazados;
    private int[] semillasCompradas;
    private int[] vecesSembradas;
    private String[][] datosPartidas;
    private double oroTotal;
    private int nutricionTotal;
    private int vidaCuradaTotal;
    private int celdasCompradas;
    private int duracionPartida;
    private Thread timer;

    public Reportes() {
        criasCompradas = new int[2];
        semillasCompradas = new int[2];
        animalesDestazados = new int[2];
        vecesSembradas = new int[2];
        oroTotal = 0;
        nutricionTotal = 0;
        vidaCuradaTotal = 0;
        duracionPartida = 0;
        celdasCompradas = 0;
        timer = new Thread(this);
    }

    private String convertirHora() {
        if (duracionPartida < 60) {
            if (duracionPartida<10){
                return "0:0"+duracionPartida;
            } else {
                return "0:" + duracionPartida;
            }
        } else {
            int segundos = duracionPartida % 60;
            String sec = segundos+"";
            int minutos = ((duracionPartida - segundos) / 60);
            if (segundos<10){
                sec = "0"+segundos;
            }
            return minutos + ":" + sec;
        }
    }

    public void grabarDatosPartida(Usuario usuario) {
        int vecesJugado;
        if (datosPartidas == null) {
            vecesJugado = 0;
        } else {
            vecesJugado = datosPartidas.length;
        }
        String[][] temp = new String[vecesJugado + 1][];
        for (int k = 0; k < vecesJugado; k++) {
            temp[k] = datosPartidas[k];
        }
        temp[vecesJugado] = new String[]{usuario.getName(), convertirHora(), oroTotal + "", nutricionTotal + "", vidaCuradaTotal + "", celdasCompradas + ""};
        datosPartidas = temp;
        oroTotal = 0;
        nutricionTotal = 0;
        vidaCuradaTotal = 0;
        duracionPartida = 0;
        celdasCompradas = 0;
    }

    public JTable dibujarDatosPartida(){
        String[] header = new String[]{"Nombre de Usuario","Duracion","Oro Ganado","Alimento Producido","Vida Curada","Terrenos Comprados"};
        return  getjTable(datosPartidas,header);
    }

    public JTable dibujarDatosAnimales(Usuario usuario){
        String[] header = new String[]{"Animal","Crias Compradas","Veces Destazado"};
        String[][] datosAnimales = new String[animalesDestazados.length][];
        for (int k = 0; k < datosAnimales.length;k++){
            datosAnimales[k] = new String[]{usuario.getRepertorio().getListaAnimal().getObjetoDeLista(k).getEspecie(),criasCompradas[k]+"",animalesDestazados[k]+""};
        }
        return getjTable(datosAnimales,header);
    }

    public JTable dibujarDatosPlantas(Usuario usuario){
        String[] header = new String[]{"Planta","Semillas Compradas","Terrenos Sembrados"};
        String[][] datosPlantas = new String[semillasCompradas.length][];
        for (int k = 0;k < datosPlantas.length;k++){
            datosPlantas[k] = new String[]{usuario.getRepertorio().getListaPlantas().getObjetoDeLista(k).getEspecie(),semillasCompradas[k]+"",vecesSembradas[k]+""};
        }
        return getjTable(datosPlantas,header);
    }

    public Thread getTimer() {
        return timer;
    }

    public void setTimer(Thread timer) {
        this.timer = timer;
    }

    public void setCriasCompradas(int[] criasCompradas) {
        this.criasCompradas = criasCompradas;
    }

    public void agregarOroTotal(double sumaOro) {
        this.oroTotal = oroTotal + sumaOro;
    }

    public void agregarNuticionTotal(int nutricion) {
        if (nutricion >0) {
            this.nutricionTotal = nutricionTotal + nutricion;
        }
    }

    public void restarNutricionTotal(int nutricion) {
        this.nutricionTotal = nutricionTotal - nutricion;
    }

    public void agregarVidaCuradaTotal(int sumarCuracion) {
        this.vidaCuradaTotal = vidaCuradaTotal + sumarCuracion;
    }

    public void agregarCriasCompradas(int k) {
        this.criasCompradas[k] = criasCompradas[k] + 1;
    }

    public void agregarAnimalesDestazados(int k) {
        this.animalesDestazados[k] = animalesDestazados[k] + 1;
    }

    public void agregarSemillas(int k) {
        this.semillasCompradas[k] = semillasCompradas[k] + 1;
    }

    public void agregarPraderasSembradas(int k) {
        this.vecesSembradas[k] = vecesSembradas[k] + 1;
    }

    public int[] getCriasCompradas() {
        return criasCompradas;
    }

    public int[] getAnimalesDestazados() {
        return animalesDestazados;
    }

    public int[] getSemillasCompradas() {
        return semillasCompradas;
    }

    public int[] getVecesSembradas() {
        return vecesSembradas;
    }

    public double getOroTotal() {
        return oroTotal;
    }

    public int getNutricionTotal() {
        return nutricionTotal;
    }

    public int getVidaCuradaTotal() {
        return vidaCuradaTotal;
    }

    public int buscarAnimal(Usuario usuario, Animal animal){
        int index = 0;
        for (int k = 0;k < usuario.getRepertorio().getListaAnimal().getNumElementosPresentes();k++){
            if (animal == usuario.getRepertorio().getListaAnimal().getObjetoDeLista(k)){
                index = k;
                break;
            }
        }
        return index;
    }

    public void expandirDatosAnimales() {
        int[] temp = new int[criasCompradas.length + 1];
        int[] tempDestace = new int[animalesDestazados.length + 1];
        for (int k : criasCompradas) {
            tempDestace[k] = animalesDestazados[k];
            temp[k] = criasCompradas[k];
        }
        tempDestace[criasCompradas.length] = 0;
        temp[criasCompradas.length] = 0;
        criasCompradas = temp;
        animalesDestazados = tempDestace;
    }

    public void expandirDatosPlantas() {
        int[] temp = new int[semillasCompradas.length + 1];
        int[] tempSemillas = new int[vecesSembradas.length + 1];
        for (int k : semillasCompradas) {
            tempSemillas[k] = semillasCompradas[k];
            temp[k] = vecesSembradas[k];
        }
        tempSemillas[semillasCompradas.length] = 0;
        temp[semillasCompradas.length] = 0;
        vecesSembradas = temp;
        semillasCompradas = tempSemillas;
        this.timer = new Thread(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            duracionPartida++;
        }
    }

    public void setDuracionPartida(int duracionPartida) {
        this.duracionPartida = duracionPartida;
    }
}
