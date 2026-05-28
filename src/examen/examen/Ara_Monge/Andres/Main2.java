package org.example;


import org.example.DAO.ClienteDAOImpl;
import org.example.DAO.MotorFactory;
import org.example.DAO.MotorSQL;
import org.example.DAO.PeliculaDAOImpl;
import org.example.beans.Cliente;
import org.example.beans.Pelicula;

import java.util.ArrayList;

public class Main2 {

    public static void main(String[] args) {
        /* MotorSQL motorSQL =
                MotorFactory.create(
                        MotorFactory.POSTGRE
                ); */
        PeliculaDAOImpl peliculaDAO =
                new PeliculaDAOImpl(MotorFactory.
                        create(
                                MotorFactory.POSTGRE));
        peliculaDAO.check();
        // Prueba Unitaria: ADD Película
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("300");
        pelicula.setDirector("Zack Snyder");
        pelicula.setAnyo(2007);
        pelicula.setGenero("Cine Epico");
        peliculaDAO.add(pelicula);
        // Fin Prueba Unitaria: ADD Película
        // Prueba Unitaria: LISTAR PELÍCULAS
        ArrayList<Pelicula> lstPelicula = peliculaDAO.findAll();
        for (Pelicula pelicula1:lstPelicula
             ) {
            System.out.println(pelicula1.toString());
        }
        // Fin Prueba Unitaria: LISTAR PELÍCULAS
        // Prueba Unitaria: ELIMINAR
            peliculaDAO.delete(9);
        // Prueba Unitaria: FIN ELIMINAR
        // Fin Prueba Unitaria: ELIMINAR

        // Prueba Unitaria: FIND
        peliculaDAO.find(2);
        // Prueba Unitaria: FIND

        // Prueba Unitaria: UPDATE
        Pelicula pUpdate = new Pelicula("300: El origen de un imperio", "Noam Murro", "Cine Epico", 2014, 102);
        peliculaDAO.update(2, pUpdate);
        // Prueba Unitaria: UPDATE
        // Prueba Unitaria: FIND DETALLE DE PEDIDO
        peliculaDAO.findDetallePeliculaByPelicula(5);
        // Prueba Unitaria:  FIND DETALLE DE PEDIDO

        // ==========================================
        // PRUEBAS UNITARIAS: CLIENTE DAO
        // ==========================================
        System.out.println("\n=== PRUEBAS UNITARIAS CLIENTE ===");
        ClienteDAOImpl clienteDAO =
                new ClienteDAOImpl(MotorFactory.
                        create(MotorFactory.POSTGRE));
        clienteDAO.check();

        // 1. ADD Cliente
        System.out.println("--- PRUEBA ADD CLIENTE ---");
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre("Juan Perez");
        nuevoCliente.setEmail("juan.perez@example.com");
        nuevoCliente.setDia("28");
        nuevoCliente.setMes("05");
        nuevoCliente.setAnyo("2026");
        clienteDAO.add(nuevoCliente);

        // 2. LISTAR CLIENTES
        System.out.println("--- PRUEBA LISTAR CLIENTES ---");
        ArrayList<Cliente> lstClientes = clienteDAO.findAll();
        for (Cliente cliente : lstClientes) {
            System.out.println("Cliente: " + cliente.getId() + " - " + cliente.getNombre() + " - " + cliente.getEmail());
        }

        // 3. FIND Cliente
        System.out.println("--- PRUEBA FIND CLIENTE ---");
        Cliente c = clienteDAO.find(1);
        if (c != null) {
            System.out.println("Cliente encontrado (ID 1): " + c.getNombre() + " (" + c.getEmail() + ")");
        } else {
            System.out.println("Cliente con ID 1 no encontrado.");
        }

        // 4. UPDATE Cliente
        System.out.println("--- PRUEBA UPDATE CLIENTE ---");
        if (c != null) {
            c.setNombre("Juan Perez Modificado");
            clienteDAO.update(1, c);
            Cliente cModificado = clienteDAO.find(1);
            if (cModificado != null) {
                System.out.println("Cliente modificado: " + cModificado.getNombre());
            }
        }

        // 5. DELETE Cliente
        System.out.println("--- PRUEBA DELETE CLIENTE ---");
        clienteDAO.delete(99);

        // 6. METODOS NO APLICABLES (Stubs)
        System.out.println("--- PRUEBA METODOS NO APLICABLES (STUBS) ---");
        System.out.println("Búsqueda por género (stub): " + clienteDAO.findByGenero("Comedia"));
        System.out.println("Búsqueda por director (stub): " + clienteDAO.findByDirector("Spielberg"));
        System.out.println("Búsqueda de detalle de película (stub): " + clienteDAO.findDetallePeliculaByPelicula(1));
    }
}