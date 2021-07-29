package ExportarResultados.ImprimirHojaPreInv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import Inicio.VentanaPrincipal.VentanaPrincipal;
import RevisarArticulos.VentanaMarbetes.ListaMarbetes;
import RevisarArticulos.VentanaMarbetes.VentanaMarbete;
import RevisarArticulos.VentanaRevision.DatosRevision;
import Scanner.DatosScanners;

public class PrepararImprePreInv {
	
	VentanaMarbete vMarb;
	VentanaPrincipal vtana;
	
	public PrepararImprePreInv (VentanaPrincipal vtana, VentanaMarbete vMarb){
		this.vMarb=vMarb;
		this.vtana=vtana;
	}
		
	public ArrayList<DatosRevision> cargarSelec(){
		ArrayList<DatosRevision> datSelec = new ArrayList<DatosRevision>();
		Iterator<DatosScanners> barScan = vtana.panelScanner.tablaScanners.nvomodelo.datScan.iterator();
		while(barScan.hasNext()){
			DatosScanners linScan = (DatosScanners) barScan.next();
			Iterator<ListaMarbetes> barMarb = vMarb.seleccion.iterator();
			while(barMarb.hasNext()){
				ListaMarbetes linMarb = barMarb.next();
				if(linScan.getScan().equals(linMarb.getScan()) & linScan.getMarbete().equals(linMarb.getMarbete())){
					datSelec.add(new DatosRevision(Integer.toString(datSelec.size()), linScan.getScan(), 0, linMarb.getNombre(),
							linMarb.getUbiacion(), linScan.getMarbete(), linScan.getArt(), linScan.getCant(), linScan.getDescripcion(),
							null, null, null, null));
				}
			}
		}
		return datSelec;		
	}
		
	public ArrayList<DatosRevision> concentrarLista(ArrayList<DatosRevision> datScan){
		Comparator<DatosRevision> compArt=new Comparator<DatosRevision>() {
			public int compare(DatosRevision o1, DatosRevision o2) {
				return new Long(o1.getArt()).compareTo(o2.getArt());
			}
		};
		Collections.sort(datScan, compArt);
		if(vMarb.chckbxImpConsentrado.isSelected()==false){
			Comparator<DatosRevision> compMarb=new Comparator<DatosRevision>() {
				public int compare(DatosRevision o1, DatosRevision o2) {
					return o1.getMarb().compareTo(o2.getMarb());
				}
			};
			Collections.sort(datScan, compMarb);
			Comparator<DatosRevision> compScan=new Comparator<DatosRevision>() {
				public int compare(DatosRevision o1, DatosRevision o2) {
					return o1.getScan().compareTo(o2.getScan());
				}
			};
			Collections.sort(datScan, compScan);
		}		
		ArrayList<DatosRevision> concent=new ArrayList<DatosRevision>();
		Iterator<DatosRevision> revScan=datScan.iterator();
		DatosRevision linAgr=null;
		while (revScan.hasNext()) {
			DatosRevision linea=revScan.next();
			if(linAgr==null){
				linAgr=linea;
			}else{
				if(vMarb.chckbxImpConsentrado.isSelected()==true){
					if(linea.getArt()==linAgr.getArt()){
						linAgr.setCant((short) (linAgr.getCant()+linea.getCant()));
					}else{
						linAgr.setFila(Integer.toString(concent.size()));
						linAgr.setScan("S000");
						linAgr.setMarb("");
						concent.add(linAgr);
						linAgr=linea;
					}
				}else{
					if(linea.getArt()==linAgr.getArt() && linea.getMarb().equals(linAgr.getMarb()) &&
							linea.getScan().equals(linAgr.getScan())){
						linAgr.setCant((short) (linAgr.getCant()+linea.getCant()));
					}else{
						linAgr.setFila(Integer.toString(concent.size()));
						concent.add(linAgr);
						linAgr=linea;
					}
				}
				
			}
		}
		if(vMarb.chckbxImpConsentrado.isSelected()==true){
			linAgr.setScan("S000");
			linAgr.setMarb("Consentrado");	
		}		
		concent.add(linAgr);
		return concent;	
	}
	
	public ArrayList<ArrayList<DatosRevision>> separarConteo(ArrayList<DatosRevision> conteo){
		ArrayList<ArrayList<DatosRevision>> nvoConteo = new ArrayList<ArrayList<DatosRevision>>();
		Iterator<DatosRevision> recorrer = conteo.iterator();
		ArrayList<DatosRevision> temporal=new ArrayList<DatosRevision>();
		while (recorrer.hasNext()) {
			DatosRevision linea = (DatosRevision) recorrer.next();
			if(temporal.isEmpty()){
				temporal.add(linea);
			}else{
				if(temporal.size() < 27 && linea.getScan().equals(temporal.get(0).getScan()) &&
						linea.getMarb().equals(temporal.get(0).getMarb())){
					temporal.add(linea);
				}else{
					nvoConteo.add(temporal);
					temporal=new ArrayList<DatosRevision>();
					temporal.add(linea);
				}
			}
		}
		nvoConteo.add(temporal);
		return nvoConteo;		
	}

}
