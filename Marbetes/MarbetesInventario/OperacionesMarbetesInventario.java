package Marbetes.MarbetesInventario;

import Inicio.VentanaPrincipal.VentanaPrincipal;

public class OperacionesMarbetesInventario {
	
	VentanaPrincipal vtana;
	public PanelMarbetesInventario panMarb;
	//private long limite;
	
	public OperacionesMarbetesInventario(VentanaPrincipal vtana) {
		this.vtana=vtana;
		cargarMarbetes();
	}
		
	private void cargarMarbetes(){
		panMarb=new PanelMarbetesInventario(vtana);
		vtana.panelExtra.add(panMarb);
		for(int a=0;a<vtana.listMarbete.size();a++){			
			String avance=avanceMarbetes(a);
			panMarb.modMarbInv.addFila(vtana.listMarbete.get(a).get(0),vtana.listMarbete.get(a).get(1),avance);
		}
	}
	
	public int modifMarbetes(String scanner){
		Boolean existe=false;
		int fila=-1;
		for(int a=0;a<vtana.listMarbete.size();a++){
			if(vtana.listMarbete.get(a).contains(scanner)==true){
				existe=true;
				fila=a;
				vtana.listMarbete.get(a).remove(scanner);
				vtana.listMarbeteContado.get(a).add(scanner);
			}
		}
		return fila;
	}
	
	public String avanceMarbetes(int fila){
		double marbetesRestantes;
		double totMarbetes;
		marbetesRestantes=vtana.listMarbete.get(fila).size()-2;
		totMarbetes=Double.parseDouble(vtana.listMarbete.get(fila).get(1));
		String avance;
		String nvoAvance = "";
		Boolean punto=false;
		String l;
		int decimal=0;
		try{
			avance=Double.toString(((totMarbetes-marbetesRestantes)/totMarbetes)*100);//Se convierte a String para poder manipular como texto
		}catch(NumberFormatException ex){
			avance="0.00";		
		}
		
		for(int a=0;a<avance.length();a++){//Inicia conversion a numero con centecimos
			l=avance.substring(a,a+1);
			if(l.equals(".")){
				punto=true;
			}
			if(punto==true){
				decimal++;
			}
			nvoAvance=nvoAvance+l;
			if(decimal>2){
				a=avance.length();
				avance=nvoAvance;
			}
		}
		if(punto==true){
			while (decimal<=2){
				avance=avance+"0";
				decimal++;
			}
		}
		avance=avance+" %";
		return avance;		
	}
	
	public String avanceTotal(){
		Double porc=0.0;
		String avance="";
		String l;
		String nvoAvance="";
		Boolean punto=false;
		int decimal = 0;
		int b;
		for(b=0;b<vtana.listMarbete.size();b++){
			double totMarbetes=Double.parseDouble(vtana.listMarbete.get(b).get(1));
			double marbetesRestantes=vtana.listMarbete.get(b).size()-2;
			porc=porc+((totMarbetes-marbetesRestantes)/totMarbetes);
		}		
		avance=Double.toString(porc/b*100);
		for(int a=0;a<avance.length();a++){
			l=avance.substring(a,a+1);
			if(l.equals(".")){
				punto=true;
			}
			if(punto==true){
				decimal++;
			}
			nvoAvance=nvoAvance+l;
			if(decimal>2){
				a=avance.length();
				avance=nvoAvance;
			}
		}
		if(punto==true){
			while (decimal<=2){
				avance=avance+"0";
				decimal++;
			}
		}
		avance=avance+" %";
		return avance;
		
	}

}
