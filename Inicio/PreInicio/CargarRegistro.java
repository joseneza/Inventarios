package Inicio.PreInicio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JOptionPane;

import Inicio.CatalogoArticulos.DatosMarbetes;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import Scanner.DatosScanners;

public class CargarRegistro {
	
	String dir;
	VentanaPrincipal vtana;
	ArrayList<LineaRegistro> cargarRegi = new ArrayList<LineaRegistro>();
	
	public CargarRegistro(VentanaPrincipal vtana, String dir){		
		this.dir=dir;
		this.vtana=vtana;				
	}
	
	public void leerArch(){
		int nlin=0;		
		try {			
			BufferedReader leerArchivo=new BufferedReader(new FileReader(dir));//Leer el archivo de los registros por linea			
			try {
				String linea;
				do{				
					linea=leerArchivo.readLine();
					nlin++;
					if(linea!=null){						
						if(linea.substring(0, 2).equals("rg")){
							vtana.numreg++;
						}else{
							cargarRegistro(dividirLinea(linea));
						}
					}				
				}while(linea!=null);
				ordenarReg();
				cambiar();
				leerArchivo.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(vtana, "Error en linea "+nlin, "Error", JOptionPane.ERROR_MESSAGE, vtana.error);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(vtana, "No se encontro el archivo de Registro", "Error",
					JOptionPane.ERROR_MESSAGE, vtana.error);
		}
	}
	
	private LineaRegistro dividirLinea(String linea){
		String tipo="";
		String fila="";
		String scan="";
		String marb="";
		String sku="";
		String cant="";
		String descr="";
		int colum=0;
		for(int num=0;num<linea.length();num++){
			String a=linea.substring(num, num+1);						
			if(a.equals("|")){
				colum++;
			}else{
				switch (colum) {
				case 0:
					tipo=tipo+a;
					break;
				case 1:
					scan=scan+a;
					break;
				case 2:
					fila=fila+a;
					break;
				case 3:
					marb=marb+a;
					break;
				case 4:
					sku=sku+a;
					break;
				case 5:
					cant=cant+a;
					break;
				case 6:
					descr=descr+a;
				default:
					break;
				}
			}
		}
		Short t=0;//Tipo
		try{
			t=Short.parseShort(tipo);
		}catch(NumberFormatException ex){}
		int f=-1;//Fila
		try{
			f=Integer.parseInt(fila);
		}catch(NumberFormatException ex){}
		int s=0;//Articulo
		try{
			s=Integer.parseInt(sku);
		}catch(NumberFormatException ex){}
		short c=0;//Cantidad
		try{
			c=Short.parseShort(cant);
		}catch(NumberFormatException ex){}
		return new LineaRegistro(f, t, s, c, scan, marb, descr);
	}
	
	private void cargarRegistro(LineaRegistro linReg){
		/* Se crea un iterator para hacer una barrida de los datos del Conteo */
			Iterator<DatosScanners> revScan = vtana.panelScanner.tablaScanners.nvomodelo.datScan.iterator();
			int fila = 0;
			while(revScan.hasNext()){//Barrida del Conteo
				DatosScanners linScan = revScan.next();//Se toma la linea a comparar
				if(linReg.getScan().equals(linScan.getScan())){//Scanner renglon vs Scanner conteo
					if(linReg.getFila()==linScan.getConsecutivo()){//Consecutivo vs Consecutivo
						LineaRegistro tipo=new LineaRegistro(fila,linReg.getTipo(),linReg.getSku(),
								linReg.getCant(),linReg.getScan(),linReg.getMarbete(),linReg.getDescripcion());
						cargarRegi.add(tipo);
					}
				}
				fila++;
			}
		}
	
	private void ordenarReg(){
		Comparator<LineaRegistro> ordArt=new Comparator<LineaRegistro>() {
			public int compare(LineaRegistro o1, LineaRegistro o2) {
				return new Integer(o2.getFila()).compareTo(o1.getFila());
			}
		};
		Collections.sort(cargarRegi, ordArt);
		Comparator<LineaRegistro> ordTipo=new Comparator<LineaRegistro>() {
			public int compare(LineaRegistro o1, LineaRegistro o2) {
				return new Short(o1.getTipo()).compareTo(o2.getTipo());
			}
		};
		Collections.sort(cargarRegi, ordTipo);
		Iterator<LineaRegistro> agrReg = cargarRegi.iterator();
		while(agrReg.hasNext()){
			LineaRegistro linReg = agrReg.next();
		}
	}
	
	private void cambiar(){
		Iterator<LineaRegistro> agrReg = cargarRegi.iterator();
		while(agrReg.hasNext()){
			LineaRegistro linReg = agrReg.next();
			DatosScanners linScan = (DatosScanners) vtana.panelScanner.tablaScanners.nvomodelo.datScan.get(linReg.getFila());
			switch (linReg.getTipo()) {
			case 1://Confirmar el articulo
				if(vtana.listCatalogo.contains(linReg)==false){
					vtana.listCatalogo.add(linReg.getSku());
					vtana.listDescripcion.add(linReg.getDescripcion());
				}				
				vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(true, linReg.getFila(), 6);
				vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(linReg.getDescripcion(),
						linReg.getFila(), 7);
				for(int num=0;num<vtana.datMarbete.size();num++){//Actualiza la lista de datMarbete
					DatosMarbetes linMarb = (DatosMarbetes) vtana.datMarbete.get(num);
					if(linMarb.getScan().equals(linReg.getScan()) & linMarb.getMarbete().equals(linReg.getMarbete())){
						linMarb.setError(linMarb.getError()-linReg.getCant());
					}
				}
				break;
			case 2://Cambiar los datos
				if(linScan.getCant() < linReg.getCant()){
					vtana.artAd=vtana.artAd+linReg.getCant()-linScan.getCant();
				}else{
					vtana.artBorr=vtana.artBorr+linScan.getCant()-linReg.getCant();
				}
				int nArt = vtana.listCatalogo.indexOf(linReg.getSku());
				if(nArt<0){
					vtana.listCatalogo.add(linReg.getSku());
					vtana.listDescripcion.add(linReg.getDescripcion());
				}
				Boolean tipo = (Boolean) vtana.panelScanner.tablaScanners.nvomodelo.getValueAt(linScan.getFila(), 6);
				int cant = linScan.getCant();
				vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(linReg.getSku(), linReg.getFila(), 3);
				vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(linReg.getCant(), linReg.getFila(), 5);
				vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(true, linReg.getFila(), 6);
				vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(linReg.getDescripcion(), linReg.getFila(), 7);
				if(tipo==false){
					for(int num=0;num<vtana.datMarbete.size();num++){//Actualiza la lista de datMarbete
						DatosMarbetes linMarb = (DatosMarbetes) vtana.datMarbete.get(num);					
							if(linMarb.getScan().equals(linReg.getScan()) & linMarb.getMarbete().equals(linReg.getMarbete())){
								linMarb.setError(linMarb.getError()-cant);						
						}
					}
				}				
				break;
			case 3://Borrar el articulo
				Boolean existe = vtana.panelScanner.tablaScanners.nvomodelo.datScan.get(linReg.getFila()).getExiste();
				long art = vtana.panelScanner.tablaScanners.nvomodelo.datScan.get(linReg.getFila()).getArt();
				vtana.panelScanner.tablaScanners.nvomodelo.borrarFila(linReg.getFila());	
				for(int num=0;num<vtana.datMarbete.size();num++){//Actualiza la lista de datMarbete
					DatosMarbetes linMarb = (DatosMarbetes) vtana.datMarbete.get(num);
					if(linMarb.getScan().equals(linReg.getScan()) & linMarb.getMarbete().equals(linReg.getMarbete())){
						linMarb.setTotal(linMarb.getTotal()-linReg.getCant());
						if(existe.equals(false)){
							linMarb.setError(linMarb.getError()-linReg.getCant());
						}						
						vtana.artBorr=vtana.artBorr+linReg.getCant();
					}
				}			
				break;
			case 4://Agregar nuevos datos
				String descripcion=linReg.getDescripcion();
				if(descripcion.equals("")){
					int s = vtana.listCatalogo.indexOf(linReg.getSku());
					if(s>-1){
						descripcion = vtana.listDescripcion.get(s);
					}else{
						descripcion = "Articulo sin descripcion";
					}
				}
				vtana.panelScanner.tablaScanners.nvomodelo.nvaFila(linReg.getFila(), linReg.getScan(),
						linReg.getMarbete(), linReg.getSku(), false, linReg.getCant(), 
						descripcion, true);
				if(vtana.listCatalogo.contains(linReg.getSku())==false){
					vtana.listCatalogo.add(linReg.getSku());
				}
				for(int num=0;num<vtana.datMarbete.size();num++){//Actualiza la lista de datMarbete
					DatosMarbetes linMarb = (DatosMarbetes) vtana.datMarbete.get(num);
					if(linMarb.getScan().equals(linReg.getScan()) & linMarb.getMarbete().equals(linReg.getMarbete())){
						try{
							linMarb.setTotal(linMarb.getTotal()+linReg.getCant());
						}catch(NumberFormatException e){
							System.out.println("Error cargar Registro cantidad "+linReg.getCant());
						}
					}
				}
				vtana.artAd=vtana.artAd+linReg.getCant();
			default:
				break;
			}
		}
		vtana.granTot=vtana.totArt+vtana.artAd-vtana.artBorr;
		vtana.panelAvance.lblTotArticulos.setText("Se han contabilizado "+vtana.totArt+" Articulos.");
		vtana.panelAvance.lblTotAgregados.setText("Se han agregado "+vtana.artAd+" Articulos.");
		vtana.panelAvance.lblTotBorrados.setText("Se han borrado "+vtana.artBorr+" Articulos.");
		vtana.panelAvance.lblGranTotal.setText("Total "+vtana.granTot+" Articulos.");
		vtana.panelScanner.tablaScanners.nvomodelo.ajustarFilas();
	}
	

}
