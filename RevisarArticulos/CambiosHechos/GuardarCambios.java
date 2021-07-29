package RevisarArticulos.CambiosHechos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.swing.JOptionPane;

import Inicio.CatalogoArticulos.DatosMarbetes;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import RevisarArticulos.VentanaMarbetes.ListaMarbetes;
import RevisarArticulos.VentanaMarbetes.VentanaMarbete;
import RevisarArticulos.VentanaRevision.DatosRevision;
import RevisarArticulos.VentanaRevision.VentanaRevision;

public class GuardarCambios {
	
	VentanaPrincipal vtana;
	VentanaRevision vtanaRev;
	RegistroCambios agrReg;
	ArrayList<String> registro;
	int lineaUno;
	int consecutivo;
	int opcion;
	private String tipOpcion;
	int num=0;
	
	public GuardarCambios(VentanaPrincipal vtana, VentanaRevision vtanaRev, VentanaMarbete vtanaMarb, int opcion){//Constructor General		
		this.vtana = vtana;
		this.vtanaRev=vtanaRev;
		this.opcion=opcion;
		this.lineaUno=Integer.parseInt(vtanaRev.lineaUno);
		this.consecutivo=vtanaRev.tablaRev.modRev.datRev.get(0).getConsecutivo();
		switch (opcion) {
		case 1:
			tipOpcion=" No Encontrados";
			break;
		case 2:
			tipOpcion=" Marbetes Duplicados";
			break;
		case 3:
			tipOpcion=" Revision Completa";
			break;
		default:
			break;
		}
		actualizarDatos(vtanaRev.tablaRev.modRev.datRev, vtanaMarb);
	}
	
	public GuardarCambios(VentanaPrincipal vtana, ArrayList<DatosRevision> datRev, VentanaMarbete vtanaMarb, int opcion){
		//Constructor para Borrar Marbetes
		this.vtana = vtana;
		this.opcion=opcion;
		tipOpcion=" Marbetes Duplicados - Borrar Marbetes";
		actualizarDatos(datRev, vtanaMarb);
	}
	
