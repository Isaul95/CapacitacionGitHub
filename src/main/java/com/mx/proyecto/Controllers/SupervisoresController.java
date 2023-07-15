package com.mx.proyecto.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.proyecto.Dto.Supervisores;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Services.ServiceSupervisores;

@Controller
@RequestMapping(value="tarea")
public class SupervisoresController {
	
		@Autowired
		private ServiceSupervisores serviceSupervisores;
		
	@ResponseBody //NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value="/getSupervisores", method= RequestMethod.GET, produces = "application/json")
	ResponseEntity < List<Supervisores> > getSupervisores(){
		final HttpHeaders httpHeaders = new HttpHeaders();
		List<Supervisores> lista = serviceSupervisores.getSupervisores();
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <List<Supervisores>> (lista, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody //NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value="/insertSupervisores", method= RequestMethod.POST, produces = "application/json")
	ResponseEntity <ResponseDto> insertSupervisores(@RequestBody Supervisores supervisores){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceSupervisores.insertSupervisores(supervisores);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody //NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value="/updateSupervisores", method= RequestMethod.PUT, produces = "application/json")
	ResponseEntity <ResponseDto> updateSupervisores(@RequestBody Supervisores supervisores){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceSupervisores.updateSupervisores(supervisores);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}	
	
	@ResponseBody //NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value="/deleteSupervisores", method= RequestMethod.DELETE, produces = "application/json")
	ResponseEntity <ResponseDto> deleteSupervisores(@RequestBody Supervisores supervisores){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceSupervisores.deleteSupervisores(supervisores);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}	
	
	@ResponseBody //NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value="/insertSupervisoresMasivo", method= RequestMethod.POST, produces = "application/json")
	ResponseEntity <ResponseDto> insertSupervisoresMasivo(@RequestBody Supervisores[] arregloSupervisores){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceSupervisores.insertSupervisoresMasivo(arregloSupervisores);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody //NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value="/cargaSupervisoresMasivoByTxtFile", method= RequestMethod.POST, produces = "application/json")
	ResponseEntity <ResponseDto> cargaSupervisoresMasivoByTxtFile(){
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceSupervisores.cargaSupervisoresMasivoByTxtFile();
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}

}
