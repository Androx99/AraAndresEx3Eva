package org.example.DAO;

import org.example.beans.Agencia;
import org.example.motores.MotorSQL;
import org.example.beans.Satelite;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AgenicaDAOImpl
        extends AbstractDAO<Agencia> {

    private static final String SQL_FIND_ALL =
            "SELECT * " +
                    "FROM AGENCIA " +
                    "ORDER BY id";

    private static final String SQL_FIND =
            "SELECT * " +
                    "FROM AGENCIA " +
                    "WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO AGENCIA " +
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
            "UPDATE AGENCIA " +
                    "SET " +
                    "nombre = ?, " +
                    "orbita = ?, " +
                    "peso = ?, " +
                    "coste = ?, " +
                    "activo = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM AGENCIA " +
                    "WHERE id = ?";

    public AgenciaDAOImpl(MotorSQL motorSQL) {
        super(motorSQL);
    }

    public AgenicaDAOImpl(MotorSQL motorSQL) {
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
    public void add(Agencia agencia) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, agencia.getNombre());
            motorSQL.getPs().setString(2, agencia.getOrbita());
            motorSQL.getPs().setInt(3, agencia.getPeso());
            motorSQL.getPs().setInt(4, agencia.getCoste());
            motorSQL.getPs().setBoolean(5, agencia.getActivo());

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
            Agencia agencia) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, agencia.getNombre());
            motorSQL.getPs().setString(2, agencia.getOrbita());
            motorSQL.getPs().setInt(3, agencia.getPeso());
            motorSQL.getPs().setInt(4, agencia.getCoste());
            motorSQL.getPs().setBoolean(5, agencia.getActivo());
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
    public void add(Agencia object) {

    }

    @Override
    public void update(int id, Agencia object) {

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
    public Agencia find(int id) {
        Agencia agencia = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                agencia = mapAgencia(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return agencia;
    }

    @Override
    public ArrayList<Agencia> findAll() {
        ArrayList<Agencia> agencias = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                agencias.add(mapAgencia(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return agencias;
    }

    /*
     * =========================
     * CONSULTAS AVANZADAS
     * =========================
     */

    @Override
    public ArrayList<Agencia> findByGenero(String genero) {
        return new ArrayList<>(); // No aplicable a Agencias
    }

    @Override
    public ArrayList<Agencia> findByDirector(String director) {
        return new ArrayList<>(); // No aplicable a Agencias
    }



    /*
     * =========================
     * MAPPING
     * =========================
     */

    private Agencia mapAgencia(ResultSet rs) throws Exception {
        Agencia cliente = new Agencia();
        cliente.setId(rs.getInt("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setOrbita(rs.getString("orbita"));
        cliente.setPeso(rs.getInt("peso"));
        cliente.setCoste(rs.getInt("coste"));
        cliente.setActivo(rs.getBoolean("activo"));
        return cliente;
    }
}