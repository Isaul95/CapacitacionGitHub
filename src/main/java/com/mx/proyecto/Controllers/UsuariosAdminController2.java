package com.mx.proyecto.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Entity.UsuariosAdmin;
import com.mx.proyecto.Services.UsuariosAdminService;
import com.mx.proyecto.Services.UsuariosAdminService2;

@Controller
@RequestMapping(value="usuariosHibernate2")
public class UsuariosAdminController2 {
	
	@Autowired
	private UsuariosAdminService2 usuariosAdminService2; // es la inyeccion de dependencias

	// CRUD = CREATE - READ - UPDATE - DELETE
	
	
	// lista de usuarios -> select * from tabla;
	@ResponseBody
	@RequestMapping(value="/getUsuariosAdmin", method= RequestMethod.GET, produces = "application/json")
	public ResponseDto getUsuariosAdmin(){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		return usuariosAdminService2.getUsuarios();
	}
	
	
	
	// Servicio para inserta un nuevo usuario
	@ResponseBody
	@RequestMapping(value="/insertUsuarios", method= RequestMethod.POST, produces = "application/json")
	ResponseEntity <ResponseDto> insertUsuarios(@RequestBody UsuariosAdminDTO nuevoUsuario){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = usuariosAdminService2.insertUsuariosAdmin(nuevoUsuario);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	
	
	// Sevicios Rest = respuestas con JSON -> { "nombre" : "Juan" } -> (json, texto, etc.)
	// Sevicios Soap = archivos xml
	
	
	
	
	// Eliminar registro
		@ResponseBody
	    @RequestMapping(value="/eliminarUsuario", method = RequestMethod.POST, produces = "application/json")
		public ResponseEntity <ResponseDto> eliminarUsuario(@RequestBody UsuariosAdminDTO idUser){
			final HttpHeaders httpHeaders = new HttpHeaders();

			ResponseDto respuesta = usuariosAdminService2.eliminarUsuario(idUser);
			
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			return new ResponseEntity <ResponseDto> (respuesta, httpHeaders, HttpStatus.OK);	
	    }
	
		
		// Actualizar registro
		@ResponseBody
	    @RequestMapping(value="/actualizarDatos", method = RequestMethod.POST, produces = "application/json")
		ResponseEntity < ResponseDto > actualizarDatosUsuario(@RequestBody UsuariosAdmin datos){
			final HttpHeaders httpHeaders = new HttpHeaders();

			ResponseDto respuesta = usuariosAdminService2.actualizarUsuario(datos);
			
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			return new ResponseEntity <ResponseDto> (respuesta, httpHeaders, HttpStatus.OK);	
	    }
		
		/*
		
		Arquitectura: Spring Framework -> Peticiones -> POST y GET
		
		Arquitectura: Spring Boot -> Peticiones -> (Post, Put, Delete, Get)
				
		 */
		
		
		
		// Servicio para hacer una busqueda de informacion por X dato
		// select * from tabla where curp/rfc/estatus = "HERRST36484";
		@ResponseBody
		@RequestMapping(value="/getUsuariosPorEstado", method= RequestMethod.POST, produces = "application/json")
		public ResponseDto getUsuariosPorEstado(@RequestBody UsuariosAdminDTO datos){
			
			return usuariosAdminService2.getUsuariosPorEstado(datos);
		}
		
		
		
		
		
		// Servicio para consultar la informacion por el id
		// select * from tabla where id = 5;
		@ResponseBody
		@RequestMapping(value="/getUsuariosPorId", method= RequestMethod.POST, produces = "application/json")
		public ResponseDto getUsuariosPorId(@RequestBody UsuariosAdminDTO datos){
			
			return usuariosAdminService2.getUsuariosPorId(datos);
		}
		
		
		
		
}
