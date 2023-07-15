package com.mx.proyecto.RepositoryImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mx.proyecto.Entity.UsuariosAdmin;
import com.mx.proyecto.Repository.UsuariosAdminDAO;

@Repository
public class UsuariosAdminDAOImpl implements UsuariosAdminDAO{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	// ------------   Constructor   --------------
	
	 public UsuariosAdminDAOImpl() {// -> Constructor vacio		 
	 }
	
	 public UsuariosAdminDAOImpl(SessionFactory sessionFactory) {// -> Constructor con un parametro
		 this.sessionFactory = sessionFactory;
	 }
	
	 
	 
	
	// primer servicios para realiza la consulta select * from tabla
	@Override
	@SuppressWarnings("unchecked")// Quitar las advertencias (son las lineas amarillas)
	@Transactional() // Es lo equivalente a un commit en oracle (Confirmar los cambios)
	public List<UsuariosAdmin> obtenerTodosUsuarios() {
		
		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(UsuariosAdmin.class); // select * from UsuariosAdmin
		
//		criteria.add(Restrictions.eq("rol", 3)); // --> where rol = 3
//		criteria.add(Restrictions.eq("edad", 37));
				
		return (List<UsuariosAdmin>) criteria.list();
	}

	
	
	/**  ------  IMPORTANTE ----
	 * Para consultas por hibernate importante 2 cosas:
	 * 1.- La session actual -> final Session session = sessionFactory.getCurrentSession();
	 * 2.- Usar la Entity/entidad -> (Es la tabla de DB) -> final Criteria criteria = session.createCriteria(UsuariosAdmin.class);
	 */
	
	
	
	// insertar registro en la BD con hibernate
	@Override
	@Transactional() // Es lo equivalente a un commit en oracle (Confirmar los cambios)
	public Integer insertarUsuario(UsuariosAdmin datos) {

		sessionFactory.getCurrentSession().save(datos); // Esto es el insert a la tabla Usuarios_Admin
		
		return 1;
	}

	
	
	@Override
	@Transactional() // Es lo equivalente a un commit en oracle (Confirmar los cambios)
	public Integer eliminarUsuarioPorId(UsuariosAdmin datos) {

		sessionFactory.getCurrentSession().delete(datos);// eliminar por hibernate
		
		return 1;
	}

	
	@Override
	@Transactional() // Es lo equivalente a un commit en oracle (Confirmar los cambios)
	public Integer actualizarUsuario(UsuariosAdmin datos) {

		sessionFactory.getCurrentSession().update(datos); // actualizar con hibernate
		
		return 1;
	}
	
	

}
