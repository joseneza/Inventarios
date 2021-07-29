package Scanner.Archivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Inicio.CatalogoArticulos.DatosMarbetes;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import Scanner.CajaNombre;
import Scanner.Operaciones.ConsentradoScanner;
import Scanner.Operaciones.LeerScanner;
import Scanner.TablaScanner.TablaResultadoCargadeScanners;

/* Clase para agregar un conteo 
 * por medio de un archivo 
 */
public class GenererScannerArchivo {
	
	VentanaPrincipal vtana;
	TablaResultadoCargadeScanners tabla;
	ArrayList<Long> listCatalogo;
	public ArrayList<DatosMarbetes> datMarbete;
	CajaNombre tituloScanner;
	String ruta;
	int sku=-1;
	int cant=-1;
	public String conteo="";
	public String nScan;
	
	public GenererScannerArchivo(VentanaPrincipal vtana){
		this.vtana=vtana;
		this.tabla=vtana.panelScanner.tablaScanners;
		this.listCatalogo=vtana.listCatalogo;
		this.datMarbete=vtana.datMarbete;
		if(abrirArchivo()==true){
			if(ruta!=null){
				conteo=crearCadena(ruta);
			}else{
				JOptionPane.showMessageDialog(vtana, "No se escogio ningun archivo.", "Conteo Cancelado",
						JOptionPane.INFORMATION_MESSAGE, vtana.information);
			}
		}else{
			JOptionPane.showMessageDialog(vtana, "Has cancelado el conteo.", "Conteo cancelado",
					JOptionPane.INFORMATION_MESSAGE, vtana.information);
		}
		
	}
	
	private Boolean abrirArchivo(){
		tituloScanner = new CajaNombre(vtana);//Clase para agregar nombre y ubicacion al conteo
		if(tituloScanner.valor==true){
			//Metoto para fijar la extenson del archivo a abrir
			FileNameExtensionFilter extension=new FileNameExtensionFilter("Archivo csv", "csv");
			//Metodo para escojer un archivo
			JFileChooser nvadir=new JFileChooser(new File(".").getPath());
			nvadir.setDialogTitle("Escoge el archivo del conteo");//Titulo para el showOpenDialog
			nvadir.setFileFilter(extension);
			int acc=nvadir.showOpenDialog(vtana);
			if(acc==JFileChooser.APPROVE_OPTION){
				ruta=nvadir.getSelectedFile().getPath();
			}
		}
		return tituloScanner.valor;
	}
	
	private String crearCadena(String ruta){
		String cadena="";
		String linea;
		Boolean contin;
		File arch=new File(ruta);//Carga del archivo Scanner			
		if(arch.exists()){//Si el archivo existe
			BufferedReader leerArch;
			try {
				leerArch = new BufferedReader(new FileReader(arch));//Se abre Flujo
				try {
					linea=leerArch.readLine();
					contin=validarColumnas(linea);
					if(contin==true){
						while(linea!=null){
							linea=leerArch.readLine();//Leer linea por linea
							if(linea!=null){
								cadena=cadena+leerLinea(linea);
							}
						}
					}else{
						JOptionPane.showMessageDialog(vtana, "No se encontraron las columnas solicitadas o estan mal escritas "
								+ "checalo y vuelve a itentarlo", "Error al cargar el Archivo", JOptionPane.INFORMATION_MESSAGE,
								vtana.information);
					}
				} catch (IOException e1) {}
				leerArch.close();
			} catch (IOException e1) {}
		}
		nScan=numeroScanner(vtana.listscanners.size());
		//System.out.println(cadena);
		return cadena;
	}
	
