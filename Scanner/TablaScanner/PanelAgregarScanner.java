package Scanner.TablaScanner;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Inicio.VentanaPrincipal.VentanaPrincipal;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;

public class PanelAgregarScanner extends JPanel {
	
	private VentanaPrincipal vtana;
	public TablaResultadoCargadeScanners tablaScanners;
	private JPanel panelBotones;
	public JButton nvoscanner;
	public JButton nvomanual;
	public JButton nvoarchivo;

	public PanelAgregarScanner(VentanaPrincipal vtana) {
		this.vtana=vtana;
		iniciar();
		valorPanel();
		acciones();
	}
	
	private void iniciar(){
		nvoscanner=new JButton("Cargar Scanners");
		nvoscanner.setIcon(new ImageIcon(PanelAgregarScanner.class.getResource("/Iconos/16x16/Scanner.png")));
		nvomanual = new JButton("Carga Manual");
		nvomanual.setIcon(new ImageIcon(PanelAgregarScanner.class.getResource("/Iconos/16x16/hojaylapiz.png")));
		nvoarchivo= new JButton("Cargar Archivo");
		nvoarchivo.setIcon(new ImageIcon(PanelAgregarScanner.class.getResource("/Iconos/16x16/document-import-2.png")));
		panelBotones=new JPanel();
		tablaScanners=new TablaResultadoCargadeScanners(0);
	}
	
	private void valorPanel(){
		setLayout(new BorderLayout(0, 0));
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelBotones.add(nvoscanner);		
		panelBotones.add(nvomanual);
		panelBotones.add(nvoarchivo);
		add(panelBotones, BorderLayout.SOUTH);
		add(tablaScanners, BorderLayout.CENTER);
	}
	
	private void acciones(){
		this.nvoscanner.addActionListener(new ActionListener() {//Boton Scanner
			public void actionPerformed(ActionEvent e) {
				AccionScanner nvoConteo = new AccionScanner(vtana);
				nvoConteo.nvoScanner(tablaScanners);
			}
		});
		this.nvomanual.addActionListener(new ActionListener() {//Boton Manual
			public void actionPerformed(ActionEvent e) {
				AccionScanner nvoConteo = new AccionScanner(vtana);
				nvoConteo.nvoManual();
			}
		});
		this.nvoarchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccionScanner nvoConteo = new AccionScanner(vtana);
				nvoConteo.nvoArchivo();			
			}
		});
	}

}

