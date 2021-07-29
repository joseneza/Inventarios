package Marbetes.TablaMarbetes;


import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TablaMarbetes extends JTable{
	
	private static final long serialVersionUID = 1L;
	ModAbstractTablaMarb modTablaAbst;
	String tipo;
	int max;
	long limite;
	
	public TablaMarbetes(ModAbstractTablaMarb modTablaAbst, String tipo, int max){
		super(modTablaAbst);
		this.modTablaAbst=modTablaAbst;
		this.tipo=tipo;
		limite=cargarLimite(max);
		iniciar();		
	}
	
	private Long cargarLimite(int tamano){
		String cadena="";
		int num=tamano-tipo.length();
		for(int n=0;n<num;n++){
			cadena=cadena+"9";
		}
		return Long.parseLong(cadena);		
	}
	
	private void iniciar(){
		setPreferredScrollableViewportSize(new Dimension(500, 350));
		getColumnModel().getColumn(0).setPreferredWidth(25);
		getColumnModel().getColumn(1).setPreferredWidth(120);
		getColumnModel().getColumn(2).setPreferredWidth(65);
		getColumnModel().getColumn(3).setPreferredWidth(120);
		getColumnModel().getColumn(4).setPreferredWidth(120);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//Se usa para centrar los datos de las celdas
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		getColumnModel().getColumn(3).setCellRenderer(tcr);
		getColumnModel().getColumn(4).setCellRenderer(tcr);
		this.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ev){
				if(getSelectedColumn()==2){
					char caracter=ev.getKeyChar();
					if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)) 
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
					if (e.getKeyCode() != KeyEvent.VK_ENTER){
						if (columna==2){
							if(modTablaAbst.datos.size()>fila+1 && fila>0){
								int limite=modTablaAbst.datos.size();
								for(int num=fila;num<limite-1;num++){
									modTablaAbst.sincronizarLineas(fila);
								}
							}
		            	}
					}else{
						if (columna==1){
							if(fila==0){
								changeSelection(-1, 2, false, false);/* Se usa para ubicar el focus(cursor de la tabla)
								 * en la  siguiente columna
								 */
							}else{
								changeSelection(fila-1, 2, false, false);
							}
						}
						if (columna==2){
			            	if(modTablaAbst.datos.size()>fila+1){//Si no es la ultima linea de la tabla
			            		if(fila>0){
			            			int lim=modTablaAbst.datos.size();
			            			for(int num=fila;num<lim-1;num++){
			            				modTablaAbst.sincronizarLineas(fila);
			            				changeSelection(fila,1,false, false);/* Se usa para ubicar el focus(cursor
			            				* de la tabla) en la  siguiente fila
										*/
			            			}
			            		}
							}else{//Si es la ultima linea de la tabla genera otra linea
								long marbete;
								DatosLineaMarbete ultLinea=(DatosLineaMarbete) modTablaAbst.datos.get(fila);
								String ultMarbete=ultLinea.getFin();
								try{
									marbete=Long.parseLong(ultMarbete.substring(tipo.length()));
								}catch(NumberFormatException ex){
									marbete=limite;
								}
								if(marbete<limite){
									if(fila>0){
										modTablaAbst.sincronizarLineas(fila);
									}
									modTablaAbst.addFila(1,tipo,limite);
									changeSelection(fila,1,false, false);
								}
								//Fin de agregar linea
							}//Fin de sincronizar lineas
						}//Fin de columna 2
		            }//Fin del evento enter	
			}//Fin keyPressed
		});
	}
	/* Se sobreescribe el metodo changeSelection del JTable para que no permita
	 * seleccionar la columna 0,3 y 4.
	 */
	public void changeSelection(int rowIndex, int columnIndex,
			boolean toggle, boolean extend) {
		switch (columnIndex) {
		case 0:
			super.changeSelection(rowIndex, columnIndex + 1, toggle, extend);//Lo manda a la columna 1
			break;
		case 3:
			super.changeSelection(rowIndex, columnIndex - 2, toggle, extend);//Lo manda a la columna 1
			break;
		case 4:
			super.changeSelection(rowIndex, columnIndex - 3, toggle, extend);//Lo manda a la columna 1
			break;
		default:
			super.changeSelection(rowIndex, columnIndex, toggle, extend);
			break;
		}
	}

}
