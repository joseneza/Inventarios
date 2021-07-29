package Impresion.MuestraEnPanel.TablaHojas;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import Scanner.TablaScanner.RedendererTablaScanner;

public class PanelTablaNumHojas extends JScrollPane {
	
	public ModeloTablaImprPanel tablHojas;
	JTable tabla;
	
	public PanelTablaNumHojas(){
		tablHojas = new ModeloTablaImprPanel();
		tabla = new JTable(tablHojas);
		tabla.setPreferredScrollableViewportSize(new Dimension(165, 150));
		//tabla.setDefaultRenderer(Object.class, new RedendererTablaScanner());
		tabla.getColumnModel().getColumn(0).setPreferredWidth(25);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(40);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabla.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		setViewportView(tabla);//La tabla se vera dentro del panel de las barras de desplazamiento
	}

}