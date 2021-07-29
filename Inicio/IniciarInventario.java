package Inicio;
import Inicio.VentanaPrincipal.VentanaPrincipal;
import Marbetes.AgregarMarbetes;
import Marbetes.InicioNombreInv;

public class IniciarInventario {
	
	String titulo;
	VentanaPrincipal vtana;
	public InicioNombreInv nomInv;
	public Boolean oper=false;
	public String mensaje;
	
	public IniciarInventario(VentanaPrincipal vtana){
		this.vtana=vtana;
		iniciarAcciones();
	}
	
	private void iniciarAcciones(){
		mensaje="Inicio de Inventario - Cargando Titulo";
		vtana.mensajeEstado.setText(mensaje);
		nomInv=new InicioNombreInv(vtana);
		if(nomInv.valor==0){
			vtana.setTitle(nomInv.textoTitulo.getText() + " - " + vtana.getTitle());
			cargarDatos();
			System.out.println("Inicio Inventario");
		}
	}
	
	private void cargarDatos(){
		vtana.botones.btnNvoInvent.setEnabled(false);
		vtana.menu.nuevo.setEnabled(false);
		vtana.botones.btnConfig.setEnabled(false);
		vtana.menu.configuracion.setEnabled(false);
		AgregarMarbetes nvosMarbetes=new AgregarMarbetes(vtana);
		mensaje="Inicio de Inventario - Ingreso de Marbetes";
		vtana.mensajeEstado.setText(mensaje);
		vtana.panelTabla.setBounds(2, 0, 500, 380);
		vtana.panelTabla.add(nvosMarbetes);
	}


}
