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
import com.mx.proyecto.Dto.Supervisores;
import com.mx.proyecto.Dto.UsuariosAdminDTO;
import com.mx.proyecto.Entity.UsuariosAdmin;
import com.mx.proyecto.Services.UsuariosAdminService;

@Controller
@RequestMapping(value="usuariosAdmin")
public class UsuariosAdminController {
	
	@Autowired
	private UsuariosAdminService usuariosAdminService; // es la inyeccion de dependencias

	// CRUD = CREATE - READ - UPDATE - DELETE
	
	
	// lista de usuarios -> select * from tabla;
	@ResponseBody
	@RequestMapping(value="/getUsuariosAdmin", method= RequestMethod.GET, produces = "application/json")
	ResponseEntity < List<UsuariosAdmin> > getUsuariosAdmin(){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		List<UsuariosAdmin> lista = usuariosAdminService.getUsuarios();
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <List<UsuariosAdmin>> (lista, httpHeaders, HttpStatus.OK);
	}
	
	
	
	// Servicio para inserta un nuevo usuario
	@ResponseBody
	@RequestMapping(value="/insertUsuarios", method= RequestMethod.POST, produces = "application/json")
	ResponseEntity <ResponseDto> insertUsuarios(@RequestBody UsuariosAdminDTO nuevoUsuario){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = usuariosAdminService.insertUsuariosAdmin(nuevoUsuario);
		
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

			ResponseDto respuesta = usuariosAdminService.eliminarUsuario(idUser);
			
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			return new ResponseEntity <ResponseDto> (respuesta, httpHeaders, HttpStatus.OK);	
	    }
	
		
		// Actualizar registro
		@ResponseBody
	    @RequestMapping(value="/actualizarDatos", method = RequestMethod.POST, produces = "application/json")
		ResponseEntity < ResponseDto > actualizarDatosUsuario(@RequestBody UsuariosAdmin datos){
			final HttpHeaders httpHeaders = new HttpHeaders();

			ResponseDto respuesta = usuariosAdminService.actualizarUsuario(datos);
			
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			return new ResponseEntity <ResponseDto> (respuesta, httpHeaders, HttpStatus.OK);	
	    }
	
	
} //  fin de la clase
