package Marbetes;
/* Programa Inventarios
 * Clase que crea los marbetes que se capturan
 * en la tabla de marbetes
 */
import java.util.ArrayList;

public class GenerarMarbetes {
	String tipo;
	long max;
	public GenerarMarbetes(String tipo, int tamano){
		this.tipo=tipo;
		max=cargarLimite(tamano);
	}
	
	private Long cargarLimite(int tamano){
		String cadena="";
		int num=tamano-tipo.length();
		for(int n=0;n<num;n++){
			cadena=cadena+"9";
		}
		return Long.parseLong(cadena);		
	}
	
	
	public ArrayList<String> cargarMarbetes(String ubicacion, String cantidad, String inicio){
		ArrayList<String> parcial = new ArrayList<String>();
		String marb="";
		long cant;
		long in;
		try{
			cant=Integer.parseInt(cantidad);
		}catch(NumberFormatException ex){
			cant=0;
		}
		try{
			in=Long.parseLong(inicio);
		}catch(NumberFormatException ex){
			in=max;
		}
		if((in+cant)>max){
			cant=max-in;
		}
		if(cant>0){			
			parcial.add(ubicacion);
			parcial.add(cantidad);
			for(int ini=0; ini<=cant-1;ini++){
				marb=Long.toString(in+ini);
				for(int c=marb.length();c<Long.toString(max).length();c++){
					marb="0"+marb;
				}
				marb=tipo+marb;
				parcial.add(marb);
			}			
		}	
		return parcial;
	}

}
