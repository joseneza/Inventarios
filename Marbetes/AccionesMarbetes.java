package Marbetes;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import Inicio.VentanaPrincipal.VentanaPrincipal;
import Marbetes.ImprimirMarbetes.DatosMarbete;
import Marbetes.MarbetesInventario.OperacionesMarbetesInventario;
import Marbetes.TablaMarbetes.DatosLineaMarbete;
import Scanner.TablaScanner.PanelAgregarScanner;

class AccionesMarbetes {
	
	VentanaPrincipal vtana;
	AgregarMarbetes agrMarb;
	
	AccionesMarbetes(AgregarMarbetes agrMarb, VentanaPrincipal vtana){
		this.vtana = vtana;
		this.agrMarb = agrMarb;
	}
	
	void imprMarbetes(){
		Boolean conMarb = false;
		Iterator<DatosLineaMarbete> revMarbetes = agrMarb.modTablaAbst.datos.iterator();
		while(revMarbetes.hasNext()){
			DatosLineaMarbete linea = revMarbetes.next();
			if(linea.getCantidad().length()>0 && linea.getUbicacion().length()>0) conMarb = true;
		}
		if(conMarb==true){
			vtana.mensajeEstado.setText("Inicio de Inventario - Ingreso de Marbetes");
			if(agrMarb.excederMarbetes()==true){
				JOptionPane.showMessageDialog(vtana, "Has excedido el limite de marbetes que se permite, reduce el maximo de marbetes o "+
							"cambia la confuguracion del inventario.", "Limite Excedido", JOptionPane.INFORMATION_MESSAGE,
							vtana.information);
			}else{
				int y=JOptionPane.showConfirmDialog(vtana, "¿Quieres imprimir los marbetes para este inventario?", "IMPRIMIR MARBETES",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, vtana.question);
				if(y==0){
					ArrayList<DatosMarbete> listaMarbetes = agrMarb.generarMarbetes();
					agrMarb.imprimir(listaMarbetes);
				}
				vtana.menu.menuInventario.remove(agrMarb);
			}
		}else{
			vtana.mensajeEstado.setText("Inicio de Inventario - Ingreso de Marbetes - No agregaste ningun marbete");
		}
	}
	
	void genMarbetes(){
		Boolean conMarb = false;
		ArrayList<DatosMarbete> listaMarbetes;
		RespaldarMarbetes guardarMarbetes;
		Iterator<DatosLineaMarbete> revMarbetes = agrMarb.modTablaAbst.datos.iterator();
		while(revMarbetes.hasNext()){
			DatosLineaMarbete linea = revMarbetes.next();
			if(linea.getCantidad().length()>0 && linea.getUbicacion().length()>0) conMarb = true;
		}
		if(conMarb==true){
			vtana.mensajeEstado.setText("Inicio de Inventario - Ingreso de Marbetes");
			if(agrMarb.excederMarbetes()==true){
				JOptionPane.showMessageDialog(vtana, "Has excedido el limite de marbetes que se permite, reduce el maximo de marbetes o "+
							"cambia la confuguracion del inventario.", "Limite Excedido", JOptionPane.INFORMATION_MESSAGE,
							vtana.information);
			}else{
				int y=JOptionPane.showConfirmDialog(vtana, "¿Quieres terminar con la captura de marbetes?",
						"Inventario ",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, vtana.question);
				if(y==0){
					listaMarbetes = agrMarb.generarMarbetes();
					int p=JOptionPane.showConfirmDialog(vtana, "¿Quieres imprimir los marbetes antes de continuar?",
							"IMPRIMIR MARBETES", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, vtana.question);
					if(p==0){
						agrMarb.imprimir(listaMarbetes);
						if(agrMarb.impr==false)listaMarbetes = new ArrayList<DatosMarbete>();
					}
					if(listaMarbetes.size()>0){
						agrMarb.oper=true;
						vtana.listMarbete = agrMarb.marbetes;
						vtana.listMarbeteContado = agrMarb.marbetesContados;
						guardarMarbetes = new RespaldarMarbetes(agrMarb.modTablaAbst.datos, 
								vtana.nvoinv.nomInv.textoTitulo.getText(), vtana.dirInventario,
								agrMarb.tipoMarbete, agrMarb.max);
						vtana.iniMarb=true;
						vtana.panelScanner=new PanelAgregarScanner(vtana);
						agrMarb.setVisible(false);
						vtana.panelTabla.setBounds(2, 0, 430, 370);
						vtana.panelTabla.add(vtana.panelScanner);
						vtana.operInv=new OperacionesMarbetesInventario(vtana);
						vtana.mensajeEstado.setText("Agregar Scanners");
						vtana.botones.btnNvoInvent.setEnabled(false);
						vtana.menu.nuevo.setEnabled(false);
						vtana.botones.btnRevisArt.setEnabled(true);
						vtana.menu.revisar.setEnabled(true);
						vtana.botones.btnImpPreCont.setEnabled(true);
						vtana.menu.imprimir.setEnabled(true);
						vtana.botones.btnMostResultados.setEnabled(true);
						vtana.menu.exportar.setEnabled(true);
						vtana.menu.conteo.setEnabled(true);
						vtana.menu.menuInventario.remove(agrMarb.mMarbetes);
					}
				}						
			}
		}else{
			vtana.mensajeEstado.setText("Inicio de Inventario - Ingreso de Marbetes - No agregaste ningun marbete");
		}		
	}
	
	void cancMarbetes(){
		agrMarb.oper=false;
		int Y=JOptionPane.showConfirmDialog(vtana, "Si se cancela el ingreso de marbetes, no se puede continuar con el Inventario."+
				"\n¿Seguro que quieres continuar?",	"INVENTARIO CANCELADO",	JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, vtana.cancelar);
		if(Y==0){
			agrMarb.setVisible(false);
			vtana.botones.btnNvoInvent.setEnabled(true);
			vtana.menu.nuevo.setEnabled(true);
			vtana.botones.btnConfig.setEnabled(true);
			vtana.menu.configuracion.setEnabled(true);
			vtana.setTitle(VentanaPrincipal.titulo);
			vtana.menu.menuInventario.remove(agrMarb.mMarbetes);
		}
	}

}
