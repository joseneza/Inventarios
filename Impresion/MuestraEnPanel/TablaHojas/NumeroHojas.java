package Impresion.MuestraEnPanel.TablaHojas;

public class NumeroHojas {
	
	Boolean selec;
	int hoja;
	String informacion;
	
	public NumeroHojas(int hoja, String informacion){
		selec=false;
		this.hoja=hoja;
		this.informacion=informacion;		
	}
	
	public void setSelec(Boolean selec){
		this.selec=selec;
	}
	
	public Boolean getSelec(){
		return selec;
	}
	
	public int getHoja(){
		return hoja;
	}
	
	public String getInfo(){
		return informacion;
	}

}
