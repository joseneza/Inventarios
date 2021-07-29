package Configuracion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import Inicio.VentanaPrincipal.VentanaPrincipal;

public class CargarConfiguracion {
	
	File archConfig;
	VentanaPrincipal vtana;
	
	public CargarConfiguracion(VentanaPrincipal vtana){
		this.vtana=vtana;
		if(existe()==true){
			cargarConfig();
		}else{
			int Q=JOptionPane.showConfirmDialog(vtana, "No has iniciado configuraciones no se puede realizar el inventario"+
					"\n¿Deseas iniciar la configuracion del programa?\n\nNOTA: Si la opcion es NO el programa se va a cerrar.",
					"SIN CONFIGURACION",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, vtana.question);
			if(Q==0){
				abrirConfig();
			}else{
				System.exit(0);
			}
		}
		
	}
	
	private Boolean existe(){
		archConfig=new File(new File(".").getPath()+"/Configuracion.dat");//Se crea objeto para el archivo Configuracion
		return archConfig.exists();		
	}
	
	private void cargarConfig(){
		try {
			BufferedReader leerArch = new BufferedReader(new FileReader(archConfig));
			try {
				vtana.dirCatalogo=leerArch.readLine();			
				vtana.dirScanner=leerArch.readLine();
				String marbete=leerArch.readLine();
				Boolean marb=true;
				String iniMarb="";
				String tamMarb="";
				for(int n=0;n<marbete.length();n++){
					String c=marbete.substring(n, n+1);
					if(c.equals(",")){
						marb=false;
					}else{
						if(marb==true){
							iniMarb=iniMarb+c;
						}else{
							tamMarb=tamMarb+c;
						}
					}
				}
				vtana.iniMarbete=iniMarb;
				try{
					vtana.tamMarbete=Short.parseShort(tamMarb);
				}catch(NumberFormatException ex){
					vtana.tamMarbete=0;
				}
				vtana.dirInventario=leerArch.readLine();
			} catch (IOException e) {}
		} catch (FileNotFoundException e) {}
	}
	
	private void abrirConfig(){
		new CambiarConfiguracion(vtana);
	}
	
}
