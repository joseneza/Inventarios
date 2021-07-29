package Scanner.TablaScanner;

import java.io.FileNotFoundException;
import java.io.IOException;

import Inicio.VentanaPrincipal.VentanaPrincipal;
import Scanner.CargarScanner;
import Scanner.Archivo.GenererScannerArchivo;
import Scanner.Manual.VentanaCargaManual;
import Scanner.Operaciones.ConsentradoScanner;

public class AccionScanner {
	
	VentanaPrincipal vtana;
	String mensaje;
	
	public AccionScanner(VentanaPrincipal vtana){
		this.vtana=vtana;
	}
	
	public void nvoScanner(TablaResultadoCargadeScanners tablaScanners){
		mensaje=vtana.mensajeEstado.getText();
		vtana.mensajeEstado.setText(mensaje+" - Cargando Condeto desde Scanner");
		CargarScanner nvoscan=new CargarScanner(vtana);
		try {
			nvoscan.cargarScanner(tablaScanners);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		vtana.mensajeEstado.setText(mensaje);
	}
	
	public void nvoManual(){
		mensaje=vtana.mensajeEstado.getText();
		vtana.mensajeEstado.setText(mensaje+" - Cargando Condeto Manualmente");
		VentanaCargaManual vtanaMan=new VentanaCargaManual(vtana);
		if(vtanaMan.agregar==false){
			vtanaMan.dispose();
		}else{
			vtanaMan.manual.generarConteo();
			try {
				new ConsentradoScanner(vtanaMan.manual.nscan, vtanaMan.manual.scannerManual,
						vtanaMan.textNombre.getText(),	vtanaMan.textUbic.getText(), vtana.dirInventario);
			} catch (FileNotFoundException e1) {}
				vtana.panelAvance.lblTotScanner.setText("Se han agregado "+vtana.listscanners.size()+" Scanners");
			vtanaMan.dispose();
		}
		vtana.mensajeEstado.setText(mensaje);
	}
	
	public void nvoArchivo(){
		mensaje=vtana.mensajeEstado.getText();
		vtana.mensajeEstado.setText(mensaje+" - Cargando Condeto desde un Archivo csv");
		GenererScannerArchivo contArch=new GenererScannerArchivo(vtana);
		if(contArch.conteo!=""){
			contArch.cargarConteo();
		}
		vtana.mensajeEstado.setText(mensaje);
	}

}
