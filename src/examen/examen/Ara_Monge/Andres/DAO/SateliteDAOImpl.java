package examen.examen.Ara_Monge.Andres.DAO;

import examen.examen.Ara_Monge.Andres.beans.Agencia;
import examen.examen.Ara_Monge.Andres.beans.Satelite;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SateliteDAOImpl
        extends AbstractDAO<Cliente> {

    private static final String SQL_FIND_ALL =
            "SELECT * " +
                    "FROM clientes " +
                    "ORDER BY id";

    private static final String SQL_FIND =
            "SELECT * " +
                    "FROM clientes " +
                    "WHERE id = ?";

    private static final String SQL_INSERT =
            "INSERT INTO clientes " +
                    "(" +
                    "nombre, " +
                    "email, " +
                    "dia, " +
                    "mes, " +
                    "anyo" +
                    ") " +
                    "VALUES " +
                    "(" +
                    "?, ?, ?, ?, ?" +
                    ")";

    private static final String SQL_UPDATE =
            "UPDATE clientes " +
                    "SET " +
                    "nombre = ?, " +
                    "email = ?, " +
                    "dia = ?, " +
                    "mes = ?, " +
                    "anyo = ? " +
                    "WHERE id = ?";

    private static final String SQL_DELETE =
            "DELETE FROM clientes " +
                    "WHERE id = ?";

    public ClienteDAOImpl(
            MotorSQL motorSQL) {
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
    public void add(Cliente cliente) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_INSERT);
            motorSQL.getPs().setString(1, cliente.getNombre());
            motorSQL.getPs().setString(2, cliente.getEmail());
            motorSQL.getPs().setString(3, cliente.getDia());
            motorSQL.getPs().setString(4, cliente.getMes());
            motorSQL.getPs().setString(5, cliente.getAnyo());

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
            Cliente cliente) {
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_UPDATE);
            motorSQL.getPs().setString(1, cliente.getNombre());
            motorSQL.getPs().setString(2, cliente.getEmail());
            motorSQL.getPs().setString(3, cliente.getDia());
            motorSQL.getPs().setString(4, cliente.getMes());
            motorSQL.getPs().setString(5, cliente.getAnyo());
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
    public Cliente find(int id) {
        Cliente cliente = null;
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND);
            motorSQL.getPs().setInt(1, id);
            ResultSet rs = motorSQL.executeQuery();
            if (rs.next()) {
                cliente = mapCliente(rs);
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return cliente;
    }

    @Override
    public ArrayList<Cliente> findAll() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            motorSQL.connect();
            motorSQL.prepare(SQL_FIND_ALL);
            ResultSet rs = motorSQL.executeQuery();
            while (rs.next()) {
                clientes.add(mapCliente(rs));
            }
        } catch (Exception e) {
            printError(e);
        } finally {
            motorSQL.close();
        }
        return clientes;
    }

    /*
     * =========================
     * CONSULTAS AVANZADAS
     * =========================
     */

    @Override
    public ArrayList<Cliente> findByGenero(String genero) {
        return new ArrayList<>(); // No aplicable a Clientes
    }

    @Override
    public ArrayList<Cliente> findByDirector(String director) {
        return new ArrayList<>(); // No aplicable a Clientes
    }

    @Override
    public Pelicula findDetallePeliculaByPelicula(int idPelicula) {
        return null; // No aplicable a Clientes
    }

    /*
     * =========================
     * MAPPING
     * =========================
     */

    private Cliente mapCliente(ResultSet rs) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setEmail(rs.getString("email"));
        cliente.setDia(rs.getString("dia"));
        cliente.setMes(rs.getString("mes"));
        cliente.setAnyo(rs.getString("anyo"));
        return cliente;
    }
}
