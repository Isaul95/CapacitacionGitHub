package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Dto.Supervisores;

public interface RepositorySupervisores {
	
	List<Supervisores> getSupervisores();

	Integer insertSupervisores(Supervisores supervisores);
	
	Integer updateSupervisores(Supervisores supervisores);
	
	Integer deleteSupervisores(Supervisores supervisores);
	
	int[][] insertSupervisoresMasivo(List<Supervisores> listaSupervisores);
	
	Supervisores getSupervisorById(long idSupervisor);

}
