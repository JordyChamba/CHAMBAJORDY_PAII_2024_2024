
package Interfaces;

import java.sql.SQLException;

import default_package.Student;

public interface IDAOStudent {
    void read();
    void update();
    void delete();
	Boolean createStudent(Student student) throws SQLException;
}
