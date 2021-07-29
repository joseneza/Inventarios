package Inicio.PreInicio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Inicio.CatalogoArticulos.DatosMarbetes;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import Scanner.DatosScanners;
import Scanner.Operaciones.LeerScanner;
import Scanner.TablaScanner.TablaResultadoCargadeScanners;

public class RestaurarConteo {
	ArrayList<String> listscanners;
	VentanaPrincipal vtana;
	TablaResultadoCargadeScanners tablaScanners;
	public String numscan;
	String nombre;
	String ubicacion;
	String scan;
	String dir;
	
	public RestaurarConteo(VentanaPrincipal vtana, String dir) throws IOException{
		this.vtana=vtana;
		this.listscanners=vtana.listscanners;
		this.tablaScanners=vtana.panelScanner.tablaScanners;
		this.dir=dir;
		recuperarConteo();
	}
	
	private void recuperarConteo() throws IOException{
		String cadena;
		BufferedReader conteo=new BufferedReader(new FileReader(dir));//Se abre el archivo Consentrado.dat
		try{
			do{
				cadena=conteo.readLine();
				if(cadena!=null){
					LeerScanner scanner=new LeerScanner(vtana);
					numscan=scanner.depurarArticulo(cadena.substring(0, 18));
					nombre=scanner.depurarArticulo(cadena.substring(18, 36));
					ubicacion=scanner.depurarArticulo(cadena.substring(36, 54));
					scan=cadena.substring(54, cadena.length());
					scanner.cargarScanner(scan, numscan);
					listscanners.add(scan);
					for(int n=0;n<scanner.sumaxmarbetes.size();n++){
						vtana.datMarbete.add(new DatosMarbetes(numscan, nombre, ubicacion, scanner.sumaxmarbetes.get(n).get(0), 
								Integer.parseInt(scanner.sumaxmarbetes.get(n).get(1)),
								Integer.parseInt(scanner.sumaxmarbetes.get(n).get(2))));
					}
				}
			}while(cadena!=null);
		}catch (Exception e) {}
		conteo.close();
	}

}
