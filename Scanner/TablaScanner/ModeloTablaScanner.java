package Scanner.TablaScanner;
/* Programa Inventarios
 * Modelo para tabla de Scanners
 */

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Scanner.DatosScanners;


public class ModeloTablaScanner extends AbstractTableModel{
	
	public ArrayList<DatosScanners> datScan=new ArrayList<DatosScanners>();
	
	public String[] columnNames = {//Se crean los Nombres de las Columnas en un Array(Vector)
			"Linea", "Scanner", "Marbete", "Articulo", "EAN", "Cant", "", "Descripcion"
		};
	
	public String getColumnName(int col) {//Se ingresa el Nombres de las Columnas
		return columnNames[col];
	}

	
	public int getRowCount() {//Metodo para el largo de la tabla 
		return datScan.size();
	}

	public int getColumnCount() {//Metodo para el ancho de la tabla
		return columnNames.length;
	}
	
	public Object getValueAt(int y, int x) {
		DatosScanners linea=(DatosScanners) datScan.get(y);//Se obtiene la fila a cargar
		switch(x){//Se obtiene la columna a cargar
		case 0:
			return linea.getFila();
        case 1:
        	return linea.getScan();
        case 2:
        	return linea.getMarbete();
        case 3:
        	return linea.getArt();
        case 4:
        	return linea.getEan();
        case 5:
        	return linea.getCant();
        case 6:
        	return linea.getExiste();
        case 7:
        	return linea.getDescripcion();
        default:
        	return null;        	
		}
	}
	
	/*Se indica que la tabla no es editable
	 * y por eso no se agrega el metodo setValueAt
	 * en caso contrario se tendria que agregar
	 */	
	public boolean isCellEditable(int y, int x) {
		return false;
	}
	
	/* Este metodo sirve para determinar el editor predeterminado
	 * para cada columna de celdas */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	public void addFila(int fila,String scan,int consecutivo,String marbete,long art,Boolean ean,short cant,String descr,Boolean existe){
		DatosScanners linea=new DatosScanners(fila,scan,consecutivo,marbete,art,ean,cant,descr,existe);
		datScan.add(linea);
		fireTableDataChanged();
	}
	
	public void nvaFila(int fila,String scan,String marbete,long art,Boolean ean,short cant,String descr,Boolean existe){
		DatosScanners linea=new DatosScanners(fila,scan,0,marbete,art,ean,cant,descr,existe);
		datScan.add(fila, linea);
		fireTableDataChanged();
	}
	
	public void borrarFila(int fila){
		datScan.remove(fila);
		fireTableDataChanged();
	}
	
	public void setValueAt(Object dato, int y, int x){
		DatosScanners linea=(DatosScanners) datScan.get(y);//Se obtiene la fila a cargar
		switch (x) {
		case 0:
			linea.setFila((int) dato);
			break;
        case 1:
        	linea.setScan((String) dato);
        	break;
        case 2:
        	linea.setMarbete((String) dato);
        	break;
        case 3:
        	linea.setArt((long) dato);
        	break;
        case 4:
        	linea.setEan((Boolean) dato);
        	break;
        case 5:
        	linea.setCant((short) dato);
        	break;
        case 6:
        	linea.setExiste((Boolean) dato);
        	break;
        case 7:
        	linea.setDescripcion((String) dato);
        	break;
		}
		fireTableDataChanged();		
	}
	
	public void ajustarFilas(){
		for(int n=0;n<datScan.size();n++){
			DatosScanners linea=(DatosScanners) datScan.get(n);//Se obtiene la fila a cargar
			linea.setFila(n);
		}
		fireTableDataChanged();
	}

}

