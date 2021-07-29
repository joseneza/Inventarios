package RevisarArticulos.VentanaMarbetes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ExportarResultados.ImprimirHojaPreInv.ImprimirPreInventario;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import RevisarArticulos.CambiosHechos.BorrarMarbetes;
import RevisarArticulos.CambiosHechos.GuardarCambios;
import RevisarArticulos.Imprimir.ImprimirReportes;
import RevisarArticulos.VentanaRevision.DatosRevision;
import RevisarArticulos.VentanaRevision.VentanaRevision;

import javax.swing.JCheckBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

public class VentanaMarbete extends JDialog {
	
	public VentanaPrincipal vtana;
	VentanaRevision vtanaRev;
	String titulo;
	String tit;
	public PanelTablaRevMarbetes panelTabla;
	JPanel panelBotones;
	private JPanel panelSuperior;
	private JLabel lblMensajeTitulo;
	private int opcion;
	public int valor = -1;
	VentanaMarbete vtanaMarb = this;
	public ArrayList<ListaMarbetes> seleccion;
	private JButton btnMostrar;
	private JButton btnSalir;
	private JButton btnBorrar;
	private JButton btnImprimir;
	private String mensaje="";
	Boolean botonbor=false;
	public ArrayList<String> marbetesDobles;
	ImprimirReportes imprimirtabla;
	private JPanel panelImpMarbetes;
	private JButton btnImpMarb;
	private JCheckBox chckbxSelecAll;
	public JCheckBox chckbxImpConsentrado;
	public JCheckBox chckbxSelecMultiple;
	/**
	 * @wbp.parser.constructor
	 */	
	public VentanaMarbete(VentanaPrincipal vtana, int opcion, String titulo, Boolean botonbor) {//Contructor basico
		super(vtana, true);
		this.titulo=titulo;
		if(opcion!=4){
			this.tit="Escoje los marbetes quieres revisar";
		}else{
			this.tit="Escoje los marbetes quieres imprimir";
		}
		this.opcion=opcion;
		this.vtana=vtana;
		this.botonbor=botonbor;
		valoresVentana(titulo);		
	}	
	
	public VentanaMarbete(VentanaPrincipal vtana, int opcion, String titulo, Boolean botonbor,
			ArrayList<String> marbDupli) {//Constructor para agregar mas datos
		super(vtana, true);
		this.opcion=opcion;
		this.vtana=vtana;
		this.marbetesDobles=marbDupli;
		this.botonbor=botonbor;
		valoresVentana(titulo);		
	}
		
	private void valoresVentana(String titulo) {
		setTitle(titulo);
		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		crearElementos();
		agregarElementos();		
		agregarLineas();
		agregarAcciones();
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(580, 360);
		setLocationRelativeTo(null);//Da la ubicacion de la ventana como es null la centra
		setVisible(true);		
	}
	
