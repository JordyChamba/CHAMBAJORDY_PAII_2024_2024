package default_package;

import java.sql.Time;

public class Schedule {
	int ideMateria;
	int ideAlumno;
	int ideProfesor;
	Time horaInicio;
	Time horaFinal;

	public int getIdeMateria() {
		return ideMateria;
	}

	public void setIdeMateria(int ideMateria) {
		this.ideMateria = ideMateria;
	}

	public int getIdeAlumno() {
		return ideAlumno;
	}

	public void setIdeAlumno(int ideAlumno) {
		this.ideAlumno = ideAlumno;
	}

	public int getIdeProfesor() {
		return ideProfesor;
	}

	public void setIdeProfesor(int ideProfesor) {
		this.ideProfesor = ideProfesor;
	}

	
	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	String dia;
	
	public Schedule(int ideMateria, int ideAlumno, int ideProfesor, Time horaInicio, Time horaFinal, String dia) {
		// TODO Auto-generated constructor stub
		this.dia=dia;
		this.horaFinal=horaFinal;
		this.horaInicio=horaInicio;
		this.ideAlumno=ideAlumno;
		this.ideMateria=ideMateria;
		this.ideProfesor=ideProfesor;
	}
	
	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Time horaFinal) {
		this.horaFinal = horaFinal;
	}

	@Override
	public String toString() {
	    return "Schedule{" +
	            ", ideMateria=" + ideMateria +
	            ", ideAlumno=" + ideAlumno +
	            ", ideProfesor=" + ideProfesor +
	            ", horaInicio=" + horaInicio +
	            ", horaFinal=" + horaFinal +
	            ", dia='" + dia + '\'' +
	            '}';
	}

}
