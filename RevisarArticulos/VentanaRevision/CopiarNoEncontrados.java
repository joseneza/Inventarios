package RevisarArticulos.VentanaRevision;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.Iterator;

public class CopiarNoEncontrados {
	
	public CopiarNoEncontrados(ArrayList<DatosRevision> datRev){
		Clipboard portaPapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection data = new StringSelection(cargarArticulos(datRev));
		portaPapeles.setContents(data, data);
	}
	
	private String cargarArticulos(ArrayList<DatosRevision> datRev){
		String articulos="";
		Iterator<DatosRevision> barRev = datRev.iterator();
		while(barRev.hasNext()){
			DatosRevision linea = barRev.next();
			if(linea.getExiste()==false){
				articulos=articulos+linea.getValorArt()+"\n";
			}
		}
		System.out.println("CopiarNoEncontrados articulos " + articulos);
		return articulos;		
	}

}
