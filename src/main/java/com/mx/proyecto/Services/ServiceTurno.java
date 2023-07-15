package com.mx.proyecto.Services;

import java.util.List;

import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.Turno;

public interface ServiceTurno {
	
	List<Turno> getTurno();
	
	//List<Turno> getTurno(Turno turno);
	
	ResponseDto insertTurno (Turno turno);
	
	ResponseDto updateTurno (Turno turno);
	
	ResponseDto deleteTurno (Turno turno);

	ResponseDto insertTurnoMasivo (Turno[] arregloTurno);
	
	ResponseDto cargaTurnoMasivoByTxtFile ();

 
}
