package MisClases;

import javax.swing.*;
import java.awt.*;

public class PanelDibujo extends JPanel
{
	private Image dibujoAux;
	public Image fondo;
	private Graphics gAux;
	private Dimension dimAux;
	private Dimension dimPanel;	
	public Image coche;
	private Image rayas;
	private Image c1;
	private Image c2;
	private int yPos = 80;
	private int xRay = 0;
	private int c1Pos = 550;
	public PanelDibujo( Dimension frame )
	{
		fondo = new ImageIcon( MarcoAplicacion.class.getResource("pasto2.jpg")).getImage();
		coche = new ImageIcon( MarcoAplicacion.class.getResource("coche.png")).getImage();
		rayas = new ImageIcon( MarcoAplicacion.class.getResource("rayas.png")).getImage();
		c1 = new ImageIcon( MarcoAplicacion.class.getResource("c1.png")).getImage();
		c2 = new ImageIcon( MarcoAplicacion.class.getResource("c2.png")).getImage();
		dimPanel = frame.getSize();
	}
	
	public void update( Graphics g )
	{
		paint(g);
	}

	public void paint( Graphics g )
	{
		if( gAux == null || dimAux == null || dimPanel.width != dimAux.width 
			|| dimPanel.height != dimAux.height){
			dimAux = dimPanel;
			dibujoAux = createImage( dimAux.width, dimAux.height );
			gAux = dibujoAux.getGraphics();
		}


		gAux.drawImage(fondo, 0, 0, null);
		
		gAux.setColor( new Color(127,127,127) );
		//carretera parte gris
		gAux.fillRect( 0, 50, 500, 130 );				
		
		gAux.drawImage( rayas, xRay, 85, null );
		
		//for( int i=0, a=0; i<8; i++, a+=75 )
		//	gAux.fillRect( a, 110, 55, 15 );
		gAux.drawImage( c1, c1Pos, 60, null );
		
		//gAux.setColor( Color.RED );
		//gAux.drawRect( c1Pos, 60, 128, 36 );

		gAux.drawImage( c2, c1Pos+300, 130, null );
		
		gAux.drawImage(coche, 150, yPos, null);

		g.drawImage(dibujoAux, 0, 0, this);
	}

	public void reiniciar( Graphics g )
	{
		setYpos(50);
		setC1pos(550);
		coche = new ImageIcon( MarcoAplicacion.class.getResource("coche.png")).getImage();
		g.drawImage(coche, 150, yPos, null);
		repaint();
	}

	
	public void setYpos( int v )
	{
		this.yPos = v;
	}
	
	public int getYpos()
	{
		return yPos;
	}
	
	public void setXray( int v )
	{
		this.xRay = v;
	}
	
	public int getXray()
	{
		return xRay;
	}

	public void setC1pos( int v )
	{
		this.c1Pos = v;
	}
	
	public int getC1pos()
	{
		return c1Pos;
	}
}
