package Impresion.MuestraEnPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.roncemer.barcode.BarCodeRenderer;
import com.roncemer.barcode.Code39BarCodeRenderer;

import Marbetes.ImprimirMarbetes.DatosMarbete;
import RevisarArticulos.VentanaRevision.DatosRevision;

public class PanelHoja extends JPanel implements Printable{
	
	String titulo;
	ArrayList<DatosRevision> listImp;
	ArrayList<DatosMarbete> listMarb;
	BarCodeRenderer codigoBarra;
	Font fuente;
	int pag;
	int pags;
	int opcion;
	
	/* Constructor para PreConteo y Revision */
	public PanelHoja(String titulo, ArrayList<DatosRevision> listImp, int pag, int pags, Dimension dim, int opcion) {
		setBackground(Color.WHITE);		
		this.titulo=titulo;
		this.listImp=listImp;
		this.pag=pag;
		this.pags=pags;
		setSize(dim);
		setPreferredSize(dim);
		this.opcion=opcion;				
	}
	
	/* Constructor para Marbetes */
	public PanelHoja(String titulo, ArrayList<DatosMarbete> listMarb, int pag, int pags, Dimension dim){
		setBackground(Color.WHITE);		
		this.titulo=titulo;
		this.listMarb=listMarb;
		this.pag=pag;
		this.pags=pags;
		setSize(dim);
		setPreferredSize(dim);
		this.opcion=1;				
	}
	
	public void paint(Graphics gr) {		
		super.paint(gr);
		switch (opcion) {
		case 1:
			pintarMarbete(gr);
			break;
		case 2:
			pintarRevision(gr);
			break;
		case 3:
			pintarPreConteo(gr);
			break;
		default:
			break;
		}
		
	}
	
