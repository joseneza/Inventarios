package Scanner.Operaciones;
/* Programa Inventarios
 * Clase JFileChooser para abrir un archivo  .txt
 */

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Inicio.VentanaPrincipal.VentanaPrincipal;

public class AbrirScanner{
	VentanaPrincipal ventanapadre;
	public AbrirScanner(VentanaPrincipal padre){
		this.ventanapadre=padre;
	}
	
	public String abrirScanner(){
		String ruta="";
		FileNameExtensionFilter extension=new FileNameExtensionFilter("Archivo de Texto", "txt");
		/* Se carga el JFileChooser con el directorio del proyecto
		 * para hacer eso se usa la clase File con su metodo getPath
		 */
		JFileChooser nvadir=new JFileChooser(ventanapadre.dirScanner);
		nvadir.setDialogTitle("Escoge el archivo para el Scanner");//Titulo para el showOpenDialog
		nvadir.setFileFilter(extension);
		int acc=nvadir.showOpenDialog(ventanapadre);
		if(acc==JFileChooser.APPROVE_OPTION){
			ruta=nvadir.getSelectedFile().getPath();
		}
		return ruta;
	}

}
