package RevisarArticulos.VentanaRevision;

/* Modelo de tabla para manejar los datos 
 * de la revision de Articulos
 */

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import Scanner.TablaScanner.PanelAgregarScanner;

public class ModeloTablaRevision extends AbstractTableModel{
	
	public ArrayList<DatosRevision> datRev = new ArrayList<DatosRevision>();
	
	public String[] columnNames = {//Se crean los Nombres de las Columnas en un Array(Vector)
			"Linea", "Scanner", "Contador", "Ubicacion",
			"Marbete", "Articulo", "Cant", "", "Descripcion",
			"Confirmar", "Borrar", "Cambiar"
		};
	
	public void setColumnName(String[] columnNames){//Metodo para cambiar el nombre de las Columnas
		this.columnNames=columnNames;
	}
	
	public String getColumnName(int col) {//Se ingresa el Nombres de las Columnas
		return columnNames[col];
	}

	public int getColumnCount() {//Metodo para el ancho de la tabla
		return columnNames.length;
	}

	public int getRowCount() {//Metodo para el largo de la tabla 
		return datRev.size();
	}

	public Object getValueAt(int y, int x) {//Tomar valores de la Tabla
		DatosRevision linRev=(DatosRevision) datRev.get(y);
		switch (x) {
		case 0:
			return linRev.getFila();
		case 1:
			return linRev.getScan();
		case 2:
			return linRev.getContador();
		case 3:
			return linRev.getUbic();
		case 4:
			return linRev.getMarb();
		case 5:
			return linRev.getArt();
		case 6:
			return linRev.getCant();
		case 7:
			return linRev.getExiste();	
		case 8:
			return linRev.getDescripcion();
		case 9:
			return linRev.getAgregar();
		case 10:
			return linRev.getBorrar();
		case 11:
			return linRev.getCambiar();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object valor, int y, int x) {//Agrega valores a la tabla
		DatosRevision linRev=(DatosRevision) datRev.get(y);//Se obtiene la fila a agregar
		switch(x){
		case 5:
			if(linRev.getCambiar()==true){
				long art=(long) valor;
				if(Long.toString(art).length() > 13){
					JOptionPane.showMessageDialog(null, "El articulo es demasiado largo y no se puede ingresar",
							"Error", JOptionPane.ERROR_MESSAGE,
							new ImageIcon(PanelAgregarScanner.class.getResource("/Iconos/48x48/dialog-error.png")));
					art=linRev.getValorArt();
				}
				linRev.setArt(art);
			}
			break;
		case 6:
			if(linRev.getCambiar()==true){
				short cant=(short) valor;
				if(cant>999){
					cant=999;
				}
				linRev.setCant(cant);
			}
			break;
		case 8:
			if((linRev.getCambiar()==true || linRev.getAgregar()==true)	&& linRev.getExiste()==false){
				String descripcion=valor.toString();
				if(descripcion.length()>30){
					descripcion=descripcion.substring(0, 30);
				}
				linRev.setDescripcion(descripcion);
			}
			break;
		case 9:
			linRev.setAgregar((Boolean) valor);
			if((Boolean) valor==true){
				linRev.setBorrar(false);
				linRev.setCambiar(false);
				linRev.setArt(linRev.getValorArt());
				linRev.setCant((short) linRev.getValorCant());
			}
			break;
		case 10:
			linRev.setBorrar((Boolean) valor);
			if((Boolean) valor==true){
				linRev.setAgregar(false);
				linRev.setCambiar(false);				
			}
			break;
		case 11:
			linRev.setCambiar((Boolean) valor);
			if((Boolean) valor==true){
				linRev.setBorrar(false);
				linRev.setAgregar(false);
			}
			break;
		}		
		fireTableDataChanged();
	}
	
	public boolean isCellEditable(int y, int x) {//Se indica que la tabla es editable
		if(x>4 && x!=7 && x!=8){			
			return true;
		}else{
			if(getValueAt(y, x).equals("\"ERROR\" Articulo no encontrado")
					|| getValueAt(y, x).equals("") || getValueAt(y, x).equals("Sin Descripcion")){
				return true;
			}else{
				return false;
			}
		}
	}
	
	/* Este metodo sirve para determinar el editor predeterminado
	 * para cada columna de celdas */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	public void addFila(String fila, String scan, int consec, String cont, String ubic, String marb,
			long art, short cant, String descripcion, Boolean existe, Boolean agregar){
		DatosRevision linea=new DatosRevision(fila, scan, consec, cont, ubic, marb, art, cant, descripcion, existe, agregar, false, false);
		datRev.add(linea);
		fireTableDataChanged();
	}
	
	public void nvoArt(String fila, String scan, int consec, String cont, String ubic, String marb,
			long art, short cant, String descripcion){
		DatosRevision linea=new DatosRevision(fila, scan, consec, cont, ubic, marb, art, cant, descripcion, false ,false, false, true);
		datRev.add(linea);
		fireTableDataChanged();
	}
}
