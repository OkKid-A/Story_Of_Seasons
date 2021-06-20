package Start;

import Granero.*;
import Granja.*;
import InterfazGráfica.*;
import Mercado.Mercado;
import Usuario.*;

import javax.swing.*;

public class Start {
    public static void main(String[] args) {
        Granja myGranja = new Granja();
        Repertorio repertorio = new Repertorio();
        Mercado mercado = new Mercado();
        Granero granero = new Granero(repertorio,mercado);
        Reportes reportes = new Reportes();
        Usuario usuario = new Usuario(repertorio,granero,myGranja,reportes);
        ThreadGroup terrenos = new ThreadGroup("Granja");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaIntroduccion introduccion = new VentanaIntroduccion(usuario);
            }
        });
    }
}
