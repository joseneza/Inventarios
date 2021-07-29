package RevisarArticulos.VentanaMarbetes;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaRevMarbetes extends AbstractTableModel {
	
	public ArrayList<ListaMarbetes> listMarb=new ArrayList<ListaMarbetes>();//Lista de marbetes
	Boolean selecMultiple=false;
	
	public String[] columnNames = {//Se crean los Nombres de las Columnas en un Array(Vector)
			"Scanner",
			"Contador",
			"Ubicacion",
			"Marbete",
			"Total Art",
			"Errores",
			""
	};
	
	public String getColumnName(int col) {//Se ingresa el Nombres de las Columnas
		return columnNames[col];
	}

	public int getColumnCount() {//Metodo para el ancho de la tabla
		return columnNames.length;
	}

	public int getRowCount() {//Metodo para el largo de la tabla 
		return listMarb.size();
	}

	public Object getValueAt(int y, int x) {//Metodo para el cambio de valores
		ListaMarbetes linea=(ListaMarbetes) listMarb.get(y);//Se obtiene la fila a cargar
		switch(x){//Se obtiene la columna a cargar
		case 0:
			return linea.getScan();
        case 1:
        	return linea.getNombre();
        case 2:
        	return linea.getUbiacion();
        case 3:
        	return linea.getMarbete();
        case 4:
        	return linea.getTotal();
        case 5:
        	return linea.getError();
        case 6:
        	return linea.getEscojer();
        default:
        	return null;        	
		}
	}
	
	public void setValueAt(Object valor, int y, int x) {//Agrega valores a la tabla
		ListaMarbetes linea=(ListaMarbetes) listMarb.get(y);//Se obtiene la fila a agregar
		switch(x){
		case 6:
			linea.setEscojer((Boolean) valor);
			if(selecMultiple==false){
				barridoLineas(y);
			}
			break;
		}
		fireTableDataChanged();//Actualiza la tabla
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	public boolean isCellEditable(int y, int x) {//Se indica que la tabla es editable
		if(x==6){								//Donde solo es editable la columna 6
			return true;
		}else{
			return false;
		}
    }
	
	public void addFila(String scan, String nombre, String ubicacion, String marbete,
			int total, int error, Boolean escojer){
		ListaMarbetes linea=new ListaMarbetes(scan, nombre, ubicacion, marbete, total, error, escojer);
		listMarb.add(linea);
		fireTableDataChanged();
	}
	
	public void barridoLineas(int lin){
		for(int n=0;n<listMarb.size();n++){
			if(n!=lin){
				ListaMarbetes linea=(ListaMarbetes) listMarb.get(n);
				linea.setEscojer(false);
			}
		}
		fireTableDataChanged();
	}
	
	public void selecAll(){
		for(int n=0;n<listMarb.size();n++){
			ListaMarbetes linea=(ListaMarbetes) listMarb.get(n);
			linea.setEscojer(true);
		}
		fireTableDataChanged();
	}
	
		

}
