package Interfaces;

import java.sql.SQLException;

import default_package.Schedule;

public interface IDAOSchulede {
	public void read();
	public void update();
	public void delete();
	Boolean createSchedule(Schedule schedule) throws SQLException;
}
