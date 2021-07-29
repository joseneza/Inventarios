package Impresion.MuestraEnPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Impresion.MuestraEnPanel.TablaHojas.NumeroHojas;
import Impresion.MuestraEnPanel.TablaHojas.PanelTablaNumHojas;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import Marbetes.ImprimirMarbetes.DatosMarbete;
import RevisarArticulos.VentanaMarbetes.VentanaMarbete;
import RevisarArticulos.VentanaRevision.DatosRevision;
import Scanner.TablaScanner.PanelAgregarScanner;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JRadioButton;

public class VentanaPreVisualizacion extends JDialog {
	String tituloImp;
	ArrayList<ArrayList<DatosRevision>> concReportes;
	ArrayList<ArrayList<DatosMarbete>> contMarb;
	JPanel panelIzq;
	JButton btnImprimir;
	JButton btnCancelar;
	JScrollPane panelDeslizable;
	JScrollPane panelTexto;
	private JPanel panelCentral;
	VentanaPreVisualizacion vtanaPre=this;
	public Boolean imprimir=false;
	public ArrayList<PanelHoja> imgImpr;
	private JPanel panelBoton;
	private JPanel panelDHojas;
	private JRadioButton rdbtnImprTodo;
	private JRadioButton rdbtnSeleccionarImpr;
	private JPanel panelrdbtn;
	private JLabel textNumHojas;
	private JPanel panelInferiorIzq;
	private PanelTablaNumHojas tablaHojas;
	private JPanel panelCentralIzq;
	private Dimension medidaHoja;
	int opcion;
	public ImageIcon information = new ImageIcon(VentanaPreVisualizacion.class.getResource("/Iconos/48x48/dialog-information.png"));
	
	//Constructor para Imprimir Reportes de Inventario
	public VentanaPreVisualizacion(String tituloImp, ArrayList<ArrayList<DatosRevision>> concReportes, VentanaMarbete vMarb, Dimension medidaHoja, 
			int opcion) {
		super(vMarb, true);
		String mensaje = vMarb.vtana.mensajeEstado.getText();
		vMarb.vtana.mensajeEstado.setText(mensaje+" - Impresion de Reportes");
		this.tituloImp=tituloImp;
		this.concReportes=concReportes;
		this.opcion=opcion;
		this.medidaHoja=medidaHoja;
		cargarVtana();
		vMarb.vtana.mensajeEstado.setText(mensaje);
	}
	
	//Constructor para Imprimir Preconteos
	public VentanaPreVisualizacion(String tituloImp, ArrayList<ArrayList<DatosMarbete>> contMarb, VentanaPrincipal vtana, Dimension medidaHoja) {
		super(vtana, true);
		String mensaje = vtana.mensajeEstado.getText();
		vtana.mensajeEstado.setText(mensaje+" - Impresion de Preconteos");
		this.tituloImp=tituloImp;
		this.contMarb=contMarb;
		this.opcion=1;
		this.medidaHoja=medidaHoja;
		cargarVtana();
		vtana.mensajeEstado.setText(mensaje);
	}
	
	private void cargarVtana(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		iniciarElementos();
		agregarElementos();
		agregarAccion();
		setSize(1100, 700);
		setLocationRelativeTo(null);//Da la ubicacion de la ventana como es null la centra
		setVisible(true);
	}
	
