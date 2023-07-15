package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.proyecto.Dto.Supervisores;

public class SupervisoresListMapper<T> implements RowMapper<Supervisores> {

	@Override
	public Supervisores mapRow(ResultSet rs, int rowNum) throws SQLException {
		Supervisores supervisores = new Supervisores();
		
		supervisores.setIdSupervisores(rs.getLong("ID_SUPERVISORES"));
		supervisores.setNombre(rs.getString("NOMBRE"));
		supervisores.setDireccion(rs.getString("DIRECCION"));
		supervisores.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
		supervisores.setEdad(rs.getInt("EDAD"));
		supervisores.setSexo(rs.getString("SEXO"));
		supervisores.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
		supervisores.setSalario(rs.getDouble("SALARIO"));
		supervisores.setCorreoElectronico(rs.getString("CORREO_ELECTRONICO"));
		supervisores.setCelular(rs.getLong("CELULAR"));
		
		return supervisores;
	}
	
	

}
