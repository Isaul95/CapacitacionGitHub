package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Dto.Turno;

public interface RepositoryTurno {
	
	List<Turno> getTurno();
	
	Integer insertTurno (Turno turno);

	Integer updateTurno (Turno turno);
	
	Integer deleteTurno (Turno turno);

	int[][] insertTurnoMasivo(List<Turno> listaTurno);

}
