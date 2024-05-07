package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import Interfaces.IDAOSchulede;
import default_package.Conexion;
import default_package.Schedule;


public class DAOSchudele implements IDAOSchulede {
    private Connection connection;

    public DAOSchudele() throws SQLException {
        Conexion conexion = new Conexion();
        connection = conexion.getConnection();
    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Boolean createSchedule(Schedule schedule) throws SQLException {
        Boolean stateOp = false;
        // Preparar la sentencia SQL para insertar un estudiante
        String sql = "INSERT INTO schedule (Id_student, Id_teacher, Id_subjects, start_time, end_time, day_of_week) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Establecer los parámetros en la sentencia preparada
            ps.setInt(1, schedule.getIdeAlumno());
            ps.setInt(2, schedule.getIdeProfesor());
            ps.setInt(3, schedule.getIdeMateria());
            ps.setTime(4, schedule.getHoraInicio());
            ps.setTime(5, schedule.getHoraFinal());
            ps.setString(6, schedule.getDia());
            
            // Desactivar el modo de autocommit para confirmar manualmente las transacciones
            connection.setAutoCommit(false);
            
            // Ejecutar la sentencia de inserción
            ps.executeUpdate();
            
            // Confirmar las transacciones realizadas
            connection.commit();
            
            stateOp = true; // Indicar que la operación fue exitosa
        } catch (SQLException e) {
            // Manejar cualquier excepción que pueda ocurrir durante la inserción o la confirmación
            e.printStackTrace();
            // Revertir la transacción si ocurre un error
            connection.rollback();
        } finally {
            // Restablecer el modo de autocommit a true
            connection.setAutoCommit(true);
        }
        return stateOp;
    }



    @Override
    public void read() {
        try {
            String sql = "SELECT * FROM schedule";
            try (PreparedStatement ps = connection.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int studentId = rs.getInt("Id_student");
                    int teacherId = rs.getInt("Id_teacher");
                    int subjectsId = rs.getInt("Id_subjects");
                    Time startTime = rs.getTime("start_time");
                    Time endTime = rs.getTime("end_time");
                    String dayOfWeek = rs.getString("day_of_week");

                    System.out.println("ID: " + id + ", Student ID: " + studentId + ", Teacher ID: " + teacherId +
                            ", Subjects ID: " + subjectsId + ", Start Time: " + startTime + ", End Time: " + endTime +
                            ", Day of Week: " + dayOfWeek);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            String sql = "UPDATE schedule SET Id_student = ?, Id_teacher = ?, Id_subjects = ?, start_time = ?, end_time = ?, day_of_week = ? WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, 2); // Nuevo ID del estudiante
                ps.setInt(2, 3); // Nuevo ID del profesor
                ps.setInt(3, 4); // Nuevo ID de la materia
                ps.setTime(4, new Time(System.currentTimeMillis())); // Nuevo tiempo de inicio
                ps.setTime(5, new Time(System.currentTimeMillis())); // Nuevo tiempo de fin
                ps.setString(6, "Nuevo día de la semana");
                ps.setInt(7, 1); // ID del horario a actualizar

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Actualización exitosa");
                } else {
                    System.out.println("No se encontró el horario con el ID especificado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            String sql = "DELETE FROM schedule WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, 1); // ID del horario a eliminar

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Eliminación exitosa");
                } else {
                    System.out.println("No se encontró el horario con el ID especificado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
