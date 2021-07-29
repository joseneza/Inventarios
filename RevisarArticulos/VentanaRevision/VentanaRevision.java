package RevisarArticulos.VentanaRevision;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Inicio.CatalogoArticulos.DatosMarbetes;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import RevisarArticulos.Imprimir.ImprimirReportes;
import RevisarArticulos.VentanaMarbetes.ListaMarbetes;
import RevisarArticulos.VentanaMarbetes.VentanaMarbete;
import Scanner.DatosScanners;
import Scanner.Operaciones.PegarTabla;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class VentanaRevision extends JDialog {

	private static final long serialVersionUID = -4966730490078500947L;
	public VentanaPrincipal vtana;
	VentanaMarbete vtanaMarb;
	String tituloV;
	public TablaRev tablaRev;
	ArrayList<DatosScanners> datScan;
	ArrayList<DatosMarbetes> datMarbete;
	JPanel panelBotones;
	JButton btnGuardar;
	JButton btnCancelar;
	public Boolean valor = false;
	String titulo;
	String msj;
	private JLabel mensaje;
	public ArrayList<ListaMarbetes> seleccion;
	private JPanel panelInferior;
	private JPanel panelTitulo;
	private JCheckBox chckbxBorrar;
	private JCheckBox chckbxAgregar;
	private VentanaRevision vtanaRev=this;
	private JPanel panelCentral;
	private JScrollPane panelTabla;
	private ModeloTablaRevision modRev;
	int tipo;
	private JButton btnImprimir;
	private JPanel panelAgregarQuitar;
	private JButton btnAgregarLinea;
	public int nvaLinea=0;
	public String lineaUno=null;
	private JButton btnCopiarNEncontrados;
	private JButton btnActCatalogo;
	private JButton btnPegarPortapapeles;

	public VentanaRevision(VentanaPrincipal vtana, VentanaMarbete vtanaMarb, String tituloV, Boolean agrArt,int tipo) {		
		super(vtana, true);
		this.vtana=vtana;
		this.vtanaMarb=vtanaMarb;
		setTitle(tituloV);
		this.tituloV=tituloV;
		this.datScan = vtana.panelScanner.tablaScanners.nvomodelo.datScan;
		this.datMarbete = vtana.datMarbete;
		this.seleccion = vtanaMarb.seleccion;
		this.tipo=tipo;
		iniciar();
		agregar(agrArt);
		agregarDatos();
		acciones();
		setSize(970, 400);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {
				msj="Deseas salir sin guardar";
				titulo="No encontrados - Salir";
				confirmarCerrar();
			}
			public void windowClosed(WindowEvent arg0) {}
			public void windowActivated(WindowEvent arg0) {}
		});
		setLocationRelativeTo(null);//Da la ubicacion de la ventana como es null la centra
		vtanaMarb.setVisible(false);
		setVisible(true);		
	}
	
	private void iniciar(){	
		modRev = new ModeloTablaRevision();
		tablaRev = new TablaRev(modRev, tipo, this);
		panelTabla = new JScrollPane(tablaRev);
	}
	
	private void agregar(Boolean agregarArt){
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 620, 300);
		panelCentral = new JPanel();
		panelCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelCentral.setLayout(new FlowLayout());
		panelCentral.add(panelTabla);
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		
		panelInferior = new JPanel();
		getContentPane().add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new BorderLayout(0, 0));
		
		panelAgregarQuitar = new JPanel();
		panelInferior.add(panelAgregarQuitar, BorderLayout.NORTH);
		
		chckbxAgregar = new JCheckBox("Agregar todos los articulos");
		panelAgregarQuitar.add(chckbxAgregar);
		chckbxAgregar.setFont(new Font("Arial", Font.PLAIN, 11));
		
		chckbxBorrar = new JCheckBox("Borrar todos los articulos");
		panelAgregarQuitar.add(chckbxBorrar);
		chckbxBorrar.setFont(new Font("Arial", Font.PLAIN, 11));
		
		btnAgregarLinea = new JButton("");
		btnAgregarLinea.setToolTipText("Agregar Linea");
		btnAgregarLinea.setIcon(new ImageIcon(VentanaRevision.class.getResource("/Iconos/16x16/list-add.png")));
		panelAgregarQuitar.add(btnAgregarLinea);
		btnAgregarLinea.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAgregarLinea.setVisible(agregarArt);
		btnAgregarLinea.setEnabled(agregarArt);
		
		btnPegarPortapapeles = new JButton("");
		btnPegarPortapapeles.setToolTipText("Pegar de Portapapeles");
		btnPegarPortapapeles.setFont(new Font("Arial", Font.PLAIN, 11));
		btnPegarPortapapeles.setIcon(new ImageIcon(VentanaRevision.class.getResource("/Iconos/16x16/edit-paste.png")));
		panelAgregarQuitar.add(btnPegarPortapapeles);
		btnPegarPortapapeles.setVisible(agregarArt);
		btnPegarPortapapeles.setEnabled(agregarArt);
		
		btnCopiarNEncontrados = new JButton("Copiar No Encontrados");
		btnCopiarNEncontrados.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCopiarNEncontrados.setToolTipText("Copiar al portapapeles");
		btnCopiarNEncontrados.setIcon(new ImageIcon(VentanaRevision.class.getResource("/Iconos/16x16/edit-copy.png")));		
		panelAgregarQuitar.add(btnCopiarNEncontrados);
		
		btnActCatalogo = new JButton("Actualizar Catalogo");
		btnActCatalogo.setFont(new Font("Arial", Font.PLAIN, 11));
		btnActCatalogo.setToolTipText("Actializar desde un archivo .csv");
		panelAgregarQuitar.add(btnActCatalogo);
		
		panelBotones = new JPanel();
		
		panelInferior.add(panelBotones, BorderLayout.SOUTH);
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(VentanaRevision.class.getResource("/Iconos/16x16/document-save.png")));
		btnGuardar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCancelar = new JButton("Cancelar");		
		btnCancelar.setIcon(new ImageIcon(VentanaRevision.class.getResource("/Iconos/16x16/edit-delete.png")));
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 11));
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));		
		
		btnImprimir = new JButton("Imprimir Reporte");
		btnImprimir.setIcon(new ImageIcon(VentanaRevision.class.getResource("/Iconos/16x16/printer.png")));
		btnImprimir.setFont(new Font("Arial", Font.PLAIN, 11));
		panelBotones.add(btnImprimir);
		
		getRootPane().setDefaultButton(btnGuardar);
		panelBotones.add(btnGuardar);
		panelBotones.add(btnCancelar);
		btnGuardar.setActionCommand("Aceptar");
		btnCancelar.setActionCommand("Cancelar");
		
		panelTitulo = new JPanel();
		getContentPane().add(panelTitulo, BorderLayout.NORTH);
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		mensaje = new JLabel("Confirma, borra o modifica los articulos que el programa no identifico");
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitulo.add(mensaje);
		mensaje.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
	}
	
	private void agregarDatos(){
		Iterator<DatosScanners> leerDatScan = datScan.iterator();// Recorremos el ArrayList con un Iterator pare leer todos los datos
		while(leerDatScan.hasNext()){
			DatosScanners linea = leerDatScan.next();//Se lee por linea los datos del escaner
			Iterator<ListaMarbetes> leerSelec = seleccion.iterator();
			while(leerSelec.hasNext()){
				ListaMarbetes linSelec = leerSelec.next();//Se lee por linea la seleccion de marbetes
				if(linSelec.getScan().equals(linea.getScan()) & linSelec.getMarbete().equals(linea.getMarbete())){//Confirma la seleccion de marbetes
					switch (tipo) {
					case 1:
						if(linea.getExiste()==false){
							agregarLinea(linea);
						}
						break;
					case 2:
						agregarLinea(linea);
						break;
					case 3:
						agregarLinea(linea);
						break;
					default:
						break;
					}
					
				}
			}
		}
		//Se captura la primera linea del marbete por si se agregan articulos
		lineaUno=tablaRev.modRev.datRev.get(0).getFila();
	}
		
	private void acciones(){
		this.chckbxAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxAgregar.isSelected()==true){
					int Y=JOptionPane.showConfirmDialog(vtanaRev, "¿Deseas agregar todos los articulos?", "CONFIRMAR",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, vtana.question);
					if(Y==0){
						chckbxBorrar.setSelected(false);
						for(int n=0;n<tablaRev.modRev.datRev.size();n++){
							DatosRevision linea = (DatosRevision) tablaRev.modRev.datRev.get(n);
							linea.setAgregar(true);
							linea.setBorrar(false);
							linea.setCambiar(false);
						}
						tablaRev.modRev.fireTableDataChanged();
					}else{
						chckbxAgregar.setSelected(false);
					}
				}else{
					for(int n=0;n<tablaRev.modRev.datRev.size();n++){
						DatosRevision linea = (DatosRevision) tablaRev.modRev.datRev.get(n);
						linea.setAgregar(false);
					}
					tablaRev.modRev.fireTableDataChanged();
				}
			}
		});
		this.chckbxBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxBorrar.isSelected()==true){
					int Y=JOptionPane.showConfirmDialog(vtanaRev, "¿Deseas borrar todos los articulos?", "CONFIRMAR",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, vtana.question);
					if(Y==0){
						chckbxAgregar.setSelected(false);
						for(int n=0;n<tablaRev.modRev.datRev.size();n++){
							DatosRevision linea = (DatosRevision) tablaRev.modRev.datRev.get(n);
							linea.setAgregar(false);
							linea.setBorrar(true);
							linea.setCambiar(false);
						}
					}else{
						chckbxBorrar.setSelected(false);
					}
					tablaRev.modRev.fireTableDataChanged();
				}else{
					for(int n=0;n<tablaRev.modRev.datRev.size();n++){
						DatosRevision linea = (DatosRevision) tablaRev.modRev.datRev.get(n);
						linea.setBorrar(false);
					}
					tablaRev.modRev.fireTableDataChanged();
				}
			}
		});
		this.btnAgregarLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nvaLinea==0){
					lineaUno=tablaRev.modRev.datRev.get(0).getFila();
				}
				tablaRev.modRev.nvoArt("nvo", tablaRev.modRev.datRev.get(0).getScan(), tablaRev.modRev.datRev.get(0).getConsecutivo(),
						tablaRev.modRev.datRev.get(0).getContador(), tablaRev.modRev.datRev.get(0).getUbic(),
						tablaRev.modRev.datRev.get(0).getMarb(), 0, (short) 0, "");
				nvaLinea++;
				tablaRev.changeSelection(tablaRev.modRev.datRev.size()-1, 5, false, false);
			}
		});
		this.btnPegarPortapapeles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PegarTabla(vtanaRev);
			}
		});
		this.btnCopiarNEncontrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CopiarNoEncontrados(tablaRev.modRev.datRev);
			}
		});
		this.btnActCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ActializarCatalogo(vtana, vtanaRev);			
			}
		});
		this.btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterator <DatosRevision> posRev=vtanaRev.tablaRev.modRev.datRev.iterator();
				Boolean cambio = true;
				int Y=-1;
				while(posRev.hasNext()){
				DatosRevision linea = posRev.next();
					if(linea.getBorrar()==false & linea.getAgregar()==false & linea.getCambiar()==false){
						cambio = false;
					}
				}
				if(cambio==false){
					Y = JOptionPane.showConfirmDialog(vtanaRev, "No se han marcado todos los articulos para confirmarlos, borrarlos o cambiarlos, "
							+ "Si continuas a esos articulos no se les va a hacer ningun cambio.", "Articulos sin Revisar", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, vtana.question);
				}else{
					Y=0;
				}if(Y==0){
					msj="Deseas salir y guardar los cambios";
					titulo=tituloV + " - Guardar y Salir";
					valor = true;
					confirmarCerrar();
				}
			}
		});
		this.btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ImprimirReportes(vtana, vtanaMarb, vtana.getTitle()+" - "+tituloV);				
			}
		});
		this.btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msj="Deseas salir sin guardar";
				titulo=tituloV + " - Salir";
				confirmarCerrar();
			}
		});
	}
	
	private void agregarLinea(DatosScanners linea){
		String cont="N/A";
		String ubic="N/A";
		Iterator<DatosMarbetes> revMarbete = datMarbete.iterator();
		while(revMarbete.hasNext()){
			DatosMarbetes linMarbete = revMarbete.next();
			if(linMarbete.getScan().equals(linea.getScan())){
				cont=linMarbete.getNombre();
				ubic=linMarbete.getUbic();
			}
		}
		int lin=vtana.listCatalogo.indexOf(linea.getArt());
		String descrip;
		if(lin>=0){
			descrip=vtana.listDescripcion.get(lin);
		}else{
			descrip=linea.getDescripcion();
		}
		tablaRev.modRev.addFila(Integer.toString(linea.getFila()), linea.getScan(), linea.getConsecutivo(), cont, ubic, linea.getMarbete(),
				linea.getArt(), linea.getCant(), descrip, linea.getExiste(), linea.getExiste());
	}
	
	private void confirmarCerrar(){
		int y = JOptionPane.showConfirmDialog(this, msj, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, vtana.question);
		if(y == 0){
			setVisible(false);
		}
	}

}

