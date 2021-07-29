package Marbetes.MarbetesInventario;


public class DatosTablaMarbetes {
	
	String ubicacion;
	String marbetes;
	String avance;
	
	public DatosTablaMarbetes(String ubicacion,String cant,String avance){
		this.ubicacion=ubicacion;
		this.marbetes=cant;
		this.avance=avance;
	}
	
	public String getUbicacion(){
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion){
		this.ubicacion=ubicacion;
	}
	
	public String getMarbetes(){
		return marbetes;
	}
	
	public void setMarbetes(String marbetes){
		this.marbetes=marbetes;
	}
	
	public String getAvance(){
		return avance;
	}
	
	public void setAvance(String avance){
		this.avance=avance;
	}

}
