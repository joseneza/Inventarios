package Scanner.Operaciones;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import ExportarResultados.DatEsport;
import Inicio.PreguntaAbierta;
import RevisarArticulos.VentanaRevision.TablaRev;
import RevisarArticulos.VentanaRevision.VentanaRevision;
import Scanner.DatosScanners;
import Scanner.Manual.DatosCargaManual;
import Scanner.Manual.TablaCargaManual;
import Scanner.Manual.VentanaCargaManual;

public class PegarTabla{
	private Clipboard portapapeles;
	private ArrayList<DatEsport> datImport;
	VentanaRevision vtanaRev;
	int contError = 0;
	private ImageIcon error = new ImageIcon(PegarTabla.class.getResource("/Iconos/48x48/dialog-error.png"));
	private ImageIcon question = new ImageIcon(PegarTabla.class.getResource("/Iconos/48x48/dialog-question.png"));
	
	
	public PegarTabla(VentanaRevision vtanaRev) {		
		portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
		if(verificarDatos()==true){
			pegarTablaRev(vtanaRev);
		}		
	}
	
	public PegarTabla(VentanaCargaManual vtanaMan) {
		portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
		if(verificarDatos()==true){
			pegarTablaManual(vtanaMan);
		}
	}

	void showErrorMessage(String msg){
		JOptionPane.showMessageDialog(vtanaRev, "ERROR AL COPIAR", msg, JOptionPane.ERROR_MESSAGE, error);
	}
	
	Boolean verificarDatos(){
		Boolean copiar = false;
		portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();

		try{
			String data= (String)portapapeles.getData(DataFlavor.stringFlavor);
			if(data==null) {
				showErrorMessage("No hay datos en el portapapeles");
			}else{
				//devuelve clipboard contenido
				StringTokenizer stg, stgTmp;
				datImport = new ArrayList<DatEsport>();
				stg=new StringTokenizer (data,"\n");
				int colCount;
				//copia a la tabla
				while(stg.hasMoreTokens()){
					stgTmp = new StringTokenizer (stg.nextToken(),"\t");
					colCount = 0;
					DatEsport linImport = new DatEsport(0, (short) 0);
					while(stgTmp.hasMoreTokens ()){
						String valTmp = stgTmp.nextToken();
						switch (colCount) {
						case 0:
							try{
								linImport.setArt(Long.parseLong(valTmp));
							}catch(NumberFormatException ex){
								contError++;
							}
							break;
						case 1:
							try{
								linImport.setCant(Short.parseShort(valTmp));								
							}catch(NumberFormatException ex){
								contError++;
							}
							break;
						default:
							break;
						}
						colCount++;					
					}
					if(linImport.getArt()>0 && linImport.getCant()>0){
						datImport.add(linImport);						
					}
				}
				if(contError>0){
					PreguntaAbierta pregunta = new PreguntaAbierta(vtanaRev, "<html><body>Algunos valores no se pueden "
							+ "copiar en las celdas porque no son valores numericos ¿Que quieres hacer?",
							"ERROR AL COPIAR", "COPIAR SOLO VALORES NUMERICOS", "NO COPIAR");
					if(pregunta.valor==0){
						copiar = true;
					}
				}else{
					copiar = true;
				}			
			}
			
		}
		catch(UnsupportedFlavorException uf){
			showErrorMessage("uf="+uf.getMessage());
		}
		catch(IOException io){
			showErrorMessage("io="+io.getMessage());
		}
		return copiar;
	}
	
