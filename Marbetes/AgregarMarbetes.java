package Marbetes;
/* Clase que quenera el panel de la tabla
 * para capturar los marbetes que se van a utilizar
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Inicio.VentanaPrincipal.VentanaPrincipal;
import Marbetes.ImprimirMarbetes.DatosMarbete;
import Marbetes.ImprimirMarbetes.ImprimirMarbetes;
import Marbetes.MarbetesInventario.OperacionesMarbetesInventario;
import Marbetes.TablaMarbetes.DatosLineaMarbete;
import Marbetes.TablaMarbetes.ModAbstractTablaMarb;
import Marbetes.TablaMarbetes.TablaMarbetes;
import Scanner.TablaScanner.PanelAgregarScanner;

import javax.swing.ImageIcon;


public class AgregarMarbetes extends JPanel{
	private VentanaPrincipal vtana;
	ArrayList<ArrayList<String>> marbetes;
	ArrayList<ArrayList<String>> marbetesContados;
	ModAbstractTablaMarb modTablaAbst;
	private JLabel titulo;
	private JScrollPane panelTabla;
	public TablaMarbetes tablaMarb;
	private JPanel botones;
	private JButton btnGenerarMarb;
	private JButton btnCancelar;
	public Boolean oper=false;
	String tipoMarbete;
	int max;
	private long limite;
	private JButton btnImprimirMarbetes;
	Boolean impr;
	AgregarMarbetes agrMarb = this;
	AccionesMarbetes acMarb;
	JMenu mMarbetes;
	JMenuItem imprMarbetes;
	JMenuItem genMarbetes;
	JMenuItem salMarbetes;

	public AgregarMarbetes(VentanaPrincipal vtana) {
		this.vtana=vtana;
		this.tipoMarbete=vtana.iniMarbete;
		this.max=vtana.tamMarbete;
		this.limite=cargarLimite(max);
		iniciar();
		valorPanel();
		valorTabla();
		agregarMenu();
		agregarAcciones();
	}

	
	private void iniciar() {
		panelTabla = new JScrollPane();
		modTablaAbst=new ModAbstractTablaMarb();
		tablaMarb=new TablaMarbetes(modTablaAbst, tipoMarbete, max);				
	}
	
	private void valorPanel(){
		setLayout(new BorderLayout(0, 0));
		add(panelTabla, BorderLayout.CENTER);
		botones=new JPanel();		
		add(botones, BorderLayout.SOUTH);
		btnGenerarMarb = new JButton("Generar Marbetes");		
		btnGenerarMarb.setIcon(new ImageIcon(AgregarMarbetes.class.getResource("/Iconos/16x16/view-barcode-add.png")));
		btnGenerarMarb.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnCancelar = new JButton("Cancelar");		
		btnCancelar.setIcon(new ImageIcon(AgregarMarbetes.class.getResource("/Iconos/16x16/edit-delete.png")));
		btnCancelar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		
		btnImprimirMarbetes = new JButton("Imprimir Marbetes");
		btnImprimirMarbetes.setIcon(new ImageIcon(AgregarMarbetes.class.getResource("/Iconos/16x16/printer.png")));
		btnImprimirMarbetes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		botones.add(btnImprimirMarbetes);
		botones.add(btnGenerarMarb);	
		botones.add(btnCancelar);
		titulo = new JLabel("Ingresa los marbetes que se van a usar en  el Inventario");
		add(titulo, BorderLayout.NORTH);
		titulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		
	}
	
	private void valorTabla(){	
		panelTabla.setViewportView(tablaMarb);//La tabla se vera dentro del panel de las barras de desplazamiento
		modTablaAbst.addFila(1, tipoMarbete, cargarLimite(max));
	}
	
	private void agregarMenu(){
		mMarbetes = new JMenu("Marbetes");
		imprMarbetes = new JMenuItem("Imprimir Marbetes");
		mMarbetes.add(imprMarbetes);
		genMarbetes = new JMenuItem("Generar Marbetes");
		mMarbetes.add(genMarbetes);
		salMarbetes = new JMenuItem("Cancelar Marbetes");
		mMarbetes.add(salMarbetes);
		vtana.menu.menuInventario.add(mMarbetes, 1);
	}
	
	Boolean excederMarbetes(){
		Iterator<DatosLineaMarbete> barridaMarnetes=modTablaAbst.datos.iterator();
		long marb=0;
		while(barridaMarnetes.hasNext()){
			DatosLineaMarbete linea=barridaMarnetes.next();
			try{
				marb=marb+Long.parseLong(linea.getCantidad());
			}catch(NumberFormatException e){}
		}
		if(marb>limite){
			return true;
		}else{
			return false;
		}
		 		
	}
	
	private void agregarAcciones(){
		acMarb = new AccionesMarbetes(agrMarb, vtana);
		btnImprimirMarbetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acMarb.imprMarbetes();
			}
		});
		imprMarbetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acMarb.imprMarbetes();
			}
		});
		
		
		
		btnGenerarMarb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acMarb.genMarbetes();
			}

		});
		genMarbetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acMarb.genMarbetes();
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acMarb.cancMarbetes();			
			}
		});
		salMarbetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acMarb.cancMarbetes();	
			}
		});
	}
	
	private Long cargarLimite(int tamano){
		String cadena="";
		int num=tamano-tipoMarbete.length();
		for(int n=0;n<num;n++){
			cadena=cadena+"9";
		}
		return Long.parseLong(cadena);		
	}
	
	ArrayList<DatosMarbete> generarMarbetes(){
		marbetes = new ArrayList<ArrayList<String>>();
		marbetesContados = new ArrayList<ArrayList<String>>();
		GenerarMarbetes masmarbetes=new GenerarMarbetes(tipoMarbete, max);
		ArrayList<DatosMarbete> listaImprimir = new ArrayList<DatosMarbete>();
		long totMarbetes=0;
		for(int x=0;x<modTablaAbst.datos.size();x++){
			ArrayList<String> temporal=new ArrayList<String>();
			ArrayList<String> temporalC=new ArrayList<String>();
			DatosLineaMarbete linea=(DatosLineaMarbete) modTablaAbst.datos.get(x);
			if(linea.getUbicacion().length()!=0 && linea.getCantidad().length()!=0){
				totMarbetes=totMarbetes+Long.parseLong(linea.getCantidad());
				temporal=masmarbetes.cargarMarbetes(linea.getUbicacion(), linea.getCantidad(), 
							linea.getInicio().substring(vtana.iniMarbete.length()));
				temporalC.add(linea.getUbicacion());
				if(temporal.size()>0){
					marbetes.add(temporal);
					marbetesContados.add(temporalC);
					for(int n=2;temporal.size()>n;n++){
						listaImprimir.add(new DatosMarbete(temporal.get(0), temporal.get(n)));
					}
				}
			}
		}
		return listaImprimir;
	}
	
	void imprimir(ArrayList<DatosMarbete> listaImprimir){
		ImprimirMarbetes impMarbetes;		
		if(listaImprimir.isEmpty()){}else{
			impMarbetes = new ImprimirMarbetes(vtana, listaImprimir);
			impr = impMarbetes.impresion;
		}
	}

}
