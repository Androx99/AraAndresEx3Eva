package org.example.beans;

public class Satelite {
    private int id;
    private String nombre;
    private String orbita;
    private int peso;
    private int coste;
    private Boolean activo;
    private String AUTOR_EXAMEN;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getOrbita() {
        return orbita;
    }
    public void setOrbita(String orbita) {
        this.orbita = orbita;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    public int getCoste() {
        return coste;
    }
    public void setCoste(int coste) {
        this.coste = coste;
    }
    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public String getAUTOR_EXAMEN() {
        return AUTOR_EXAMEN;
    }
    public void setAUTOR_EXAMEN(String aUTOR_EXAMEN) {
        AUTOR_EXAMEN = aUTOR_EXAMEN;
    }

    

}
