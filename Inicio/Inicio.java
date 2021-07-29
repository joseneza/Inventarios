package Inicio;
/* Primer programa completo en Java
 * Inventarios V 0.4.5
 * Inicio 10 de diciembre del 2014
 * Modificado el 18 de Abril del 2015
 * @author Jose Nezahualcoyotl Prieto Amador
 * joseneza12@yahoo.com.mx
 * joseneza@gmail.com
 */

import java.io.IOException;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Inicio.VentanaPrincipal.VentanaPrincipal;
import Scanner.TablaScanner.PanelAgregarScanner;

public class Inicio {

	public static void main(String[] args) throws IOException {
		if(vigencia()==true){
			System.out.println("Iniciando Aplicacion v.0.8.2 Beta - Icono Principal");
			new VentanaPrincipal();
		}else{
			JOptionPane.showMessageDialog(null, "No se puede iniciar el programa", "ERROR AL INICIAR", JOptionPane.ERROR_MESSAGE,
					new ImageIcon(PanelAgregarScanner.class.getResource("/Iconos/48x48/dialog-error.png")));
		}
		
	}
	
	private static Boolean vigencia(){
		Boolean vigencia = true;
		Calendar fecha = Calendar.getInstance();
		int  yy=fecha.get(Calendar.YEAR);
		int mm=fecha.get(Calendar.MONTH);
		int dd=fecha.get(Calendar.DAY_OF_MONTH);
		int vig_yy=2016;
		int vig_mm=1;
		int vig_dd=14;
		if(yy>vig_yy){
			vigencia=false;
		}else{
			if(yy==vig_yy){
				if(mm>vig_mm){
					vigencia=false;
				}else{
					if(mm==vig_mm){
						if(dd>vig_dd){
							vigencia=false;
						}
					}
				}
			}			
		}
		return vigencia;		
	}
}
