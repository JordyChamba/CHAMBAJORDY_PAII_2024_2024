
package default_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
 
    private final String url = "jdbc:mysql://localhost:3306/institute"; // Cambiar la URL para MySQL
    private final String usuario = "root"; // Cambiar el usuario si es necesario
    private final String password = "Usuario1125"; // Cambiar la contraseña por la tuya
    protected Connection connection = null;
 
    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Cambiar el nombre del driver para MySQL
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
