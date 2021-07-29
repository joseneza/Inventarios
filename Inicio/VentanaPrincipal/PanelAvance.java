package Inicio.VentanaPrincipal;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Color;

public class PanelAvance extends JPanel {
	public JLabel lblAvanceTot;
	public JLabel lblTotScanner;
	public JLabel lblTotArticulos;
	public JLabel lblTotAgregados;
	public JLabel lblTotBorrados;
	public JLabel lblGranTotal;
	

	public PanelAvance() {
		lblAvanceTot = new JLabel("");
		lblAvanceTot.setForeground(new Color(0, 0, 255));
		lblTotScanner = new JLabel("");
		lblTotScanner.setForeground(new Color(0, 0, 128));
		lblTotArticulos = new JLabel("");
		lblTotArticulos.setForeground(new Color(0, 128, 0));
		lblTotAgregados = new JLabel("");
		lblTotAgregados.setForeground(new Color(50, 205, 50));
		lblTotBorrados = new JLabel("");
		lblTotBorrados.setForeground(new Color(255, 0, 0));
		lblGranTotal = new JLabel("");
		lblGranTotal.setForeground(new Color(0, 0, 205));
		agregar();		
	}
	
	private void agregar(){
		setLayout(new GridLayout(6, 0));
		lblAvanceTot.setHorizontalAlignment(SwingConstants.LEFT);
		lblAvanceTot.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		add(lblAvanceTot);		
		lblTotScanner.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotScanner.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		add(lblTotScanner);		
		lblTotArticulos.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotArticulos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		add(lblTotArticulos);
		lblTotAgregados.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotAgregados.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		add(lblTotAgregados);
		lblTotBorrados.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotBorrados.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		add(lblTotBorrados);
		lblGranTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblGranTotal.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		add(lblGranTotal);
	}
	

}
