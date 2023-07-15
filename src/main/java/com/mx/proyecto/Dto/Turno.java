package com.mx.proyecto.Dto;

public class Turno {
	
	private String turno;
	private String area;
	private long empleado;
	private long supervisor;
	
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public long getEmpleado() {
		return empleado;
	}
	public void setEmpleado(long empleado) {
		this.empleado = empleado;
	}
	public long getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(long supervisor) {
		this.supervisor = supervisor;
	}

	

}
