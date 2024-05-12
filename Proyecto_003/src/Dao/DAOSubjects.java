
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Interfaces.IDAOSubjects;
import default_package.Conexion;
import default_package.Subjects;

public class DAOSubjects implements IDAOSubjects {
    private Connection connection;

    public DAOSubjects() throws SQLException {
        Conexion conexion = new Conexion();
        connection = conexion.getConnection();
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Boolean createSubjects(Subjects suject) throws SQLException {
        Boolean stateOp = false;
        // Preparar la sentencia SQL para insertar un estudiante
        String sql = "INSERT INTO subjects (name, description, level) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Establecer los parámetros en la sentencia preparada
            ps.setString(1, suject.getName());
            ps.setString(2, suject.getDescription());
            ps.setInt(3, suject.getLevel());
            
            // Ejecutar la sentencia de inserción
            ps.executeUpdate();
            
            // Desactivar el modo de autocommit para confirmar manualmente las transacciones
            connection.setAutoCommit(false);
            // Confirmar las transacciones realizadas
            connection.commit();
            
            stateOp = true; // Indicar que la operación fue exitosa
        } catch (SQLException e) {
            // Manejar cualquier excepción que pueda ocurrir durante la inserción o la confirmación
            e.printStackTrace();
            // Revertir la transacción si ocurre un error
            connection.rollback();
        }
        return stateOp;
    }


    @Override
    public void read() {
        try {
            String sql = "SELECT * FROM subjects";
            try (PreparedStatement ps = connection.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int level = rs.getInt("level");

                    System.out.println("ID: " + id + ", Name: " + name + ", Description: " + description + ", Level: " + level);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            String sql = "UPDATE subjects SET name = ?, description = ?, level = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, "Nuevo Nombre");
                ps.setString(2, "Nueva Descripción");
                ps.setInt(3, 2);
                ps.setInt(4, 1); // ID de la materia a actualizar

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Actualización exitosa");
                } else {
                    System.out.println("No se encontró la materia con el ID especificado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            String sql = "DELETE FROM subjects WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, 1); // ID de la materia a eliminar

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Eliminación exitosa");
                } else {
                    System.out.println("No se encontró la materia con el ID especificado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
