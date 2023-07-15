package com.mx.proyecto.ServicesImpl;


import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.Empleados;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Repository.RepositoryEmpleados;
import com.mx.proyecto.Services.ServiceEmpleados;

@Service
public class ServiceEmpleadosImpl implements ServiceEmpleados{
	
	@Autowired
	private RepositoryEmpleados repositoryEmpleados;
	
	@Override
	public List<Empleados> getEmpleados(Empleados empleado) {
		StringBuffer query = new StringBuffer();
		List<Object> valores = new ArrayList();
		query.append("SELECT * FROM EMPLEADOS WHERE 1=1");
		
		if (empleado.getIdEmpleados()>0) {
			query.append("AND ID_EMPLEADO = ?");
			valores.add(empleado.getIdEmpleados());
		}
		
		if (empleado.getNombre()!=null &&!empleado.getNombre().equals("")) {
			query.append("AND NOMBRE = ?");
			valores.add(empleado.getNombre());
		}
		
		if(empleado.getDireccion()!=null &&!empleado.getDireccion().equals("")) {
			query.append("AND DIRECCION = ?");
			valores.add(empleado.getDireccion());
			
		}
		
		if(empleado.getFechaNacimiento()!= null) {
			query.append("AND FECHA_NACIMIENTO = ?");
			valores.add(empleado.getFechaNacimiento());
		}
		
		if(empleado.getEdad()>0&&empleado.getEdad()>20&&empleado.getEdad()<120) {
			query.append("AND EDAD = ?");
			valores.add(empleado.getEdad());
		}
		
		if(empleado.getSalario()>0&&empleado.getSalario()>2000&&empleado.getSalario()<120000) {
			query.append("AND SALARIO = ?");
			valores.add(empleado.getSalario());			
		}
		
		if(empleado.getOcupacion()!=null&&!empleado.getOcupacion().equals("")) {
			query.append("AND OCUPACION = ?");
			valores.add(empleado.getOcupacion());
		}
			
		
		// TODO Auto-generated method stub
		return repositoryEmpleados.getEmpleados(empleado, query, valores);
	}
	
	/*
	@Override
	public List<Empleados> getEmpleados() {
		
		
		// TODO Auto-generated method stub
		System.out.println("Llegando al metodo getEmpleados de la clase ServiceEmpleadosImpl");
		return repositoryEmpleados.getEmpleados();
	}
	*/

	
	public ResponseDto insertEmpleados(Empleados empleado){
		Integer resultado = repositoryEmpleados.insertEmpleados(empleado);
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
	public ResponseDto updateEmpleado(Empleados empleado) {
		StringBuffer query = new StringBuffer();
		List<Object> valores = new ArrayList();
		ResponseDto response = new ResponseDto();
		boolean ejecutarQuery=false,unElemento=false;
	
		query.append("UPDATE EMPLEADOS SET ");
	
		if(empleado.getNombre()!=null&&!empleado.getNombre().equals("")) {
			query.append("NOMBRE = ?, ");
			valores.add(empleado.getNombre());
			unElemento=true;
			
		}
	    if(empleado.getDireccion()!=null&&!empleado.getDireccion().equals("")) {
			query.append("DIRECCION = ?, ");
			valores.add(empleado.getDireccion());
			unElemento=true;
			
	    }
		if(empleado.getFechaNacimiento()!=null) {
			query.append("FECHA_NACIMIENTO = ?, ");
			valores.add(empleado.getOcupacion());
			unElemento=true;
	
			
		}
	    if(empleado.getEdad()>0&&empleado.getEdad()>20&&empleado.getEdad()<120) {
		    	query.append("EDAD = ?, ");
				valores.add(empleado.getEdad());
				unElemento=true;
		
		}
		if(empleado.getSalario()>0&&empleado.getSalario()>2000&&empleado.getSalario()<120000) {
			query.append("SALARIO = ?, ");
			valores.add(empleado.getSalario());
			unElemento=true;

		}
		if(empleado.getOcupacion()!=null&&!empleado.getOcupacion().equals("")) {
			System.out.println("ENTRE AL IF DE OCUPACION");
			query.append("OCUPACION = 'CAJERASSSS' ");
			valores.add(empleado.getOcupacion());
			unElemento=true;

		}
		if(empleado.getIdEmpleados()>0) {
			query.append("WHERE ID_EMPLEADO = 6");
			valores.add(empleado.getIdEmpleados());
			ejecutarQuery=true;
		}

        System.out.println(query.toString());
        for(Object valor:  valores) {
            System.out.println(String.valueOf(valor));
        }
		if(ejecutarQuery==true&&unElemento==true) {
			Integer resultado = repositoryEmpleados.updateEmpleado(empleado,query,valores);
		
			if(resultado == 1) {
				response.setMessage("Se actualizo correctamente");
				response.setCode(1);
			}
			else {
				response.setMessage("No se actualizo correctamente");
				response.setCode(-1);
			}
		}
		else {
			response.setMessage("No existen suficientes valores para actualizar");
			response.setCode(-500);
		}

		
		// TODO Auto-generated method stub
		return response;
	}


	@Override
	public ResponseDto deleteEmpleado(Empleados empleado) {
		Integer resultado = repositoryEmpleados.deleteEmpleado(empleado);
		ResponseDto response = new ResponseDto();
		String respuesta = "";
		
		if(resultado == 1)
			respuesta = "Se elimino correctamente el registro " + empleado.getIdEmpleados();
		
		else	
			respuesta = "No se elimino el registro " + empleado.getIdEmpleados();
		
		response.setMessage(respuesta);
		response.setCode(
				
				(respuesta.contains("correctamente")?1 : -1)
				
				);
		
		// TODO Auto-generated method stub
		return response;
	}


	

}
