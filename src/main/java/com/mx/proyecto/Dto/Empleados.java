package com.mx.proyecto.Dto;

import java.sql.Date;

public class Empleados {
	
	private long idEmpleados;
	private String nombre;
	private String direccion;
	private Date fechaNacimiento;
	private int edad;
	private double salario;
	private char sexo;
	private String ocupacion;
	
	
	public long getIdEmpleados() {
		return idEmpleados;
	}
	public void setIdEmpleados(long idEmpleados) {
		this.idEmpleados = idEmpleados;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	
	
}
