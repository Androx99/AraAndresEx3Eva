package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private static final String URL = "jdbc:postgresql://database-2.cxhptkfuwfna.us-east-1.rds.amazonaws.com:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String SQL_SELECT = "SELECT * FROM CLIENTES";
    private static final String SQL_SELECT_PELICULAS = "SELECT * FROM PELICULAS";
    private static Connection conn;
    private static Statement st;

    private static void initConexion(){
        System.out.println("Intentando conectar a la base de datos...");
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (Exception e){

        }
    }
    private static ResultSet ejecutarQuery(String SQL){
        ResultSet rs = null;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(SQL);
            }catch(Exception e){
            }finally {
                return rs;
        }

    }
    public static void main(String[] args) {
    }
}
