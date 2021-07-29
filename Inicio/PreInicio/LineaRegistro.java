package Inicio.PreInicio;

public class LineaRegistro {
	
	private int fila;
	private short tipo;
	private long sku;
	private String scan;
	private String marb;
	private short cant;
	private String descr;
	
	public LineaRegistro(int fila, short tipo, long sku,  short cant, String scan, String  marb, String descr){
		this.fila=fila;
		this.tipo=tipo;
		this.sku=sku;
		this.scan=scan;
		this.marb=marb;
		this.cant=cant;
		this.descr=descr;
	}
	
	public int getFila(){
		return this.fila;
	}
	
	public void setFila(int fila){
		this.fila=fila;
	}
	
	public short getTipo(){
		return this.tipo;
	}
	
	public void setTipo(short tipo){
		this.tipo=tipo;
	}
	
	public long getSku(){
		return this.sku;
	}
	
	public void setSku(long sku){
		this.sku=sku;
	}
	
	public String getScan(){
		return this.scan;
	}
	
	public void setScan(String scan){
		this.scan=scan;
	}
	
	public String getMarbete(){
		return this.marb;
	}
	
	public void setMarbete(String marb){
		this.marb=marb;
	}
	
	public short getCant(){
		return this.cant;
	}
	
	public void setCant(short cant){
		this.cant=cant;
	}
	
	public String getDescripcion(){
		return descr;		
	}

}
