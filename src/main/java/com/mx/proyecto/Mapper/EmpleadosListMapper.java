package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.proyecto.Dto.Empleados;

public class EmpleadosListMapper<T> implements RowMapper<Empleados> {

	@Override
	public Empleados mapRow(ResultSet rs, int rowNum) throws SQLException {
		Empleados empleados = new Empleados();
		
		empleados.setIdEmpleados(rs.getLong("ID_EMPLEADO"));
		empleados.setNombre(rs.getString("NOMBRE"));
		empleados.setDireccion(rs.getString("DIRECCION"));
		empleados.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
		empleados.setEdad(rs.getInt("EDAD"));
		empleados.setSalario(rs.getDouble("SALARIO"));
		//empleados.setSexo(rs.getChar("SEXO"));
		empleados.setOcupacion(rs.getString("OCUPACION"));
		
		return empleados;
	}
	
	

}
