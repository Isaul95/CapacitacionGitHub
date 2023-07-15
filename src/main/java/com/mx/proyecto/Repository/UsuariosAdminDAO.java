package com.mx.proyecto.Repository;

import java.util.List;
import com.mx.proyecto.Entity.UsuariosAdmin;

public interface UsuariosAdminDAO {
	
	List<UsuariosAdmin> obtenerTodosUsuarios();
	
	Integer insertarUsuario(UsuariosAdmin datos);

	Integer eliminarUsuarioPorId(UsuariosAdmin datos);

	Integer actualizarUsuario(UsuariosAdmin datos);

}
