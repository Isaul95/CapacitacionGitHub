package com.mx.proyecto.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Entity.UsuariosAdmin;
import com.mx.proyecto.Repository.UsuariosAdminDAO;
import com.mx.proyecto.Services.UsuariosAdminService;

@Service
public class UsuariosAdminServiceImpl implements UsuariosAdminService{

	@Autowired
	private UsuariosAdminDAO usuariosAdminDAO;

	
	
	@Override
	public List<UsuariosAdmin> getUsuarios(){
		
		List<UsuariosAdmin> listUsuarios = usuariosAdminDAO.obtenerTodosUsuarios();
		
		return listUsuarios;
	}



	@Override
	public ResponseDto insertUsuariosAdmin(UsuariosAdminDTO nuevoUsuario) {
		
		ResponseDto response = new ResponseDto();
		
//Los datos que vienen desde postamn vienen en el objeto -> nuevoUsuario	
//	nuevoUsuario =	{
//			"idUser" : "1",
//			"nombreCompleto" : "x",
//			"edad" : "18",
//			"direccion" : "calle x",
//		}
		
// REGLA PARA TRABAJAR CON HIBERNATE SE USA LA ENTIDAD.
		
//es crear un objeto ->datos, como tiene el  = new UsuariosAdmin() es como inicializar algo nuevo (un obj vacio)
		UsuariosAdmin datos = new UsuariosAdmin();
			datos.setIdUser(nuevoUsuario.getIdUser());
			datos.setNombreCompleto(nuevoUsuario.getNombreCompleto());
			datos.setEdad(nuevoUsuario.getEdad());
			datos.setDireccion(nuevoUsuario.getDireccion());
			datos.setEstado(nuevoUsuario.getEstado());
			datos.setRol(nuevoUsuario.getRol());
//			datos =	{
//					"idUser" : "1",
//					"nombreCompleto" : "x",
//					"edad" : "18",
//					"direccion" : "calle x",
//				}
			
			Integer resp = usuariosAdminDAO.insertarUsuario(datos);
			
			if(resp == 1) {
				response.setMessage("Se inserto correctamente");
				response.setCode(200); // OK
			}
			else {
				response.setMessage("No se inserto correctamente");
				response.setCode(500);
			}
			

		return response;
	}



	@Override
	public ResponseDto eliminarUsuario(UsuariosAdminDTO idUser) {
		ResponseDto response = new ResponseDto();
//El id que vienen desde postamn vienen en el objeto -> idUser	
//		idUser =	{
//						"idUser" : "1",
//						"nombreCompleto" : "",
//						"edad" : "",
//						"direccion" : "",
//					}
		
		UsuariosAdmin datos = new UsuariosAdmin();
		datos.setIdUser(idUser.getIdUser());
		
		Integer resp = usuariosAdminDAO.eliminarUsuarioPorId(datos);
		
		if(resp == 1) {
			response.setMessage("Se elimino correctamente");
			response.setCode(200); // OK
		}
		else {
			response.setMessage("No se pudo eliminar correctamente");
			response.setCode(500);
		}
		
		return response;
	}



	
	@Override
	public ResponseDto actualizarUsuario(UsuariosAdmin datos) {
		ResponseDto response = new ResponseDto();
		
		
		Integer resp = usuariosAdminDAO.actualizarUsuario(datos);
		
		if(resp == 1) {
			response.setMessage("Se actualizo correctamente");
			response.setCode(200); // OK
		}
		else {
			response.setMessage("No se pudo actualizar correctamente");
			response.setCode(500);
		}

		return response;
	}
	
	
	
	
	
	
	
	
	
}// fin de la clase
