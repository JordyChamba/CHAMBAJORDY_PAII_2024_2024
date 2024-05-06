package Main;

import java.sql.SQLException;

import Dao.DAOStudent;
import default_package.Student;


public class Principal {
    public static void main(String[] args) {
  
    	// Crear un objeto Student
        Student student1 = new Student(1, "Jordy", "Chamba", 25);
        Student student2 = new Student(2, "Juan", "Perez", 30); // Crear otro estudiante
        
        // Crear una instancia de DAOStudent
        DAOStudent dao = null;
        try {
            dao = new DAOStudent();
            // Insertar el primer estudiante en la base de datos
            dao.createStudent(student1);
            System.out.println("Primer estudiante insertado correctamente.");
            
            // Insertar el segundo estudiante en la base de datos
            dao.createStudent(student2);
            System.out.println("Segundo estudiante insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar el estudiante.");
        } finally {
            // Cerrar la conexión
            if (dao != null) {
                try {
                    dao.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }	
    }
}
