package Componentes;

import Animales.Animal;
import Animales.AnimalHerviboro;
import Animales.AnimalOmnivoro;
import Usuario.Usuario;

import javax.swing.*;

public class AlimentosLista extends JList {
    private Usuario usuario;
    private Animal animal;

    public AlimentosLista(Usuario usuario, Animal animal) {
        this.usuario = usuario;
        this.animal = animal;
        tipoLista(animal);
    }

    public JList tipoLista(Animal animal){
        if (animal instanceof AnimalHerviboro){
            return alimentosNombres((AnimalHerviboro) animal);
        } else {
            return alimentosNombres((AnimalOmnivoro)animal);
        }
    }

    private JList alimentosNombres(AnimalHerviboro animal){
        String[] nombres = new String[3];
        for (int k = 0; k < 3;k++){
            nombres[k] = usuario.getGranero().getMercado().getListaAlimentos().getObjetoDeLista(k).getNombreAlimento();
        }
        JList nombresList = new JList(nombres);
        return nombresList;
    }

    private JList alimentosNombres(AnimalOmnivoro animal){
        String[] nombres = new String[3];
        for (int k = 3; k < 6;k++){
            nombres[k-3] = usuario.getGranero().getMercado().getListaAlimentos().getObjetoDeLista(k).getNombreAlimento();
        }
        JList nombresList = new JList(nombres);
        return nombresList;
    }
}
