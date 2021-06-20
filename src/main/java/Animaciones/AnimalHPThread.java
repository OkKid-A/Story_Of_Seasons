package Animaciones;

import Animales.Animal;
import Animales.AnimalVivo;
import Componentes.TiempoProduccionLabel;
import Usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AnimalHPThread extends JPanel implements Runnable {
    private int width = 100;
    private Usuario usuario;
    private AnimalVivo animal;
    private JLabel vida;

    public AnimalHPThread(Usuario usuario, AnimalVivo animal) {
        this.usuario = usuario;
        this.animal = animal;
        this.width = usuario.getHp();
        this.setPreferredSize(new Dimension(100, 21));
        this.setLayout(null);
        this.vida = new JLabel("Vida");
        this.add(vida);
    }

    public boolean revisarHP() {
        if (animal.gethPAnimal() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.drawRect(0, 0, 100, 20);
        g.fillRect(0, 0, width = animal.gethPAnimal(), 20);
    }

    @Override
    public void run() {
        while (!animal.isAnimalMuerto()) {
            int contadorProduccionLista = 0;
            int contador = 0;
            int segNecesariosParaProducir = (int) (animal.getAnimal().getTamano() * 600);
            while (revisarHP()) {
                animal.sethPAnimal(-1);
                for (int k = 0; k < 40 - ((int) (animal.getAnimal().getTamano() * 2)); k++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.repaint();
                    contador++;
                    if (!animal.isProduccionLista()) {
                        contadorProduccionLista++;
                    }
                    if (contadorProduccionLista % segNecesariosParaProducir == 0) {
                        animal.setProduccionLista(true);
                        animal.getTiempoProduccionLabel().setValue("Puedes procesar Al Animal");
                    } else {
                        animal.getTiempoProduccionLabel().setValue("Faltan " + (segNecesariosParaProducir - (contadorProduccionLista % segNecesariosParaProducir)) / 10 + "seg Para cosechar");
                    }
                    if (contador == animal.getAnimal().getCria().getTiempoCrecimiento() * 10 && animal.getEtapaAnimal() != 1) {
                        animal.setEtapaAnimal(1);
                        animal.getEdadLabel().setText((animal.getAnimal().getEspecie() + " Adulto"));
                    } else if (contador == animal.getAnimal().getCria().getTiempoCrecimiento() * 50) {
                        animal.setEtapaAnimal(2);
                        animal.getEdadLabel().setText((animal.getAnimal().getEspecie() + " Viejo"));
                    }
                }
                if (animal.getEtapaAnimal() == 2) {
                    Random r = new Random();
                    int morirPorVejez = r.nextInt(100);
                    if (morirPorVejez == 10) {
                        animal.setAnimalMuerto(true);
                    }
                }
            }
            animal.setProduccionLista(false);
            JOptionPane.showMessageDialog(this, "Tu " + animal.getAnimal().getEspecie() + " Ha Muerto...");
            animal.getEdadLabel().setText("Dado de baja");
            //Reinicio reset = new Reinicio();
        }
    }
}

