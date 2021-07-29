package Inicio.CatalogoArticulos;

public class DatosMarbetes {
	
	String scan;
	String nombre;
	String ubic;
	String marbete;
	int total;
	int error;
	
	public DatosMarbetes(String scan, String nombre, String ubic, String marbete, int total, int error){
		this.scan=scan;
		this.nombre=nombre;
		this.ubic=ubic;
		this.marbete=marbete;
		this.total=total;
		this.error=error;		
	}
	
	public void setScan(String scan){
		this.scan=scan;
	}
	
	public String getScan(){
		return scan;
	}
	
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setUbic(String ubic){
		this.ubic=ubic;
	}
	
	public String getUbic(){
		return ubic;
	}
	
	public void setMarbete(String marbete){
		this.marbete=marbete;
	}
	
	public String getMarbete(){
		return marbete;
	}
	
	public void setTotal(int total){
		this.total=total;
	}
	
	public int getTotal(){
		return total;
	}
	
	public void setError(int error){
		this.error=error;
	}
	
	public int getError(){
		return error;
	}

}