	void actualizarDatos(ArrayList<DatosRevision> datRev, VentanaMarbete vtanaMarb){
		registro = new ArrayList<String>();
		for(int totLineasRev = datRev.size()-1; totLineasRev>-1; totLineasRev--){
			DatosRevision linea = (DatosRevision) datRev.get(totLineasRev);
			if(linea.getAgregar() != linea.getBorrar()){
				if(linea.getBorrar()==true){//Borrar linea
					for(int n=0;n<vtana.datMarbete.size();n++){//Actualiza la lista de tituloScan
						DatosMarbetes lin = (DatosMarbetes) vtana.datMarbete.get(n);
						if(lin.getScan().equals(linea.getScan()) & lin.getMarbete().equals(linea.getMarb())){
							lin.setTotal(lin.getTotal()-linea.getCant());
							if(linea.getExiste().equals(false)){
								lin.setError(lin.getError()-linea.getCant());
							}
							Iterator<ListaMarbetes> rev=vtanaMarb.panelTabla.tabRevMarv.listMarb.iterator();
							while(rev.hasNext()){
								ListaMarbetes linRev=(ListaMarbetes) rev.next();
								if(linRev.getScan().equals(linea.getScan()) & linRev.getMarbete().equals(linea.getMarb())){
									linRev.setTotal(lin.getTotal());
									linRev.setError(lin.getError());
								}
							}
						}
					}					
					borrarArticulo(linea);
					vtana.artBorr=vtana.artBorr+linea.getCant();
				}else{
					if(linea.getAgregar()!=linea.getExiste()){
						if(linea.getAgregar()==true){//Agregar linea
							for(int n=0;n<vtana.datMarbete.size();n++){//Actualiza la lista de tituloScan
								DatosMarbetes lin = (DatosMarbetes) vtana.datMarbete.get(n);
								Boolean tipo=(Boolean) vtana.panelScanner.tablaScanners.nvomodelo.getValueAt(Integer.parseInt(linea.getFila()), 6);
								if(tipo==false){
									if(lin.getScan().equals(linea.getScan()) & lin.getMarbete().equals(linea.getMarb())){
										lin.setError(lin.getError()-linea.getCant());
										Iterator<ListaMarbetes> rev=vtanaMarb.panelTabla.tabRevMarv.listMarb.iterator();
										while(rev.hasNext()){
											ListaMarbetes linRev=(ListaMarbetes) rev.next();
											if(linRev.getScan().equals(linea.getScan()) & linRev.getMarbete().equals(linea.getMarb())){
												linRev.setError(lin.getError());
											}
										}
									}
								}								
							}
						agregarArticulo(linea);
						num++;
						}
					}
				}
			}else{//Cambiar Datos
				if(linea.getCambiar()==true & linea.getArt()>0 & linea.getCant()>0){
					Boolean exist = vtana.listCatalogo.contains(linea.getArt());
					if(exist==false){
						int Y=JOptionPane.showConfirmDialog(vtana, "El articulo "+linea.getArt()+" no se encontro en el catalogo "+
								"¿aun asi deseas agregarlo?", "Articulo no encontrado", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, vtana.question);
						if(Y==0){
							vtana.listCatalogo.add(linea.getArt());
							if(linea.getDescripcion().length()==0){
								linea.setDescripcion("Articulo sin descripcion");
							}
							vtana.listDescripcion.add(linea.getDescripcion());
							exist = true;
						}
					}
					if(exist==true){
						if(linea.getDescripcion().length()==0 | linea.getDescripcion().equals("\"ERROR\" Articulo no encontrado")){
							int nArt = vtana.listCatalogo.indexOf(linea.getArt());
							if(nArt>-1){
								linea.setDescripcion(vtana.listDescripcion.get(nArt));
							}
						}
						if(linea.getFila().equals("nvo")){//Nvo ingreso de articulos
							vtana.panelScanner.tablaScanners.nvomodelo.nvaFila(lineaUno, linea.getScan(), linea.getMarb(),
									linea.getArt(), false, linea.getCant(), linea.getDescripcion(), exist);
							registro.add("4|"+linea.getScan()+"|"+linea.getConsecutivo()+"|"+linea.getMarb()+"|"+linea.getArt()+
									"|"+linea.getCant()+"|"+linea.getDescripcion());
							for(int n=0;n<vtana.datMarbete.size();n++){//Actualiza la lista de tituloScan
								DatosMarbetes lin = (DatosMarbetes) vtana.datMarbete.get(n);
								if(lin.getScan().equals(linea.getScan()) & lin.getMarbete().equals(linea.getMarb())){
									lin.setTotal(lin.getTotal()+linea.getCant());
									Iterator<ListaMarbetes> rev=vtanaMarb.panelTabla.tabRevMarv.listMarb.iterator();
									while(rev.hasNext()){
										ListaMarbetes linRev=(ListaMarbetes) rev.next();
										if(linRev.getScan().equals(linea.getScan()) & linRev.getMarbete().equals(linea.getMarb())){
											linRev.setTotal(lin.getTotal());
										}
									}
								}								
							}
							vtana.artAd=vtana.artAd+linea.getCant();
						}else{
							cambiarArticulo(linea);
							if(linea.getCant()>linea.getValorCant()){
								vtana.artAd=(int) (vtana.artAd+linea.getCant()-linea.getValorCant());
							}else{
								vtana.artBorr=(int) (vtana.artBorr+linea.getCant()-linea.getValorCant());
							}
								for(int n=0;n<vtana.datMarbete.size();n++){//Actualiza la lista de tituloScan
									DatosMarbetes lin = (DatosMarbetes) vtana.datMarbete.get(n);
									if(lin.getScan().equals(linea.getScan()) & lin.getMarbete().equals(linea.getMarb())){
										lin.setTotal((int) (lin.getTotal()+linea.getCant()-linea.getValorCant()));
										lin.setError((int) (lin.getError()-linea.getValorCant()));
										Iterator<ListaMarbetes> rev=vtanaMarb.panelTabla.tabRevMarv.listMarb.iterator();
										while(rev.hasNext()){
											ListaMarbetes linRev=(ListaMarbetes) rev.next();
											if(linRev.getScan().equals(linea.getScan()) & linRev.getMarbete().equals(linea.getMarb())){
												linRev.setError(lin.getError());
											}
										}
									}								
								}
							registro.add("2|"+linea.getScan()+"|"+linea.getConsecutivo()+"|"+linea.getMarb()+"|"
									+linea.getArt()+"|"+linea.getCant()+"|"+linea.getDescripcion());
						}
					}
				}
			}
		}
		cargarRegistro();//Graba la hora del registro
		vtanaMarb.panelTabla.tabRevMarv.fireTableDataChanged();
		vtana.granTot=vtana.totArt+vtana.artAd-vtana.artBorr;
		vtana.panelAvance.lblTotArticulos.setText("Se han contabilizado "+vtana.totArt+" Articulos.");
		vtana.panelAvance.lblTotAgregados.setText("Se han agregado "+vtana.artAd+" Articulos.");
		vtana.panelAvance.lblTotBorrados.setText("Se han borrado "+vtana.artBorr+" Articulos.");
		vtana.panelAvance.lblGranTotal.setText("Total "+vtana.granTot+" Articulos.");	
		vtana.panelScanner.tablaScanners.nvomodelo.ajustarFilas();
		vtanaMarb.quitarMarbeteCeros();
		if(opcion==1){
			vtanaMarb.quitarMarbetesSinErrores();
		}
	}
	
