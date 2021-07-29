package Inicio.CatalogoArticulos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Inicio.VentanaPrincipal.VentanaPrincipal;

public class CargarCatalogo {
	public ArrayList<Long> listCatalogo;
	public ArrayList<String> listDescripcion;
	public ArrayList<Long> listEan;
	String dirCatalogo;
	File archCatalogo;
	int colSku=-1;
	int colDescr=-1;
	int colEan=-1;
	public Boolean agregado=false;
	public Boolean validar=false;
	
	public CargarCatalogo(String dirCatalogo){//Carga de catalogo inicial
		this.dirCatalogo=dirCatalogo;		
		if(buscarArchivo()==true){
			listCatalogo=new ArrayList<Long>();
			listDescripcion=new ArrayList<String>();
			listEan=new ArrayList<Long>();
			cargarCatalogo();
		}		
	}
	
	private Boolean buscarArchivo(){		
		archCatalogo=new File(dirCatalogo);//Se crea objeto para el archivo Catalogo
		return archCatalogo.exists();
	}
	
	private void cargarCatalogo(){
		String linea = null;		
		try {
			BufferedReader leerArch = new BufferedReader(new FileReader(archCatalogo));//Leer el archivo por linea
			try {
				linea=leerArch.readLine();
				if(linea!=null){
					validar=validarColumnas(linea);
				}
			} catch (IOException e1) {
				linea=null;
			}
			if(validar==true){
				while(linea!=null){
					try {
						linea=leerArch.readLine();
						if(linea!=null){
							agregarLinea(linea);
						}
					} catch (IOException e) {}//Fin de tratamiento de linea
				}
			}else{
				JOptionPane.showMessageDialog(null, "No se encontraron las columnas decesasias para cargar el catalogo.\n"
						+ "Asegurate de que esten bien escritas la tres columnas:\nSku.\nDescripcion\nEan",
						"Error en Titulos", JOptionPane.INFORMATION_MESSAGE);
			}
			if(listCatalogo.size()>0){
				agregado=true;
			}
			try {
				leerArch.close();
			} catch (IOException e) {}
		} catch (FileNotFoundException e1) {}//Fin de tratamiento de archivo Catalogo
	}
	
	private void agregarLinea(String linea){
		String sku="";
		String descripcion="";
		String ean="";
		String c;
		int col=0;
		for(int a=0;a<linea.length();a++){
			c=linea.substring(a,a+1);
			if(c.equals(",")){//Checa si el caracter es una coma
				col++;
			}else{
				if(col==colSku){
					if(esNumero(c)==true)sku=sku+c;
				}else{
					if(col==colDescr){
						descripcion=descripcion+c;
						}else{
							if(col==colEan){
								if(esNumero(c)==true){
									ean=ean+c;
								}
							}
						}
				}
			}
		}
		long s;
		try{
			s=Long.parseLong(sku);
		}catch(NumberFormatException ex){
			s=0;
		}
		long e;
		try{
			e=Long.parseLong(ean);
		}catch(NumberFormatException ex){
			e=0;
		}
		if(s!=0){
			listCatalogo.add(s);
			listDescripcion.add(descripcion);
			listEan.add(e);
		}		
	}
	
	private Boolean validarColumnas(String titulo){
		Boolean valido=false;
		String [] separarlinea = titulo.split(","); // Obtengo los datos de la linea separados por una coma (,)
		for(int i=0;i<separarlinea.length;i++){
			String valor=separarlinea[i];
			switch (valor) {
			
			case "Sku":	colSku=i;	break;
			case "sku":	colSku=i;	break;
			case "SKU":	colSku=i;	break;
				
			case "ean":	colEan=i;	break;
			case "EAN":	colEan=i;	break;
			case "Ean":	colEan=i;	break;	
			
			case "Descripcion":	colDescr=i;	break;
			case "descripcion":	colDescr=i;	break;
			case "DESCRIPCION":	colDescr=i;	break;
				
			default:	break;
			}
		}
		if(colSku>=0 && colEan>=0 && colDescr>=0){
			valido=true;
		}
		return valido;
	}
	
	private Boolean esNumero(String c){
		try{
			Integer.parseInt(c);
			return true;
		}catch(NumberFormatException ex){
			return false;
		}
	}
}
