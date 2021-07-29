package RevisarArticulos.CambiosHechos;

import java.util.ArrayList;
import java.util.Iterator;

import Inicio.VentanaPrincipal.VentanaPrincipal;
import RevisarArticulos.VentanaMarbetes.ListaMarbetes;
import RevisarArticulos.VentanaMarbetes.VentanaMarbete;
import RevisarArticulos.VentanaRevision.DatosRevision;
import Scanner.DatosScanners;

public class BorrarMarbetes {
	
	VentanaPrincipal vtana;
	VentanaMarbete vtanaMarb;
	ArrayList<ListaMarbetes> seleccion;
	ArrayList<DatosRevision> lineaBorrar;
	GuardarCambios cambios;
	
	public BorrarMarbetes(VentanaPrincipal vtana,VentanaMarbete vtanaMarb){
		this.vtana=vtana;
		this.vtanaMarb=vtanaMarb;
		this.seleccion=vtanaMarb.seleccion;
		tomarLineas();
		cambios=new GuardarCambios(vtana, lineaBorrar, vtanaMarb, 2);
	}
	
	private void tomarLineas(){
		lineaBorrar=new ArrayList<DatosRevision>();
		for(int n=0;n<seleccion.size();n++){
			Iterator<DatosScanners> barrerScan = vtana.panelScanner.tablaScanners.nvomodelo.datScan.iterator();
			while(barrerScan.hasNext()){
				DatosScanners linea=(DatosScanners) barrerScan.next();
				if(linea.getScan().equals(seleccion.get(n).getScan()) & linea.getMarbete().equals(seleccion.get(n).getMarbete())){
					DatosRevision nvaLin = new DatosRevision(Integer.toString(linea.getFila()), linea.getScan(), linea.getConsecutivo(),
							seleccion.get(n).getNombre(), seleccion.get(n).getUbiacion(), linea.getMarbete(), linea.getArt(),
							linea.getCant(), linea.getDescripcion(), linea.getExiste(), false, true, false);
					lineaBorrar.add(nvaLin);
				}
			}
		}
	}

}
