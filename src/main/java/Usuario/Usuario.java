package Usuario;

import Granero.*;
import Granja.*;
import Terreno.Terreno;

public class Usuario {
    private String nickname;
    private String name;
    private double gold;
    private int hp;
    private Repertorio repertorio;
    private Granero granero;
    private Granja granja;
    private ThreadGroup terrenosThreadGroup;
    private Reportes reportes;

    public Usuario(Repertorio repertorio, Granero granero, Granja granja, Reportes reportes) {
        this.reportes = reportes;
        this.gold = 100;
        this.hp = 100;
        this.repertorio = repertorio;
        this.granero = granero;
        this.granja = granja;
        this.terrenosThreadGroup = new ThreadGroup("Terrenos Agrupados");
        terrenosThreadGroup.activeCount();
    }

    public Reportes getReportes() {
        return reportes;
    }

    public void setData(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Repertorio getRepertorio() {
        return repertorio;
    }

    public Granero getGranero() {
        return granero;
    }

    public Granja getGranja() {
        return granja;
    }

    public void reiniciarThreadGroup(){
        this.terrenosThreadGroup = new ThreadGroup("Terrenos Agrupados");
    }

    public ThreadGroup getTerrenosThreadGroup() {
        return terrenosThreadGroup;
    }

    public void setTerrenosThreadGroup(ThreadGroup terrenosThreadGroup) {
        this.terrenosThreadGroup = terrenosThreadGroup;
    }

    public void statThread(Thread thread){
        thread.start();
    }
}
