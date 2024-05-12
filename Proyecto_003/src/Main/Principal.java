package Main;

import java.sql.SQLException;
import java.sql.Time;

import Dao.DAOSchudele;
import Dao.DAOStudent;
import Dao.DAOSubjects;
import Dao.DAOTeacher;
import default_package.Schedule;
import default_package.Student;
import default_package.Subjects;
import default_package.Teacher;

public class Principal {
    public static void main(String[] args) {
  
    	// Crear objetos Student
        Student student1 = new Student("Jordy", "Chamba", 25);
        Student student2 = new Student("Kevin", "Pozoz", 30); // Crear otro estudiante
        
        // Crear objetos Teacher
        Teacher teacher1 = new Teacher("Bryan", "Loya", 25);
        Teacher teacher2 = new Teacher("Kevin", "Andrade", 32); // Crear otro profesor
        
        // Crear objetos Subjects
        Subjects subjects1 = new Subjects("Matematicas", "Dificil", 5);
        Subjects subjects2 = new Subjects("Redes", "Complicado", 7); // Crear otro tema
        
        // Crear una instancia de Schedule con valores específicos
        Schedule schedule1 = new Schedule(45, 1, 11, Time.valueOf("07:00:00"), Time.valueOf("13:00:00"), "Lunes");
        
        // Crear otra instancia de Schedule con valores específicos diferentes
        Schedule schedule2 = new Schedule(46, 2, 12, Time.valueOf("05:00:00"), Time.valueOf("18:00:00"), "Martes"); // Corregido para evitar duplicación

        // Crear una instancia de DAOStudent, DAOTeacher y DAOSubjects
        DAOStudent daoStudent = null;
        DAOTeacher daoTeacher = null;
        DAOSubjects daoSubjects = null;
        DAOSchudele daoSchedule = null; // Cambiado el nombre de la clase
        try {
            // Para los estudiantes
            daoStudent = new DAOStudent();
            // Insertar el primer estudiante en la base de datos
            daoStudent.createStudent(student1);
            System.out.println("Primer estudiante insertado correctamente.");
            
            // Insertar el segundo estudiante en la base de datos
            daoStudent.createStudent(student2);
            System.out.println("Segundo estudiante insertado correctamente.");
            
            // Para los profesores
            daoTeacher = new DAOTeacher();
            // Insertar el primer profesor en la base de datos
            daoTeacher.createTeacher(teacher1);
            System.out.println("Primer profesor insertado correctamente.");
            
            // Insertar el segundo profesor en la base de datos
            daoTeacher.createTeacher(teacher2);
            System.out.println("Segundo profesor insertado correctamente.");
            
            // Para los temas
            daoSubjects = new DAOSubjects();
            // Insertar el primer tema en la base de datos
            daoSubjects.createSubjects(subjects1);
            System.out.println("Primer tema insertado correctamente.");
            
            // Insertar el segundo tema en la base de datos
            daoSubjects.createSubjects(subjects2);
            System.out.println("Segundo tema insertado correctamente.");
            
            // Para los horarios
            daoSchedule = new DAOSchudele(); // Cambiado el nombre de la clase
            // Insertar el primer horario en la base de datos
            daoSchedule.createSchedule(schedule1);
            System.out.println("Primer horario insertado correctamente.");
            
            // Insertar el segundo horario en la base de datos
            daoSchedule.createSchedule(schedule2);
            System.out.println("Segundo horario insertado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al insertar el estudiante, profesor, tema o horario.");
        } finally {
            // Cerrar la conexión de Student
            if (daoStudent != null) {
                try {
                    daoStudent.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Cerrar la conexión de Teacher
            if (daoTeacher != null) {
                try {
                    daoTeacher.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Cerrar la conexión de Subjects
            if (daoSubjects != null) {
                try {
                    daoSubjects.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Cerrar la conexión de Schedule
            if (daoSchedule != null) { // Cambiado el nombre de la variable
                try {
                    daoSchedule.closeConnection(); // Cambiado el nombre del método
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }	
    }
}