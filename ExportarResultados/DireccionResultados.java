package ExportarResultados;
/* Programa Inventarios
 * Clase JFileChooser para abrir un archivo  .txt
 */


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Inicio.VentanaPrincipal.VentanaPrincipal;

public class DireccionResultados{
	VentanaPrincipal vtana;
	public DireccionResultados(VentanaPrincipal vtana){
		this.vtana=vtana;
	}
	
	public String dirArchivo(){
		String ruta=null;
		int acc;
		FileNameExtensionFilter extension=new FileNameExtensionFilter("Archivo delimitado por comas: .csv", "csv");
		/* Se carga el JFileChooser con el directorio del proyecto
		 * para hacer eso se usa la clase File con su metodo getPath
		 */
		do{
			JFileChooser nvadir=new JFileChooser(vtana.dirInventario);
			nvadir.setDialogTitle("Guardar resultados del Scanner");//Titulo para el showOpenDialog
			nvadir.setFileFilter(extension);
			acc=nvadir.showSaveDialog(vtana);
			if(acc==JFileChooser.APPROVE_OPTION){
				ruta=nvadir.getSelectedFile().getPath();
				System.out.println("GuardarResultados dir "+ruta.substring(ruta.length()-4));
				if(ruta.substring(ruta.length()-4).equals(".csv")){}else{
					ruta=ruta+".csv";
				}
				File archivo = new File(ruta);
				if(archivo.exists()){
					int q=JOptionPane.showConfirmDialog(vtana, "El archivo "+nvadir.getSelectedFile().getName()+
							" ya existe deseas reemplazarlo", "Archivo Existente", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, vtana.question);
					if(q!=0){
						ruta=null;
					}
				}	
			}
						
		}while(ruta==null && acc==JFileChooser.APPROVE_OPTION);
		
		return ruta;
	}

}
