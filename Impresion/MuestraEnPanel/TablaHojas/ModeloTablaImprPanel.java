package Impresion.MuestraEnPanel.TablaHojas;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import Scanner.DatosScanners;

public class ModeloTablaImprPanel extends AbstractTableModel{
	
	public ArrayList<NumeroHojas> listHojas = new ArrayList<NumeroHojas>();
	public JLabel textNumHojas = new JLabel();
	public String[] columnNames = {"", "Hoja", "Informacion"};//Se crean los Nombres de las Columnas en un Array(Vector)
	
	public String getColumnName(int col) {//Se ingresa el Nombres de las Columnas
		return columnNames[col];
	}


	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return listHojas.size();
	}

	public Object getValueAt(int y, int x) {
		NumeroHojas linea=(NumeroHojas) listHojas.get(y);
		switch (x) {
		case 0:
			return linea.getSelec();
		case 1:
			return linea.getHoja();
		case 2:
			return linea.getInfo();
		default:
			return null;
		}
		
	}
	
	public boolean isCellEditable(int y, int x) {
		if(x==0){
			return true;
		}else{
			return false;
		}
	}
	
	/* Este metodo sirve para determinar el editor predeterminado
	 * para cada columna de celdas */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	public void setValueAt(Object dato, int y, int x){
		NumeroHojas linea=(NumeroHojas) listHojas.get(y);//Se obtiene la fila a cargar
		switch (x) {
		case 0:
			linea.setSelec((Boolean) dato);
			textNumHojas.setText(recorerLista());
			break;				
		}
		
	}
	
	private String recorerLista(){
		String lista="";
		Iterator<NumeroHojas> barHojas = listHojas.iterator();
		while(barHojas.hasNext()){
			NumeroHojas linea = barHojas.next();
			if(linea.getSelec()==true){
				if(lista.length()==0){
					lista=Integer.toString(linea.getHoja());
				}else{
					lista=lista+","+Integer.toString(linea.getHoja());
				}
			}
		}
		return lista;		
	}
	
	public void addFila(int hoja, String descripcion){
		NumeroHojas linea=new NumeroHojas(hoja, descripcion);
		listHojas.add(linea);
		fireTableDataChanged();
	}
	
	public void selecTabla(String seleccion){
		System.out.println("ModeloTablaImprPanel seleccion "+seleccion);
		ArrayList<String> sel = new ArrayList<String>();
		String linea = "";
		for(int n=0;n<seleccion.length();n++){
			String l=seleccion.substring(n, n+1);
			if(l.equals(",")){
				if(linea.length()>0) sel.add(linea);
				linea="";
			}else{
				linea=linea+l;
			}
		}
		if(linea.length()>0) sel.add(linea);
		System.out.println("ModeloTablaImprPanel selecTaba "+sel);
		Iterator<NumeroHojas> barHojas = listHojas.iterator();
		while(barHojas.hasNext()){
			NumeroHojas linHoja = barHojas.next();
			linHoja.setSelec(false);
		}
		barHojas = listHojas.iterator();
		while(barHojas.hasNext()){
			NumeroHojas linHoja = barHojas.next();
			for(int n=0;n<sel.size();n++){
				int num;
				try{
					num= Integer.parseInt(sel.get(n));
				}catch(NumberFormatException e){
					num=-1;
				}
				if(num == linHoja.getHoja()){
					linHoja.setSelec(true);
				}
			}
		}
		fireTableDataChanged();
	}

}
