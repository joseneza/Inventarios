package RevisarArticulos;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import Inicio.CatalogoArticulos.DatosMarbetes;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import RevisarArticulos.VentanaMarbetes.VentanaMarbete;
import RevisarArticulos.VentanaRevision.VentanaRevision;

public class AcciondeOpcionesRevision {
	
	VentanaPrincipal vtana;
	VentanaRevision vtanaRev;
	VentanaMarbete vtanaMarb;
	ArrayList<DatosMarbetes> datMarbete;
	ArrayList<String> marbDupli;
	String mensaje;
	int valor;
	
	public AcciondeOpcionesRevision(VentanaPrincipal vtana,int valor){
		this.valor=valor;
		this.vtana=vtana;
		mensaje = vtana.mensajeEstado.getText();
		accion();
	}
	
	private void accion(){
		switch (valor){
		case 1:
			Boolean error = false;
			Iterator<DatosMarbetes> revMarbetes=vtana.datMarbete.iterator();
			while(revMarbetes.hasNext()){
				DatosMarbetes linRev = revMarbetes.next();
				if(linRev.getError()>0){
					error = true;
				}
			}
			vtana.mensajeEstado.setText(mensaje+" - Articulos No Encontrados");
			if(error==true){
				vtanaMarb = new VentanaMarbete(vtana, valor, "Revision - Articulos No Encontrados", false);	
			}else{
				JOptionPane.showMessageDialog(vtana, "Todos los articulos fueron reconocidos por el programa", "ARTICULOS NO ENCONTRADOS",
						JOptionPane.INFORMATION_MESSAGE, vtana.information);
			}
			break;
		case 2:
			vtana.mensajeEstado.setText(mensaje+" - Marbetes Duplicados");
			this.datMarbete=vtana.datMarbete;
			marbDupli=new ArrayList<String>();
			if(buscarDuplicados()==true){
				vtanaMarb=new VentanaMarbete(vtana, valor, "Revision - Marbetes Duplicados", true, marbDupli);
			}else{
				JOptionPane.showMessageDialog(vtana, "No se encontraron marbetes duplicados en este inventario", "REVISION DE MARBETES",
						JOptionPane.INFORMATION_MESSAGE, vtana.information);
			}
			break;			
		case 3:
			vtana.mensajeEstado.setText(mensaje+" - Ver Marbetes");
			vtanaMarb = new VentanaMarbete(vtana, valor, "Revision - Ver Marbetes", true);
			break;			
		case 0:			
			break;
		}
		vtana.mensajeEstado.setText(mensaje);
	}
	
	public Boolean buscarDuplicados(){
		Boolean dupli=false;
		ArrayList<String> marbetes = new ArrayList<String>();
		Iterator<DatosMarbetes> revMarbetes=datMarbete.iterator();
		while(revMarbetes.hasNext()){
			DatosMarbetes linea = revMarbetes.next();
			if(linea.getTotal()>0){
				if(marbetes.contains(linea.getMarbete())){
					dupli=true;
					marbDupli.add(linea.getMarbete());
				}else{
					marbetes.add(linea.getMarbete());
				}
			}			
		}		
		return dupli;
	}
}
