package Impresion;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Iterator;

import Impresion.MuestraEnPanel.PanelHoja;

public class Imprimir {
	
	PrinterJob generarImpresion;
	
	public Imprimir(){
		;
	}
	
	public PageFormat formatoReportes(){
		PageFormat pf = new PageFormat();
		Paper p=new Paper();
		p.setSize(612,792);		
		p.setImageableArea(p.getWidth()*0.057, p.getHeight()*0.044,
				p.getWidth()*0.886, p.getHeight()*0.912);
		pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT);
		pf.setPaper(p);
		return pf;		
	}
	
	public PageFormat formato(){
		PageFormat pf = new PageFormat();
		Paper p = new Paper();
		p.setSize(612,792);		
		p.setImageableArea(p.getWidth()*0.02, p.getHeight()*0.02,
				p.getWidth()*0.98, p.getHeight()*0.98);		
		pf.setOrientation(PageFormat.PORTRAIT);
		pf.setPaper(p);
		return pf;		
	}
	
	public Book crearLibroReporte(String tituloImp, ArrayList<PanelHoja> libroRev, PageFormat pf){
		Iterator<PanelHoja> leerLibro=libroRev.iterator();
		Book libro=new Book();
		while(leerLibro.hasNext()){			
			PanelHoja hoja=(PanelHoja) leerLibro.next();
			libro.append(hoja, pf);
		}
		return libro;
	}
	
	public Book crearLibroMarbete(String tituloImp, ArrayList<PanelHoja> libroMarbetes, PageFormat pf){
		Iterator<PanelHoja> leerLibro=libroMarbetes.iterator();
		Book libro=new Book();
		while(leerLibro.hasNext()){			
			PanelHoja hojaMarb=(PanelHoja) leerLibro.next();
			libro.append(hojaMarb, pf);
		}
		return libro;
	}
	
	public Book crearLibroPreInventario(ArrayList<PanelHoja> libroPreInv, PageFormat pf){
		Iterator<PanelHoja> leerLibro=libroPreInv.iterator();
		Book libro=new Book();
		while(leerLibro.hasNext()){			
			PanelHoja hoja=(PanelHoja) leerLibro.next();
			libro.append(hoja, pf);
		}
		return libro;	
	}
	
	public void imprimir(Book libro){
		System.out.println("Imprimir Hojas "+libro.getNumberOfPages());
		generarImpresion=PrinterJob.getPrinterJob();
		generarImpresion.setPageable(libro);
		try {
			generarImpresion.print();
		} catch (PrinterException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

}
