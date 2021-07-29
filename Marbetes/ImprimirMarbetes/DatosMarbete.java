package Marbetes.ImprimirMarbetes;

public class DatosMarbete {
	
	private String ubicacion;
	private String marbete;
	
	public DatosMarbete(String ubicacion, String marbete){
		this.ubicacion=ubicacion;
		this.marbete=marbete;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion){
		this.ubicacion=ubicacion;
	}
	
	public String getMarbete(){
		return marbete;
	}
	
	public void setMarbete(String marbete){
		this.marbete=marbete;
	}

}
