package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Dto.Empleados;

public interface RepositoryEmpleados {
	
	//List<Empleados> getEmpleados();
	
	List<Empleados> getEmpleados(Empleados empleado,StringBuffer query, List<Object> valores);
	
	Integer insertEmpleados(Empleados empleado);
	
	Integer updateEmpleado(Empleados empleado,StringBuffer query, List<Object> valores);
	
	Integer deleteEmpleado(Empleados empleado);
	
	Empleados getEmpleadoById(long idEmpleado);

}
