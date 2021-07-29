package Scanner.Manual;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JTable;

import Inicio.VentanaPrincipal.VentanaPrincipal;

public class TablaCargaManual extends JTable {
	
	public ModeloTablaCargarManual modTablaManual;
	ArrayList<Long> listCatalogo;
	ArrayList<String> listDescripcion;

	
	public TablaCargaManual(VentanaPrincipal vtana,ModeloTablaCargarManual modTablaManual) {
		super(modTablaManual);
		this.modTablaManual = modTablaManual;
		this.listCatalogo = vtana.listCatalogo;
		this.listDescripcion = vtana.listDescripcion;
		iniciar();
		accionesTabla();
	}
	
	private void iniciar(){
		modTablaManual.addFilas(1);
		setPreferredScrollableViewportSize(new Dimension(575, 200));
		setDefaultRenderer(Object.class, new RedendererTablaManual());
		getColumnModel().getColumn(0).setPreferredWidth(50);
		getColumnModel().getColumn(1).setPreferredWidth(100);
		getColumnModel().getColumn(2).setPreferredWidth(75);
		getColumnModel().getColumn(3).setPreferredWidth(25);
		getColumnModel().getColumn(4).setPreferredWidth(250);
		setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
	}
	
	private void accionesTabla(){
		this.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ev){
				char caracter=ev.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)){
						ev.consume();// ignorar el evento de teclado
				}
			}
		});
		addKeyListener(new KeyListener() {			
			public void keyTyped(KeyEvent e) {}			
			public void keyReleased(KeyEvent e) {}			
			public void keyPressed(KeyEvent e) {
				int fila = getSelectedRow();
				int columna = getSelectedColumn();
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					if(columna==1){
						changeSelection(fila-1, 2, false, false);/* Se usa para ubicar el focus(cursor de la tabla
																				* en la  siguiente columna */						
					}
					if (columna==2){
						DatosCargaManual linea=(DatosCargaManual) modTablaManual.datos.get(fila);
						Long articulo;
						try{
							articulo = Long.parseLong(linea.getArt());
						}catch(NumberFormatException ex){
							articulo = (long) 0;
						}
						int nArt = listCatalogo.indexOf(articulo);
						if(nArt<0){
							linea.setExist(false);
							linea.setDescripcion("\"ERROR\" Articulo no encontrado");
						}else{
							linea.setExist(true);
							linea.setDescripcion(listDescripcion.get(nArt));
						}
						if(modTablaManual.datos.size()<=(fila+1)){
							modTablaManual.addFilas(1);
							changeSelection(fila, 1, false, false);/* Se usa para ubicar el focus(cursor de la tabla
																				* en la  siguiente fila */
						}//Fin de agregar linea
					}//Fin de columna 2
	            }//Fin del evento enter
			}//Fin keyPressed
		});
	}
	
	public void changeSelection(int rowIndex, int columnIndex,
			boolean toggle, boolean extend) {
		switch (columnIndex) {
		case 0:
			super.changeSelection(rowIndex, columnIndex + 1, toggle, extend);//Lo manda a la columna 1
			break;
		case 3:
			super.changeSelection(rowIndex, columnIndex - 1, toggle, extend);//Lo manda a la columna 1
			break;
		default:
			super.changeSelection(rowIndex, columnIndex, toggle, extend);
			break;
		}
	}

}
