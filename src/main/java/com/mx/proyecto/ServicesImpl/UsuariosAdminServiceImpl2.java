package com.mx.proyecto.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Entity.UsuariosAdmin;
import com.mx.proyecto.Repository.UsuariosAdminDAO2;
import com.mx.proyecto.Services.UsuariosAdminService2;

@Service
public class UsuariosAdminServiceImpl2 implements UsuariosAdminService2{ // la capa de negocio
	
	@Autowired
	private UsuariosAdminDAO2 usuariosAdminDAO2;
	
	
	// los codigos http
	// 404 - no encontrado
	// 500 - error interno en el servidor
	// 401 - falta de permisos(FALTA DE ROLES)
	// 200 - OK

	// Regla 01: Necesito un lista de usuarios
	// Regla 02: Necesito que cuando no existan usuarios el sistema me arroje un mensaje -> "No existen registro"
	@Override
	public ResponseDto getUsuarios() {
		ResponseDto responde = new ResponseDto();// inicializar el objt -> response		
		try {
			List<UsuariosAdmin> listaUsuarios = usuariosAdminDAO2.obtieneUsuarios();			
			System.out.println("Imprimir resultado -> " + listaUsuarios); // Imprimir datos en consola
			
			if(listaUsuarios != null) { // Es diferente de null -> (eso kiere que si contiene lista de usuarios)
				responde.setCode(200); // ok
				responde.setMessage("Lista de usuarios");
				responde.setList(listaUsuarios);
			}else {				
				responde.setCode(100); // cuando los datos no coninciden/vacio
				responde.setMessage("No existen registro");
			}						
			
		}catch(Exception e) {
			responde.setCode(500); // Error interno en el servidor
			responde.setMessage("Ocurrio un error en la clase: UsuariosAdminServiceImpl2 y en el metodo: getUsuarios()");
		}
		
		return responde;
	}
	
	
	/*
	 	1.- Si todos los datos deben ser obligatorios
	 	 1.1.- CAMPOS OBLIGATORIOS: -> IF() NombreCompleto, edad, direccion	 	 
	 	 2.- Los campos no vengan en null->(que contenga informacion)
	 */
	@Override
	public ResponseDto insertUsuariosAdmin(UsuariosAdminDTO nuevoUsuario) {
		ResponseDto responde = new ResponseDto();// inicializar el objt -> response
		
		try {
			
			if(nuevoUsuario != null) {// VAL.GENERAL si es dif de null -> (que no venga vacio)
				// Operadores
				// && => AND (y) la condicion es se cumple uno y la otra
				// || => OR  (o) la condicion es uno o la otra 
				
				// null / ('')
				
				System.out.println("NOMBRECOMPLETO -> "+ nuevoUsuario.getNombreCompleto());
				System.out.println("EDAD -> "+ nuevoUsuario.getEdad());
				System.out.println("DIRECCION -> "+ nuevoUsuario.getDireccion());
				
				if(nuevoUsuario.getNombreCompleto() != null && !nuevoUsuario.getNombreCompleto().isEmpty() &&
				   nuevoUsuario.getEdad() != 0 && 
				   nuevoUsuario.getDireccion() != null && !nuevoUsuario.getDireccion().isEmpty()) {
					
						UsuariosAdmin datos = new UsuariosAdmin();
						datos.setIdUser(nuevoUsuario.getIdUser());
						datos.setNombreCompleto(nuevoUsuario.getNombreCompleto());
						datos.setEdad(nuevoUsuario.getEdad());
						datos.setDireccion(nuevoUsuario.getDireccion());
						datos.setEstado(nuevoUsuario.getEstado());
						datos.setRol(nuevoUsuario.getRol());
						
						usuariosAdminDAO2.create(datos); // Insertando registros en la TABLA
						responde.setCode(200); // ok
						responde.setMessage("Los datos se guardaron correctamente");					
				}else {
					responde.setCode(300);
					responde.setMessage("Los datos obligatorios vienen vacios, (Nombre completo, edad y dirección)");	
				}								
			}else {			
				responde.setCode(400);
				responde.setMessage("Los datos vienen vacios");				
			}
						
		}catch(Exception e) {
			responde.setCode(500); // Error interno en el servidor
			responde.setMessage("Ocurrio un error en la clase: UsuariosAdminServiceImpl2 y en el metodo: insertUsuariosAdmin()");
		}			
		return responde;
	}
	
	
	
	
// servicio para eliinar un usuario
//	Validar que el campo id no venga vacio
	@Override
	public ResponseDto eliminarUsuario(UsuariosAdminDTO idUser) {
		ResponseDto responde = new ResponseDto();// inicializar el objt -> response
		try {
			
			usuariosAdminDAO2.delete(idUser.getIdUser()); // Eliminar un reg de la DB
			responde.setCode(200); // ok
			responde.setMessage("El registro se elimino correctamente");
			
		}catch(Exception e) {
			responde.setCode(500); // Error interno en el servidor
			responde.setMessage("Ocurrio un error en la clase: UsuariosAdminServiceImpl2 y en el metodo: insertUsuariosAdmin()");
		}
		
		return responde;
	}
	
	
	
	
	

	@Override
	public ResponseDto actualizarUsuario(UsuariosAdmin datos) {
		ResponseDto responde = new ResponseDto();// inicializar el objt -> response
		
		try {
			
			usuariosAdminDAO2.update(datos);
			responde.setCode(200); // ok
			responde.setMessage("El registro se actualizo correctamente");
			
		}catch(Exception e) {
			responde.setCode(500); // Error interno en el servidor
			responde.setMessage("Ocurrio un error en la clase: UsuariosAdminServiceImpl2 y en el metodo: insertUsuariosAdmin()");
		}		
		
		return responde;
	}


	
	// conusltar por el campo estado /que el resultado solo retorne un solo registro
	@Override
	public ResponseDto getUsuariosPorEstado(UsuariosAdminDTO datos) {
		ResponseDto response = new ResponseDto();// inicializar el objt -> response
		
		try {
			
//			List<UsuariosAdmin> listaUsuarios = usuariosAdminDAO2.obtieneUsuarios();
			UsuariosAdmin resultado = usuariosAdminDAO2.consultaPorEstado(datos.getEstado());
			response.setCode(200); // ok
			response.setMessage("Consulta por estado");
			response.setContent(resultado);
		
		}catch(Exception e) {
			response.setCode(500); // Error interno en el servidor
			response.setMessage("Ocurrio un error en la clase: UsuariosAdminServiceImpl2 y en el metodo: insertUsuariosAdmin()");
		}
		
		return response;
	}

	

//	select * from tabla where id = 5;
	@Override
	public ResponseDto getUsuariosPorId(UsuariosAdminDTO datos) {
		ResponseDto response = new ResponseDto();// inicializar el objt -> response

		try {
			
			UsuariosAdmin resulConsulta = usuariosAdminDAO2.read(datos.getIdUser());
			
			response.setCode(200); // ok
			response.setMessage("Consulta por Id");
			response.setContent(resulConsulta);
			
		}catch(Exception e) {
			response.setCode(500); // Error interno en el servidor
			response.setMessage("Ocurrio un error en la clase: UsuariosAdminServiceImpl2 y en el metodo: insertUsuariosAdmin()");
		}
		
		return response;
	}
	
	
	
	
	

}
