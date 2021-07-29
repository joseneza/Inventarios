package ExportarResultados.ImprimirHojaPreInv;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.util.ArrayList;

import Impresion.Imprimir;
import Impresion.MuestraEnPanel.PanelHoja;
import Impresion.MuestraEnPanel.VentanaPreVisualizacion;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import RevisarArticulos.VentanaMarbetes.VentanaMarbete;
import RevisarArticulos.VentanaRevision.DatosRevision;

public class ImprimirPreInventario {
	PrepararImprePreInv nvoPreInv;
	ArrayList<DatosRevision> datImpr;
	Imprimir imprimirPerInv;
	VentanaPreVisualizacion preVisual;
	
	public ImprimirPreInventario(VentanaPrincipal vtana, VentanaMarbete vtanaMarb){
		nvoPreInv=new PrepararImprePreInv(vtana, vtanaMarb);
		imprimirPerInv=new Imprimir();
	}
	
	public ArrayList<ArrayList<DatosRevision>> prepararImpresion(){		
		datImpr = nvoPreInv.cargarSelec();//Se carga la seleccion de marbetes
		ArrayList<DatosRevision> conteo = nvoPreInv.concentrarLista(datImpr);
		return nvoPreInv.separarConteo(conteo);
	}
	
	public void preVisualizacion(String tituloImp, VentanaMarbete vtanaMarb, ArrayList<ArrayList<DatosRevision>> contCon){
		vtanaMarb.setVisible(false);
		preVisual=new VentanaPreVisualizacion(tituloImp, contCon, vtanaMarb, new Dimension(881, 1140),3);
		if(preVisual.imprimir==true){
			imprimir(preVisual.imgImpr);
		}
		vtanaMarb.setVisible(true);
		
		
	}
	
	public void imprimir(ArrayList<PanelHoja> contConsent){
		PageFormat pf=imprimirPerInv.formato();
		Book libro=imprimirPerInv.crearLibroPreInventario(contConsent, pf);
		imprimirPerInv.imprimir(libro);
	}
	
	

}
