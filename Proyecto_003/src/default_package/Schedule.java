package default_package;

public class Schedule {
	int id;
	int ideMateria;
	int ideAlumno;
	int ideProfesor;
	int horaInicio;
	int horaFinal;
	String dia;
	
	public Schedule(int id, int ideMateria, int ideAlumno, int ideProfesor, int horaInicio, int horaFinal, String dia) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.dia=dia;
		this.horaFinal=horaFinal;
		this.horaInicio=horaInicio;
		this.ideAlumno=ideAlumno;
		this.ideMateria=ideMateria;
		this.ideProfesor=ideProfesor;
	}
	
	@Override
	public String toString() {
	    return "Schedule{" +
	            "ide=" + id +
	            ", ideMateria=" + ideMateria +
	            ", ideAlumno=" + ideAlumno +
	            ", ideProfesor=" + ideProfesor +
	            ", horaInicio=" + horaInicio +
	            ", horaFinal=" + horaFinal +
	            ", dia='" + dia + '\'' +
	            '}';
	}

}
