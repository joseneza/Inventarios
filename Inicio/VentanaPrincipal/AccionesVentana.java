package Inicio.VentanaPrincipal;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Configuracion.CambiarConfiguracion;
import ExportarResultados.OrdenarConsentrarScanner;
import Inicio.IniciarInventario;
import RevisarArticulos.AcciondeOpcionesRevision;
import RevisarArticulos.OpcionesRevision;
import RevisarArticulos.VentanaMarbetes.VentanaMarbete;

public class AccionesVentana {
	
	VentanaPrincipal vtana;
	String mensaje;
	
	public AccionesVentana(VentanaPrincipal vtana){
		this.vtana=vtana;
		mensaje = vtana.mensajeEstado.getText();
	}
	
	public void nvoInv(){
		vtana.nvoinv=new IniciarInventario(vtana);
		if(vtana.nvoinv.oper==true){
			vtana.botones.btnNvoInvent.setEnabled(false);
			vtana.botones.btnConfig.setEnabled(false);
			vtana.botones.btnRevisArt.setEnabled(true);
		}
	}
	
	public void revInv(){
		vtana.mensajeEstado.setText("Revisar Articulos");
		if(vtana.listscanners.size()>0){		
			OpcionesRevision nvarevision = new OpcionesRevision(vtana);
			new AcciondeOpcionesRevision(vtana, nvarevision.valor);
			
		}else{
			mostrarMensaje("Conteo vacio - No hay conteo que revisar", "Vacio");
		}
		vtana.mensajeEstado.setText(mensaje);
	}
	
	public void impPreCont(){
		vtana.mensajeEstado.setText("Impresion de Preconteos");
		if(vtana.listscanners.size()>0){
			new VentanaMarbete(vtana, 4, "Impresion de Marbetes", false);
		}else{
			mostrarMensaje("Conteo vacio - No hay resultados que imprimir", "Vacio");
		}
		vtana.mensajeEstado.setText(mensaje);
	}
	
	public void mostResult(){
		vtana.mensajeEstado.setText("Exportar Resultados");
		if(vtana.listscanners.size()>0){
			new OrdenarConsentrarScanner(vtana, vtana.panelScanner.tablaScanners.nvomodelo.datScan);
		}else{
			mostrarMensaje("Conteo vacio - No hay resultados que mostrar", "Vacio");
		}
		vtana.mensajeEstado.setText(mensaje);
	}
	
	public void configuracion(){
		vtana.mensajeEstado.setText("Cambiando Configuracion");
		new CambiarConfiguracion(vtana);
		vtana.mensajeEstado.setText(mensaje);
	}
	
	public void acercaD(){
		vtana.mensajeEstado.setText("");
		String mensaje = "Inventarios\n\nVersion 1.0\n\nHerramienta simple para el ingreso de articulos para realizar "+
				"un inventario.\n   Funciona principalmente tomando datos de un archivo de texto concatenado que se "+
				"descarga de un Scanner (Casio DT-930 por ejemplo).\n   Tambien puede tomar dados ingresandolos "+
				"manualmente o a traves de un archivo csv.\n\nDesarrollado por Jose Nezahualcoyotl Prieto "+
				"Amador.\nCorreo: jnprietoa@gmail.com\n\nDesarrollado en Ecplipse Luna 4.4";
		JOptionPane.showMessageDialog(vtana, mensaje, "Acerca de Inventario 1.0", JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(AccionesVentana.class.getResource("/Iconos/LOGO JOSE.png")));
		vtana.mensajeEstado.setText(this.mensaje);
	}
	
	public void salir(){
		vtana.confirmarCerrar();
	}
	
	private void mostrarMensaje(String mensaje, String titulo){
		JOptionPane.showMessageDialog(vtana, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE, vtana.information);
	}
}
