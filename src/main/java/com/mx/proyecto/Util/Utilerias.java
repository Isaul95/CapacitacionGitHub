package com.mx.proyecto.Util;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;
import com.mx.proyecto.Dto.Supervisores;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utilerias {

	public List<Supervisores> leerArchivoSupervisores(){
		
		 BufferedReader buffer = null;

	        Supervisores supervisor = null;
	        List<Supervisores> listaSupervisores = new ArrayList<>();

	        try {
	            // Obtenci n de archivoC:\Users\Hitss\Desktop\ALEXIS\ESCRITORIO\CURSOS\MARZO
	            FileInputStream fileInput = new FileInputStream("C:/Users/1100389751/Documents/JORGE/PROGRAMADOR/ECLIPSE - ALEXIS/BASE DE DATOS TRABAJADORES.txt"); // OBTIENE EL ARCHIVO TXT DE LA RUTA DE NUESTRA MAQUINA
	            // Apertura de archivo
	            DataInputStream dataInput = new DataInputStream(fileInput);
	            buffer = new BufferedReader(new InputStreamReader(dataInput)); // LEE EL CONTENIDO DEL ARCHIVO
	            String linea = "";
	            int lineas = 0;
	            boolean continuarLeyendo = true; // SOLO PUEDE CONTENER VALORES TRUE OR FALSE
	            boolean error = false;            
	            // Bucle de lectura de archivo 
	            while ((linea = buffer.readLine()) != null && continuarLeyendo) {
	                supervisor = new Supervisores();
	                SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-DD");
	               
	                //System.out.println(linea); 
	                String separador = Pattern.quote("|");
	                String[] partes = linea.split(separador);
	                System.out.println(linea);
	                Date fechaNacimiento = formato.parse(partes[2]);
	               
	                supervisor.setNombre(partes[0]);
	                supervisor.setDireccion(partes[1]);
	                supervisor.setFechaNacimiento(fechaNacimiento);
	                supervisor.setEdad(Integer.parseInt(partes[3]));
	                supervisor.setSexo(partes[4]);
	                supervisor.setEstadoCivil(partes[5]);
	                supervisor.setSalario(Double.parseDouble(partes[6]));
	                supervisor.setCorreoElectronico(partes[7]);
	                supervisor.setCelular(Long.parseLong(partes[8]));
	                
	                //producto.setCategoria(semillas.getIdCategoria());
	                listaSupervisores.add(supervisor);
	                lineas++;
	            }
	            // Cierre de archivo 
	            dataInput.close();
	            System.out.println("Lineas: " + lineas);

	        } catch (FileNotFoundException e) {
	            System.out.println("El archivo no existe");
	        }
	        catch(NumberFormatException NFE){
	             //System.out.println("La linea: " + lineas + " tiene un error debido a que no tiene informacion al leer el: "+datoLeido(elemento));
	            return null;
	        } catch (IOException e) {
	        	 System.out.println("Error al leer el archivo");
			} catch (ParseException e) {
				 System.out.println("Error al convertir la fecha del archivo");
			}  

		return listaSupervisores;
	}
	
}
