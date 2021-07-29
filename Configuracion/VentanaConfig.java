package Configuracion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import Inicio.VentanaPrincipal.VentanaPrincipal;
import Scanner.TablaScanner.PanelAgregarScanner;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class VentanaConfig extends JDialog {

	private static final long serialVersionUID = 1L;
	int valor=1;
	private JPanel panelCentral;
	
	private JLabel labelCatalogo;
	private JButton btnCatalogo;
	public JTextField textCatalogo;
	
	private JLabel labelScaner;
	private JButton btnScanner;
	public JTextField textScanner;
	
	private JLabel labelInventario;
	private JButton btnInventario;
	public JTextField textInventario;
	
	JTextField textRegCambios;
	
	private JPanel panelBotones;
	private JButton guardar;
	private JButton salir;
	private JLabel lblConfigTipoDeMarb;
	JTextField textInicia;
	JRadioButton rdbtnNueves;
	JRadioButton rdbtnOcho;
	JRadioButton rdbtnPersonalizado;
	public String marbetes;
	private JLabel lblInicia;
	private JLabel lblTotCar;
	JTextField textTotCar;
	
	String catalogo;
	String scanner;
	String inventario;
	String tipoMarb;
	String marb="";
	String caract="";
	VentanaConfig vConfig=this;
	ImageIcon question = new ImageIcon(PanelAgregarScanner.class.getResource("/Iconos/48x48/dialog-question.png"));
	
	
	
	public VentanaConfig(VentanaPrincipal vtana, String catalogo, String scanner, String inventario, String tipoMarb) {
		super(vtana, true);
		this.catalogo=catalogo;
		this.scanner=scanner;
		this.inventario=inventario;
		this.tipoMarb=tipoMarb;
		setTitle("Configuracion");
		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		setBounds(100, 100, 450, 300);
		setSize(440, 347);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
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
		iniciarElementos();
		agregarElementos();
		agregarAcciones();
		setLocationRelativeTo(null);//Da la ubicacion de la ventana como es null la centra
		setVisible(true);
		
	}
	
	private void iniciarElementos(){
		
		panelCentral = new JPanel();
		
		labelCatalogo = new JLabel("Direccion del Catalogo");
		labelCatalogo.setBounds(5, 5, 150, 20);
		labelCatalogo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));		
		textCatalogo = new JTextField(catalogo);
		textCatalogo.setBounds(5, 25, 360, 20);
		textCatalogo.setFont(new Font("Arial", Font.PLAIN, 12));
		textCatalogo.setColumns(10);		
		btnCatalogo = new JButton("...");
		btnCatalogo.setBounds(370, 25, 45, 20);
		btnCatalogo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		
		labelScaner = new JLabel("Direccion del Scanner");
		labelScaner.setBounds(5, 45, 150, 20);
		labelScaner.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));		
		textScanner = new JTextField(scanner);
		textScanner.setBounds(5, 65, 360, 20);
		textScanner.setFont(new Font("Arial", Font.PLAIN, 12));
		textScanner.setColumns(10);		
		btnScanner = new JButton("...");
		btnScanner.setBounds(370, 65, 45, 20);
		btnScanner.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		
		labelInventario = new JLabel("<html><body>Direccion de Archivos del Inventario (Consentrado, Registro y Marbetes)");
		labelInventario.setHorizontalAlignment(SwingConstants.LEFT);
		labelInventario.setBounds(5, 85, 350, 40);
		labelInventario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));		
		textInventario = new JTextField(inventario);
		textInventario.setBounds(5, 125, 360, 20);
		textInventario.setFont(new Font("Arial", Font.PLAIN, 12));
		textInventario.setColumns(10);		
		btnInventario = new JButton("...");
		btnInventario.setBounds(370, 125, 45, 20);
		btnInventario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		
		lblConfigTipoDeMarb = new JLabel("Configurar tipo de Marbetes");
		lblConfigTipoDeMarb.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblConfigTipoDeMarb.setBounds(5, 5, 175, 20);
		
		rdbtnNueves = new JRadioButton("99990000 Inicia con 4 nueves y 8 caracteres en total");
		rdbtnNueves.setBounds(10, 25, 291, 20);		
		rdbtnNueves.setSelected(true);
		rdbtnNueves.setFont(new Font("Arial", Font.PLAIN, 11));
		rdbtnOcho = new JRadioButton("888800000 Inicia con 4 ochos y 9 caracteres en total");
		rdbtnOcho.setBounds(10, 45, 291, 20);		
		rdbtnOcho.setFont(new Font("Arial", Font.PLAIN, 11));
		rdbtnPersonalizado = new JRadioButton("Personalizado");
		rdbtnPersonalizado.setBounds(10, 65, 93, 23);
		rdbtnPersonalizado.setFont(new Font("Arial", Font.PLAIN, 11));
		lblInicia = new JLabel("Inicia");
		lblInicia.setFont(new Font("Arial", Font.PLAIN, 11));
		lblInicia.setEnabled(false);		
		lblInicia.setBounds(15, 90, 25, 20);
		textInicia = new JTextField();
		textInicia.addKeyListener(new KeyAdapter() {//Limitar el Texto a 8 Caracteres
			public void keyTyped(KeyEvent e){
				if (textInicia.getText().length()== 8)e.consume();				
			}			
		});
		textInicia.setBounds(50, 90, 80, 20);
		textInicia.setColumns(10);
		textInicia.setEnabled(false);		
		lblTotCar = new JLabel("Total de Caracteres (Max 13)");
		lblTotCar.setBounds(135, 90, 150, 20);
		lblTotCar.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTotCar.setEnabled(false);		
		textTotCar = new JTextField();
		textTotCar.addKeyListener(new KeyAdapter() {//Limitar el Texto a 2 Caracteres
			public void keyTyped(KeyEvent e){
				char caracter=e.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE))e.consume();// ignorar el evento de teclado
				if (textTotCar.getText().length()== 2)e.consume();				
			}			
		});
		textTotCar.setFont(new Font("Arial", Font.PLAIN, 11));
		textTotCar.setColumns(10);
		textTotCar.setEnabled(false);		
		textTotCar.setBounds(285, 90, 30, 20);
		textInicia.setFont(new Font("Arial", Font.PLAIN, 11));	
		
		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
		guardar = new JButton("Guardar");
		guardar.setIcon(new ImageIcon(VentanaConfig.class.getResource("/Iconos/16x16/document-save.png")));
		guardar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		guardar.setActionCommand("Aceptar");
		salir = new JButton("Salir");
		salir.setIcon(new ImageIcon(VentanaConfig.class.getResource("/Iconos/16x16/exit.png")));
		salir.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		salir.setActionCommand("Cancelar");
		
	}
	
	private void agregarElementos(){
		
		getContentPane().setLayout(new BorderLayout());
		panelCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		
		panelCentral.setLayout(null);
		panelCentral.add(labelScaner);
		panelCentral.add(textScanner);
		panelCentral.add(btnScanner);
		panelCentral.add(labelInventario);
		panelCentral.add(textInventario);
		panelCentral.add(btnInventario);
		panelCentral.add(textCatalogo);
		panelCentral.add(btnCatalogo);
		panelCentral.add(labelCatalogo);
		
		JPanel panelConfigMarb = new JPanel();
		panelConfigMarb.setBounds(5, 150, 409, 121);		
		panelConfigMarb.setLayout(null);
		panelConfigMarb.add(lblConfigTipoDeMarb);	
		panelConfigMarb.add(rdbtnNueves);
		panelConfigMarb.add(rdbtnOcho);
		panelConfigMarb.add(rdbtnPersonalizado);
		panelConfigMarb.add(lblInicia);		
		panelConfigMarb.add(textInicia);		
		panelConfigMarb.add(lblTotCar);		
		panelConfigMarb.add(textTotCar);		
		panelCentral.add(panelConfigMarb);
		tipoDMarbete();
		getContentPane().add(panelBotones, BorderLayout.SOUTH);		
		panelBotones.add(guardar);
		getRootPane().setDefaultButton(guardar);		
		panelBotones.add(salir);
		
	}
	
	private void tipoDMarbete(){
		Boolean esmarb=true;
		for(int n=0; n<tipoMarb.length(); n++){			
			String c=tipoMarb.substring(n, n+1);
			if(c.equals(",")){
				esmarb=false;
			}else{
				if(esmarb==true){
					marb=marb+c;
				}else{
					caract=caract+c;
				}				
			}
		}
		if(marb.equals("9999") & caract.equals("8")){
			rdbtnNueves.setSelected(true);
			rdbtnOcho.setSelected(false);
			rdbtnPersonalizado.setSelected(false);
		}else{
			if(marb.equals("8888") & caract.equals("9")){
				rdbtnNueves.setSelected(false);
				rdbtnOcho.setSelected(true);
				rdbtnPersonalizado.setSelected(false);
			}else{
				rdbtnNueves.setSelected(false);
				rdbtnOcho.setSelected(false);
				rdbtnPersonalizado.setSelected(true);
				lblInicia.setEnabled(true);
				textInicia.setEnabled(true);
				textInicia.setText(marb);
				lblTotCar.setEnabled(true);
				textTotCar.setEnabled(true);
				textTotCar.setText(caract);
			}
		}
	}
	
	private void confirmarCerrar(){
		int Y=JOptionPane.showConfirmDialog(this, "Deseas cerrar la ventana de configuracion.","Salir de Configuracion",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, question);
		if(Y==0){
			this.setVisible(false);
			valor=1;
		}
	}
	
	private void agregarAcciones(){
		this.guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int Y=JOptionPane.showConfirmDialog(vConfig, "Deseas terminar y guardar la configuracion.","Guardar de Configuracion",
						JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, question);
				if(Y==0){
					valor=0;
					if(rdbtnNueves.isSelected()){
						tipoMarb="9999,8";
					}else{
						if(rdbtnOcho.isSelected()){
							tipoMarb="8888,9";
						}else{
							if(rdbtnPersonalizado.isSelected()){
								try{
									if(Integer.parseInt(textTotCar.getText())>13){
										textTotCar.setText("13");
									}
								}catch(NumberFormatException e){}
								tipoMarb=textInicia.getText()+","+textTotCar.getText();
							}
						}
					}
					System.out.println("VentanaConfig caractes Marbete "+tipoMarb);
				}
				vConfig.setVisible(false);
			}
		});
		this.salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmarCerrar();
			}
		});
		this.btnCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EscojerArchivo arch=new EscojerArchivo(vConfig);
				String nvaDir=arch.abrirScanner("Direccion del Catalogo de Articulos", textCatalogo.getText(),
						"Archivo csv", 1);
				textCatalogo.setText(nvaDir);				
			}
		});
		this.btnScanner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EscojerArchivo arch=new EscojerArchivo(vConfig);
				String nvaDir=arch.abrirScanner("Direccion del Scanner", textCatalogo.getText(),
						"Archivo de Scaner", 2);
				textScanner.setText(nvaDir);
			}
		});
		this.btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EscojerArchivo arch=new EscojerArchivo(vConfig);
				String nvaDir=arch.abrirScanner("Direccion del Inventario", textInventario.getText(),
						"Directorio", 3);
				textInventario.setText(nvaDir);
			}
		});		
		this.rdbtnNueves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnNueves.setSelected(true);
				rdbtnOcho.setSelected(false);
				rdbtnPersonalizado.setSelected(false);
				lblInicia.setEnabled(false);
				textInicia.setEnabled(false);
				textInicia.setText("");
				lblTotCar.setEnabled(false);
				textTotCar.setEnabled(false);
				textTotCar.setText("");
				
			}
		});
		this.rdbtnOcho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnNueves.setSelected(false);
				rdbtnOcho.setSelected(true);
				rdbtnPersonalizado.setSelected(false);
				lblInicia.setEnabled(false);
				textInicia.setEnabled(false);
				textInicia.setText("");
				lblTotCar.setEnabled(false);
				textTotCar.setEnabled(false);
				textTotCar.setText("");
			}
		});
		this.rdbtnPersonalizado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnNueves.setSelected(false);
				rdbtnOcho.setSelected(false);
				rdbtnPersonalizado.setSelected(true);
				lblInicia.setEnabled(true);
				textInicia.setEnabled(true);
				textInicia.setText(marb);
				lblTotCar.setEnabled(true);
				textTotCar.setEnabled(true);
				textTotCar.setText(caract);
			}
		});
	}
	
}
