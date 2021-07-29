package Inicio.VentanaPrincipal;
/* Programa Inventarios
 * Clase para general la ventana principal
 * del programa
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Configuracion.CambiarConfiguracion;
import Configuracion.CargarConfiguracion;
import Inicio.IniciarInventario;
import Inicio.PreguntaAbierta;
import Inicio.CatalogoArticulos.CargarCatalogo;
import Inicio.CatalogoArticulos.DatosMarbetes;
import Inicio.PreInicio.PreInicio;
import Marbetes.MarbetesInventario.OperacionesMarbetesInventario;
import Scanner.TablaScanner.PanelAgregarScanner;

import java.awt.Font;
import java.awt.CardLayout;
import java.awt.GridLayout;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = -2552083667913258600L;
	PreInicio pre;
	public ArrayList<Long> listCatalogo=new ArrayList<Long>();//Lista del catalogo para comparacion
	public ArrayList<Long> listEan=new ArrayList<Long>();//Lista del catalogo para comparacion
	public ArrayList<String> listDescripcion=new ArrayList<String>();//Lista del catalogo para la Descripcion;
	public ArrayList<ArrayList<String>> listMarbete=new ArrayList<ArrayList<String>>();//Lista de marbetes con ubicacion para el inventario
	public ArrayList<ArrayList<String>> listMarbeteContado=new ArrayList<ArrayList<String>>();
	public ArrayList<DatosMarbetes> datMarbete=new ArrayList<DatosMarbetes>();//Contiene nombre y ubicacion del conteo
	public ArrayList<String> listscanners=new ArrayList<String>();//Carga el consentrado del conteo por scanner
	public IniciarInventario nvoinv;
	JPanel panelTrabajo;//Panel central
	public MenuPrincipal menu;
	public PanelBotones botones;
	public JPanel panelTabla;//Panel donde va a incluir el panel de Marbetes o Scanner
	public JPanel panelExtra;//Panel Cantidad de Marbetes
	public PanelAgregarScanner panelScanner;//Panel donde de muestra los articulos ingresados
	public JLabel mensajeEstado;
	public Boolean iniMarb=false;
	public OperacionesMarbetesInventario operInv;
	JPanel estado;
	Boolean seguirmismo=false;
	VentanaPrincipal vtana=this;
	public PanelAvance panelAvance;//Panel de avance del Inventario
	public int numreg=0;//Cuenta los cambios o alteraciones hechas al inventario	
	public String dirCatalogo;
	public String dirScanner;
	public String dirInventario;
	public String iniMarbete;//Es el inicio del marbete que se usa en el inventario
	public short tamMarbete;//Cantidad de caracteres que usa el Marbete
	private Boolean errorCargar;
	public static String titulo="Inventarios V 0.8";
	public int totArt = 0;
	public int artAd = 0;
	public int artBorr = 0;
	public int granTot = 0; 
	private JPanel panelDerecho;
	public ImageIcon error = new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/48x48/dialog-error.png"));
	public ImageIcon information = new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/48x48/dialog-information.png"));
	public ImageIcon notification = new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/48x48/dialog-notification.png"));
	public ImageIcon warning = new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/48x48/dialog-warning.png"));
	public ImageIcon question = new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/48x48/dialog-question.png"));
	public ImageIcon cancelar = new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/48x48/dialog-cancel.png"));
	
	public VentanaPrincipal(){
		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Iconos/Inventario.jpg")));
		this.addWindowListener(new WindowListener (){//Metodo para confirmar cerrar al presionar "X"
			public void windowClosing(WindowEvent e) {
				confirmarCerrar();
			}
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosed(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		});
		new CargarConfiguracion(this);
		do{
			errorCargar=true;
			if(cargarCatalogo()==true){
				agregarElementos();
				inicio();
				setVisible(true);
				preInicio();
				errorCargar=false;
			}else{
				int Q=JOptionPane.showConfirmDialog(this, "No se encontro el catalogo de articulos no se puede realizar el inventario"+
					"\n¿Deseas cambiar la configuracion del programa para agregar un nuevo Catalogo?\n\nNOTA: Si la opcion es NO el"+
					" programa se va a cerrar.",
					"FALTA EL CATALOGO DE ARTICULOS", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, question);
				if(Q==0){
					new CambiarConfiguracion(this);
				}else{
					System.exit(0);
				}
			}
		}while(errorCargar==true);
	}
	
	private Boolean cargarCatalogo(){
		CargarCatalogo catalogo=new CargarCatalogo(dirCatalogo);
		listCatalogo=catalogo.listCatalogo;
		listDescripcion=catalogo.listDescripcion;
		listEan=catalogo.listEan;
		return catalogo.agregado;
	}
	
	private void preInicio(){
		pre=new PreInicio();
		if(pre.preInicio(dirInventario)==true){
			PreguntaAbierta arch=new PreguntaAbierta(this, "<html><body>"+pre.mensaje+" ¿deseas seguir con este "+
					"o quieres iniciar uno nuevo?", "Ya existe un Inventario", "Continuar con el que ya existe",
					"Empezar un nuevo Inventario");
			switch(arch.valor){
			case 0://Inicio valor cero
				menu.nuevo.setEnabled(false);
				botones.btnNvoInvent.setEnabled(false);
				menu.configuracion.setEnabled(false);
				botones.btnConfig.setEnabled(false);
				pre.cargarPreInicio(this);
				break;//Fin valor dos
			case 1://Inicio Valor Uno
				if (pre.consMarb==true){
					pre.archconsMarb.delete();
				}
				if(pre.cons==true){
					pre.archcons.delete();
				}
				if(pre.regis==true){
					pre.archReg.delete();
				}
				menu.nuevo.setEnabled(true);
				botones.btnNvoInvent.setEnabled(true);
				menu.configuracion.setEnabled(true);
				botones.btnConfig.setEnabled(true);
				break;//Fin de valor Uno
			default://Si no coincide ningun valor
				JOptionPane.showMessageDialog(this, "No se selecciono ninguna opcion el programa se va a cerrar","Error al iniciar el programa",
						JOptionPane.INFORMATION_MESSAGE, information);
				System.exit(0);
			}//Fin de Switch
		}		
	}
	
	private void inicio(){
		setTitle(titulo);
		setSize(950,600);		
		setLocationRelativeTo(null);//Da la ubicacion de la ventana como es null la centra
	}

	private void agregarElementos(){
		Dimension dpanelCentral = new Dimension(950, 500);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panelSuperior = new JPanel();
		menu = new MenuPrincipal(this);
		setJMenuBar(menu);
		botones = new PanelBotones(this);
		panelSuperior.setLayout(new GridLayout(1, 0));
		panelSuperior.add(botones);
		getContentPane().add(panelSuperior, BorderLayout.NORTH);		
		panelTrabajo = new JPanel();
		panelTrabajo.setSize(dpanelCentral);
		panelTrabajo.setLayout(new BorderLayout(0, 0));
		panelTabla = new JPanel();
		panelTrabajo.add(panelTabla, BorderLayout.CENTER);
		estado=new JPanel();
		getContentPane().add(estado, BorderLayout.SOUTH);
		estado.setLayout(new CardLayout(0, 0));		
		mensajeEstado = new JLabel("");
		estado.add(mensajeEstado, "name_7352179067897");
		mensajeEstado.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		mensajeEstado.setHorizontalAlignment(SwingConstants.LEFT);
		
		panelDerecho = new JPanel();
		panelDerecho.setLayout(new BorderLayout(0, 0));
		
		panelAvance = new PanelAvance();
		panelDerecho.add(panelAvance, BorderLayout.SOUTH);
				
		panelTrabajo.add(panelDerecho, BorderLayout.EAST);
		panelExtra = new JPanel();
		panelDerecho.add(panelExtra, BorderLayout.NORTH);
		panelExtra.setSize(300, 500);
		JScrollPane panelDeslizable = new JScrollPane();
		panelDeslizable.setPreferredSize(dpanelCentral);
		panelDeslizable.setViewportView(panelTrabajo);
		getContentPane().add(panelDeslizable, BorderLayout.CENTER);
		
								
	}
	
	
	
	void confirmarCerrar(){//Metodo para cerrar la ventana
		String mensaje = mensajeEstado.getText();
		mensajeEstado.setText("Cerrando Aplicacion");
		int Y=JOptionPane.showConfirmDialog(this, "Deseas cerrar la Aplicacion","Salir del Inventario",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, question);
		if(Y==0){
			System.exit(0);
		}else{
			mensajeEstado.setText(mensaje);
		}
	}
}
