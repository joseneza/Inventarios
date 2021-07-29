package Scanner.Operaciones;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Scanner.TablaScanner.PanelAgregarScanner;

/* Programa Inventarios
 * Crear el consentrado del inventario
 */
public class ConsentradoScanner {
	String dir;
	String scanner;
	String numero;
	String nombre;
	String ubicacion;
	public ConsentradoScanner(String numero, String scanner, String nombre, String ubicacion, String dirInventario) throws FileNotFoundException{
		this.scanner=scanner;
		this.numero=llenarEspacios(numero);
		this.nombre=llenarEspacios(nombre);
		this.ubicacion=llenarEspacios(ubicacion);
		cargarConsentrado(dirInventario);
	}
	
	private void cargarConsentrado(String dirInventario){
		dir=dirInventario+"\\Concentrado.dat";
		PrintStream scr;
		try {
			scr = new PrintStream(new FileOutputStream(dir,true));
			scr.println(numero+nombre+ubicacion+scanner);
			scr.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al escribir en archivo de Concentrado","ERROR",JOptionPane.ERROR_MESSAGE,
					new ImageIcon(PanelAgregarScanner.class.getResource("/Iconos/48x48/dialog-error.png")));
			e.printStackTrace();
		}
		
	}
	
	private String llenarEspacios(String cadena){
		for(int cad=cadena.length();cad<18;cad++){
			cadena=cadena+" ";
		}
		return cadena;
		
	}

}
