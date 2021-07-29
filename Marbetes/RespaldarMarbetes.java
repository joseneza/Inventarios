package Marbetes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import Marbetes.TablaMarbetes.DatosLineaMarbete;

public class RespaldarMarbetes {
	String dir;
	String titulo;
	ArrayList<DatosLineaMarbete> datos;
	PrintStream grabarTabla;
	
	public RespaldarMarbetes(ArrayList<DatosLineaMarbete> datos, String titulo, String dir, String tipoM, int max){
		this.datos=datos;
		this.titulo=titulo;
		this.dir=dir+"\\ValoresMarbetes.dat";
		/* Se crea un PrintStream para poder dar formato se auxilia de la clase FileOutputStream para poder escribir un archivo al cual no se le 
		 * agrega el segundo parametro para que se crea un archivo nuevo y se borre el anterior
		 */
		try {
			grabarTabla=new PrintStream(new FileOutputStream(this.dir));
			grabarTabla.println(titulo);
			grabarTabla.println(tipoM+","+Integer.toString(max));
			cargarDatos();
			grabarTabla.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void cargarDatos(){
		int y=datos.size()-1;
		for(int i=0; i<y; i++) {
			DatosLineaMarbete linea=(DatosLineaMarbete) datos.get(i);
			String ubic=linea.getUbicacion();
			for(int h=linea.getUbicacion().length();h<30;h++){
				ubic=ubic+" ";
			}
			String cant=linea.getCantidad();
			for(int h=linea.getCantidad().length();h<5;h++){
				cant=cant+" ";
			}
			grabarTabla.println(ubic+cant);
		}
	}

}
