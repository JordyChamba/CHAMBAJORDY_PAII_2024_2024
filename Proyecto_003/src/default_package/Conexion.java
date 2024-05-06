package default_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Conexion {
 
    private final String url = "jdbc:postgresql://localhost:5432/Institute";
    private final String usuario = "postgres";
    private final String password = "root";
    protected Connection connection = null;
 
    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, usuario, password);
                connection.setAutoCommit(false); // Establecer autocommit a false al obtener la conexión
                System.out.println("Conexión realizada");
            } catch (ClassNotFoundException e) {
                System.out.println("Error al cargar el driver: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base: " + e.getMessage());
            }
        }
        return connection;
    }
}
