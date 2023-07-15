package com.mx.proyecto.RepositoryImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.mx.proyecto.Dto.Turno;
import com.mx.proyecto.Mapper.TurnoListMapper;
import com.mx.proyecto.Repository.RepositoryTurno;

@Repository
public class RepositoryTurnoImpl implements RepositoryTurno{
	
	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public List<Turno> getTurno() {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.query("SELECT * FROM TURNO", new TurnoListMapper <Turno>());
	}
	
	@Override
	public Integer insertTurno(Turno turno) {
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate.update("INSERT INTO TURNO(TURNO, AREA, EMPLEADO, SUPERVISOR) VALUES(?,?,?,?)",
				turno.getTurno(), turno.getArea(), turno.getEmpleado(), turno.getSupervisor());
	}
	
	@Override
	public Integer updateTurno(Turno turno) {
		jdbcTemplate.setDataSource(getDataSource());
				
		return jdbcTemplate.update("UPDATE TURNO SET TURNO=?, AREA=? WHERE EMPLEADO=?, SUPERVISOR=?",
				new Object [] {turno.getTurno(), turno.getArea(), turno.getEmpleado(), turno.getSupervisor()});
	}
	
	@Override
	public Integer deleteTurno(Turno turno) {
		jdbcTemplate.setDataSource(getDataSource());
				
		return jdbcTemplate.update("DELETE FROM TURNO WHERE EMPLEADO = ?",
				new Object[] {turno.getEmpleado()});
	}
	
	@Override
	public int[][] insertTurnoMasivo(List<Turno> listaTurno) {
		jdbcTemplate.setDataSource(getDataSource());
		int[][] updateCounts=null;
		try {
				jdbcTemplate.setDataSource(getDataSource());
				updateCounts = jdbcTemplate.batchUpdate(
						"INSERT INTO TURNO(TURNO, AREA, EMPLEADO, SUPERVISOR) VALUES(?,?,?,?)",
						//INSERT INTO TABLA(CAMPO1, CAMPO2) VALUES(VALOR1, VALOR2)
						listaTurno, 
						10,
						new ParameterizedPreparedStatementSetter<Turno>() {
							@Override
							public void setValues(PreparedStatement ps, Turno argument) throws SQLException{
								ps.setString(1, argument.getTurno());
								ps.setString(2, argument.getArea());
								ps.setLong(3, argument.getEmpleado());
								ps.setLong(4, argument.getSupervisor());
								
							}
						}
						);
			
		}catch(org.springframework.dao.DuplicateKeyException DKE) {
			System.out.println("EXCEPTION POR DATO DUPLICADO: "+DKE.getMessage());
		}
		
		return updateCounts;

	}


	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	

	

	
	
	

}
