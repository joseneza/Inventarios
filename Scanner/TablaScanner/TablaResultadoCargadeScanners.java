package Scanner.TablaScanner;
/* Programa Inventarios
 * Tabla donde se muestra la carga de scanners
 */
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JScrollPane;
/*	Mostrara el resultado tras la carga del Scaner
 */
import javax.swing.JTable;

import RevisarArticulos.VentanaRevision.RedendererTablaRev;

public class TablaResultadoCargadeScanners extends JScrollPane{
	public ModeloTablaScanner nvomodelo;
	JTable nvatabla;
	int fila;
	
	public TablaResultadoCargadeScanners(int fila){
		this.fila=fila;
		nvomodelo=new ModeloTablaScanner();
		nvatabla=new JTable(nvomodelo);//Se asigna el modelo al momento de construir la tabla
		nvatabla.setPreferredScrollableViewportSize(new Dimension(650, 380));
		nvatabla.setDefaultRenderer(Object.class, new RedendererTablaScanner());
		nvatabla.getColumnModel().getColumn(0).setPreferredWidth(40);
		nvatabla.getColumnModel().getColumn(1).setPreferredWidth(60);
		nvatabla.getColumnModel().getColumn(2).setPreferredWidth(80);
		nvatabla.getColumnModel().getColumn(3).setPreferredWidth(90);
		nvatabla.getColumnModel().getColumn(4).setPreferredWidth(30);
		nvatabla.getColumnModel().getColumn(5).setPreferredWidth(40);
		nvatabla.getColumnModel().getColumn(6).setPreferredWidth(20);
		nvatabla.getColumnModel().getColumn(7).setPreferredWidth(290);
		nvatabla.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		setViewportView(nvatabla);//La tabla se vera dentro del panel de las barras de desplazamiento
	}

}