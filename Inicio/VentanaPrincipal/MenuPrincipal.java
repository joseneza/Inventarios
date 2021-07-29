package Inicio.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Scanner.TablaScanner.AccionScanner;

public class MenuPrincipal extends JMenuBar{
	
	public JMenu menuInventario, menuAyuda;
	public JMenu conteo;
	public JMenuItem nuevo, revisar, imprimir, exportar, salir;
	public JMenuItem scanner, manual, archivo;
	public JMenuItem configuracion, acerca;
	VentanaPrincipal vtana;
	
	public MenuPrincipal(VentanaPrincipal vtana) {
		this.vtana=vtana;
		crearMenus();
		agregarMenus();
		agregarAccion();
	}
	
	private void crearMenus(){
		menuInventario=new JMenu("Inventario");
		menuInventario.setMnemonic('i');
		menuAyuda=new JMenu("Ayuda");
		nuevo=new JMenuItem("Nuevo Inventario");
		conteo=new JMenu("Nvo Conteo");
		conteo.setEnabled(false);
		scanner=new JMenuItem("Por Scanner");
		manual=new JMenuItem("Manual");
		archivo=new JMenuItem("De Archivo");
		revisar=new JMenuItem("Revisar Inventario");
		revisar.setEnabled(false);
		imprimir=new JMenuItem("Imprimir Preconteos");
		imprimir.setEnabled(false);
		exportar=new JMenuItem("Exportar Resultados");
		exportar.setEnabled(false);
		salir=new JMenuItem("Salir");
		menuAyuda.setMnemonic('h');
		configuracion=new JMenuItem("Configuracion");
		acerca=new JMenuItem("Acerca de...");
	}
	
	private void agregarMenus(){
		this.add(menuInventario);
		menuInventario.add(nuevo);
		conteo.add(scanner);
		conteo.add(manual);
		conteo.add(archivo);
		menuInventario.add(conteo);
		menuInventario.add(revisar);
		menuInventario.add(imprimir);
		menuInventario.add(exportar);
		menuInventario.add(salir);
		this.add(menuAyuda);
		menuAyuda.add(configuracion);
		menuAyuda.add(acerca);
	}
	
	private void agregarAccion(){
		this.nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.nvoInv();
			}
		});
		
		this.scanner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionScanner acs = new AccionScanner(vtana);
				acs.nvoScanner(vtana.panelScanner.tablaScanners);
			}
		});
		
		this.manual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionScanner acs = new AccionScanner(vtana);
				acs.nvoManual();
			}
		});
		
		this.archivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionScanner acs = new AccionScanner(vtana);
				acs.nvoArchivo();
			}
		});
		
		this.revisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.revInv();
			}
		});
		
		this.imprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.impPreCont();
			}
		});
		
		this.salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.salir();
			}
		});
		
		this.exportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.mostResult();
			}
		});
		
		this.configuracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.configuracion();
			}
		});
		
		this.acerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.acercaD();				
			}
		});
		
	}

}
