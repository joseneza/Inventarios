package Configuracion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import Inicio.VentanaPrincipal.VentanaPrincipal;

public class CambiarConfiguracion {
	
	VentanaPrincipal vtana;
	VentanaConfig vConfig;
	File archConfig;
	String catalogo;
	String scanner;
	String inventario;
	String tipoMarb;
	
	public CambiarConfiguracion(VentanaPrincipal vtana){
		this.vtana=vtana;
		buscarConfig();
		vConfig=new VentanaConfig(vtana, catalogo, scanner, inventario, tipoMarb);
		if(vConfig.valor==0){
			grabarConfiguracion();
		}
	}
	
	private void buscarConfig(){
		archConfig=new File(new File(".").getPath()+"/Configuracion.dat");//Se crea objeto para el archivo Configuracion		
		if(archConfig.exists()==true){
			try {
				BufferedReader leerArch = new BufferedReader(new FileReader(archConfig));
				try {
					catalogo=leerArch.readLine();
					scanner=leerArch.readLine();
					tipoMarb=leerArch.readLine();
					inventario=leerArch.readLine();
				} catch (IOException e) {}
			} catch (FileNotFoundException e) {}//Leer el archivo por linea
		}else{
			String dir="";
			try {
				dir=archConfig.getCanonicalPath().substring(0, archConfig.getCanonicalPath().length()-archConfig.getName().length());
			} catch (IOException e) {}
			catalogo=dir+"Catalogo.csv";
			scanner=dir+"Scan.txt";
			inventario=dir;
			tipoMarb="9999,8";
		}
	}
	
	private void grabarConfiguracion(){
		try {
			PrintStream scr=new PrintStream(new FileOutputStream(archConfig,false));
			scr.println(vConfig.textCatalogo.getText());//Se carga la direccion del Catalogo
			vtana.dirCatalogo=vConfig.textCatalogo.getText();
			scr.println(vConfig.textScanner.getText());//Se carga la direccion del Scanner
			vtana.dirScanner=vConfig.textScanner.getText();
			scr.println(vConfig.tipoMarb);//Se carga tipo de marbete
			if(vConfig.rdbtnNueves.isSelected()==true){
				vtana.iniMarbete="9999";
				vtana.tamMarbete=8;
			}else{
				if(vConfig.rdbtnOcho.isSelected()==true){
					vtana.iniMarbete="8888";
					vtana.tamMarbete=9;
				}else{
					if(vConfig.rdbtnPersonalizado.isSelected()==true){				
						vtana.iniMarbete=vConfig.textInicia.getText();
						vtana.tamMarbete=Short.parseShort(vConfig.textTotCar.getText());
					}
				}
			}			
			scr.println(vConfig.textInventario.getText());//Se carga la direccion delInventario
			vtana.dirInventario=vConfig.textInventario.getText();
			scr.close();
		} catch (FileNotFoundException e) {	}
	}

}
