package Marbetes;
/* Programa Inventarios
 * Clase que crea una ventana para el nombre del inventario
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Inicio.VentanaPrincipal.VentanaPrincipal;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioNombreInv extends JDialog {
	private JPanel panelNombre;
	private JPanel botonPanel;
	private JLabel mensajeTitulo;
	public JTextField textoTitulo;
	JButton aceptar;
	JButton cancel;
	public int valor=-1;
	VentanaPrincipal vtana;
	String mensajePrevio;
	
	public InicioNombreInv(VentanaPrincipal vtana){
		super(vtana,"TITULO DEL INVENTARIO",true);
		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.vtana=vtana;
		iniciarComponentes();
		agregarComponentes();
		agregarEventos();	
		setBounds(100, 100, 312, 138);
		setLocationRelativeTo(null);//Da la ubicacion de la ventana como es null la centra
		setVisible(true);
		
	}
	
	private void iniciarComponentes(){
		panelNombre=new JPanel();		
		mensajeTitulo = new JLabel("Ingresa en nombre que va a llevar el inventario");		
		mensajeTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		mensajeTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		textoTitulo = new JTextField();
		textoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		textoTitulo.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		textoTitulo.setToolTipText("");
		botonPanel= new JPanel();
		aceptar = new JButton("Aceptar");
		aceptar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		aceptar.setActionCommand("Aceptar");		
		cancel = new JButton("Cancelar");
		cancel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		cancel.setActionCommand("Cancelar");
		mensajePrevio=vtana.mensajeEstado.getText();
	}
	
	private void agregarComponentes(){
		getContentPane().setLayout(new BorderLayout());
		panelNombre.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelNombre.setLayout(new GridLayout(2, 0));
		panelNombre.add(mensajeTitulo);
		panelNombre.add(textoTitulo);
		getContentPane().add(panelNombre, BorderLayout.CENTER);	
		botonPanel.add(aceptar);
		getRootPane().setDefaultButton(aceptar);
		botonPanel.add(cancel);
		botonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(botonPanel, BorderLayout.SOUTH);
	}
	
	private void agregarEventos(){
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textoTitulo.getText().isEmpty()){
					vtana.mensajeEstado.setText(mensajePrevio + " - No has agregado ningun titulo");
				}else{
					vtana.mensajeEstado.setText("");
					valor=0;
					setVisible(false);					
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vtana.mensajeEstado.setText("");
				valor=1;
				setVisible(false);
			}
		});
	}

}
