package Interfaces;

public interface IDAOSchulede {
	public void read();
	public void update();
	public void delete();
	Boolean createSchedule(Schedule schedule) throws SQLException;
}
