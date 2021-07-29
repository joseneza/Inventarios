package Inicio.PreInicio;
/* Programa Inventarios
 * Clase para checar si ya existe 
 * o no un inventario
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Inicio.PreguntaAbierta;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import Marbetes.GenerarMarbetes;
import Marbetes.MarbetesInventario.OperacionesMarbetesInventario;
import Scanner.TablaScanner.PanelAgregarScanner;

public class PreInicio {
	public String mensaje;
	public Boolean cons;
	public Boolean consMarb;
	public Boolean regis;
	public File archcons;
	public File archconsMarb;
	public File archReg;
	RestaurarConteo restCont;
	
	public PreInicio(){
		mensaje="";
		cons=false;
		consMarb=false;
	}
	
	public Boolean preInicio(String dir){	
		archcons=new File(dir+"/Concentrado.dat");//Se crea objeto para leer o buscar el archivo Consentrado
		cons=archcons.exists();
		archconsMarb=new File(dir+"/ValoresMarbetes.dat");//Se crea objeto para el archivo marbetes
		consMarb=archconsMarb.exists();
		archReg=new File(dir+"/RegistroCambios.dat");//Se crea objeto para el archivo marbetes
		regis=archReg.exists();		
		if(consMarb==true){//Si existe el archivo de marbetes
			mensaje="Ya existe un inventario iniciado con marbetes";
			if(cons==true){//Si existe el archivo consentrado de los conteos
				mensaje=mensaje+" y con scanner agregados";
			}else{
				mensaje=mensaje+" pero sin scanner agregados";
			}
			return true;
		}else{
			return false;
		}
	}
	
	public void cargarPreInicio(VentanaPrincipal vtana){
		GenerarMarbetes nvosMarbetes;		
		String linea;
		String inicio="1";
		String ubic;
		String cant;
		vtana.mensajeEstado.setText("Restaurando Conteo - Cargando Marbetes");
		try{
			BufferedReader leerArchMarb=new BufferedReader(new FileReader(archconsMarb));//Leer el archivo de los marbetes por linea 
			try {
				linea=leerArchMarb.readLine();//La primera linea del archivo es el titulo del Inventario
				vtana.setTitle(linea+" - "+vtana.getTitle());
				linea=leerArchMarb.readLine();//La segunda linea es el tipo de marbete que se utiliza en el Inventario
				cargarTipoM(linea, vtana);//Metodo cara cargar el tipo de marbete
				nvosMarbetes=new GenerarMarbetes(vtana.iniMarbete, vtana.tamMarbete);
				while(linea!=null){//Se empiezan a leer las demas lineas del archivo hasta el final de este
					linea=leerArchMarb.readLine();
					if(linea!=null){
						ubic=depurarTexto(linea.substring(0, 30));
						cant=depurarTexto(linea.substring(30));
						vtana.listMarbete.add(nvosMarbetes.cargarMarbetes(ubic, cant, inicio));//Se restauran los marbetes del Inventario
						ArrayList<String> ubicacion = new ArrayList<String>();
						ubicacion.add(ubic);
						vtana.listMarbeteContado.add(ubicacion);
						inicio=Integer.toString(Integer.parseInt(cant)+Integer.parseInt(inicio));
					}
				}
				leerArchMarb.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(vtana, "Error al cargar el archivo de marbetes","ERROR",
						JOptionPane.ERROR_MESSAGE, vtana.error);
			}			
			vtana.panelScanner=new PanelAgregarScanner(vtana);
			vtana.panelScanner.nvoscanner.setEnabled(false);
			vtana.panelScanner.nvomanual.setEnabled(false);
			vtana.panelScanner.nvoarchivo.setEnabled(false);
			vtana.panelTabla.setBounds(2, 0, 440, 370);
			vtana.panelTabla.add(vtana.panelScanner);
			vtana.operInv=new OperacionesMarbetesInventario(vtana);
				if(cons==true){
					try {
						vtana.mensajeEstado.setText("Restaurando Conteo - Cargando Conteos");
						restCont=new RestaurarConteo(vtana, archcons.getPath());//Se restaura el conteo del Inventario						
					} catch (IOException ex) {}				
					int scan;
					try{
						scan=Integer.parseInt(restCont.numscan.substring(0, 4));
					}catch(NumberFormatException ex){
						scan=0;
					}			
					vtana.panelAvance.lblTotScanner.setText("Se han agregado "+scan+" Scanners");
					String avance=vtana.operInv.avanceTotal();		
					vtana.panelAvance.lblAvanceTot.setText("Avance total "+avance);
				}
			if(regis==true){
				PreguntaAbierta exiReg;
				do{
				exiReg=new PreguntaAbierta(vtana, "<html><body>Existe un registro de cambios en este inventario" +
						"¿Quieres borrar el registro y eliminar los cambios, o dejarlo y agregar los cambios hechos", "Se han hecho cambios en "+
						"el Inventario", "Agregar los cambios", "Borrar Registro");
				}while(exiReg.valor<0);//Se repite mientras no se de una respuesta
				switch (exiReg.valor){
				case 0:
					vtana.mensajeEstado.setText("Restaurando Conteo - Cargando Cambios");
					CargarRegistro iniReg = new CargarRegistro(vtana, archReg.getPath());
					iniReg.leerArch();
					break;
				case 1:
					archReg.delete();
					break;
				default:
					break;
				}
			}
			vtana.botones.btnRevisArt.setEnabled(true);
			vtana.menu.revisar.setEnabled(true);
			vtana.botones.btnImpPreCont.setEnabled(true);
			vtana.menu.imprimir.setEnabled(true);
			vtana.botones.btnMostResultados.setEnabled(true);
			vtana.menu.exportar.setEnabled(true);
			vtana.mensajeEstado.setText("Ingreso de Conteos");
			vtana.menu.conteo.setEnabled(true);
			vtana.panelScanner.nvoscanner.setEnabled(true);
			vtana.panelScanner.nvomanual.setEnabled(true);
			vtana.panelScanner.nvoarchivo.setEnabled(true);
		}catch(FileNotFoundException ex){
			;
		}
	}
	
	public void cargarTipoM(String linea, VentanaPrincipal vtana){
		String iniMarb="";
		String totCar="";
		Boolean ini=true;
		for(int n=0; n<linea.length(); n++){
			String c=linea.substring(n, n+1);
			if(c.equals(",")){
				ini=false;
			}else{
				if(ini==true){
					iniMarb=iniMarb+c;				
				}else{
					totCar=totCar+c;
				}				
			}
		}
		vtana.iniMarbete=iniMarb;
		try{
			vtana.tamMarbete=Short.parseShort(totCar);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(vtana, "Error al cargar el tipo de marvete el numero no es correcto", "ERROR",
					JOptionPane.ERROR_MESSAGE, vtana.error);
		}
	}
	
	public String depurarTexto(String art){
		String newart="";
		int largo=art.length();
		for(int med=0;med<largo;med++){
			String a=art.substring(med,med+1);
			newart=newart+a.replace(" ", "");
		}
		return newart;
		
	}
	
}
