package Mercado;

public class AlimentoAnimal {
    private double precio;
    private int nutricion;
    private boolean herviboro;
    private String nombreAlimento;

    public AlimentoAnimal(int nutricion, int precio, String nombreAlimento, boolean herviboro) {
        this.nutricion = nutricion;
        this.precio = precio;
        this.nombreAlimento = nombreAlimento;
        this.herviboro = herviboro;
    }

    public double getPrecio() {
        return precio;
    }

    public int getNutricion() {
        return nutricion;
    }

    public String getNombreAlimento() {
        return nombreAlimento;
    }

    public String isHerviboro(){
        if (this.herviboro) {
            return "Herviboro";
        } else {
            return "Omnivoro";
        }
    }
    }
