
package Interfaces;

import java.sql.SQLException;
import default_package.Teacher;

public interface IDAOTeacher {
	public void read();
	public void update();
	public void delete();
	Boolean createTeacher(Teacher teacher) throws SQLException;
}

