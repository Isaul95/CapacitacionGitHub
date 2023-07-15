package com.mx.proyecto.ServicesImpl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.proyecto.Dto.Empleados;
import com.mx.proyecto.Dto.ResponseDto;
import com.mx.proyecto.Dto.Supervisores;
import com.mx.proyecto.Dto.Turno;
import com.mx.proyecto.Repository.RepositoryEmpleados;
import com.mx.proyecto.Repository.RepositorySupervisores;
import com.mx.proyecto.Repository.RepositoryTurno;
import com.mx.proyecto.RepositoryImpl.RepositorySupervisoresImpl;
import com.mx.proyecto.Services.ServiceTurno;
import com.mx.proyecto.Util.TurnoUtilerias;


@Service
public class ServiceTurnoImpl implements ServiceTurno{
	
	@Autowired
	private RepositoryTurno repositoryTurno;

	@Autowired
	private RepositoryEmpleados repositoryEmpleados;
	
	@Autowired
	private RepositorySupervisores repositorySupervisores;
 
	@Override
	public List<Turno> getTurno() {
		// TODO Auto-generated method stub
		return repositoryTurno.getTurno();
	}
	
	/*@Override
	public List<Turno> getTurno(Turno turno) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public ResponseDto insertTurno(Turno turno) {
		Integer resultado = repositoryTurno.insertTurno(turno);
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
	public ResponseDto updateTurno(Turno turno) {
		Integer resultado = repositoryTurno.updateTurno(turno);
		ResponseDto response = new ResponseDto();
		if(resultado == 1) {
			response.setMessage("Se actualizo correctamente " + turno.getEmpleado());
			response.setCode(1);
		}
		else {
			response.setMessage("No se actualizo correctamente " + turno.getEmpleado());
			response.setCode(-1);
		}
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public ResponseDto deleteTurno(Turno turno) {
		Integer resultado = repositoryTurno.deleteTurno(turno);
		ResponseDto response = new ResponseDto();
		if(resultado == 1) {
			response.setMessage("Se elimino correctamente " + turno.getEmpleado());
			response.setCode(1);
		}
		else {
			response.setMessage("No se elimino correctamente " + turno.getEmpleado());
			response.setCode(-1);
		}
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public ResponseDto insertTurnoMasivo(Turno[] arregloTurno) {
		ResponseDto response = new ResponseDto();
		List<Turno> listaTurno = new ArrayList<>();
		
		for(Turno turno: arregloTurno) {
			listaTurno.add(turno);
		}
		
		repositoryTurno.insertTurnoMasivo(listaTurno);
		
		response.setCode(1);
		response.setMessage("Se insertaron todos los registros");
		
		return response;
	}

	@Override
	public ResponseDto cargaTurnoMasivoByTxtFile() {
		ResponseDto response = new ResponseDto();
		TurnoUtilerias llamar = new TurnoUtilerias();
		response =leerArchivoTurno();
		if(response.getCode()>0) {
			List<Turno> listaTurno=(List<Turno>) response.getContent();
					repositoryTurno.insertTurnoMasivo(listaTurno);
			response.setCode(1);
			response.setMessage("Se insertaron todos los registros");
		}
		
	
		return response;
		
		
		
	}
	
	public ResponseDto leerArchivoTurno() {
		

	
		String errores = "";
		
		ResponseDto respuesta = new ResponseDto();
		BufferedReader buffer = null;

		Turno turno = null;
		List<Turno> listaTurno = new ArrayList<>();

		try {
			// Obtenci n de archivoC:\Users\Hitss\Desktop\ALEXIS\ESCRITORIO\CURSOS\MARZO
			FileInputStream fileInput = new FileInputStream(
					"C:/Users/1100389751/Documents/JORGE/PROGRAMADOR/ECLIPSE - ALEXIS/HORARIOS.txt");                       
			// OBTIENE EL ARCHIVO TXT DE LA RUTA DE NUESTRA MAQUINA																											
																														
			// Apertura de archivo
			DataInputStream dataInput = new DataInputStream(fileInput);
			buffer = new BufferedReader(new InputStreamReader(dataInput)); // LEE EL CONTENIDO DEL ARCHIVO
			String linea = "";
			int lineas = 0;
			boolean continuarLeyendo = true; // SOLO PUEDE CONTENER VALORES TRUE OR FALSE
			boolean error = false;
			// Bucle de lectura de archivo
			while ((linea = buffer.readLine()) != null && continuarLeyendo) {
				turno = new Turno();
				SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-DD");

				// System.out.println(linea);
				String separador = Pattern.quote("|");
				String[] partes = linea.split(separador);
				System.out.println(linea);
				
				if(partes[0].length()<15) {
					turno.setTurno(partes[0]);
				}else {
					errores+="El turno tiene una longitud mayor a 15 en la linea "+lineas+"\n";
				}
                if(partes[1].length()<30) {
                	turno.setArea(partes[1]);
				}
                else {
                	errores+="El area tiene una longitud mayor a 30 en la linea "+lineas+"\n";
				}
				
                Empleados empleado =repositoryEmpleados.getEmpleadoById(Long.parseLong(partes[2]));
                if(empleado != null) {
                	turno.setEmpleado(Long.parseLong(partes[2]));
                }
                else {
                	errores+="El id "+partes[2]+" no existe en la tabla EMPLEADOS.en la linea "+lineas+"\n";
                }
                Supervisores supervisor = repositorySupervisores.getSupervisorById(Long.parseLong(partes[3]));
                if(supervisor != null) {
                	turno.setSupervisor(Long.parseLong(partes[3]));
                }
                else {
                	errores+="El id "+partes[3]+" no existe en la tabla SUPERVISORES.en la linea "+lineas+"\n";
                }
                
				if(errores.equals("")) {
					listaTurno.add(turno);
				}
				
				lineas++;
			}
			if(listaTurno!=null&&listaTurno.size()>0&&errores.equals("")) {
				respuesta.setContent(listaTurno);
				respuesta.setCode(1);
				respuesta.setMessage("La lista tiene informaci�n");
			}
			else {
				respuesta.setCode(-1);
				respuesta.setMessage(errores);
				respuesta.setContent("Por lo tanto no se carg� ningun registro");
			}

			// Cierre de archivo
			dataInput.close();
			System.out.println("Lineas: " + lineas);

		} catch (FileNotFoundException e) {
			System.out.println("El archivo no existe");
		} catch (NumberFormatException NFE) {
			// System.out.println("La linea: " + lineas + " tiene un error debido a que no
			// tiene informacion al leer el: "+datoLeido(elemento));
			return null;
		} catch (IOException e) {
			System.out.println("Error al leer el archivo");
		} 

		return respuesta;
	}

	
	

}