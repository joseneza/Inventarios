package Inicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PreguntaAbierta extends JDialog{
	
	private static final long serialVersionUID = 1L;
	public int valor=-1;
	private String mensaje;
	private String titulo;
	private String bnvo;
	private String bexist;
	JButton exist;
	JButton nvo;

	
	public PreguntaAbierta(JFrame padre,String mensaje,String titulo,String bexist,String bnvo) {
		super(padre);
		this.mensaje=mensaje;
		this.titulo=titulo;
		this.bnvo=bnvo;
		this.bexist=bexist;
		initialize();
		agregaListeners();
		setSize(435, 125);
		setLocationRelativeTo(null);//Da la ubicacion de la ventana como es null la centra
		setVisible(true);
	}
	
	public PreguntaAbierta(JDialog padre,String mensaje,String titulo,String bexist,String bnvo) {
		super(padre);
		this.mensaje=mensaje;
		this.titulo=titulo;
		this.bnvo=bnvo;
		this.bexist=bexist;
		initialize();
		agregaListeners();
		setSize(435, 125);
		setLocationRelativeTo(null);//Da la ubicacion de la ventana como es null la centra
		setVisible(true);
	}

	private void initialize() {
		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		setModal(true);
		setTitle(titulo);
		setBounds(100, 100, 440, 120);
		getContentPane().setLayout(null);
		
		JLabel msj = new JLabel(mensaje);
		msj.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		msj.setHorizontalAlignment(SwingConstants.CENTER);
		msj.setBounds(5, 5, 410, 45);
		getContentPane().add(msj);
		
		exist = new JButton(bexist);
		exist.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		exist.setBounds(5, 55, 200, 25);
		getContentPane().add(exist);
		
		nvo = new JButton(bnvo);
		nvo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		nvo.setBounds(215, 55, 200, 25);
		getContentPane().add(nvo);
	}
	
	private void agregaListeners(){
		this.nvo.addActionListener(new ActionListener() {//Accion Boton Nuevo
			public void actionPerformed(ActionEvent e) {
				valor=1;
				setVisible(false);
				}
		});
		this.exist.addActionListener(new ActionListener() {//Accion Boton Existente
			public void actionPerformed(ActionEvent e) {
				valor=0;
				setVisible(false);
			}
		});
	}
}
