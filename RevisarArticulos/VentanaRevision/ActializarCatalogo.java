package RevisarArticulos.VentanaRevision;

import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import Inicio.CatalogoArticulos.CargarCatalogo;
import Inicio.VentanaPrincipal.VentanaPrincipal;

public class ActializarCatalogo {
	VentanaPrincipal vtana;
	VentanaRevision vtanaRev;
	CargarCatalogo cargarCat;
	
	String ruta="";	
	public ActializarCatalogo(VentanaPrincipal vtana, VentanaRevision vtanaRev){
		this.vtana=vtana;
		this.vtanaRev=vtanaRev;
		abrirCatalogo();
		if(ruta.length()>0){
			cargarCat = new CargarCatalogo(ruta);
			if(cargarCat.validar==true){
				actualizarCatalogo();
			}
		}
	}
	
	private void abrirCatalogo(){
		JFileChooser nvadir=new JFileChooser();
		nvadir.setDialogTitle("");//Titulo para el showOpenDialog
		nvadir.setFileFilter(new FileNameExtensionFilter("Archivo .csv", "csv"));
		int acc=nvadir.showOpenDialog(vtanaRev);
		if(acc==JFileChooser.APPROVE_OPTION){
			ruta=nvadir.getSelectedFile().getPath();
		}
	}
	
	private void actualizarCatalogo(){
		Iterator<DatosRevision> barRev = vtanaRev.tablaRev.modRev.datRev.iterator();
		while (barRev.hasNext()) {			
			DatosRevision linea = (DatosRevision) barRev.next();
			
			if(linea.getExiste()==false){
				int num=cargarCat.listCatalogo.indexOf(linea.getArt());
				if(num>-1){
					linea.setDescripcion(vtana.listDescripcion.get(num));
					linea.setAgregar(true);
				}
			}			
		}
		vtanaRev.tablaRev.modRev.fireTableDataChanged();
	}

}
