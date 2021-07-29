package Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Inicio.CatalogoArticulos.DatosMarbetes;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import Scanner.Operaciones.AbrirScanner;
import Scanner.Operaciones.ConsentradoScanner;
import Scanner.Operaciones.LeerScanner;
import Scanner.TablaScanner.TablaResultadoCargadeScanners;

public class CargarScanner {
	
	String scanner="";
	VentanaPrincipal vtana;
	public ArrayList<DatosMarbetes> datMarbete;
	ArrayList<String> listscanners;
	
	public CargarScanner(VentanaPrincipal vtana){
		this.vtana=vtana;
		this.listscanners=vtana.listscanners;
		this.datMarbete=vtana.datMarbete;
	}
	
	public void cargarScanner(TablaResultadoCargadeScanners tabladScanners) throws IOException{
		Boolean dupli=false; 
		int nulo=0;
		int numero=0;
		Boolean igual=false;
		String dir;
		String numScan;
		String mensaje="";
		/* Se crea una clase para abrir un JFileChooser (clase para abrir un cuadro de dialogo 
		 * y escojer un archivo). 
		 */
		CajaNombre datosscanner=new CajaNombre(vtana);
		if (datosscanner.valor==true){
		dir=vtana.dirScanner;
		if(dir!=""){//Si se escogio un archivo para cargar el Scanner
			System.out.println("Clase CargarScanner ------> Cargando Scanner....");
			File fileScan=new File(dir);//Carga del archivo Scanner			
			if(fileScan.exists()){//Si el archivo existe
				BufferedReader leerScan=new BufferedReader(new FileReader(fileScan));//Se abre Flujo
				LeerScanner nvoscanner=new LeerScanner(vtana);
				do{
					String parcial;
					try{
						parcial=leerScan.readLine();
						if(parcial==null){
							parcial="";
							nulo++;
						}
						scanner=scanner+parcial;
					}catch(IOException e){
						parcial="";
					}	
				}while(scanner=="" || nulo>9);
				if(scanner!=""){
					for(int num=0;num<listscanners.size();num++){//Comparacion de Scanners para ver posibles duplicados
						dupli=nvoscanner.compararScanner(scanner, listscanners.get(num));
						if(dupli==true){
							numero=num;
							num=listscanners.size();
						}
					}
					if(dupli==true){//Si se encotro un similitus se pregunta si se quiere agregar
						int pregunta=JOptionPane.showConfirmDialog(vtana, "Se encontro una similitud pacial en este Scanner con el conteo "+(numero+1)+"\n¿Quieres checarlo completamente?",
								"Similitud en Scanner",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, vtana.question);
						if(pregunta==0){//Respuesta de Similitud
							dupli=nvoscanner.compararTotal(scanner, listscanners.get(numero));
							if(dupli==false){
								int q=JOptionPane.showConfirmDialog(vtana, "De este escaner son iguales la primeras " + (nvoscanner.med/18) + 
										" filas, las restantes " + (scanner.length() - nvoscanner.med)/18 + " ya no coinciden " +
										"¿Quieres agregar estas ultimas?","Similitud en Scanner",JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, vtana.question);
								if(q==0){
									scanner=scanner.substring(nvoscanner.med, scanner.length());
									dupli=false;
								}else{
									dupli=true;
								}
							}else{
								mensaje="Este Scanner es exactamente igual al conteo numero "+(numero+1)+".\n";
							}
						}
					}
					if(dupli==false){//Al no encontrar alguna similitud se carga el Scanner
						String avance;
						numScan=nvoscanner.numeroScanner(listscanners.size());
						nvoscanner.cargarScanner(scanner,(numScan) + "S");//Se carga el scaner a la tabla
						listscanners.add(scanner);
						new ConsentradoScanner(numScan + "S", scanner, datosscanner.tnombre.getText(),
								datosscanner.tubicacion.getText(), vtana.dirInventario);
						for(int n=0;n<nvoscanner.sumaxmarbetes.size();n++){
							datMarbete.add(new DatosMarbetes(numScan + "S", datosscanner.tnombre.getText(),
									datosscanner.tubicacion.getText(), nvoscanner.sumaxmarbetes.get(n).get(0), 
									Integer.parseInt(nvoscanner.sumaxmarbetes.get(n).get(1)),
									Integer.parseInt(nvoscanner.sumaxmarbetes.get(n).get(2))));
						}
						
						vtana.panelAvance.lblTotScanner.setText("Se han agregado "+listscanners.size()+" Scanners");//  Actializacion de reporte en 
						avance=vtana.operInv.avanceTotal();												//  la ventana principal
						vtana.panelAvance.lblAvanceTot.setText("Avance total "+avance);								
					}else{
						JOptionPane.showMessageDialog(null, mensaje+"No se cargo el Scanner.",
								"Error al Cargar el Scanner",JOptionPane.INFORMATION_MESSAGE, vtana.information);
						scanner="";
					}
					leerScan.close();//Se cierra flujo				
				}
			}else{//Si no se escogio ningun archivo
				JOptionPane.showMessageDialog(vtana, "No se escogio ningun archivo para cargar el Scanner", 
						"No se cargo el Scanner", JOptionPane.INFORMATION_MESSAGE, vtana.information);
			}//Fin de escoger el archivo
		}//Fin de Caja Nombre
		}
	}
}
