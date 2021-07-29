package RevisarArticulos.CambiosHechos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class RegistroCambios {
	
	PrintStream scr;
	
	public RegistroCambios(String dir, Boolean tipo){		
		abrirArchivo(dir, tipo);
		
	}
	
	private void abrirArchivo(String dir, Boolean tipo){
		try {
			scr=new PrintStream(new FileOutputStream(dir,tipo));
		} catch (FileNotFoundException e) {
			scr=null;
		}
	}
	
	public void escribirLinea(String linea){
		scr.println(linea);
	}
	
	public void cerrarReg(){
		scr.close();
	}

}
