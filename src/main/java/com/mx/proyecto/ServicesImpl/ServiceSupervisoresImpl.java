package com.mx.proyecto.ServicesImpl;

import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.Supervisores;
import com.mx.proyecto.Repository.RepositorySupervisores;
import com.mx.proyecto.Services.ServiceSupervisores;
import com.mx.proyecto.Util.Utilerias;

@Service
public class ServiceSupervisoresImpl implements ServiceSupervisores{
	
	@Autowired
	private RepositorySupervisores repositorySupervisores;

	@Override
	public List<Supervisores> getSupervisores() {
		// TODO Auto-generated method stub
		return repositorySupervisores.getSupervisores();
	}

	@Override
	public ResponseDto insertSupervisores(Supervisores supervisores) {
		Integer resultado = repositorySupervisores.insertSupervisores(supervisores);
		ResponseDto response = new ResponseDto();
		if(resultado == 1) {
			response.setMessage("Se inserto correctamente");
			response.setCode(1);
		}
		else {
			response.setMessage("No se inserto correctamente");
			response.setCode(-1);
		}
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public ResponseDto updateSupervisores(Supervisores supervisores) {
		Integer resultado = repositorySupervisores.updateSupervisores(supervisores);
		ResponseDto response = new ResponseDto();
		
		if(resultado == 1) {
			response.setMessage("Se actualizo correctamente");
			response.setCode(1);
		}
		else {
			response.setMessage("No se actualizo correctamente");
			response.setCode(-1);
		}
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public ResponseDto deleteSupervisores(Supervisores supervisores) {
		Integer resultado = repositorySupervisores.deleteSupervisores(supervisores);
		ResponseDto response = new ResponseDto();
		
		if(resultado == 1) {
			response.setMessage("Se elimino correctamente");
			response.setCode(1);
		}
		else {
			response.setMessage("No se elimino correctamente");
			response.setCode(-1);
		}
		// TODO Auto-generated method stub
		return response;
		
	}

	@Override
	public ResponseDto insertSupervisoresMasivo(Supervisores[] arregloSupervisores) {
		ResponseDto response = new ResponseDto();
		List<Supervisores> listaSupervisores = new ArrayList<>();
		
		for(Supervisores supervisores: arregloSupervisores) {
			listaSupervisores.add(supervisores);
		}
		
		repositorySupervisores.insertSupervisoresMasivo(listaSupervisores);
		
		response.setCode(1);
		response.setMessage("Se insertaron todos los registros");
		
		return response;
	}

	@Override
	public ResponseDto cargaSupervisoresMasivoByTxtFile() {
		ResponseDto response = new ResponseDto();
		Utilerias llamar = new Utilerias();
		List<Supervisores> listaSupervisores=llamar.leerArchivoSupervisores();
		
		repositorySupervisores.insertSupervisoresMasivo(listaSupervisores);
		response.setCode(1);
		response.setMessage("Se insertaron todos los registros");
		
		return response;
	}
	
	

}
