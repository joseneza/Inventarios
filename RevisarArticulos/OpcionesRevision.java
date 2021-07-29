package RevisarArticulos;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;

import Inicio.VentanaPrincipal.VentanaPrincipal;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpcionesRevision extends JDialog {

	VentanaPrincipal vtana;
	JButton botonNoencontrado;
	JButton botonMarbetesDupli;
	JButton botonMarbetes;
	JButton botonSalir;
	public int valor = 0;
	
	public OpcionesRevision(VentanaPrincipal vtana) {
		super(vtana, true);
		this.vtana=vtana;
		iniciar();
		agregar();
		acciones();
		setLocationRelativeTo(null);//Da la ubicacion de la ventana como es null la centra
		setVisible(true);
	}
	
	private void iniciar(){
		setTitle("Revisar Articulos");
		setBounds(100, 100, 280, 160);
		getContentPane().setLayout(new GridLayout(4, 0));
		botonNoencontrado = new JButton("Articulos no encontrados");
		botonNoencontrado.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));		
		botonMarbetesDupli = new JButton("Marbetes Duplicados");
		botonMarbetesDupli.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));		
		botonMarbetes = new JButton("Ver Marbetes");
		botonMarbetes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));		
		botonSalir = new JButton("Salir");
		botonSalir.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));		
	}
	
	private void agregar(){
		getContentPane().add(botonNoencontrado);
		getContentPane().add(botonMarbetesDupli);
		getContentPane().add(botonMarbetes);
		getContentPane().add(botonSalir);
	}
	
	private void acciones(){
		this.botonNoencontrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valor = 1;
				setVisible(false);
			}
		});
		
		this.botonMarbetesDupli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valor = 2;
				setVisible(false);			
			}
		});
		
		this.botonMarbetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valor = 3;
				setVisible(false);
				
			}
		});
		
		this.botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valor = 0;
				setVisible(false);
			}
		});
	}
}
