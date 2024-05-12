
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Interfaces.IDAOStudent;
import default_package.Conexion;
import default_package.Student;

public class DAOStudent implements IDAOStudent {
    private Connection connection;

    public DAOStudent() throws SQLException {
        Conexion conexion = new Conexion();
        connection = conexion.getConnection();
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Boolean createStudent(Student student) throws SQLException {
        Boolean stateOp = false;
        // Preparar la sentencia SQL para insertar un estudiante
        String sql = "INSERT INTO student (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Establecer los parámetros en la sentencia preparada
            ps.setString(1, student.getName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getAge());
            
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
            String sql = "SELECT * FROM student";
            try (PreparedStatement ps = connection.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String lastName = rs.getString("lastName");
                    int age = rs.getInt("age");

                    System.out.println("ID: " + id + ", Name: " + name + ", Last Name: " + lastName + ", Age: " + age);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            String sql = "UPDATE student SET name = ?, lastName = ?, age = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, "Nuevo Nombre");
                ps.setString(2, "Nuevo Apellido");
                ps.setInt(3, 30);
                ps.setInt(4, 1); // ID del estudiante a actualizar

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Actualización exitosa");
                } else {
                    System.out.println("No se encontró el estudiante con el ID especificado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            String sql = "DELETE FROM student WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, 1); // ID del estudiante a eliminar

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Eliminación exitosa");
                } else {
                    System.out.println("No se encontró el estudiante con el ID especificado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
