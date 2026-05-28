package org.example.beans;

public class DetalleSatelite {
    private int id;
    private int velocidadMaxina;
    private int combustible;
    private int vidaUtil;
    private String AUTOR_EXAMEN;
    private Satelite satelite;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getVelocidadMaxina() {
        return velocidadMaxina;
    }
    public void setVelocidadMaxina(int velocidadMaxina) {
        this.velocidadMaxina = velocidadMaxina;
    }
    public int getCombustible() {
        return combustible;
    }
    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }
    public int getVidaUtil() {
        return vidaUtil;
    }
    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }
    public String getAUTOR_EXAMEN() {
        return AUTOR_EXAMEN;
    }
    public void setAUTOR_EXAMEN(String aUTOR_EXAMEN) {
        AUTOR_EXAMEN = aUTOR_EXAMEN;
    }
    public Satelite getSatelite() {
        return satelite;
    }
    public void setSatelite(Satelite satelite) {
        this.satelite = satelite;
    }

}

