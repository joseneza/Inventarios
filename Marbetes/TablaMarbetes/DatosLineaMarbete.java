package Marbetes.TablaMarbetes;
/*Inventarios V 0.3.2
 * Clase que genera la linea de datos de los marbetes
 * 
 */
public class DatosLineaMarbete {
	
	private String linea;
	private String ubicacion="";
	private String cantidad="";
	private String ini;
	private String fin="";
	private String tipo;
	private long limite;
	private int maxCarLimite;
	
	
	public DatosLineaMarbete(String linea,String ini,String tipo,long limite){
		this.linea=linea;
		this.ini=ini;
		this.tipo=tipo;
		this.limite=limite;
		maxCarLimite=Long.toString(limite).length();
	}
	
	public String getLinea(){
		return linea;
	}
	
	public void setLinea(String linea){
		this.linea=linea;
	}
	
	public String getCantidad(){
		return cantidad;
	}
	
	public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
	
	public String getUbicacion(){
		return ubicacion;
	}	
	
	public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
	
	public String getInicio() {
		String marb="";
		String inicio;
		try{
			inicio=Long.toString(Long.parseLong(ini.substring(tipo.length()))+1);
			if(Long.parseLong(inicio)>=limite){
				marb=Long.toString(limite);
			}else{
				marb=inicio;
			}
		}catch(NumberFormatException e){
			marb=Long.toString(limite);
		}
		for(int c=marb.length();c<maxCarLimite;c++){//se crea el marbete de inicio
			marb="0"+marb;
		}
		marb=tipo+marb;
		return marb;	
	}
	
	public void setInicio(String ini) {
		this.ini=ini;		
	}
	
	public String getFin(){
		String marb="";
		long inicio;
		long cant;
		if(cantidad.isEmpty()){
			cant=0;
		}else{
			try{
				cant=Integer.parseInt(cantidad);
			}catch(NumberFormatException e){
				cant=limite;
			}
		}
		try{
			inicio=Long.parseLong(ini.substring(tipo.length()));
		}catch(NumberFormatException e){
			inicio=limite;
		}
		if((inicio+cant)>limite){
			marb=Long.toString(limite);
		}else{
			marb=Long.toString(cant+inicio);
		}
		for(int c=marb.length();c<maxCarLimite;c++){//Se crea el marbete final
			marb="0"+marb;
		}
		marb=tipo+marb;
		return marb;
	}
	
	public void setFin(String fin){
		this.fin=fin;
	}

}