package com.mx.proyecto.RepositoryImpl;

import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mx.proyecto.Entity.UsuariosAdmin;
import com.mx.proyecto.Repository.UsuariosAdminDAO2;


@Repository                               // GenericDAO<T, PK>
public class UsuariosAdminDAOImpl2 extends GenericDAO<UsuariosAdmin, Long> implements UsuariosAdminDAO2{

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

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

	
	
	// la consulta --> select * from tabla
	@Override
	@Transactional
	public List<UsuariosAdmin> obtieneUsuarios() {
		
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(UsuariosAdmin.class);// --> select * from tabla
		
		return (List<UsuariosAdmin>) criteria.list(); // --> retorna una lista de usuarios
	}
	
	
	
//	select * from tabla where curp/rfc/estatus = "HERRST36484";
	
//	select * from tabla where curp = "HERRST36484" and rfc = 'GATW538ED' and estatus = 0;
	@Override
	@Transactional
	public UsuariosAdmin consultaPorEstado(int estado) {
		
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(UsuariosAdmin.class); // --> select * from tabla
//		                             CAMPO, valor
		criteria.add(Restrictions.eq("estado", estado)); // --> where estado = ?
//		criteria.add(Restrictions.eq("rfc", rfc)); // --> where estado = ?
//		criteria.add(Restrictions.eq("estatus", estado)); // --> where estado = ?
		
		return (UsuariosAdmin) criteria.uniqueResult(); //--> que espera solo 1 resultado
	}

	
}
