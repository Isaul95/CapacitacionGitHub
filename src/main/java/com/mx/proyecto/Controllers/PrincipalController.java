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

import com.mx.proyecto.Dto.Empleados;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Services.ServiceEmpleados;

//CONVERTIR EN CONTROLADOR DE SPRING
@Controller
@RequestMapping(value="principal")
public class PrincipalController {
	
	@Autowired
	private ServiceEmpleados serviceEmpleados;
	
	@RequestMapping(value="/Inicio")
	String inicio() {
		return "Inicio";
	}

	@RequestMapping(value="/Buscar")
	String buscar() {
		return "Buscar";
	}
	
	@RequestMapping(value="/Contacto")
	String contacto() {
		return "Contacto";
	}
	
	@RequestMapping(value="/Directorio")
	String directorio() {
		return "Directorio";
	}
	
	////////////////////////////////// SERVICIO EMPLEADOS //////////////////////////////////////////////////////////////
	
	@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS, Y NO UNA VISTA
    @RequestMapping(value="/getEmpleados", method = RequestMethod.POST, produces = "application/json") //NOMBRE DEL SERVICIO
	ResponseEntity <  List<Empleados> > getEmpleados(@RequestBody Empleados empleado){  //NOMBRE DEL METODO
		final HttpHeaders httpHeaders = new HttpHeaders();
		System.out.println("Llegando al servicio getEmpleados");
		List<Empleados> listaEmpleados = serviceEmpleados.getEmpleados(empleado);
		System.out.println("Se obtuvieron "+listaEmpleados.size()+" registros pertenecientes a empleados");
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <List<Empleados>> (listaEmpleados, httpHeaders, HttpStatus.OK);
    }
	
	@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS, Y NO UNA VISTA
    @RequestMapping(value="/insertEmpleados", method = RequestMethod.POST, produces = "application/json") //NOMBRE DEL SERVICIO
	ResponseEntity <ResponseDto> insertEmpleados(@RequestBody Empleados empleado){  //NOMBRE DEL METODO
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceEmpleados.insertEmpleados(empleado);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
    }
	
	@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS, Y NO UNA VISTA
    @RequestMapping(value="/updateEmpleado", method = RequestMethod.PUT, produces = "application/json") //NOMBRE DEL SERVICIO
	ResponseEntity <ResponseDto> updateEmpleado(@RequestBody Empleados empleado){  //NOMBRE DEL METODO
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceEmpleados.updateEmpleado(empleado);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
    }
	
	@ResponseBody //NOS PERMITE RETORNAR UNICAMENTE DATOS, Y NO UNA VISTA
    @RequestMapping(value="/deleteEmpleado", method = RequestMethod.DELETE, produces = "application/json") //NOMBRE DEL SERVICIO
	ResponseEntity <ResponseDto> deleteEmpleado(@RequestBody Empleados empleado){  //NOMBRE DEL METODO
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceEmpleados.deleteEmpleado(empleado);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
    }
	
	
	
}
