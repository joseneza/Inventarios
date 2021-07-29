package Inicio.VentanaPrincipal;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotones extends JPanel {
	
	VentanaPrincipal vtana;
	public JButton btnNvoInvent;
	public JButton btnRevisArt;
	public JButton btnMostResultados;
	public JButton btnSalir;
	public JButton btnConfig;
	public JButton btnImpPreCont;

	public PanelBotones(VentanaPrincipal vtana) {
		this.vtana=vtana;
		this.setSize(new Dimension(295, 25));
		agregarElementos();
		agregarAccion();
	}
	
	private void agregarElementos(){
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));		
		btnNvoInvent = new JButton("");
		btnNvoInvent.setToolTipText("Nuevo Inventario");
		btnNvoInvent.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/16x16/document-new.png")));
		btnNvoInvent.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		this.add(btnNvoInvent);
		
		btnRevisArt = new JButton("");
		btnRevisArt.setToolTipText("Revisar Articulos");
		btnRevisArt.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/16x16/document-preview.png")));
		btnRevisArt.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnRevisArt.setEnabled(false);
		this.add(btnRevisArt);
		
		btnImpPreCont = new JButton("");
		btnImpPreCont.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/16x16/document-print.png")));		
		btnImpPreCont.setEnabled(false);
		btnImpPreCont.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnImpPreCont.setToolTipText("Imprimir Preconteos");
		this.add(btnImpPreCont);
		
		btnMostResultados = new JButton("");
		btnMostResultados.setToolTipText("Exportar Resultados");
		btnMostResultados.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/16x16/text-csv.png")));
		btnMostResultados.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnMostResultados.setEnabled(false);
		this.add(btnMostResultados);
		
		btnConfig = new JButton("");
		btnConfig.setToolTipText("Configuracion");
		btnConfig.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/16x16/preferences-system.png")));
		btnConfig.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		this.add(btnConfig);
		
		btnSalir = new JButton("");
		btnSalir.setToolTipText("Salir del Inventario");
		btnSalir.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/16x16/system-shutdown.png")));
		btnSalir.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		this.add(btnSalir);
		
		
	}
	
	private void agregarAccion(){
		this.btnNvoInvent.addActionListener(new ActionListener() {//Boton Inventario
			public void actionPerformed(ActionEvent e) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.nvoInv();
			}
		});
		
		this.btnRevisArt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.revInv();
			}
		});
		
		this.btnImpPreCont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.impPreCont();
			}
		});
		
		this.btnMostResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.mostResult();
			}
		});
		
		this.btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.configuracion();
			}
		});
		
		this.btnSalir.addActionListener(new ActionListener() {//Boton Salir
			public void actionPerformed(ActionEvent e) {
				AccionesVentana acc = new AccionesVentana(vtana);
				acc.salir();
			}
		});
		
	}

}