	private void iniciarElementos(){
		panelIzq = new JPanel();		
		panelBoton = new JPanel();
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnImprimir.setIcon(new ImageIcon(VentanaPreVisualizacion.class.getResource("/Iconos/64x64/printer.png")));
		btnImprimir.setActionCommand("OK");
		panelrdbtn = new JPanel();
		rdbtnImprTodo = new JRadioButton("Imprimir Todo");
		rdbtnImprTodo.setSelected(true);
		rdbtnImprTodo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));		
		rdbtnSeleccionarImpr = new JRadioButton("Seleccionar Hojas");
		rdbtnSeleccionarImpr.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		panelInferiorIzq = new JPanel();
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnCancelar.setIcon(new ImageIcon(VentanaPreVisualizacion.class.getResource("/Iconos/64x64/exit.png")));
		btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCancelar.setActionCommand("Cancel");		
		tablaHojas = new PanelTablaNumHojas();
		panelCentralIzq = new JPanel();
		textNumHojas = tablaHojas.tablHojas.textNumHojas;
		textNumHojas.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		panelTexto = new JScrollPane();
		panelCentral = new JPanel();
		panelDeslizable = new JScrollPane();
		panelDeslizable.setPreferredSize(new Dimension((int) medidaHoja.getWidth()+20, 650));
		imgImpr=new ArrayList<PanelHoja>();
		panelDHojas = new JPanel();
	}
					
	private void agregarElementos(){
		getContentPane().add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new BorderLayout(0, 0));
		panelIzq.add(panelBoton, BorderLayout.NORTH);
		panelBoton.setLayout(new GridLayout(2, 1, 5, 5));		
		btnImprimir.setAlignmentX(Component.CENTER_ALIGNMENT);		
		getRootPane().setDefaultButton(btnImprimir);
		panelBoton.add(btnImprimir);		
		panelBoton.add(panelrdbtn);
		panelrdbtn.setLayout(new GridLayout(2, 1, 5, 5));		
		panelrdbtn.add(rdbtnImprTodo);
		panelrdbtn.add(rdbtnSeleccionarImpr);
		panelIzq.add(panelInferiorIzq, BorderLayout.SOUTH);
		panelIzq.add(panelCentralIzq, BorderLayout.CENTER);
		panelInferiorIzq.add(btnCancelar);		
		panelCentralIzq.setLayout(new BorderLayout(0, 0));		
		panelCentralIzq.add(tablaHojas);
		panelCentralIzq.add(panelTexto, BorderLayout.NORTH);
		panelTexto.setPreferredSize(new Dimension(100, 35));
		panelTexto.setViewportView(textNumHojas);
		panelCentralIzq.setVisible(false);	
		panelDHojas.setBackground(new Color(240, 230, 140));
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		cargarHojas();
		panelDeslizable.setViewportView(panelDHojas);
		panelCentral.add(panelDeslizable);
		
	}
	
	private void cargarHojas(){
		if(opcion==1){
			panelDHojas.setLayout(new GridLayout(contMarb.size(), 1, 10, 10));
			for(int n=0;n<contMarb.size();n++){
				ArrayList<DatosMarbete> linea=(ArrayList<DatosMarbete>) contMarb.get(n);
				tablaHojas.tablHojas.addFila(n+1,"Hoja "+(n+1));
				PanelHoja panelHoja = new PanelHoja(tituloImp, linea, n+1, contMarb.size(), medidaHoja);
				panelDHojas.add(panelHoja);			
			}
		}else{
			panelDHojas.setLayout(new GridLayout(concReportes.size(), 1, 10, 10));
			for(int n=0;n<concReportes.size();n++){
				ArrayList<DatosRevision> linea=(ArrayList<DatosRevision>) concReportes.get(n);
				tablaHojas.tablHojas.addFila(n+1, linea.get(0).getScan()+" "+linea.get(0).getMarb());
				PanelHoja panelHoja = new PanelHoja(tituloImp, linea, n+1, concReportes.size(), medidaHoja, opcion);
				panelDHojas.add(panelHoja);			
			}
		}	
	}
	
	private void agregarAccion(){
		rdbtnImprTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnImprTodo.isSelected()==true){
					rdbtnSeleccionarImpr.setSelected(false);
					panelCentralIzq.setVisible(false);
				}else{
					rdbtnSeleccionarImpr.setSelected(true);
					panelCentralIzq.setVisible(true);
				}
			}
		});
		rdbtnSeleccionarImpr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnSeleccionarImpr.isSelected()==true){
					rdbtnImprTodo.setSelected(false);
					panelCentralIzq.setVisible(true);
				}else{
					rdbtnImprTodo.setSelected(true);
					panelCentralIzq.setVisible(false);
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vtanaPre.setVisible(false);
			}
		});
		
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean seleccion = false;
				if(rdbtnImprTodo.isSelected()==true){
					int numhojas;
					if(opcion==1){
						numhojas=contMarb.size();
					}else{
						numhojas=concReportes.size();
					}
					for(int n=0;n<numhojas;n++){
						PanelHoja hoja = (PanelHoja) panelDHojas.getComponent(n);
						imgImpr.add(hoja);		
					}
					seleccion = true;
				}else{
					ArrayList<NumeroHojas> listHojas = tablaHojas.tablHojas.listHojas;
					Iterator<NumeroHojas> revListHojas = listHojas.iterator();
					while(revListHojas.hasNext()){
						NumeroHojas linea = revListHojas.next();
						if(linea.getSelec()==true){
							seleccion = true;
						}
					}
					for(int n=0;n<listHojas.size();n++){
						if(listHojas.get(n).getSelec()==true){
							PanelHoja hoja = (PanelHoja) panelDHojas.getComponent(n);
							imgImpr.add(hoja);
						}							
					}
				}
				if(seleccion==true){
					imprimir=true;
					vtanaPre.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "No has seleccionado ninguna hoja", "SELECCIONA HOJAS",
							JOptionPane.INFORMATION_MESSAGE, information);
				}
								
			}
		});
		
	}
	
}
