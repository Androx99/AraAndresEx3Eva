package org.example.DAO;

import org.example.motores.MotorSQL;
import org.example.beans.Satelite;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SateliteDAOImpl
        extends AbstractDAO<Satelite> {

    private static final String SQL_FIND_ALL =
            "SELECT * " +
                    "FROM SATELITE " +
                    "ORDER BY id";

    private static final String SQL_FIND =
            "SELECT * " +
                    "FROM SATELITE " +
                    "WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO SATELITE " +
                    "(" +
                    "nombre, " +
                    "orbita, " +
                    "peso, " +
                    "coste, " +
                    "activo" +
                    ") " +
                    "VALUES " +
                    "(" +
                    "?, ?, ?, ?, ?" +
                    ")";

    private static final String SQL_UPDATE =
            "UPDATE SATELITE " +
                    "SET " +
                    "nombre = ?, " +
                    "orbita = ?, " +
                    "peso = ?, " +
                    "coste = ?, " +
                    "activo = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM SATELITE " +
                    "WHERE id = ?";

    public SateliteDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    public void check() {
        try {
            motorSQL.connect();
            if (motorSQL.conn != null &&
                    !motorSQL.conn.isClosed()) {
                System.out.println("CONEXION OK");
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    /*
     * =========================
     * CRUD
     * =========================
     */

    @Override
    public void add(Satelite satelite) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, satelite.getNombre());
            motorSQL.getPs().setString(2, satelite.getOrbita());
            motorSQL.getPs().setInt(3, satelite.getPeso());
            motorSQL.getPs().setInt(4, satelite.getCoste());
            motorSQL.getPs().setBoolean(5, satelite.getActivo());

            int rows = motorSQL.executeUpdate();
            System.out.println(
                    "INSERTADOS: " +
                            rows);

        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public void update(
            int id,
            Satelite satelite) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, satelite.getNombre());
            motorSQL.getPs().setString(2, satelite.getOrbita());
            motorSQL.getPs().setInt(3, satelite.getPeso());
            motorSQL.getPs().setInt(4, satelite.getCoste());
            motorSQL.getPs().setBoolean(5, satelite.getActivo());
            motorSQL.getPs().setInt(6, id);

            int rows = motorSQL.executeUpdate();
            System.out.println(
                    "ACTUALIZADOS: " +
                            rows);
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }


    @Override
    public void delete(int id) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_DELETE);
            motorSQL.getPs().setInt(1, id);

            int rows = motorSQL.executeUpdate();
            System.out.println(
                    "BORRADOS: " +
                            rows);

        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
    }

    @Override
    public Satelite find(int id) {
        Satelite satelite = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                satelite = mapSatelite(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return satelite;
    }

    @Override
    public ArrayList<Satelite> findAll() {
        ArrayList<Satelite> satelites = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                satelites.add(mapSatelite(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return satelites;
    }

    /*
     * =========================
     * CONSULTAS AVANZADAS
     * =========================
     */

    @Override
    public ArrayList<Satelite> findByGenero(String genero) {
        return new ArrayList<>(); // No aplicable a Satelites
    }

    @Override
    public ArrayList<Satelite> findByDirector(String director) {
        return new ArrayList<>(); // No aplicable a Satelites
    }



    /*
     * =========================
     * MAPPING
     * =========================
     */

    private Satelite mapSatelite(ResultSet rs) throws Exception {
        Satelite cliente = new Satelite();
        cliente.setId(rs.getInt("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setOrbita(rs.getString("orbita"));
        cliente.setPeso(rs.getInt("peso"));
        cliente.setCoste(rs.getInt("coste"));
        cliente.setActivo(rs.getBoolean("activo"));
        return cliente;
    }
}
