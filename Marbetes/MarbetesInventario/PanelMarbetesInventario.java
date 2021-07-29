package Marbetes.MarbetesInventario;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Inicio.VentanaPrincipal.VentanaPrincipal;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelMarbetesInventario extends JScrollPane {
	
	ModeloMarbetesInventario modMarbInv;
	ArrayList<ArrayList<String>> listMarbete;
	ArrayList<ArrayList<String>> listMarbetesContado;
	public JTable tablaInv;
	VentanaPrincipal vtana;
	
	public PanelMarbetesInventario(VentanaPrincipal vtana) {
		this.listMarbete=vtana.listMarbete;
		this.listMarbetesContado=vtana.listMarbeteContado;
		this.vtana=vtana;
		modMarbInv=new ModeloMarbetesInventario();
		tablaInv=new JTable(modMarbInv){
			public String getToolTipText(MouseEvent e) {
				Point punto = e.getPoint();
				int fila = tablaInv.rowAtPoint(punto);				
				return mensaje(fila);
			}
		};
		tablaInv.setPreferredScrollableViewportSize(new Dimension(220, 370));
		tablaInv.getColumnModel().getColumn(0).setPreferredWidth(100);//Linea
		tablaInv.getColumnModel().getColumn(1).setPreferredWidth(60);//Scanner
		tablaInv.getColumnModel().getColumn(2).setPreferredWidth(60);//Contador
		tablaInv.setDefaultRenderer(Object.class, new RedendererTablaMarbetesInbentario());
		tablaInv.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 10));
		tablaInv.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				int fila = tablaInv.rowAtPoint(e.getPoint());
				mensajeVtana(fila);
			}
		});
		setViewportView(tablaInv);
	}
	
	void mensajeVtana(int fila){
		ArrayList<String> renglonFaltante = listMarbete.get(fila);
		String faltante="";
		int nn=0;
		for(int n=2; renglonFaltante.size()>n ;n++){
			if(faltante.length()>0){
				faltante=faltante+renglonFaltante.get(n);
			}else{
				faltante=renglonFaltante.get(n);
			}
			nn++;
			if(nn==5){
				faltante=faltante+"\n";
				nn=0;
			}else{
				faltante=faltante+", ";
			}
		}
		if(faltante.length()==0)faltante = "NINGUNO";		
		ArrayList<String> renglonContado = listMarbetesContado.get(fila);
		String contado="";
		nn=0;
		for(int n=1; renglonContado.size()>n ;n++){
			if(contado.length()>0){
				contado=contado+renglonContado.get(n);
			}else{
				contado=renglonContado.get(n);
			}
			nn++;
			if(nn==5){
				contado=contado+"\n";
				nn=0;
			}else{
				contado=contado+", ";
			}
		}
		if(contado.length()==0) contado="NINGUNO";
		String mensaje = "UBICACION "+renglonFaltante.get(0)+"     TOTAL MARBETES "+renglonFaltante.get(1)+
				"\n\n  MARBETES FALTANTES:\n"+faltante+"\n\n  MARBETES CONTADOS:\n"+contado;
		JOptionPane.showMessageDialog(vtana, mensaje, "Informacion Marbete", JOptionPane.INFORMATION_MESSAGE,
				vtana.information);
	}
	
	String mensaje(int fila){		
		return "TOTAL MARBETES "+listMarbete.get(fila).get(1)+" MARBETES CONTADOS: "+
					(listMarbetesContado.get(fila).size()-1);		
	}

}
