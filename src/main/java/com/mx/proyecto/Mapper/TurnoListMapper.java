package com.mx.proyecto.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.proyecto.Dto.Turno;

public class TurnoListMapper <T> implements RowMapper<Turno>{

	@Override
	public Turno mapRow(ResultSet rs, int rowNum) throws SQLException {
		Turno turno = new Turno();
		
		turno.setTurno(rs.getString("TURNO"));
		turno.setArea(rs.getString("AREA"));
		turno.setEmpleado(rs.getLong("EMPLEADO"));
		turno.setSupervisor(rs.getLong("SUPERVISOR"));
		
		return turno;
	}
	
	

}
