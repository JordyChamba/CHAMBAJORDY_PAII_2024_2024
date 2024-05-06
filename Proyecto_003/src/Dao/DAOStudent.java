package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        String sql = "INSERT INTO student (id, name, lastName, age) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Establecer los parámetros en la sentencia preparada
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getLastName());
            ps.setInt(4, student.getAge());
            
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
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

    // Implementa los demás métodos CRUD aquí
}