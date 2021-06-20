package Threads;

import Terreno.Lago;
import Usuario.Usuario;

public class LagoThread implements Runnable {
    private Usuario usuario;
    private Lago lago;

    public LagoThread(Usuario usuario, Lago lago) {
        this.usuario = usuario;
        this.lago = lago;
    }

    @Override
    public void run() {
        while (true) {
            if (lago.isLagoConBotePesquero() && !lago.lagoVacio()) {
                lago.pescarPecesVivos();
                usuario.getRepertorio().getListaProductos().getObjetoDeLista(7).setCantidadExistente(usuario.getRepertorio().getListaProductos().getObjetoDeLista(7).getCantidadExistente()+1);
            } else if (!lago.isLagoConBotePesquero() && lago.getPecesVivos() < 100) {
                lago.repoblarLago();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