	public void cargarConteo(){
		LeerScanner nvoconteo;
		Boolean dupli=false;
		String mensaje="";
		int numero=0;
		try {
			nvoconteo = new LeerScanner(vtana);//Se van a tomar los valores igual que como se toman de un Scanner
			for(int num=0;num<vtana.listscanners.size();num++){
				dupli=nvoconteo.compararScanner(conteo, vtana.listscanners.get(num));
				if(dupli==true){
					numero=num;
					num=vtana.listscanners.size();
				}
			}
			if(dupli==true){
				int pregunta=JOptionPane.showConfirmDialog(vtana, "Se encontro una similitud pacial en este conteo con el conteo "+(numero+1)+"\nÂ¿Quieres checarlo completamente?",
						"Similitud en Conteo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, vtana.question);
				if(pregunta==0){//Respuesta de Similitud
					dupli=nvoconteo.compararTotal(conteo, vtana.listscanners.get(numero));
					if(dupli==false){
						int q=JOptionPane.showConfirmDialog(vtana, "De este conteo son iguales la primeras " + (nvoconteo.med/18) + 
								" filas, las restantes " + (conteo.length() - nvoconteo.med)/18 + " ya no coinciden " +
								"¿Quieres agregar estas ultimas?","Similitud en conteo",JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, vtana.question);
						if(q==0){
							conteo=conteo.substring(nvoconteo.med, conteo.length());
							dupli=false;
						}else{
							dupli=true;
						}
					}else{
						mensaje="Este Scanner es exactamente igual al conteo numero "+(numero+1)+".\n";
					}
				}
			}
			if(dupli==false){
				nvoconteo.cargarScanner(conteo, nScan);//Se agrega el conteo al la tabla
				new ConsentradoScanner(numeroScanner(vtana.listscanners.size()), conteo,
						tituloScanner.tnombre.getText(), tituloScanner.tubicacion.getText(), vtana.dirInventario);//Se agrega el conteo al respaldo
				for(int n=0;n<nvoconteo.sumaxmarbetes.size();n++){
					ArrayList<String> nvaLin = new ArrayList<String>();
					nvaLin.add(nScan);
					nvaLin.add(tituloScanner.tnombre.getText());
					nvaLin.add(tituloScanner.tubicacion.getText());
					nvaLin.add(nvoconteo.sumaxmarbetes.get(n).get(0));
					nvaLin.add(nvoconteo.sumaxmarbetes.get(n).get(1));
					nvaLin.add(nvoconteo.sumaxmarbetes.get(n).get(2));
					System.out.println("CargarScanner -------> Datos Scanner Cargado ->" + nvaLin);
					datMarbete.add(new DatosMarbetes(nScan, tituloScanner.tnombre.getText(), tituloScanner.tubicacion.getText(),
							nvoconteo.sumaxmarbetes.get(n).get(0), Integer.parseInt(nvoconteo.sumaxmarbetes.get(n).get(1)),
							Integer.parseInt(nvoconteo.sumaxmarbetes.get(n).get(2))));
				}
				vtana.listscanners.add(conteo);//Se agrega el conteo al la lista en memoria
				vtana.panelAvance.lblTotScanner.setText("Se han agregado "+vtana.listscanners.size()+" Scanners");//  Actializacion de reporte en 
				String avance=vtana.operInv.avanceTotal();											  //  la ventana principal
				vtana.panelAvance.lblAvanceTot.setText("Avance total "+avance);
			}else{
				JOptionPane.showMessageDialog(vtana, mensaje+"No se cargo el Conteo.",
						"Error al Cargar el Conteo",JOptionPane.INFORMATION_MESSAGE, vtana.information);
				conteo="";
			}
		} catch (IOException e) {}		
	}
	
	private Boolean validarColumnas(String titulo){
		Boolean valido=false;
		String [] separarlinea = titulo.split(","); // Obtengo los datos de la linea separados por una coma (,)
		for(int i=0;i<separarlinea.length;i++){
			String valor=separarlinea[i];
			switch (valor) {
			
			case "Sku":
				sku=i;
				break;
			case "sku":
				sku=i;
				break;
			case "SKU":
				sku=i;
				break;
				
			case "cant":
				cant=i;
				break;
			case "Cant":
				cant=i;
				break;
			case "CANT":
				cant=i;
				break;
			default:
				break;
			}
		}
		if(sku>=0 && cant>=0){
			valido=true;
		}
		return valido;
	}
	
	private String leerLinea(String linea){
		String art="";
		String cantid="";
		String nvalinea="";
		String [] separarlinea = linea.split(","); // Obtengo los datos de la linea separados por una coma (,)
		for(int i=0;i<separarlinea.length;i++){
			if(sku==i){
				art=separarlinea[i];
			}else{
				if(cant==i){
					cantid=separarlinea[i];
				}				
			}
		}
		String articulo=art;
		for(int a=art.length();a<14;a++){
			articulo=articulo+" ";
		}
		String cantidad=cantid;
		for(int a=cantid.length();a<3;a++){
			cantidad=cantidad+" ";
		}
		nvalinea="A"+articulo+cantidad;
		return nvalinea;		
	}
	
	private String numeroScanner(int anterior){
		String numscan=Integer.toString(anterior+1);
		for(int num=numscan.length();num<4;num++){
			numscan="0"+numscan;
		}
		return numscan+"A";		
	}

}
