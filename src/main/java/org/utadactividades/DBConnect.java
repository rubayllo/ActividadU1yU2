package org.utadactividades;

import java.sql.*;

public class DBConnect {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASS = "postgres";
    private static Connection connection;


// La conexión siempre se establecera por las funciones que la exijan y se cerrará dentro de las mismas
    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASS);

            return connection;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void createTable(){
        getConnection();

        String createTableEmpleados = "CREATE TABLE IF NOT EXISTS empleados ( id SERIAL PRIMARY KEY, nombre VARCHAR(255), apellido VARCHAR(255), dni VARCHAR(255) UNIQUE, dpto VARCHAR(255) );";

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(createTableEmpleados);

            System.out.println("Tabla creada");

            stmt.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static int insertSQL(String nomb, String ape, String dni, String dpto){

        getConnection();

        String insertQuery = "INSERT INTO empleados (nombre, apellido, dni, dpto) VALUES (?,?,?,?) RETURNING id;";

        try {
            PreparedStatement stmt = connection.prepareStatement(insertQuery);

            stmt.setString(1, nomb);
            stmt.setString(2, ape);
            stmt.setString(3, dni);
            stmt.setString(4, dpto);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
// Devuelve por pantalla el número total de los empleados insertados en la tabla y cerramos conexiones

                System.out.println("Insertado empleado número: " + resultSet.getInt(1) );

                stmt.close();
                connection.close();

                return 1;
            }

        } catch (SQLException e) {
            System.err.println("No se ha podido insertar empleado " + nomb + " " + ape + " numero de DNI duplicado " + dni);
        }
        return 0;
    }
}
