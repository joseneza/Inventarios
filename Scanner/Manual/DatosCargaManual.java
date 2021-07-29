package Scanner.Manual;

public class DatosCargaManual {
	
	private String linea;
	private String art = "";
	private String cant = "";
	private Boolean exist = false;
	private String descripcion = "";
	
	public DatosCargaManual(String linea) {
		this.linea = linea;
	}
	
	public String getLinea(){
		return linea;
	}
	
	public void setLinea(String linea){
		this.linea=linea;
	}
	
	public String getArt(){
		return art;
	}
	
	public void setArt(String art){
		this.art=art;
	}
	
	public String getCant() {
		return cant;
	}
	
	public void setCant(String cant) {
		this.cant=cant;
	}
	
	public Boolean getExist() {
		return exist;
	}
	
	public void setExist(Boolean exist) {
		this.exist=exist;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

}
