package Configuracion;

import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class EscojerArchivo {
	JDialog padre;
	public EscojerArchivo(JDialog padre){
		this.padre=padre;
	}
	
	public String abrirScanner(String titulo,String dir, String tipoArchivo, int ext){
		String ruta=dir;
		/* Se carga el JFileChooser con el directorio del proyecto
		 * para hacer eso se usa la clase File con su metodo getPath
		 */
		JFileChooser nvadir=new JFileChooser(dir);
		nvadir.setDialogTitle(titulo);//Titulo para el showOpenDialog
		switch (ext) {
		case 1://Catalogo
			nvadir.setFileFilter(new FileNameExtensionFilter(tipoArchivo, "csv"));
			break;
		case 2://Scanner
			nvadir.setFileSelectionMode(JFileChooser.FILES_ONLY);
			break;
		case 3://Archivos de Utilidad
			nvadir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);	
			break;
		default:
			break;
		}
		int acc=nvadir.showOpenDialog(padre);
		if(acc==JFileChooser.APPROVE_OPTION){
			ruta=nvadir.getSelectedFile().getPath();
		}
		return ruta;
	}

}
