package Animales;

public class Cria {
    private String criaNombre;
    private double precioCompra;
    private int tiempoCrecimiento;
    private int cantidadAlmacenada;

    public Cria(String criaNombre, double precioCompra, int tiempoCrecimiento) {
        this.cantidadAlmacenada = 0;
        this.criaNombre = criaNombre;
        this.precioCompra = precioCompra;
        this.tiempoCrecimiento = tiempoCrecimiento;
    }

    public String getCriaNombre() {
        return criaNombre;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public int getTiempoCrecimiento() {
        return tiempoCrecimiento;
    }

    public int getCantidadAlmacenada() {
        return cantidadAlmacenada;
    }

    public void setCantidadAlmacenada(int cantidadAlmacenada) {
        this.cantidadAlmacenada = cantidadAlmacenada;
    }

    public String[] datosCria() {
        String[] datos = new String[]{this.criaNombre, this.precioCompra + "", this.cantidadAlmacenada + ""};
        return datos;
    }
}
