package Scanner.Operaciones;
/* Programa Inventarios
 * Clase para leer los Scanner que se
 * ingresan.
 */

import java.util.ArrayList;

import Inicio.VentanaPrincipal.VentanaPrincipal;
import Scanner.TablaScanner.TablaResultadoCargadeScanners;

public class LeerScanner {
	VentanaPrincipal vtana;
	TablaResultadoCargadeScanners tabla;
	String marbeteInicial;
	String inicio;
	int max;
	public int med=0;
	public ArrayList<ArrayList<String>> sumaxmarbetes=new ArrayList<ArrayList<String>>();//Lista para titulos de scanner
	//Constructor
	public LeerScanner(VentanaPrincipal vtana){
		//Se cargan los Array para seguir con el consecutiv coteo
		this.vtana=vtana;
		this.tabla=vtana.panelScanner.tablaScanners;
		this.inicio=vtana.iniMarbete;
		this.max=vtana.tamMarbete;
		marbInicial();
	}
	
	private void marbInicial(){
		marbeteInicial=inicio;
		for(int n=0;n<(max-inicio.length());n++){
			marbeteInicial=marbeteInicial+"0";
		}
	}
	
	public void cargarScanner(String scan,String nscan){
		int consecutivo=1;
		int sumaArt=0;
		int sumaError=0;
		ArrayList<String> listMarbetes=new ArrayList<String>();
		String marbete=marbeteInicial;//El marbete se inicia en ceros
		int largo=scan.length();
		for(int med=0;med<largo;med+=18){
			String art;
			String cant;
			Long articulo = (long) 0;
			Short cantidad = 0;
			art=scan.substring(med+1, med+14);
			art=depurarArticulo(art);
			Boolean artEan=false;			
			try{
				articulo = Long.parseLong(art);
			}catch(NumberFormatException ex){}
			if(articulo > 0){
				if(art.length()==max && art.substring(0, inicio.length()).equals(inicio)){//Se checa si es un marbete
					if(sumaArt!=0){
						ArrayList<String> linea=new ArrayList<String>();
						linea.add(marbete);
						linea.add(Integer.toString(sumaArt));
						linea.add(Integer.toString(sumaError));							
						sumaxmarbetes.add(linea);//Se agrega el marbete y su cantidad a la lista para el titulo de scanners
						if(listMarbetes.contains(articulo)){//Busca marbetes duplicados en el mismo escanner
							for(int n=0;n<sumaxmarbetes.size();n++){
								if(sumaxmarbetes.get(n).get(0).equals(articulo)){
									sumaArt=Integer.parseInt(sumaxmarbetes.get(n).get(1));
									sumaError=Integer.parseInt(sumaxmarbetes.get(n).get(2));
									sumaxmarbetes.remove(n);
								}
							}							
						}else{//Si es marbete nuevo lo agrega							
							listMarbetes.add(art);
							sumaArt=0;
							sumaError=0;
						}
					}
					marbete=art;
					int linea=vtana.operInv.modifMarbetes(marbete);
					if(linea>-1){
						String porc=vtana.operInv.avanceMarbetes(linea);
						vtana.operInv.panMarb.tablaInv.setValueAt(porc, linea, 2);						
					}
				}else{
					cant=scan.substring(med+15, med+18);
					cant=depurarArticulo(cant);
					try{
						cantidad = Short.parseShort(cant);
					}catch(NumberFormatException ex){}
					String descrip="";
					Boolean exist=false;
					int lin=vtana.listCatalogo.indexOf(articulo);//Buscar articulo en Catalogo
					if(lin>=0){
						descrip=vtana.listDescripcion.get(lin);
						artEan=false;
						exist=true;
					}else{
						if(articulo > 0){
							lin=vtana.listEan.indexOf(articulo);//Buscar articulo en Ean
							if(lin>=0){
								articulo=vtana.listCatalogo.get(lin);
								descrip=vtana.listDescripcion.get(lin);
								artEan=true;
								exist=true;
							}else{
								descrip="\"ERROR\" Articulo no encontrado";
							}
						}
					}
					if(cantidad > 0){
						int fila=tabla.nvomodelo.datScan.size();
						tabla.nvomodelo.addFila(fila, nscan, consecutivo, marbete, articulo, artEan, cantidad, descrip, exist);
						vtana.totArt=vtana.totArt+cantidad;
						vtana.granTot=vtana.totArt+vtana.artAd-vtana.artBorr;
						vtana.panelAvance.lblTotArticulos.setText("Se han contabilizado "+vtana.totArt+" Articulos.");
						vtana.panelAvance.lblGranTotal.setText("Total "+vtana.granTot+" Articulos.");					
						sumaArt=sumaArt+cantidad;//Totaliza los articulos por marbetes
						if(exist==false){
							sumaError=sumaError+cantidad;//Totaliza los articulos no encontrados por marbetes
						}
					}					
					consecutivo++;
				}
			}
		}
		//Ultimo Marbete del Scanner
		ArrayList<String> linea=new ArrayList<String>();
		linea.add(marbete);
		linea.add(Integer.toString(sumaArt));
		linea.add(Integer.toString(sumaError));							
		sumaxmarbetes.add(linea);//Se agrega el marbete y su cantidad a la lista para el titulo de scanners
	}
	
	public String depurarArticulo(String art){
		String newart="";
		int largo=art.length();
		for(int med=0;med<largo;med++){
			String a=art.substring(med,med+1);
			newart=newart+a.replace(" ", "");
		}
		return newart;
		
	}
	
	public Boolean compararScanner(String scannernvo,String vscanner){
		Boolean igual=false;
		int contador=0;
		if(vscanner.length()>90){
			for(int med=0;med<90;med+=18){//Comparacion Parcial(Los primeros 5 Articulos)
				String nuevo=scannernvo.substring(med, med+18);
				String anterior=vscanner.substring(med, med+18);
				for(int c=0;c<nuevo.length();c++){
					if(nuevo.charAt(c)==anterior.charAt(c)){
						contador++;
					}
					if(contador>=89){
						igual=true;
					}
				}			
			}
		}else{
			for(int c=0;c<vscanner.length();c++){
				if(scannernvo.charAt(c)==vscanner.charAt(c)){
					contador++;
				}
				if(contador>=89){
					igual=true;
				}
			}
		}
		return igual;
	}
	
	public Boolean compararTotal(String scannernvo,String vscanner){
		Boolean igual=false;
		int largo;
		if(scannernvo.length()>=vscanner.length()){//Nuevo mayor que Viejo
			largo=vscanner.length();
		}else{
			largo=scannernvo.length();
		}//Fin de Scanner nuevo mayor que el que se compara			
		do{
			String nuevo=scannernvo.substring(med, med+18);
			String anterior=vscanner.substring(med, med+18);
			int igualdad=0;
			for(int c=0;c<nuevo.length();c++){//Comparacion del Scanner Caracter por caracter
				if(nuevo.charAt(c)==anterior.charAt(c)){
					igualdad++;
				}
				if(igualdad>=17){
					igual=true;
				}else{
					igual=false;
				}
			}
			if(igual==true){
				med=med+=18;
			}
		}while(med<largo && igual==true);
		if(scannernvo.length()>vscanner.length()){
			igual=false;
			med=med-=18;
		}
		return igual;
	}
		
	 
	public String numeroScanner(int anterior){
		String numscan=Integer.toString(anterior+1);
		for(int num=numscan.length();num<4;num++){
			numscan="0"+numscan;
		}
		return numscan;		
	}
	
}
