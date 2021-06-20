package Componentes;

import Animales.Animal;
import Animales.AnimalHerviboro;
import Animales.AnimalOmnivoro;
import Mercado.AlimentoAnimal;
import Usuario.Usuario;

import javax.swing.*;

import static Mercado.Mercado.CARAC_ALIMENTOS;
import static Mercado.Mercado.getjTable;

public class AlimentosTabla extends JTable {
    private Usuario usuario;
    private Animal animal;

    public AlimentosTabla(Animal animal, Usuario usuario) {
        super();
        this.animal = animal;
        this.usuario = usuario;
        tipoTable();
    }

    private JTable dibujarAlimentosEspecificos(AnimalHerviboro animal) {
        String[][] alimentosHerbivoros = new String[3][];
        String[] header = new String[]{"Alimento", "HP+","Cantidad en Existencia"};
        for (int k = 0; k < 3; k++) {
            AlimentoAnimal alimentoAnimal = usuario.getGranero().getMercado().getListaAlimentos().getObjetoDeLista(k);
            alimentosHerbivoros[k] = new String[]{alimentoAnimal.getNombreAlimento(), alimentoAnimal.getNutricion() + "",usuario.getGranero().getAlimentosPoseidos(k)+""};
        }
        return getjTable(alimentosHerbivoros, header);
    }

    private JTable dibujarAlimentosEspecificos(AnimalOmnivoro animal) {
        String[][] alimentosOmnivoros = new String[3][];
        String[] header = new String[]{"Alimento", "HP+","Cantidad en Existencia"};
        for (int k = 3; k < 6; k++) {
            AlimentoAnimal alimentoAnimal = usuario.getGranero().getMercado().getListaAlimentos().getObjetoDeLista(k);
            alimentosOmnivoros[k-3] = new String[]{alimentoAnimal.getNombreAlimento(), alimentoAnimal.getNutricion() + "",usuario.getGranero().getAlimentosPoseidos(k)+""};
        }
        return getjTable(alimentosOmnivoros, header);
    }

    public JTable tipoTable() {
        if (animal instanceof AnimalHerviboro) {
            return dibujarAlimentosEspecificos((AnimalHerviboro) animal);
        } else {
            return dibujarAlimentosEspecificos((AnimalOmnivoro) animal);
        }
    }
}
