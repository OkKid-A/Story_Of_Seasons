package Usuario;

public class Reinicio {
    private Usuario usuario;

    public Reinicio(Usuario usuario) {
        this.usuario = usuario;
        restablecerVariables();
    }

    private void restablecerVariables(){
        usuario.getGranja().generarTerrenosInicio();
        usuario.getGranja().coleccionarBotonesTerreno();
        usuario.getReportes().setDuracionPartida(0);
        usuario.getReportes().getTimer().stop();
        usuario.getReportes().setTimer(new Thread(usuario.getReportes()));
        usuario.setGold(100);
        usuario.setHp(100);
        usuario.setTerrenosThreadGroup(new ThreadGroup("Granja"));
        for (int k = 0; k < usuario.getRepertorio().getListaPlantas().getNumElementosPresentes();k++){
            usuario.getRepertorio().getListaPlantas().getObjetoDeLista(k).getSemilla().setCantidadAlmacenada(0);
        }
        for (int k = 0; k < usuario.getRepertorio().getListaAnimal().getNumElementosPresentes();k++){
            usuario.getRepertorio().getListaAnimal().getObjetoDeLista(k).getCria().setCantidadAlmacenada(0);
        }
        for (int k = 0; k < usuario.getRepertorio().getListaProductos().getNumElementosPresentes();k++){
            usuario.getRepertorio().getListaProductos().getObjetoDeLista(k).setCantidadExistente(0);
        }
        for (int k = 0; k < 6 ;k++){
            usuario.getGranero().setAlimentosPoseidos(0,k);
        }
        for (int k = 0; k < 3 ;k++){
            usuario.getGranero().setFertilizantesPoseidos(0,k);
        }
    }
}
