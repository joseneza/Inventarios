package RevisarArticulos.VentanaRevision;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RedendererTablaRev extends DefaultTableCellRenderer {
	private static final long serialVersionUID = -1409518929743798947L;

	public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean seleccion, boolean focused,
			int row, int column){
		super.getTableCellRendererComponent(tabla, valor, seleccion, focused, row, column);
		Boolean exist=(Boolean) tabla.getValueAt(row, 7);
		if(exist==false){
			setBackground(new Color(255, 255, 179));
			setForeground(new Color(255, 0, 0));
			setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 10));
		}else{
			setBackground(null);
			setForeground(null);
		}
		if(seleccion){
			setBackground(new Color(102, 255, 255));
			setForeground(new Color(0, 0, 102));
			setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		}
		if(focused){
			setBackground(new Color(255, 255, 153));
			setForeground(new Color(0, 0, 153));
			setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		}
		return this;
	}
}
