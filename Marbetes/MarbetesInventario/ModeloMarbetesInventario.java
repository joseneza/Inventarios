package Marbetes.MarbetesInventario;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloMarbetesInventario extends AbstractTableModel{
	
ArrayList<DatosTablaMarbetes> datos=new ArrayList<DatosTablaMarbetes>();
	
	public String[] columnNames = {//Se crean los Nombres de las Columnas en un Array(Vector)
			"Ubicacion",		
			"Marbetes",
			"Avance",
	};
	
	public String getColumnName(int col) {//Se ingresa el Nombres de las Columnas
		return columnNames[col];
	}
	
	public int getColumnCount() {//Indica el numero de columnas
		return columnNames.length;
	}
	
	public int getRowCount() {//Indica el numero de filas
		return datos.size();
	}
	
	public boolean isCellEditable(int y, int x) {//Se indica que la tabla no es editable
		return false;
	}
	
	public Object getValueAt(int y, int x) {//Tomar valores de la Tabla
		DatosTablaMarbetes linMarbetes=(DatosTablaMarbetes) datos.get(y);
		switch (x) {
		case 0:
			return linMarbetes.ubicacion;
		case 1:
			return linMarbetes.marbetes;
		case 2:
			return linMarbetes.avance;
		default:
			return null;
		}
	}
	
	public void setValueAt(Object valor, int y, int x) {//Agrega valores a la tabla
		DatosTablaMarbetes linMarbetes=(DatosTablaMarbetes) datos.get(y);//Se obtiene la fila a agregar
		switch(x){
		case 0:
			linMarbetes.setUbicacion(valor.toString());
			break;
		case 1:
			linMarbetes.setMarbetes(valor.toString());
			break;
		case 2:
			linMarbetes.setAvance(valor.toString());
			break;
		}
		fireTableDataChanged();
	}
	
	public Class getColumnClass(int c) {//Regresa el tipo de Objeto de la celda 
		return getValueAt(0, c).getClass();
	}
	
	void addFila(String ubicacion,String cant,String avance){
		DatosTablaMarbetes linea=new DatosTablaMarbetes(ubicacion,cant,avance);
		datos.add(linea);
		fireTableDataChanged();
	}

}