	private void crearElementos(){
		panelTabla = new PanelTablaRevMarbetes();
		panelSuperior = new JPanel();
		lblMensajeTitulo = new JLabel(tit);		
		lblMensajeTitulo.setFont(new Font("Arial", Font.BOLD, 12));
		lblMensajeTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		chckbxSelecMultiple = new JCheckBox("Seleccion Multiple");
		chckbxSelecMultiple.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxSelecMultiple.setMnemonic('m');
		chckbxSelecMultiple.setFont(new Font("Arial", Font.PLAIN, 11));
		
		chckbxSelecAll = new JCheckBox("Seleccionar Todo");
		chckbxSelecAll.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSelecAll.setEnabled(false);
		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnImprimir = new JButton("Imprimir Marbetes");
		btnImprimir.setIcon(new ImageIcon(VentanaMarbete.class.getResource("/Iconos/16x16/printer.png")));
		btnImprimir.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnImprimir.setEnabled(true);
		btnImprimir.setVisible(true);
		btnBorrar = new JButton("Borrar Marbetes");
		btnBorrar.setIcon(new ImageIcon(VentanaMarbete.class.getResource("/Iconos/16x16/edit-clear-list.png")));
		btnBorrar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnBorrar.setEnabled(botonbor);
		btnBorrar.setVisible(botonbor);
		btnMostrar = new JButton("Mostrar Marbetes");
		btnMostrar.setIcon(new ImageIcon(VentanaMarbete.class.getResource("/Iconos/16x16/edit-find-replace.png")));
		btnMostrar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnSalir = new JButton("");
		btnSalir.setToolTipText("Salir");
		btnSalir.setIcon(new ImageIcon(VentanaMarbete.class.getResource("/Iconos/16x16/exit.png")));
		btnSalir.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));		
		panelImpMarbetes = new JPanel();
		chckbxImpConsentrado = new JCheckBox("Impresion Consentrada de Marbetes");
		chckbxImpConsentrado.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));		
		btnImpMarb = new JButton("Imprimir Marbetes");
		btnImpMarb.setIcon(new ImageIcon(VentanaMarbete.class.getResource("/Iconos/16x16/document-print.png")));
		btnImpMarb.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));		
	}
	
	private void agregarElementos(){
		getContentPane().setLayout(new BorderLayout(0, 0));		
		getContentPane().add(panelSuperior, BorderLayout.NORTH);		
		GroupLayout gl_panelSuperior = new GroupLayout(panelSuperior);
		gl_panelSuperior.setHorizontalGroup(
			gl_panelSuperior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperior.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMensajeTitulo)
					.addGap(82)
					.addComponent(chckbxSelecAll)
					.addGap(18)
					.addComponent(chckbxSelecMultiple, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(35))
		);
		gl_panelSuperior.setVerticalGroup(
			gl_panelSuperior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperior.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panelSuperior.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMensajeTitulo)
						.addComponent(chckbxSelecMultiple)
						.addComponent(chckbxSelecAll)))
		);
		panelSuperior.setLayout(gl_panelSuperior);
		getContentPane().add(panelTabla, BorderLayout.CENTER);		
		panelBotones.add(btnImprimir);		
		panelBotones.add(btnBorrar);		
		panelBotones.add(btnMostrar);		
		getRootPane().setDefaultButton(btnMostrar);		
		panelBotones.add(btnSalir);		
		if(opcion!=4){
			getContentPane().add(panelBotones, BorderLayout.SOUTH);
		}else{
			panelImpMarbetes.add(chckbxImpConsentrado);
			panelImpMarbetes.add(btnImpMarb);
			panelImpMarbetes.add(btnSalir);
			getContentPane().add(panelImpMarbetes, BorderLayout.SOUTH);
		}
		
	}
	
	private void agregarLineas(){
		for(int n=0;n<vtana.datMarbete.size();n++){
			switch (opcion){
			case 1://Articulos no encontrados
				if(vtana.datMarbete.get(n).getError()>0 & vtana.datMarbete.get(n).getTotal()>0){//Agregando lineas a la tabla
					panelTabla.tabRevMarv.addFila(vtana.datMarbete.get(n).getScan(), vtana.datMarbete.get(n).getNombre(),
							vtana.datMarbete.get(n).getUbic(), vtana.datMarbete.get(n).getMarbete(), 
							vtana.datMarbete.get(n).getTotal(), vtana.datMarbete.get(n).getError(),
							false);
				}
				break;
			case 2://Marbetes Duplicados
				if(marbetesDobles.contains(vtana.datMarbete.get(n).getMarbete())){
					panelTabla.tabRevMarv.addFila(vtana.datMarbete.get(n).getScan(), vtana.datMarbete.get(n).getNombre(),
							vtana.datMarbete.get(n).getUbic(), vtana.datMarbete.get(n).getMarbete(), 
							vtana.datMarbete.get(n).getTotal(), vtana.datMarbete.get(n).getError(),
							false);
				}
				break;
			default:
				if(vtana.datMarbete.get(n).getTotal()>0){//Agregando lineas a la tabla
					panelTabla.tabRevMarv.addFila(vtana.datMarbete.get(n).getScan(), vtana.datMarbete.get(n).getNombre(),
							vtana.datMarbete.get(n).getUbic(), vtana.datMarbete.get(n).getMarbete(), 
							vtana.datMarbete.get(n).getTotal(), vtana.datMarbete.get(n).getError(),
							false);
				}				
				break;
			}				
		}
	}
	
	private void marbetesEscogidos(){//verificar marbetes escogidos
		seleccion = new ArrayList<ListaMarbetes>();
		mensaje="";
		Iterator<ListaMarbetes> verifLin = panelTabla.tabRevMarv.listMarb.iterator();
		while (verifLin.hasNext()) {
			ListaMarbetes linea = verifLin.next();
			if(linea.escojer==true){//Se verifican cuales marbetes se escogioeron para checar
				seleccion.add(linea);
				mensaje=mensaje+"\nDel scanner "+linea.getScan()+" el marbete "+linea.getMarbete()+" con "+linea.getTotal()+" articulos";
			}					
		}
	}
	
	public void quitarMarbeteCeros(){
		for(int n=(panelTabla.tabRevMarv.listMarb.size()-1);n>=0;n--){
			ListaMarbetes linea= (ListaMarbetes) panelTabla.tabRevMarv.listMarb.get(n);
			if(linea.getTotal()==0){
				panelTabla.tabRevMarv.listMarb.remove(n);
			}
		}
	}
	
	public void quitarMarbetesSinErrores(){
		for(int n=(panelTabla.tabRevMarv.listMarb.size()-1);n>=0;n--){
			ListaMarbetes linea= (ListaMarbetes) panelTabla.tabRevMarv.listMarb.get(n);
			if(linea.getError()==0){
				panelTabla.tabRevMarv.listMarb.remove(n);
			}
		}
	}
	
	private void agregarAcciones(){
		chckbxSelecAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxSelecAll.isSelected()==true){
					panelTabla.tabRevMarv.selecAll();
				}else{
					panelTabla.tabRevMarv.barridoLineas(0);
				}
			}
		});
		chckbxSelecMultiple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxSelecMultiple.isSelected()==true){
					panelTabla.tabRevMarv.selecMultiple=true;
					btnBorrar.setEnabled(false);
					chckbxSelecAll.setEnabled(true);
				}else{
					panelTabla.tabRevMarv.selecMultiple=false;
					panelTabla.tabRevMarv.barridoLineas(0);
					btnBorrar.setEnabled(true);
					chckbxSelecAll.setSelected(false);
					chckbxSelecAll.setEnabled(false);
				}
			}
		});
		chckbxImpConsentrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxImpConsentrado.isSelected()==true){
					panelTabla.setVisible(false);
					chckbxSelecMultiple.setVisible(false);
					chckbxSelecAll.setVisible(false);
					lblMensajeTitulo.setText("Se va a crear un consentrado de todo el Conteo");
				}else{
					panelTabla.setVisible(true);
					chckbxSelecMultiple.setVisible(true);
					chckbxSelecAll.setVisible(true);
					lblMensajeTitulo.setText(tit);
				}
			}
		});
		btnImpMarb.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxImpConsentrado.isSelected()!=true){
					marbetesEscogidos();
				}else{
					seleccion = (ArrayList<ListaMarbetes>) panelTabla.tabRevMarv.listMarb.clone();					
				}
				if(seleccion.size()>0){
					ImprimirPreInventario nvoPreInv = new ImprimirPreInventario(vtana, vtanaMarb);
					ArrayList<ArrayList<DatosRevision>> contConsent = nvoPreInv.prepararImpresion();
					nvoPreInv.preVisualizacion(vtana.getTitle(), vtanaMarb, contConsent);
					
				}else{
					JOptionPane.showMessageDialog(vtanaMarb, "No has escogido ningun marbete", "Cero Marbetes",
							JOptionPane.INFORMATION_MESSAGE, vtana.information);
				}
			}
		});
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boolean agrArt=false;
				marbetesEscogidos();
				if(seleccion.size()>0){									
					valor = 0;
					if(opcion==3){
						if(vtanaMarb.chckbxSelecMultiple.isSelected()==false){
							agrArt=true;
						}else{
							agrArt=false;
						}
					}
					vtanaRev = new VentanaRevision(vtana, vtanaMarb, titulo, agrArt, opcion);
					if(vtanaRev.valor==true) {
						new GuardarCambios(vtana, vtanaRev, vtanaMarb, opcion);
						if(panelTabla.tabRevMarv.listMarb.size()==0){
							JOptionPane.showMessageDialog(vtanaMarb, "Ya no hay marbetes que revisar", "REVISION DE MARBETES",
									JOptionPane.INFORMATION_MESSAGE, vtana.information);
						}else{
							vtanaMarb.setVisible(true);
						}
						
					}					
				}else{
					JOptionPane.showMessageDialog(vtanaMarb, "No has escogido ningun marbete", "Cero Marbetes",
							JOptionPane.INFORMATION_MESSAGE, vtana.information);
				}
			}
		});
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				marbetesEscogidos();	
				if(seleccion.size()>0){
					int q=JOptionPane.showConfirmDialog(vtanaMarb, "Se va a borrar "+seleccion.size()+" marbetes"+
							" los cuales son:"+mensaje+"\n¿Estas seguro que quieres continuar?","BORRAR MARBETES",
							JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE, vtana.warning);
					if(q==0){
						valor = 2;
						new BorrarMarbetes(vtana, vtanaMarb);
						if(panelTabla.tabRevMarv.listMarb.size()==0){
							JOptionPane.showMessageDialog(vtanaMarb, "Ya no hay marbetes que revisar", "BORRAR MARBETES",
									JOptionPane.INFORMATION_MESSAGE, vtana.information);
							vtanaMarb.setVisible(false);
						}
					}
				}else{
					JOptionPane.showMessageDialog(vtanaMarb, "No has escogido ningun marbete", "Cero Marbetes",
							JOptionPane.INFORMATION_MESSAGE, vtana.information);
				}
			}
		});
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valor = 1;
				vtanaMarb.setVisible(false);;
			}
		});
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marbetesEscogidos();
				mensaje = "";
				if(seleccion.size()>0){
					valor=3;
					switch (opcion) {
					case 1:
						mensaje=" Articulos no encontrados ";
						break;
					case 2:
						mensaje=" Marbetes Duplicados ";
						break;
					case 3:
						mensaje=" Revisar por Marbetes ";
						break;
					default:
						break;
					}
					mensaje=vtana.getTitle()+mensaje+"Reporte de Conteo";
					imprimirtabla = new ImprimirReportes(vtana, vtanaMarb, mensaje);
					for(int n=0;n<panelTabla.tabRevMarv.listMarb.size();n++){
						ListaMarbetes linea=(ListaMarbetes) panelTabla.tabRevMarv.listMarb.get(n);
						linea.setEscojer(false);
					}
					panelTabla.tabRevMarv.fireTableDataChanged();
				}else{
					JOptionPane.showMessageDialog(vtanaMarb, "No has escogido ningun marbete", "Cero Marbetes",
							JOptionPane.INFORMATION_MESSAGE, vtana.information);
				}				
			}
		});
	}
}
