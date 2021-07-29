package Marbetes.TablaMarbetes;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModAbstractTablaMarb extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	int fila;
	public ArrayList<DatosLineaMarbete> datos=new ArrayList<DatosLineaMarbete>();
	private String[] columnNames = {//Se crean los Nombres de las Columnas en un Array(Vector)
			"Fila",
			"Ubicacion",
			"Cantidad",
			"Inicio",
			"Fin"
			};
	
	public String getColumnName(int col) {//Se ingresa el Nombres de las Columnas
		return columnNames[col];
	}
	
	public int getColumnCount() {//Se muestra el numero de columnas		
		return columnNames.length;
	}
	
	public int getRowCount() {//Se muestra el numero de filas			
		return datos.size();
	}	
	
	public Object getValueAt(int y, int x) {//Se carga el valor de las celdas
		DatosLineaMarbete datMarbetes=(DatosLineaMarbete) datos.get(y);//Se obtiene la fila a cargar
		switch(x){//Se obtiene la columna a cargar
		case 0:
			return datMarbetes.getLinea();
        case 1:
        	return datMarbetes.getUbicacion();
        case 2:
        	return datMarbetes.getCantidad();
        case 3:
        	return datMarbetes.getInicio();
        case 4:
        	return datMarbetes.getFin();
        default:
        	return null;        	
		}
	}
	
	public void setValueAt(Object valor, int y, int x) {//Agrega valores a la tabla
		DatosLineaMarbete datMarbetes=(DatosLineaMarbete) datos.get(y);//Se obtiene la fila a agregar
		switch(x){
		case 0:
			datMarbetes.setLinea(valor.toString());
			break;
		case 1:
			String ubic=valor.toString();
			if(ubic.length()>30){
				ubic=ubic.substring(0, 30);
			}
			datMarbetes.setUbicacion(ubic);
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
			datMarbetes.setCantidad(cant);
			break;
		case 3:
			datMarbetes.setInicio(valor.toString());
			break;
		case 4:
			datMarbetes.setFin(valor.toString());
			break;
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
		
	private DatosLineaMarbete cargarLineas(int fila, String tipo, long limite){		
		String marbete=cargarMarbete(tipo, Long.toString(limite));
		if(fila!=0){//Solo si la lista de objetos no esta vacia
			DatosLineaMarbete lineanterior=(DatosLineaMarbete) datos.get(fila-1);
			marbete=lineanterior.getFin();
		}
		DatosLineaMarbete linea=new DatosLineaMarbete(Integer.toString(fila+1),marbete,tipo,limite);
		return linea;			
	}
	
	private String cargarMarbete(String tipo,String limite){
		String cadena="";
		for(int n=1;n<limite.length();n++){
			cadena=cadena+"0";
		}
		cadena=tipo+cadena;
		return cadena;		
	}

	public void addFila(int addfila, String tipo, long limite){
		long total=0;
		long cant=0;
		int vacio=0;
		for(int x=0;x<datos.size();x++){
			if(datos.size()>0){
				DatosLineaMarbete datl=(DatosLineaMarbete) datos.get(x);
				if(datl.getCantidad().isEmpty()){
					cant=0;
					vacio=vacio+1;
				}else{
					try{
						cant=Integer.parseInt(datl.getCantidad());
					}catch(NumberFormatException ex){
						cant=limite;
					}
				}
			}
			total=total+cant;
		}
		if(total<limite && vacio<2){
			for(int x=0;x<addfila;x++){
				datos.add(cargarLineas(datos.size(),tipo,limite));
			}
			fireTableDataChanged();
		}
	}
	
	void sincronizarLineas(int linea){
		DatosLineaMarbete lineAnterior=(DatosLineaMarbete) datos.get(linea-1);
		DatosLineaMarbete lineaActual=(DatosLineaMarbete) datos.get(linea);
		lineaActual.setInicio(lineAnterior.getFin());
	}
}
