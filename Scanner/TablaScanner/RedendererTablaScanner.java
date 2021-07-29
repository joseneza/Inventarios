package Scanner.TablaScanner;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RedendererTablaScanner extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -7692411915899938580L;
	
	public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean seleccion, boolean focused,
			int row, int column){
		super.getTableCellRendererComponent(tabla, valor, seleccion, focused, row, column);
		Boolean exist=(Boolean) tabla.getValueAt(row, 6);
		if(exist==false){
			setBackground(new Color(255, 255, 179));
			setForeground(Color.RED);
			setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 10));
		}else{
			setBackground(null);
			setForeground(null);
		}
		return this;
	}

}
