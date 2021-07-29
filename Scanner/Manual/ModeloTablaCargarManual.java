package Scanner.Manual;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaCargarManual extends AbstractTableModel{
	
	public ArrayList<DatosCargaManual> datos = new ArrayList<DatosCargaManual>();
	public long valTot;
	public String[] columnNames = {//Se crean los Nombres de las Columnas en un Array(Vector)
			"Linea",
			"Articulo",
			"Cantidad",
			"",
			"Descripcion"
		};
	
	public String getColumnName(int col) {//Se ingresa el Nombres de las Columnas
		return columnNames[col];
	}

	public int getColumnCount() {		
		return columnNames.length;
	}
	
	public int getRowCount() {//Se muestra el numero de filas			
		return datos.size();
	}

	public Object getValueAt(int y, int x) {
		DatosCargaManual datManual=(DatosCargaManual) datos.get(y);//Se obtiene la fila a cargar
		switch(x){//Se obtiene la columna a cargar
		case 0:
			return datManual.getLinea();
        case 1:
        	return datManual.getArt();
        case 2:
        	return datManual.getCant();
        case 3:
        	return datManual.getExist();
        case 4:
        	return datManual.getDescripcion();
        default:
        	return null;        	
		}
	}
	
	public void setValueAt(Object valor, int y, int x) {//Agrega valores a la tabla
		DatosCargaManual datManual=(DatosCargaManual) datos.get(y);//Se obtiene la fila a agregar
		switch(x){
		case 0:
			datManual.setLinea(valor.toString());
			break;
		case 1:
			String art=valor.toString();
			if(art.length()>13){
				art="Error";
			}
			datManual.setArt(art);
			break;
		case 2:
			String cant=valor.toString();
			try{
				if(Integer.parseInt(cant)>999){
					cant="999";
				}
			}catch (NumberFormatException e) {
				cant="";
			}
			datManual.setCant(cant);
			break;
		case 3:
			datManual.setExist((Boolean) valor);
		}
		Iterator<DatosCargaManual> barMan = datos.iterator();
		valTot = 0;
		while(barMan.hasNext()){
			DatosCargaManual linMan = barMan.next();
			long art = 0;
			long cant = 0;
			try{art = Long.parseLong(linMan.getArt());}catch(NumberFormatException ex){}
			try{cant = Long.parseLong(linMan.getCant());}catch(NumberFormatException ex){}
			valTot = valTot+(art*cant);
		}
		fireTableDataChanged();
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}	
	
	public boolean isCellEditable(int y, int x) {//Se indica que la tabla es editable
		if(x==1){								//Donde solo es editable la columna 2 y 3
			return true;
		}else{
			if(x==2){
				return true;
			}else{
				return false;
			}
		}
    }
	
	private DatosCargaManual cargarLineas(int fila){
		DatosCargaManual linea=new DatosCargaManual(Integer.toString(fila+1));
		return linea;			
	}
	
	public void addFilas(int addfila){
		int vacio=0;
		for(int x=0;x<datos.size();x++){
			if(datos.size()>0){
				DatosCargaManual datl=(DatosCargaManual) datos.get(x);
				if(datl.getCant().isEmpty()){
					vacio=vacio+1;
				}
			}
		}
		if(vacio<2){
			for(int x=0;x<addfila;x++){
				datos.add(cargarLineas(datos.size()));
			}
			fireTableDataChanged();
		}
	}
	
	void llenarLineas(int fila){
		DatosCargaManual linea=(DatosCargaManual) datos.get(fila);
		
	}

}
