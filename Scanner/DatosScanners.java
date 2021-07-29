package Scanner;
/* Clase para los datos del escaner */
public class DatosScanners {
	
	int fila;
	String scan;
	int consecutivo;
	String marbete;
	long art;
	Boolean ean;
	short cant;
	String descr;
	Boolean existe;
	
	public DatosScanners(int fila, String scan, int consecutivo, String marbete, long art, Boolean ean, short cant,
			String descr, Boolean existe){
		this.fila=fila;
		this.scan=scan;
		this.consecutivo=consecutivo;
		this.marbete=marbete;
		this.art=art;
		this.ean=ean;
		this.cant=cant;
		this.descr=descr;
		this.existe=existe;
	}
	
	public int getFila(){
		return fila;
	}
	
	public void setFila(int fila){
		this.fila=fila;
	}
	
	public String getScan(){
		return scan;
	}
	
	public void setScan(String scan){
		this.scan=scan;
	}	
	
	public int getConsecutivo(){
		return consecutivo;
	}
	
	public void setConsecutivo(int consecutivo){
		this.consecutivo=consecutivo;
	}	
	
	public String getMarbete(){
		return marbete;
	}
	
	public void setMarbete(String marbete){
		this.marbete=marbete;
	}
	
	
	public long getArt(){
		return art;
	}

	public void setArt(long art){
		this.art=art;
	}	
	
	public Boolean getEan(){
		return ean;
	}
	
	public void setEan(Boolean ean) {
		this.ean=ean;
	}
	
	public short getCant(){
		return cant;
	}
	
	public void setCant(short cant){
		this.cant=cant;
	}
	
	public String getDescripcion(){
		return descr;
	}
	
	public void setDescripcion(String descr){
		this.descr=descr;
	}
	
	public Boolean getExiste(){
		return existe;
	}
	
	public void setExiste(Boolean existe){
		this.existe=existe;
	}

}
