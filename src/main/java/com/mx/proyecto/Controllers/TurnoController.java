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
import com.mx.proyecto.Dto.Turno;
import com.mx.proyecto.Services.ServiceTurno;

@Controller
@RequestMapping(value = "turno")
public class TurnoController {

	@Autowired
	private ServiceTurno serviceTurno;

	@ResponseBody // NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value = "/getTurno", method = RequestMethod.GET, produces = "application/json")
	ResponseEntity<List<Turno>> getTurno() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		List<Turno> lista = serviceTurno.getTurno();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<List<Turno>>(lista, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody // NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value = "/insertTurno", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity<ResponseDto> insertTurno(@RequestBody Turno turno) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceTurno.insertTurno(turno);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody // NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value = "/updateTurno", method = RequestMethod.PUT, produces = "application/json")
	ResponseEntity<ResponseDto> updateTurno(@RequestBody Turno turno) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceTurno.updateTurno(turno);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody // NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value = "/deleteTurno", method = RequestMethod.DELETE, produces = "application/json")
	ResponseEntity<ResponseDto> deleteTurno(@RequestBody Turno turno) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceTurno.deleteTurno(turno);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody // NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value = "/insertTurnoMasivo", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity<ResponseDto> insertTurnoMasivo(@RequestBody Turno[] arregloTurno) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceTurno.insertTurnoMasivo(arregloTurno);
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}
	
	@ResponseBody // NOS PERMITE RETORNAR DATOS Y NO SOLO UNA VISTA
	@RequestMapping(value = "/cargaTurnoMasivoByTxtFile", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity<ResponseDto> cargaTurnoMasivoByTxtFile() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		
		ResponseDto response = serviceTurno.cargaTurnoMasivoByTxtFile();
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity <ResponseDto> (response, httpHeaders, HttpStatus.OK);
	}

}
