package Scanner.Manual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Inicio.VentanaPrincipal.VentanaPrincipal;
import RevisarArticulos.VentanaRevision.VentanaRevision;
import Scanner.Operaciones.PegarTabla;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class VentanaCargaManual extends JDialog {

	public GenerarScanerManual manual;
	public VentanaPrincipal vtana;
	public TablaCargaManual tablaManual;
	ModeloTablaCargarManual modTablaManual;
	private JPanel panelSup;
	private JPanel panelTabla;
	private JScrollPane scrollTabla;
	private JPanel panelBotones;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel labelNombre;
	private JLabel labelUbicacion;
	public JTextField textNombre;
	public JTextField textUbic;
	private JLabel lblTituloSup1;
	private JLabel lblTituloSup2;
	private JLabel lblMarbete;
	public JTextField textMarbete;
	public Boolean agregar=false;
	private String titulo="";
	private String mensaje="";
	private JButton btnPegar;
	private VentanaCargaManual vtanaMan = this;
	

	public VentanaCargaManual(VentanaPrincipal vtana) {
		super(vtana,"Carga Manual de Articulos",true);
		this.vtana=vtana;
		iniciar();
		setSize(600, 440);
		agregarValores();
		agregarAcciones();
		setLocationRelativeTo(null);//Da la ubicacion de la ventana como es null la centra
		pack();
		setVisible(true);
		
	}
	
	private void iniciar(){		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 439, 317);
		getContentPane().setLayout(new BorderLayout());
		this.addWindowListener(new WindowListener (){//Metodo para confirmar cerrar al presionar "X"
			public void windowClosing(WindowEvent e) {
				titulo="Cancelar conteo";
				mensaje="Deseas cancelar el ingreso manual";
				cerrarVentana();
			}
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosed(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		});
		panelSup=new JPanel();
		panelSup.setSize(210, 120);
		lblTituloSup1 = new JLabel("Ingresa el Nombre del Contador y la");
		lblTituloSup1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloSup1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTituloSup2 = new JLabel("Ubicacion del Conteo");
		lblTituloSup2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloSup2.setFont(new Font("Arial", Font.PLAIN, 12));
		labelNombre = new JLabel("Contador");
		textNombre = new JTextField();
		labelUbicacion = new JLabel("Ubicacion");
		textUbic = new JTextField();
		lblMarbete = new JLabel("No. de Marbete");
		lblMarbete.setFont(new Font("Arial", Font.PLAIN, 12));
		textMarbete = new JTextField();
		panelTabla = new JPanel();
		modTablaManual=new ModeloTablaCargarManual();
		tablaManual=new TablaCargaManual(vtana, modTablaManual);
		scrollTabla=new JScrollPane(tablaManual);
		panelBotones = new JPanel();
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(VentanaCargaManual.class.getResource("/Iconos/16x16/document-save.png")));
		btnCancelar = new JButton("Cancelar");		
		btnCancelar.setIcon(new ImageIcon(VentanaCargaManual.class.getResource("/Iconos/16x16/edit-delete.png")));
	}
	
	private void agregarValores() {		
		getContentPane().add(panelSup, BorderLayout.NORTH);
		panelSup.setLayout(new MigLayout("", "[104.00][141.00]", "[][][][][]"));
		panelSup.add(lblTituloSup1, "cell 0 0 2 1,growx");		
		panelSup.add(lblTituloSup2, "cell 0 1 2 1,growx");
		labelNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		panelSup.add(labelNombre, "cell 0 2");
		panelSup.add(textNombre, "cell 1 2,growx");		
		labelUbicacion.setFont(new Font("Arial", Font.PLAIN, 12));
		panelSup.add(labelUbicacion, "cell 0 3");
		panelSup.add(textUbic, "cell 1 3,growx");		
		panelSup.add(lblMarbete, "cell 0 4");
		panelSup.add(textMarbete, "cell 1 4,growx");
		panelTabla.add(scrollTabla);
		panelTabla.setLayout(new FlowLayout());
		panelTabla.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelTabla, BorderLayout.CENTER);
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		btnPegar = new JButton();
		btnPegar.setToolTipText("Pegar de portapapeles");
		btnPegar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnPegar.setIcon(new ImageIcon(VentanaRevision.class.getResource("/Iconos/16x16/edit-paste.png")));
		
		panelBotones.add(btnPegar);
		panelBotones.add(btnGuardar);
		panelBotones.add(btnCancelar);		
	}
	
	private void agregarAcciones(){
		
		this.btnGuardar.addActionListener(new ActionListener() {//Boton aceptar
			public void actionPerformed(ActionEvent e) {
				if(textNombre.getText().isEmpty() || textUbic.getText().isEmpty() || textMarbete.getText().isEmpty()
						|| modTablaManual.valTot<1){
					mostrarMensaje("No has llenado todos los campos");
				}else{
					if(textMarbete.getText().length() == vtana.tamMarbete && 
							textMarbete.getText().substring(0, vtana.iniMarbete.length()).equals(vtana.iniMarbete)){
						manual=new GenerarScanerManual(vtana, textMarbete.getText(),
								modTablaManual.datos);
						agregar=true;
						titulo="Terminar conteo";
						mensaje="Deseas finalizar la captura del ingreso manual";
						cerrarVentana();
					}else{
						mostrarMensaje("El marbete que ingresaste no coincide con el tipo que se esta usando en este inventario");
					}
					
				}								
			}
		});
		
		this.btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				titulo="Cancelar conteo";
				mensaje="Deseas cancelar la captura del ingreso manual";
				cerrarVentana();				
			}
		});
		
		this.btnPegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PegarTabla(vtanaMan);
			}
		});
	}
	
	private void cerrarVentana(){
		int Y=JOptionPane.showConfirmDialog(this, mensaje, titulo, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
				vtana.question);
		if(Y==0){
			this.setVisible(false);
		}
	}
	
	private void mostrarMensaje(String mensaje){
		JOptionPane.showMessageDialog(vtanaMan, mensaje, "INGRESO MANUAL",
				JOptionPane.INFORMATION_MESSAGE, vtana.information);
	}

}

