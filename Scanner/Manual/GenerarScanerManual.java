package Scanner.Manual;
/* 
 * 
 */
import java.util.ArrayList;

import Inicio.VentanaPrincipal.VentanaPrincipal;
import Scanner.TablaScanner.TablaResultadoCargadeScanners;

public class GenerarScanerManual {
	
	String marbete;
	ArrayList<DatosCargaManual> datosManual;
	TablaResultadoCargadeScanners tabla;
	ArrayList<Long> listCatalogo;
	ArrayList<String> listDescripcion;
	ArrayList<Long> listEan;
	int num;
	public String nscan;
	public String scannerManual;
	public int sumaArt=0;
	public int sumaError=0;
	
	public GenerarScanerManual(VentanaPrincipal vtana,String marbete,ArrayList<DatosCargaManual> datosManual){
		this.marbete=marbete;
		this.datosManual=datosManual;
		this.tabla=vtana.panelScanner.tablaScanners;
		this.listCatalogo=vtana.listCatalogo;
		this.listDescripcion=vtana.listDescripcion;
		this.listEan=vtana.listEan;
		num=vtana.listscanners.size();
	}

	
	public String generarConteo(){//Se crea una linea muy parecida a un Scanner
		scannerManual=llenarLinea(marbete, "1");
		int consec=1;
		for(int a=0; a<datosManual.size(); a++){
			Boolean artEan=false; 
			DatosCargaManual datLinea = (DatosCargaManual) datosManual.get(a);
			if(datLinea.getArt() != "" && datLinea.getCant() != ""){
				long articulo = 0;
				short cant = 0;
				try{
					articulo = Long.parseLong(datLinea.getArt());
				}catch(NumberFormatException ex){}
				try{
					cant = Short.parseShort(datLinea.getCant());
				}catch(NumberFormatException ex){}
				String descripcion="";
				scannerManual = scannerManual + llenarLinea(datLinea.getArt(), datLinea.getCant());//Se genera cadena para el Consentrado
				Boolean exist = listCatalogo.contains(datLinea.getArt());//Buscar articulo
				int fila = tabla.nvomodelo.datScan.size();
				nscan = numeroScanner(num);
				int lin=listCatalogo.indexOf(articulo);
				if(lin>=0){
					descripcion=listDescripcion.get(lin);
					exist=true;
				}else{
					if(articulo>0){
						lin=listEan.indexOf(articulo);
						if(lin>=0){
							articulo=listEan.get(lin);
							descripcion=listDescripcion.get(lin);
							artEan=true;
							exist=true;
						}else{
							descripcion="\"ERROR\" Articulo no encontrado";
						}
					}
				}
				tabla.nvomodelo.addFila(fila, nscan, consec, marbete, articulo, artEan, cant, descripcion, exist);
				sumaArt=sumaArt+Integer.parseInt(datLinea.getCant());//Totaliza los articulos por marbetes
				if(exist==false){
					sumaError=sumaError+Integer.parseInt(datLinea.getCant());//Totaliza los articulos no encontrados por marbetes
				}
				consec++;
			}			
		}
		return scannerManual;
	}
	
	private String llenarLinea(String art, String cant){
		String linea;
		String articulo=art;
		for(int a=art.length();a<14;a++){
			articulo=articulo+" ";
		}
		String cantidad=cant;
		for(int a=cant.length();a<3;a++){
			cantidad=cantidad+" ";
		}
		linea="M"+articulo+cantidad;
		return linea;		
	}
	
	private String numeroScanner(int anterior){
		String numscan=Integer.toString(anterior+1);
		for(int num=numscan.length();num<4;num++){
			numscan="0"+numscan;
		}
		return numscan+"M";		
	}

}