	private void pegarTablaManual(VentanaCargaManual vtanaMan){
		TablaCargaManual tabla = vtanaMan.tablaManual;
		int fila = -1;
		int ultimaFila = tabla.modTablaManual.datos.size();
		System.out.println("PegarTabla ultima fila "+ultimaFila+" tamaño tabla "+tabla.modTablaManual.datos.size());
		//Verificar filas limpias
		for (int n=tabla.modTablaManual.datos.size()-1;n>-1;n--){
			System.out.println("PegarTabla n "+n+" art "+tabla.modTablaManual.datos.get(n).getArt().isEmpty());
			if(tabla.modTablaManual.datos.get(n).getArt().isEmpty()){
				fila = n;
			}else{
				fila = ultimaFila;
				n = -1;
			}
		}
		Iterator<DatEsport> importar = datImport.iterator();
		while(importar.hasNext()){
			DatEsport linEsport = (DatEsport) importar.next();
			if(fila >= tabla.modTablaManual.datos.size()){
				System.out.println("PegarTabla ultima fila "+fila+" tamaño tabla "+tabla.modTablaManual.datos.size());
				tabla.modTablaManual.addFilas(1);
			}
			tabla.modTablaManual.datos.get(fila).setArt(Long.toString(linEsport.getArt()));
			tabla.modTablaManual.datos.get(fila).setCant(Short.toString(linEsport.getCant()));
			int nArt = vtanaMan.vtana.listCatalogo.indexOf(linEsport.getArt());
			if(nArt>-1){
				tabla.modTablaManual.datos.get(fila).setDescripcion(vtanaMan.vtana.listDescripcion.get(nArt));
				tabla.modTablaManual.datos.get(fila).setExist(true);
			}else{
				tabla.modTablaManual.datos.get(fila).setDescripcion("\"ERROR\" Articulo no encontrado");
			}
			fila++;
		}
		Iterator<DatosCargaManual> barMan = tabla.modTablaManual.datos.iterator();
		tabla.modTablaManual.valTot = 0;
		while(barMan.hasNext()){
			DatosCargaManual linMan = barMan.next();
			long art = 0;
			long cant = 0;
			try{art = Long.parseLong(linMan.getArt());}catch(NumberFormatException ex){}
			try{cant = Long.parseLong(linMan.getCant());}catch(NumberFormatException ex){}
			tabla.modTablaManual.valTot = tabla.modTablaManual.valTot+(art*cant);
		}
		tabla.modTablaManual.fireTableDataChanged();
	}
	
	private void pegarTablaRev(VentanaRevision vtanaRev){
		TablaRev tabla = vtanaRev.tablaRev;
		int fila = -1;
		int ultimaFila = tabla.modRev.datRev.size();
		System.out.println("PegarTabla ultima fila "+ultimaFila);
		//Verificar filas limpias
		for (int n=tabla.modRev.datRev.size()-1;n>-1;n--){				
			if(tabla.modRev.datRev.get(n).getArt()!=0){
				fila = ultimaFila;
				n = -1;
			}else{
				fila = n;
			}
		}
		System.out.println("PegarTabla ultima fila "+fila);
		Iterator<DatEsport> importar = datImport.iterator();
		while(importar.hasNext()){
			DatEsport linEsport = (DatEsport) importar.next();
			if(fila >= tabla.modRev.datRev.size()){
				tabla.modRev.addFila("nvo", tabla.modRev.datRev.get(0).getScan(), tabla.modRev.datRev.get(0).getConsecutivo(),
						tabla.modRev.datRev.get(0).getContador(), tabla.modRev.datRev.get(0).getUbic(),
						tabla.modRev.datRev.get(0).getMarb(), 0, (short) 0, "", false, false);
			}
			tabla.modRev.datRev.get(fila).setArt(linEsport.getArt());
			tabla.modRev.datRev.get(fila).setCant(linEsport.getCant());
			int nArt = vtanaRev.vtana.listCatalogo.indexOf(linEsport.getArt());
			if(nArt>-1){
				tabla.modRev.datRev.get(fila).setDescripcion(vtanaRev.vtana.listDescripcion.get(nArt));
				tabla.modRev.datRev.get(fila).setExiste(true);
				tabla.modRev.datRev.get(fila).setCambiar(true);
			}else{
				tabla.modRev.datRev.get(fila).setDescripcion("\"ERROR\" Articulo no encontrado");
			}
			fila++;
		}
		tabla.modRev.fireTableDataChanged();
	}
}
