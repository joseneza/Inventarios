package Marbetes.ImprimirMarbetes;
/* 
 * Clase que imprime los marbetes
 */
import java.awt.Dimension;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.Iterator;

import Impresion.Imprimir;
import Impresion.MuestraEnPanel.VentanaPreVisualizacion;
import Inicio.VentanaPrincipal.VentanaPrincipal;

public class ImprimirMarbetes {
	
	VentanaPreVisualizacion vtanaPreVisual;
	ArrayList<ArrayList<DatosMarbete>> libroMarbetes;
	Imprimir impMarb;
	PageFormat pf;
	Book libro;
	public Boolean impresion = false;
	
	public ImprimirMarbetes(VentanaPrincipal vtana, ArrayList<DatosMarbete> listImpresion){
		impMarb = new Imprimir();
		pf = impMarb.formato();
		cargarLista(listImpresion);
		vtanaPreVisual = new VentanaPreVisualizacion(VentanaPrincipal.titulo+" "+vtana.nvoinv.nomInv.textoTitulo.getText(), libroMarbetes, vtana,
				new Dimension(881, 1140));
		if(vtanaPreVisual.imprimir==true){
			libro = impMarb.crearLibroMarbete(VentanaPrincipal.titulo+" "+vtana.nvoinv.nomInv.textoTitulo.getText(), vtanaPreVisual.imgImpr, pf);
			impMarb.imprimir(libro);
			impresion = true;
		}		
	}
	
	private void cargarLista(ArrayList<DatosMarbete> listImpresion){
		libroMarbetes=new ArrayList<ArrayList<DatosMarbete>>();
		Iterator<DatosMarbete> recorrer = listImpresion.iterator();
		ArrayList<DatosMarbete> temporal=new ArrayList<DatosMarbete>();
		while (recorrer.hasNext()) {
			DatosMarbete linea = (DatosMarbete) recorrer.next();
			if(temporal.size() < 12){
				temporal.add(linea);
			}else{
				libroMarbetes.add(temporal);
				temporal=new ArrayList<DatosMarbete>();
				temporal.add(linea);
			}			
		}
		libroMarbetes.add(temporal);
	}
	
	
}
