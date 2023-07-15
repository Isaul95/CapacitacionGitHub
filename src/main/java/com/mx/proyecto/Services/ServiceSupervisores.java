package com.mx.proyecto.Services;

import java.util.List;

import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.Supervisores;

public interface ServiceSupervisores {
	
	List<Supervisores> getSupervisores();
	
	ResponseDto insertSupervisores (Supervisores supervisores);
	
	ResponseDto updateSupervisores (Supervisores supervisores);
	
	ResponseDto deleteSupervisores (Supervisores supervisores);
	
	ResponseDto insertSupervisoresMasivo (Supervisores[] arregloSupervisores);
	
	ResponseDto cargaSupervisoresMasivoByTxtFile ();

}
