package com.mx.proyecto.RepositoryImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.mx.proyecto.Dto.Supervisores;
import com.mx.proyecto.Mapper.SupervisoresListMapper;
import com.mx.proyecto.Repository.RepositorySupervisores;

@Repository
public class RepositorySupervisoresImpl implements RepositorySupervisores{
	
	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public List<Supervisores> getSupervisores() {
		jdbcTemplate.setDataSource(getDataSource());//LA CONEXION SE LE ASIGNA AL JDBCTEMPLATE PARA QUE SEPA
		//A QUE BASE DE DATOS VA A CONSULTAR
		return jdbcTemplate.query("SELECT * FROM SUPERVISORES", new SupervisoresListMapper<Supervisores>());
	}
	
	@Override
	public Integer insertSupervisores(Supervisores supervisores) {
		jdbcTemplate.setDataSource(getDataSource());
		// TODO Auto-generated method stub
		return jdbcTemplate.update("INSERT INTO SUPERVISORES(ID_SUPERVISORES, NOMBRE, DIRECCION, FECHA_NACIMIENTO, EDAD, SEXO, ESTADO_CIVIL, SALARIO, CORREO_ELECTRONICO, CELULAR) VALUES(?,?,?,?,?,?,?,?,?,?)", 
				supervisores.getIdSupervisores(), supervisores.getNombre(), supervisores.getDireccion(), supervisores.getFechaNacimiento(), supervisores.getEdad(),
				supervisores.getSexo(), supervisores.getEstadoCivil(), supervisores.getSalario(), supervisores.getCorreoElectronico(),
				supervisores.getCelular());
	}
	
	@Override
	public Integer updateSupervisores(Supervisores supervisores) {
		jdbcTemplate.setDataSource(getDataSource());
		// TODO Auto-generated method stub
		return jdbcTemplate.update("UPDATE SUPERVISORES SET NOMBRE=?, DIRECCION=?, FECHA_NACIMIENTO=?, EDAD=?, SEXO=?, ESTADO_CIVIL=?, SALARIO=?, CORREO_ELECTRONICO=?, CELULAR=? WHERE ID_SUPERVISORES=?"
				, new Object[] {supervisores.getNombre(), supervisores.getDireccion(), supervisores.getFechaNacimiento(), supervisores.getEdad(), supervisores.getSexo(), supervisores.getEstadoCivil(), supervisores.getSalario(), supervisores.getCorreoElectronico(), supervisores.getCelular(), supervisores.getIdSupervisores()});
	}
	
	@Override
	public Integer deleteSupervisores(Supervisores supervisores) {
		jdbcTemplate.setDataSource(getDataSource());
		// TODO Auto-generated method stub
		 return jdbcTemplate.update("DELETE FROM SUPERVISORES WHERE ID_SUPERVISORES = ? "
				, new Object[] {supervisores.getIdSupervisores()});
	}
	
	@Override
	public int[][] insertSupervisoresMasivo(List<Supervisores> listaSupervisores) {
		jdbcTemplate.setDataSource(getDataSource());
		int[][] updateCounts=null;
		try {
				jdbcTemplate.setDataSource(getDataSource());
				updateCounts = jdbcTemplate.batchUpdate(
						"INSERT INTO SUPERVISORES (ID_SUPERVISORES, NOMBRE, DIRECCION, FECHA_NACIMIENTO, "
						+ "EDAD, SEXO, ESTADO_CIVIL, SALARIO, CORREO_ELECTRONICO, CELULAR)"
						+ "VALUES (CAPACITACION1.SEQ_SUPERVISORES.NEXTVAL,?,?,SYSDATE,?,?,?,?,?,?)",
						listaSupervisores, 
						10,
						new ParameterizedPreparedStatementSetter<Supervisores>() {
							@Override
							public void setValues(PreparedStatement ps, Supervisores argument) throws SQLException{
								ps.setString(1, argument.getNombre());
								ps.setString(2, argument.getDireccion());
								ps.setInt(3, argument.getEdad());
								ps.setString(4, argument.getSexo());
								ps.setString(5, argument.getEstadoCivil());
								ps.setDouble(6, argument.getSalario());
								ps.setString(7, argument.getCorreoElectronico());
								ps.setLong(8, argument.getCelular());
							}
						}
						);
			
		}catch(org.springframework.dao.DuplicateKeyException DKE) {
			System.out.println("EXCEPTION POR DATO DUPLICADO: "+DKE.getMessage());
		}
		
		return updateCounts;
	}

	@Override
	public Supervisores getSupervisorById(long idSupervisor) {
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate.queryForObject("SELECT * FROM SUPERVISORES WHERE ID_SUPERVISORES =?", 
				new Object[] {idSupervisor}, new SupervisoresListMapper<Supervisores>());
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