	private void borrarArticulo(DatosRevision linea){
		vtana.panelScanner.tablaScanners.nvomodelo.borrarFila(Integer.parseInt(linea.getFila()));
		registro.add("3|"+linea.getScan()+"|"+linea.getConsecutivo()+"|"+linea.getMarb()+"|"
				+linea.getArt()+"|"+linea.getCant());
	}
	
	private void agregarArticulo(DatosRevision linea){
		String descrip=linea.getDescripcion();
		if(descrip.equals("\"ERROR\" Articulo no encontrado") || descrip.equals("")){
			descrip="Sin Descripcion";
		}
		int lin=vtana.listCatalogo.indexOf(linea.getArt());
		if(lin>=0){
			if(vtana.listDescripcion.get(lin).equals("Sin Descripcion")){
				vtana.listDescripcion.remove(lin);
				vtana.listDescripcion.add(lin, descrip);
			}else{
				descrip=vtana.listDescripcion.get(lin);
			}
		}else{
			vtana.listCatalogo.add(linea.getArt());			
			vtana.listDescripcion.add(descrip);
		}		
		vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(true, Integer.parseInt(linea.getFila()), 6);
		vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(descrip, Integer.parseInt(linea.getFila()), 7);
		registro.add("1|"+linea.getScan()+"|"+linea.getConsecutivo()+"|"+linea.getMarb()+"|"
				+linea.getArt()+"|"+linea.getCant()+"|"+descrip);
	}
	
	private void cambiarArticulo(DatosRevision linea){
		vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(linea.getArt(), Integer.parseInt(linea.getFila()), 3);
		vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(linea.getCant(), Integer.parseInt(linea.getFila()), 5);
		vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(true, Integer.parseInt(linea.getFila()), 6);
		vtana.panelScanner.tablaScanners.nvomodelo.setValueAt(linea.getDescripcion(), Integer.parseInt(linea.getFila()), 7);
	}
	
	private void cargarRegistro(){//Graba el registro
		agrReg = new RegistroCambios(vtana.dirInventario+"\\RegistroCambios.dat", true);//Abriendo registro
		SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");//Clase para tomar hora y fecha del registro
		GregorianCalendar gc = new GregorianCalendar();
		String fe=fecha.format(gc.getTime());//La fecha y hora se convierte en un String para guardar
		vtana.numreg++;
		agrReg.escribirLinea("rg"+vtana.numreg+"|"+fe+"|"+tipOpcion);
		for(int x=registro.size()-1;x>=0;x--){
			agrReg.escribirLinea(registro.get(x));
		}
		agrReg.cerrarReg();//Cerrando registro
	}
	
}
