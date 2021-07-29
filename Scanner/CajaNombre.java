package Scanner;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Inicio.VentanaPrincipal.VentanaPrincipal;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CajaNombre extends JDialog{
	public Boolean valor=false;
	public JTextField tnombre;
	public JTextField tubicacion;
	private JButton aceptar;
	private JButton cancelar;
	VentanaPrincipal vtana;

	public CajaNombre(VentanaPrincipal vtana) {
		super(vtana);
		this.vtana = vtana;
		iniciar();		
		setLocationRelativeTo(null);//Centra el JDialog
		agregarAccion();
		setVisible(true);
	}

	private void iniciar() {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Datos del Scanner");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 255, 140);
		getContentPane().setLayout(null);
		
		JLabel indicacion = new JLabel("<html><body>Ingresa Nombre del Contador y Ubicacion del Scanner");
		indicacion.setFont(new Font("Arial", Font.BOLD, 11));
		indicacion.setHorizontalAlignment(SwingConstants.CENTER);
		indicacion.setBounds(0, 1, 248, 24);
		getContentPane().add(indicacion);
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		nombre.setHorizontalAlignment(SwingConstants.LEFT);
		nombre.setBounds(10, 25, 62, 24);
		getContentPane().add(nombre);
		
		JLabel ubicacion = new JLabel("Ubicacion");
		ubicacion.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		ubicacion.setHorizontalAlignment(SwingConstants.LEFT);
		ubicacion.setBounds(10, 52, 62, 24);
		getContentPane().add(ubicacion);
		
		tnombre = new JTextField();
		tnombre.setHorizontalAlignment(SwingConstants.CENTER);
		tnombre.addKeyListener(new KeyAdapter() {//Limitar el Texto a 18 Caracteres
			public void keyTyped(KeyEvent e){
				if (tnombre.getText().length()== 18)e.consume();				
			}			
		});
		tnombre.setFont(new Font("Arial", Font.PLAIN, 11));
		tnombre.setBounds(82, 25, 160, 24);
		getContentPane().add(tnombre);
		tnombre.setColumns(10);
		
		tubicacion = new JTextField();
		tubicacion.setHorizontalAlignment(SwingConstants.CENTER);
		tubicacion.addKeyListener(new KeyAdapter() {//Limitar el Texto a 18 Caracteres
			public void keyTyped(KeyEvent e){
				if (tubicacion.getText().length()== 18)e.consume();
				}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
		});
		tubicacion.setFont(new Font("Arial", Font.PLAIN, 11));
		tubicacion.setBounds(82, 52, 160, 24);
		getContentPane().add(tubicacion);
		tubicacion.setColumns(10);
		
		aceptar = new JButton("Aceptar");
		aceptar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		aceptar.setBounds(10, 80, 111, 24);
		getContentPane().add(aceptar);
		
		cancelar = new JButton("Cancelar");
		cancelar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		cancelar.setBounds(131, 80, 111, 24);
		getContentPane().add(cancelar);
	}
	
	private void agregarAccion(){
		this.aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tnombre.getText().isEmpty() || tubicacion.getText().isEmpty()){
					JOptionPane.showMessageDialog(vtana, "No has ingresado todos los datos", "NOMBRE Y UBICACION",
							JOptionPane.INFORMATION_MESSAGE, vtana.information);
				}else{
					valor=true;
					setVisible(false);
				}				
			}
		});
		this.cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valor=false;
				setVisible(false);
			}
		});
	}
}
