package ExportarResultados;
/* Clase que permite crear un resutado del inventario 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JOptionPane;

import Inicio.VentanaPrincipal.VentanaPrincipal;
import RevisarArticulos.CambiosHechos.RegistroCambios;
import Scanner.DatosScanners;

public class OrdenarConsentrarScanner {
	
	ArrayList<DatosScanners> datScan;
	ArrayList<DatEsport> concent;
	RegistroCambios guardarCambios;
	DireccionResultados dirResultados;
	String dirInventario;
	String n="";
	
	@SuppressWarnings("unchecked")
	public OrdenarConsentrarScanner(VentanaPrincipal vtana, ArrayList<DatosScanners> datScan){
		this.datScan=(ArrayList<DatosScanners>) datScan.clone();
		this.dirInventario=vtana.dirInventario;
		ordenarParaConsentrar();
		concentrar();
		guardar(vtana);
		
	}
	
	private void ordenarParaConsentrar(){
		Comparator<DatosScanners> comparar=new Comparator<DatosScanners>() {
			public int compare(DatosScanners o1, DatosScanners o2) {
				return new Long(o1.getArt()).compareTo(o2.getArt());
			}
		};
		Collections.sort(datScan, comparar);
	}
		
	private void concentrar(){
		concent=new ArrayList<DatEsport>();
		Iterator<DatosScanners> revScan=datScan.iterator();
		DatEsport linEsp=null;
		while (revScan.hasNext()) {
			DatosScanners linea=revScan.next();
			if(linEsp==null){
				linEsp=new DatEsport(linea.getArt(), linea.getCant());
			}else{
				if(linea.getArt()==linEsp.getArt()){
					linEsp.setCant((short) (linEsp.getCant()+linea.getCant()));
				}else{
					concent.add(linEsp);
					linEsp=new DatEsport(linea.getArt(), linea.getCant());
				}
			}
		}
		concent.add(linEsp);
	}
	
	private void guardar(VentanaPrincipal vtana) {
		dirResultados=new DireccionResultados(vtana);
		dirInventario=dirResultados.dirArchivo();
		Boolean error=false;
		if(dirInventario!=null){
			guardarCambios=new RegistroCambios(dirInventario, false);
			guardarCambios.escribirLinea("SKU,Cant");
			Iterator<DatEsport> barrido = concent.iterator();
			while(barrido.hasNext()){
				DatEsport linea = barrido.next();
				guardarCambios.escribirLinea(linea.getArt()+","+linea.getCant());
			}
			guardarCambios.cerrarReg();
			if(error==true){
				JOptionPane.showMessageDialog(vtana, "Hubo un error al guardar el Archivo", "Error al Guardar",
						JOptionPane.ERROR_MESSAGE, vtana.error);
			}else{
				JOptionPane.showMessageDialog(vtana, "Los resultados del Inventario se guardaron en "+dirInventario,
						"INVENTARIO GUARDADO", JOptionPane.INFORMATION_MESSAGE, vtana.information);
			}			
		}else{
			JOptionPane.showMessageDialog(vtana, "Se canceló la operación y no se guardó ningún archivo",
					"INVENTARIO NO GUARDADO", JOptionPane.INFORMATION_MESSAGE, vtana.cancelar);
		}
	}
}
