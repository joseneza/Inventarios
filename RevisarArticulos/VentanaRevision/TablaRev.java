package RevisarArticulos.VentanaRevision;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JTable;

import Inicio.VentanaPrincipal.VentanaPrincipal;


public class TablaRev extends JTable {
	
	public ModeloTablaRevision modRev;
	int opcion;
	private ArrayList<Long> listCatalogo;
	private ArrayList<String> listDescripcion;
	private ArrayList<Long> listEan;
	
	public TablaRev(ModeloTablaRevision modRev, int opcion, VentanaRevision vtanaRev) {
		super(modRev);
		this.modRev=modRev;
		this.opcion=opcion;
		this.listCatalogo=vtanaRev.vtana.listCatalogo;
		this.listDescripcion=vtanaRev.vtana.listDescripcion;
		this.listEan=vtanaRev.vtana.listEan;		
		iniciar();
		accionesTabla();
	}
	
	private void iniciar(){
		setPreferredScrollableViewportSize(new Dimension(930, 220));
		setDefaultRenderer(String.class, new RedendererTablaRev());
		getColumnModel().getColumn(0).setPreferredWidth(60);//Linea
		getColumnModel().getColumn(1).setPreferredWidth(90);//Scanner
		getColumnModel().getColumn(2).setPreferredWidth(90);//Contador
		getColumnModel().getColumn(3).setPreferredWidth(90);//Ubicacion
		getColumnModel().getColumn(4).setPreferredWidth(90);//Marbete		
		getColumnModel().getColumn(5).setPreferredWidth(120);//Articulo		
		getColumnModel().getColumn(6).setPreferredWidth(60);//Cantidad
		getColumnModel().getColumn(7).setPreferredWidth(30);//Existe
		getColumnModel().getColumn(8).setPreferredWidth(300);//Descripcion
		getColumnModel().getColumn(9).setPreferredWidth(80);//Agregar
		getColumnModel().getColumn(10).setPreferredWidth(80);//Borrar
		getColumnModel().getColumn(11).setPreferredWidth(80);//Cambiar
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
		this.addKeyListener(new KeyListener() {			
			public void keyTyped(KeyEvent e) {}			
			public void keyReleased(KeyEvent e) {}			
			public void keyPressed(KeyEvent e) {
				int fila = getSelectedRow();
				int columna = getSelectedColumn();
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					if(columna==5){
						DatosRevision linea=(DatosRevision) modRev.datRev.get(fila);
						/* Se usa para ubicar el focus(cursor de la tabla
						* en la  siguiente columna */
						changeSelection(fila-1, 6, false, false);
					}
					if (columna==6){
						DatosRevision linea=(DatosRevision) modRev.datRev.get(fila);
						if(linea.getCambiar().equals(true)){
							int lin=listCatalogo.indexOf(linea.getArt());
							if(lin>=0){
								linea.setExiste(true);
								linea.setDescripcion(listDescripcion.get(lin));
							}else{
								linea.setExiste(false);
								linea.setDescripcion("\"ERROR\" Articulo no encontrado");
							}							
						}
						/* Se usa para ubicar el focus(cursor de la tabla
						* en la  siguiente fila */
						if(fila<modRev.datRev.size()-1){
							changeSelection(fila, 5, false, false);
						}else{
							changeSelection(fila-1, 5, false, false);
						}
					}//Fin de columna 2
					System.out.println("TablaRev accionTabla fila "+fila);
				}//Fin del evento enter
			}//Fin keyPressed
		});
	}
}
