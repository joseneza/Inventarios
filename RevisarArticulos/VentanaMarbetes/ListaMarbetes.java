package RevisarArticulos.VentanaMarbetes;

/* Clase para los datos de revision de marbetes */
public class ListaMarbetes {
	
	String scan;
	String nombre;
	String ubicacion;
	String marbete;
	int total;
	int error;
	Boolean escojer;
	
	public ListaMarbetes(String scan, String nombre, String ubicacion, String marbete, int total, int error, Boolean escojer){
		this.scan=scan;
		this.nombre=nombre;
		this.ubicacion=ubicacion;
		this.marbete=marbete;
		this.total=total;
		this.error=error;
		this.escojer=escojer;
	}
	
	public String getScan(){
		return scan;
	}
	
	public void setScan(String scan){
		this.scan=scan;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public String getUbiacion(){
		return ubicacion;
	}
	
	public void setUbiacion(String ubicacion){
		this.ubicacion=ubicacion;
	}
	
	public String getMarbete(){
		return marbete;
	}
	
	public void setMarbete(String marbete){
		this.marbete=marbete;
	}
	
	public int getTotal(){
		return total;
	}
	
	public void setTotal(int total){
		this.total=total;
	}
	
	public int getError(){
		return error;
	}
	
	public void setError(int error){
		this.error=error;
	}
	
	public Boolean getEscojer(){
		return escojer;
	}
	
	public void setEscojer(Boolean escojer){
		this.escojer=escojer;
	}

}
