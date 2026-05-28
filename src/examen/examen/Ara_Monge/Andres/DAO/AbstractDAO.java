package org.example.DAO;

import org.example.motores.MotorSQL;

import java.util.ArrayList;

public abstract class AbstractDAO<T>
        implements DAO<T> {
    protected MotorSQL motorSQL;
    public AbstractDAO(MotorSQL motorSQL) {
        this.motorSQL = motorSQL;
    }
    protected void printError(Exception e){
        System.out.println(
                "[ERROR] " +
                        e.getMessage());
    }
    /*
     * CONSULTAS AVANZADAS
     */
    public abstract ArrayList<T> findByGenero(String genero);
    public abstract ArrayList<T> findByDirector(String director);
}
