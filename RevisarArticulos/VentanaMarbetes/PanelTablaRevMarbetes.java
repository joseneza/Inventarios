package RevisarArticulos.VentanaMarbetes;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelTablaRevMarbetes extends JScrollPane {
	
	public ModeloTablaRevMarbetes tabRevMarv;
	JTable tabla;
	
	public PanelTablaRevMarbetes(){
		tabRevMarv=new ModeloTablaRevMarbetes();
		tabla=new JTable(tabRevMarv);
		tabla.setPreferredScrollableViewportSize(new Dimension(600, 240));
		tabla.getColumnModel().getColumn(0).setPreferredWidth(60);//Scanner
		tabla.getColumnModel().getColumn(1).setPreferredWidth(100);//Nombre
		tabla.getColumnModel().getColumn(2).setPreferredWidth(100);//Ubicacion
		tabla.getColumnModel().getColumn(3).setPreferredWidth(90);//Marbete
		tabla.getColumnModel().getColumn(4).setPreferredWidth(60);//Total
		tabla.getColumnModel().getColumn(5).setPreferredWidth(60);//Error
		tabla.getColumnModel().getColumn(6).setPreferredWidth(30);//Escojer
		tabla.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		setViewportView(tabla);
	}

}