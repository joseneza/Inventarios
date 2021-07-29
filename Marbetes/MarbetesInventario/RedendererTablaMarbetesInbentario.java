package Marbetes.MarbetesInventario;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RedendererTablaMarbetesInbentario extends DefaultTableCellRenderer {	
	
	public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean seleccion, boolean focused,
			int row, int column){
		super.getTableCellRendererComponent(tabla, valor, seleccion, focused, row, column);
		String num = (String) tabla.getValueAt(row, 2);
		double porc;
		try{
			porc=Double.parseDouble(num.substring(0, num.length()-2));
		}catch(NumberFormatException e){
			porc=0.0;
		}
		if(porc<=20.0){
			setBackground(new Color(255, 255, 102));
			setForeground(new Color(255, 0, 0));
		}
		if(porc>20.0 & porc<=40.0){
			setBackground(new Color(255, 255, 153));
			setForeground(new Color(204, 0, 56));
		}
		if(porc>40.0 & porc<=60.0){
			setBackground(new Color(255, 255, 179));
			setForeground(new Color(158, 0, 102));
		}
		if(porc>60.0 & porc<=80.0){
			setBackground(new Color(255, 255, 204));
			setForeground(new Color(102, 0, 158));
		}
		if(porc>80.0 & porc<=95.0){
			setBackground(new Color(255, 255, 229));
			setForeground(new Color(56, 0, 158));
		}
		if(porc>95.0){
			setBackground(new Color(255, 255, 255));
			setForeground(new Color(0, 0, 102));
		}		
		return this;
	}

}
