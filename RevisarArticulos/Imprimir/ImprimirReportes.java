package RevisarArticulos.Imprimir;

import java.awt.Dimension;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Iterator;

import Impresion.Imprimir;
import Impresion.MuestraEnPanel.VentanaPreVisualizacion;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import RevisarArticulos.VentanaMarbetes.ListaMarbetes;
import RevisarArticulos.VentanaMarbetes.VentanaMarbete;
import RevisarArticulos.VentanaRevision.DatosRevision;
import Scanner.DatosScanners;

public class ImprimirReportes{
	
	ArrayList<ArrayList<DatosRevision>> libroRev;
	Imprimir impReportes;
	Book libro=new Book();
	PrinterJob generarImpresion;
	VentanaPreVisualizacion preVisual;
	Dimension medida;
	PageFormat pf;
	
	public ImprimirReportes(VentanaPrincipal vtana, VentanaMarbete vMarb, String tituloImp) {
		medida = new Dimension(761,1031);
		cargarSeleccion(vMarb.seleccion, vtana.panelScanner.tablaScanners.nvomodelo.datScan);
		preVisual = new VentanaPreVisualizacion(tituloImp, libroRev, vMarb, medida, 2);
		if(preVisual.imprimir==true){
			impReportes = new Imprimir();
			pf = impReportes.formatoReportes();
			libro = impReportes.crearLibroReporte(tituloImp, preVisual.imgImpr, pf);
			impReportes.imprimir(libro);
		}
	}
	
	private void cargarSeleccion(ArrayList<ListaMarbetes> seleccion, ArrayList<DatosScanners> datScan){
		int lineas=(int) ((medida.getHeight()-98)/22);
		libroRev=new ArrayList<ArrayList<DatosRevision>>();
		ArrayList<DatosRevision> hojaRev=new ArrayList<DatosRevision>();
		Iterator<DatosScanners> leerDatScan = datScan.iterator();// Recorremos el ArrayList con un Iterator pare leer todos los datos
		DatosRevision linAnt=null;
		while(leerDatScan.hasNext()){
			DatosScanners linScan = (DatosScanners) leerDatScan.next();//Se lee por linea los datos del escaner
			Iterator<ListaMarbetes> leerSelec=seleccion.iterator();
			while(leerSelec.hasNext()){
				ListaMarbetes linSelec=(ListaMarbetes) leerSelec.next();
				if(linSelec.getScan().equals(linScan.getScan()) & linSelec.getMarbete().equals(linScan.getMarbete())){//Confirma la seleccion de marbetes
					DatosRevision linRev=new DatosRevision(Integer.toString(linScan.getFila()), linScan.getScan(), linScan.getConsecutivo(), linSelec.getNombre(),
							linSelec.getUbiacion(), linScan.getMarbete(),	linScan.getArt(), linScan.getCant(), linScan.getDescripcion(),
							linScan.getExiste(),linScan.getExiste(),false,false);
					if(linAnt==null){
						linAnt=linRev;
					}
					if(hojaRev.size()<=lineas & linRev.getScan().equals(linAnt.getScan()) & linRev.getMarb().equals(linAnt.getMarb())){}else{
						//Si se llego al limite de hoja o cambia el scaner o el marbete
						libroRev.add(hojaRev);//Se carga la hoja al libro
						hojaRev=new ArrayList<DatosRevision>();//se inicia de nuevo la hoja
					}
					hojaRev.add(linRev);
					linAnt=linRev;
				}
			}
		}
		libroRev.add(hojaRev);//Se carga la ultima hoja
	}	

}
