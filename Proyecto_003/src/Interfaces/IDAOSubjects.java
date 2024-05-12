
package Interfaces;
import java.sql.SQLException;

import default_package.Subjects;


public interface IDAOSubjects {
	public void read();
	public void update();
	public void delete();
	Boolean createSubjects(Subjects subject) throws SQLException;
}
