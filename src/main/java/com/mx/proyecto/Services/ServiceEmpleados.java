package com.mx.proyecto.Services;

import java.util.List;

import com.mx.proyecto.Dto.Empleados;
import com.mx.proyecto.Dto.ResponseDto;

public interface ServiceEmpleados {
	
	//List<Empleados> getEmpleados();
	
	List<Empleados> getEmpleados(Empleados empleado);
	
	ResponseDto insertEmpleados(Empleados empleado);
	
	ResponseDto updateEmpleado(Empleados empleado);
	
	ResponseDto deleteEmpleado(Empleados empleado);
	
	/*List<Empleados> getEmpleados(){
	  return = null;
	 */

}
