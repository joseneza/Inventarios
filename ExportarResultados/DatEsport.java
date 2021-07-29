package ExportarResultados;

public class DatEsport {
	
	long art;
	short cant;
	
	public DatEsport(long art, short cant){
		this.art=art;
		this.cant=cant;
	}
	
	public void setArt(long art){
		this.art=art;
	}
	
	public long getArt(){
		return art;
	}
	
	public void setCant(short cant){
		this.cant=cant;
	}
	
	public short getCant(){
		return cant;
	}
}
