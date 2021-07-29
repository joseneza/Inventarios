package RevisarArticulos.VentanaRevision;

/* Clase para mostrar los datos que se van a revisar
 * en el inventario
 */

public class DatosRevision {
	
	String linea;
	String scan;
	int consecutivo;
	String contador;
	String ubic;
	String marb;
	long art;
	short cant;
	String descripcion;
	Boolean agregar;
	Boolean borrar;
	Boolean cambiar;
	private long valorArt;
	private short valorCant;
	Boolean existe;
	
	public DatosRevision(String linea, String scan, int consecutivo, String contador, String ubic, String marb,
			long art, short cant, String descripcion, Boolean existe, Boolean agregar, Boolean borrar, Boolean cambiar){//Constructor
		this.linea=linea;
		this.scan=scan;
		this.consecutivo=consecutivo;
		this.contador=contador;
		this.ubic=ubic;
		this.marb=marb;
		this.art=art;
		this.cant=cant;
		this.descripcion=descripcion;
		this.agregar=agregar;
		this.borrar=borrar;
		this.cambiar=cambiar;
		this.valorArt=art;
		this.valorCant=cant;
		this.existe=existe;
	}
	
	public String getFila(){//Metodo para mostrar linea
		return linea;
	}
	
	public void setFila(String linea){//Metodo para cambiar el linea
		this.linea=linea;
	}
	
	public String getScan(){//Metodo para mostrar scanner
		return scan;
	}
	
	public void setScan(String scan){//Metodo para cambiar el scanner
		this.scan=scan;
	}
	
	public int getConsecutivo(){
		return consecutivo;
	}
	
	public void setConsecutivo(int consecutivo){
		this.consecutivo=consecutivo;
	}
	
	public String getContador(){//Metodo para mostrar contador
		return contador;
	}
	
	public void setCont(String cont){//Metodo para cambiar el contador
		this.contador=contador;
	}
	
	public String getUbic(){//Metodo para mostrar ubicacion
		return ubic;
	}
	
	public void setUbic(String ubic){//Metodo para cambiar el ubicacion
		this.ubic=ubic;
	}
	
	public String getMarb(){//Metodo para mostrar marbete
		return marb;
	}
	
	public void setMarb(String marb){//Metodo para cambiar el marbete
		this.marb=marb;
	}
	
	public long getArt(){//Metodo para mostrar articulo
		return art;
	}
	
	public void setArt(long art){//Metodo para cambiar el articulo
		this.art=art;
	}
	
	public short getCant(){//Metodo para mostrar cantidad
		return cant;
	}
	
	public void setCant(short cant){//Metodo para cambiar el cantidad
		this.cant=cant;
	}
	
	public String getDescripcion(){
		return descripcion;		
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion=descripcion;
	}
	
	public Boolean getAgregar(){//Metodo para mostrar agregar
		return agregar;
	}
	
	public void setAgregar(Boolean agregar){//Metodo para cambiar el agregar
		this.agregar=agregar;
	}
	
	public Boolean getBorrar(){//Metodo para mostrar borrar
		return borrar;
	}
	
	public void setBorrar(Boolean borrar){//Metodo para cambiar el borrar
		this.borrar=borrar;
	}
	
	public Boolean getCambiar(){//Metodo para mostrar cambiar valor
		return cambiar;
	}
	
	public void setCambiar(Boolean cambiar){//Metodo para cambiar el cambiar valor
		this.cambiar=cambiar;
	}
	
	public long getValorArt() {
		return valorArt;
	}
	
	public long getValorCant() {
		return valorCant;
	}
	
	public void setExiste(Boolean existe){
		this.existe=existe;		
	}
	
	public Boolean getExiste() {
		return existe;
	}

}