	private void pintarMarbete(Graphics gr) {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);//Se usa para tomar la medoda de un String con fuente
		FontMetrics fm;//Clase para medir largo del String
		String cadena;
		int largoString;//Largo de String
		/* Se tomas las variantes para las lineas divisorias */
		int x0=21;//Linea Izq
		int x1=(int) ((getWidth()-x0)/3+x0);//Linea Izq central
		int x2=(int) ((getWidth()-x0)*2/3+x0);//Linea Der central
		int x3=(int) (getWidth()-6);//Linea Der
		int y0=24;//Primera Linea
		int y1=(int) ((getHeight()-y0)/4+y0);
		int y2=(int) ((getHeight()-y0)*2/4+y0);
		int y3=(int) ((getHeight()-y0)*3/4+y0);
		int y4=(int) (getHeight()-8);
		fuente=new Font("Arial", Font.BOLD, 18);
		/* Se inicia la escritura de la hoja con el escabezado */
		/* Titulo del Inventario */
		gr.setColor(Color.BLUE);
		gr.setFont(fuente);
		//Encabezado de pagina
		gr.drawString(titulo, x0, y0-gr.getFontMetrics(fuente).getDescent());
		String hojas = "Hoja "+this.pag+" de "+pags+" Hojas";
		fm = img.getGraphics().getFontMetrics(fuente);	
		largoString = (int) (1.2*fm.stringWidth(hojas));
		//Pie de Pagina
		gr.drawString(hojas, x3-largoString, y0-gr.getFontMetrics(fuente).getDescent());
		/* Dibujo de lineas */
		gr.setColor(Color.BLACK);
		for(int l=0;l<listMarb.size();l++){
			DatosMarbete linea=(DatosMarbete) listMarb.get(l);
			int x=0;
			int y=0;
			int xx=0;
			int yy=0;
			switch (l) {
			case 0:		x=x0;	y=y0;	xx=x1;	yy=y1;	break;
			case 1:		x=x1;	y=y0;	xx=x2;	yy=y1;	break;
			case 2:		x=x2;	y=y0;	xx=x3;	yy=y1;	break;
			case 3:		x=x0;	y=y1;	xx=x1;	yy=y2;	break;
			case 4:		x=x1;	y=y1;	xx=x2;	yy=y2;	break;
			case 5:		x=x2;	y=y1;	xx=x3;	yy=y2;	break;
			case 6:		x=x0;	y=y2;	xx=x1;	yy=y3;	break;
			case 7:		x=x1;	y=y2;	xx=x2;	yy=y3;	break;
			case 8:		x=x2;	y=y2;	xx=x3;	yy=y3;	break;
			case 9:		x=x0;	y=y3;	xx=x1;	yy=y4;	break;
			case 10:	x=x1;	y=y3;	xx=x2;	yy=y4;	break;
			case 11:	x=x2;	y=y3;	xx=x3;	yy=y4;	break;
			default:	break;
			}			
			//Linea punteada Horizontal
			for(int n=x; n<xx;n+=4){gr.drawLine(n, y, n+2, y);}	
			for(int n=x; n<xx;n+=4){gr.drawLine(n, yy, n+2, yy);}
			//Linea punteada Vertical
			for(int n=y; n<yy;n+=4){gr.drawLine(x, n, x, n+2);}
			for(int n=y; n<yy;n+=4){gr.drawLine(xx, n, xx, n+2);}
			//Marco interno
			gr.drawLine(x+5, y+5, xx-5, y+5);//Linea sup Horizontal
			gr.drawLine(x+5, y+6, xx-5, y+6);
			gr.drawLine(x+6, y+(yy-y)*2/12, xx-6, y+(yy-y)*2/12);//Linea titulo
			gr.drawLine(x+6, y+(yy-y)*3/12, xx-6, y+(yy-y)*3/12);//Linea ubicacion
			gr.drawLine(x+6, y+(yy-y)*4/12, xx-6, y+(yy-y)*4/12);//Linea Marbete
			gr.drawLine(x+6, y+(yy-y)*8/12, xx-6, y+(yy-y)*8/12);//Linea Codigo marbete
			gr.drawLine(x+6, y+(yy-y)*9/12, xx-6, y+(yy-y)*9/12);//Linea Contador y piezas
			gr.drawLine(x+5, yy-5, xx-5, yy-5);//Linea Inf Horizonral
			gr.drawLine(x+5, yy-6, xx-5, yy-6);			
			gr.drawLine(x+5, y+5, x+5, yy-5);//Linea Izq Vertical
			gr.drawLine(x+6, y+5, x+6, yy-5);			
			gr.drawLine(xx-5, y+5, xx-5, yy-5);//Linea Der Vertical
			gr.drawLine(xx-6, y+5, xx-6, yy-5);
			gr.drawLine(xx-(xx-x)/3, y+(yy-y)*8/12, xx-(xx-x)/3, yy-5);
			/* Titulo de Marbete */
			fuente=new Font("Arial", Font.BOLD, 15);
			gr.setFont(fuente);
			cadena="Control de";
			fm = img.getGraphics().getFontMetrics(fuente);
			largoString = (int) (1.2*fm.stringWidth(cadena));
			gr.drawString(cadena, x+(xx-(largoString+x))/2, (y+(yy-y)*2/12)-(gr.getFontMetrics(fuente).getDescent()*2+
					gr.getFontMetrics(fuente).getAscent()));
			cadena="Inventarios";
			largoString = (int) (1.2*fm.stringWidth(cadena));
			gr.drawString(cadena, x+(xx-(largoString+x))/2, (y+(yy-y)*2/12)-gr.getFontMetrics(fuente).getDescent());
			/* Ubicacion */
			fuente=new Font("Arial", Font.PLAIN, 15);
			gr.setFont(fuente);
			cadena=linea.getUbicacion();
			largoString = (int) (1.2*fm.stringWidth(cadena));
			gr.drawString(cadena, x+(xx-(largoString+x))/2, (y+(yy-y)*3/12)-gr.getFontMetrics(fuente).getDescent());
			cadena="Marbete:  "+linea.getMarbete();
			largoString = (int) (1.2*fm.stringWidth(cadena));
			gr.drawString(cadena, x+(xx-(largoString+x))/2, (y+(yy-y)*4/12)-gr.getFontMetrics(fuente).getDescent());
			/* Codigo de Barras */
			codigoBarra=new Code39BarCodeRenderer();
			int xb=(int) (x+(xx-x)*(20-listMarb.get(l).getMarbete().length())*0.035502959/2);
			int yb=y+(yy-y)*4/12;
			gr.translate(xb, yb);
			codigoBarra.render(gr, listMarb.get(l).getMarbete(), 1, 70, 0);
			gr.translate(-xb, -yb);
			//Fecha
			SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");//Clase para tomar fecha de la impresion
			GregorianCalendar gc = new GregorianCalendar();
			String fe=fecha.format(gc.getTime());//La fecha y hora se convierte en un String
			fuente=new Font("Arial", Font.PLAIN, 9);
			gr.setFont(fuente);
			gr.drawString(fe, x+10, (y+(yy-y)*8/12)-gr.getFontMetrics(fuente).getDescent());
			//Firma
			fuente=new Font("Palace Script MT", Font.ITALIC, 9);
			gr.setFont(fuente);
			fm = img.getGraphics().getFontMetrics(fuente);
			String firma="J. Neza";
			largoString = (int) (2*fm.stringWidth(firma));
			gr.drawString(firma, xx-largoString, y+(yy-y)*8/12-2*gr.getFontMetrics(fuente).getDescent());
			fuente=new Font("Arial", Font.PLAIN, 15);
			gr.setFont(fuente);
			//Contador y piezas
			gr.drawString("Contador", x+gr.getFontMetrics(fuente).getDescent()+5, (y+(yy-y)*9/12)-gr.getFontMetrics(fuente).getDescent());
			gr.drawString("Piezas", xx-(xx-x)/3+gr.getFontMetrics(fuente).getDescent(), (y+(yy-y)*9/12)-gr.getFontMetrics(fuente).getDescent());                        
		}
	}

	private void pintarRevision(Graphics gr){
		int x0=(int) (getWidth()*0.01)+1;//Fila
		int x1=(int) (getWidth()*0.10)+1;//Marbete
		int x2=(int) (getWidth()*0.23)+1;//No Articulo
		int x3=(int) (getWidth()*0.40)+1;//Cantidad
		int x4=(int) (getWidth()*0.47)+1;//Estado"
		int x5=(int) (getWidth()*0.70)+1;//Confirmar
		int x6=(int) (getWidth()*0.78)+1;//Borrar
		int x7=(int) (getWidth()*0.84)+1;//Cambiar
		int x8=(int) (getWidth()*0.99)-1;
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		FontMetrics fm;//Clase para medir largo del String
		int largoString;//Largo de String
		fuente=new Font("Arial", Font.BOLD, 12);
		gr.setColor(Color.BLUE);
		gr.setFont(fuente);
		int y=30;
		int y1=y;
		int y2=(int) (getHeight()*0.99);//Alto de dibujo
		gr.drawString(titulo, x0, y-gr.getFontMetrics(fuente).getDescent());//Encabezado de pagina
		fuente=new Font("Arial", Font.BOLD, 12);
		gr.setFont(fuente);
		String encabezadoDer = "Scanner "+listImp.get(0).getScan()+" Contador "+listImp.get(0).getContador();
		fm = img.getGraphics().getFontMetrics(fuente);		
		largoString = fm.stringWidth(encabezadoDer)+10;
		int encDer=(int) (getWidth()-largoString);
		gr.drawString(encabezadoDer, encDer, y-gr.getFontMetrics(fuente).getDescent()-gr.getFontMetrics(fuente).getAscent()
				-gr.getFontMetrics(fuente).getLeading());
		gr.drawString("Ubicacion "+listImp.get(0).getUbic(), encDer, y-gr.getFontMetrics(fuente).getDescent());
		gr.setColor(Color.BLACK);
		gr.drawLine(x0, y, x8, y);
		y+=22;
		fuente=new Font("Arial", Font.BOLD, 17);
		gr.setColor(Color.BLUE);
		gr.setFont(fuente);
		gr.drawString("Fila", x0+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
		gr.drawString("Marbete", x1+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
		gr.drawString("No Articulo", x2+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
		gr.drawString("Cant", x3+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
		gr.drawString("Estado", x4+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
		fuente=new Font("Arial", Font.BOLD, 12);
		gr.setFont(fuente);
		gr.drawString("Confirmar", x5+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
		gr.drawString("Borrar", x6+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
		gr.drawString("Cambiar", x7+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
		gr.setColor(Color.BLACK);
		gr.drawLine(x0, y, x8, y);
		fuente=new Font("Arial", Font.PLAIN, 15);
		gr.setFont(fuente);
		for(int num=0;num<listImp.size();num++){
			y+=22;//Aumenta la altura del Dibujo
			DatosRevision linea=(DatosRevision) listImp.get(num);
			gr.drawLine(x0, y, x8, y);
			String existe;
			if(linea.getExiste()==true){
				existe="Correcto";
				gr.setColor(Color.BLACK);
			}else{
				existe="No encontrado";
				gr.setColor(Color.RED);
			}
			gr.drawString(linea.getFila(), x0+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
			gr.drawString(linea.getMarb(), x1+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
			gr.drawString(Long.toString(linea.getArt()), x2+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
			gr.drawString(Short.toString(linea.getCant()), x3+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
			gr.drawString(existe, x4+gr.getFontMetrics(fuente).getDescent(), y-gr.getFontMetrics(fuente).getDescent());
			gr.setColor(Color.BLACK);			
		}
		gr.drawLine(x0, y1, x0, y);//Fila
		gr.drawLine(x1, y1, x1, y);//Marbete
		gr.drawLine(x2, y1, x2, y);//No de Articulo
		gr.drawLine(x3, y1, x3, y);//Cantidad
		gr.drawLine(x4, y1, x4, y);//Estado
		gr.drawLine(x5, y1, x5, y);//Confirmar
		gr.drawLine(x6, y1, x6, y);//Borrar
		gr.drawLine(x7, y1, x7, y);//Cambiar
		gr.drawLine(x8, y1, x8, y);
		fuente=new Font("Arial", Font.BOLD, 12);
		gr.setFont(fuente);
		SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");//Clase para tomar hora y fecha de la impresion
		GregorianCalendar gc = new GregorianCalendar();
		String fe=fecha.format(gc.getTime());//La fecha y hora se convierte en un String
		gr.drawString(fe, x0, y2);
		String pie = "Hoja "+pag+" de "+pags+" Hojas";
		fm = img.getGraphics().getFontMetrics(fuente);	
		largoString = fm.stringWidth(pie);
		int p=(int) ((getWidth()-largoString)/2);		
		gr.drawString(pie, p, y2);
		fuente=new Font("Palace Script MT", Font.ITALIC, 18);
		gr.setFont(fuente);
		fm = img.getGraphics().getFontMetrics(fuente);
		String firma="J. Neza";
		largoString = fm.stringWidth(firma)+10;
		int pieder=(int) (getWidth()-largoString);
		gr.drawString(firma, pieder, y2);
	}
	
	private void pintarPreConteo(Graphics gr){
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);//Se usa para tomar la medoda de un String con fuente
		FontMetrics fm;//Clase para medir largo del String
		String cadena;		
		/* Se tomas las variantes para las lineas divisorias */
		int largoString;//Largo de String
		int x0=6;//Linea Izq
		int x1=(int) ((getWidth()-x0)/3+x0);//Linea Izq central
		int x2=(int) ((getWidth()-x0)*2/3+x0);//Linea Der central
		int x3=(int) (getWidth()-6);//Linea Der
		int y0=30;//Primera Linea
		int y1=(int) ((getHeight()-2*y0)/9+y0);
		int y2=(int) ((getHeight()-2*y0)*2/9+y0);
		int y3=(int) ((getHeight()-2*y0)*3/9+y0);
		int y4=(int) ((getHeight()-2*y0)*4/9+y0);
		int y5=(int) ((getHeight()-2*y0)*5/9+y0);
		int y6=(int) ((getHeight()-2*y0)*6/9+y0);
		int y7=(int) ((getHeight()-2*y0)*7/9+y0);
		int y8=(int) ((getHeight()-2*y0)*8/9+y0);
		int y9=(int) (getHeight()-y0);
		/* Se inicia la escritura de la hoja con el escabezado */
		/* Titulo del Inventario */
		fuente=new Font("Arial", Font.BOLD, 18);
		gr.setColor(Color.BLUE);
		gr.setFont(fuente);
		gr.drawString(titulo, x0, y0-gr.getFontMetrics(fuente).getDescent());
		if(listImp.get(0).getMarb().equals("")){}else{
			String encabezadoDer = "Scanner "+listImp.get(0).getScan()+" Marbete "+listImp.get(0).getMarb();
			/* Ubicacion y Marbete */
			fuente=new Font("Arial", Font.BOLD, 11);
			gr.setFont(fuente);
			fm = img.getGraphics().getFontMetrics(fuente);		
			largoString = fm.stringWidth(encabezadoDer)+10;
			int encDer=(int) (getWidth()-largoString);
			gr.drawString(encabezadoDer, encDer, y0-gr.getFontMetrics(fuente).getDescent()-gr.getFontMetrics(fuente).getAscent()
					-gr.getFontMetrics(fuente).getLeading());
			encabezadoDer = "Contador "+listImp.get(0).getContador()+" Ubicacion "+listImp.get(0).getUbic();
			largoString = fm.stringWidth(encabezadoDer)+10;
			encDer=(int) (getWidth()-largoString);
			gr.drawString(encabezadoDer, encDer, y0-gr.getFontMetrics(fuente).getDescent());
		}
		/* Dibujo de lineas */
		gr.setColor(Color.BLACK);
		for(int l=0;l<listImp.size();l++){
			DatosRevision linea=(DatosRevision) listImp.get(l);
			int x=0;
			int y=0;
			int xx=0;
			int yy=0;
			switch (l) {
				case 0:		x=x0;	y=y0;	xx=x1;	yy=y1;	break;
				case 1:		x=x1;	y=y0;	xx=x2;	yy=y1;	break;
				case 2:		x=x2;	y=y0;	xx=x3;	yy=y1;	break;
				case 3:		x=x0;	y=y1;	xx=x1;	yy=y2;	break;
				case 4:		x=x1;	y=y1;	xx=x2;	yy=y2;	break;
				case 5:		x=x2;	y=y1;	xx=x3;	yy=y2;	break;
				case 6:		x=x0;	y=y2;	xx=x1;	yy=y3;	break;
				case 7:		x=x1;	y=y2;	xx=x2;	yy=y3;	break;
				case 8:		x=x2;	y=y2;	xx=x3;	yy=y3;	break;
				case 9:		x=x0;	y=y3;	xx=x1;	yy=y4;	break;
				case 10:	x=x1;	y=y3;	xx=x2;	yy=y4;	break;
				case 11:	x=x2;	y=y3;	xx=x3;	yy=y4;	break;
				case 12:	x=x0;	y=y4;	xx=x1;	yy=y5;	break;
				case 13:	x=x1;	y=y4;	xx=x2;	yy=y5;	break;
				case 14:	x=x2;	y=y4;	xx=x3;	yy=y5;	break;
				case 15:	x=x0;	y=y5;	xx=x1;	yy=y6;	break;
				case 16:	x=x1;	y=y5;	xx=x2;	yy=y6;	break;
				case 17:	x=x2;	y=y5;	xx=x3;	yy=y6;	break;
				case 18:	x=x0;	y=y6;	xx=x1;	yy=y7;	break;
				case 19:	x=x1;	y=y6;	xx=x2;	yy=y7;	break;
				case 20:	x=x2;	y=y6;	xx=x3;	yy=y7;	break;
				case 21:	x=x0;	y=y7;	xx=x1;	yy=y8;	break;
				case 22:	x=x1;	y=y7;	xx=x2;	yy=y8;	break;
				case 23:	x=x2;	y=y7;	xx=x3;	yy=y8;	break;
				case 24:	x=x0;	y=y8;	xx=x1;	yy=y9;	break;
				case 25:	x=x1;	y=y8;	xx=x2;	yy=y9;	break;
				case 26:	x=x2;	y=y8;	xx=x3;	yy=y9;	break;
				default:	break;
			}
			gr.drawLine(x, y, xx, y);//Linea sup Horizontal
			gr.drawLine(x, y+1, xx, y+1);
			gr.drawLine(x+1, y+(yy-y)*2/3, xx-1, y+(yy-y)*2/3);//Linea Media
			gr.drawLine(x, yy, xx, yy);//Linea Inf Horizonral
			gr.drawLine(x, yy+1, xx, yy+1);			
			gr.drawLine(x, y, x, yy);//Linea Izq Vertical
			gr.drawLine(x+1, y, x+1, yy);			
			gr.drawLine(xx, y, xx, yy);//Linea Der Vertical
			gr.drawLine(xx+1, y, xx+1, yy);
			int xb=x+(xx-x)*23/30;
			int yb=y+(yy-y)*2/3;
			gr.drawLine(xb, y+1, xb, yb);
			/* Cantidad */
			fuente=new Font("Arial", Font.BOLD, 30);
			gr.setFont(fuente);
			cadena=Short.toString(linea.getCant());
			fm = img.getGraphics().getFontMetrics(fuente);
			largoString = (int) (1.3*fm.stringWidth(cadena));			
			gr.drawString(cadena, xx-largoString, y+(yy-y)*1/2);
			/* Descripcion */
			fuente=new Font("Arial", Font.BOLD, 15);
			gr.setFont(fuente);
			fm = img.getGraphics().getFontMetrics(fuente);
			String linea1=linea.getDescripcion();
			String linea2="";
			do{
				largoString = (int) (fm.stringWidth(linea1));
				if(largoString>(xx-(x+2*gr.getFontMetrics(fuente).getDescent()))){
					String a;
					do{
						a=linea1.substring(linea1.length()-1, linea1.length());
						linea1=linea1.substring(0, linea1.length()-1);						
						linea2=a+linea2;
					}while(a.equals(" ")==false);
				}
			}while(largoString>(xx-(x+2*gr.getFontMetrics(fuente).getDescent())));
			largoString = (int) (fm.stringWidth(linea1));
			gr.drawString(linea1, x+(xx-(x+largoString))/2, yy-(gr.getFontMetrics(fuente).getDescent()*2+
					gr.getFontMetrics(fuente).getAscent()));
			largoString = (int) (fm.stringWidth(linea2));
			gr.drawString(linea2, x+(xx-(x+largoString))/2, yy-gr.getFontMetrics(fuente).getDescent());
			/* Codigo de Barras */
			codigoBarra=new Code39BarCodeRenderer();
			xb=(int) (x+(xx-x)*(15-Long.toString(listImp.get(l).getArt()).length())*0.047928994/2);
			yb=y+1;
			gr.translate(xb, yb);
			codigoBarra.render(gr, Long.toString(linea.getArt()), 1, 50, 15);
			gr.translate(-xb, -yb);		
			
		}
		
		fuente=new Font("Arial", Font.BOLD, 12);
		gr.setFont(fuente);
		SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");//Clase para tomar hora y fecha de la impresion
		GregorianCalendar gc = new GregorianCalendar();
		String fe=fecha.format(gc.getTime());//La fecha y hora se convierte en un String
		gr.drawString(fe, x0, y9+25);
		String pie = "Hoja "+pag+" de "+pags+" Hojas";
		fm = img.getGraphics().getFontMetrics(fuente);	
		largoString = fm.stringWidth(pie);
		int p=(int) ((getWidth()-largoString)/2);		
		gr.drawString(pie, p, y9+25);
		fuente=new Font("Palace Script MT", Font.ITALIC, 18);
		gr.setFont(fuente);
		fm = img.getGraphics().getFontMetrics(fuente);
		String firma="J. Neza";
		largoString = fm.stringWidth(firma)+10;
		int pieder=(int) (getWidth()-(largoString));
		gr.drawString(firma, pieder, y9+25);	
		
	}
	
	public int print(Graphics gr, PageFormat pf, int pageIndex) throws PrinterException {
	        Graphics2D g2d = (Graphics2D)gr;
	        g2d.translate(pf.getImageableX()+8, pf.getImageableY()+5);
	        //-------------------------ESCALAR LA IMPRESION-------------------------------//
	        g2d.scale((pf.getImageableWidth()-25)/getWidth(), (pf.getImageableHeight()-20)/getHeight());
	        //----------------------------------------------------------------------------//
	        printAll(gr);
	        return PAGE_EXISTS;
	}
}
