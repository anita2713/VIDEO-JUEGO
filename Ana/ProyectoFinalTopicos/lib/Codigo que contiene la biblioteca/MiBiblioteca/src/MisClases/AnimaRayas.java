package MisClases;

import javax.swing.*;
import java.awt.*;


public class AnimaRayas implements Runnable
{
	PanelDibujo pane;
	MarcoAplicacion elMarco;
	public AnimaRayas( PanelDibujo p, MarcoAplicacion frame )
	{
		this.pane = p;
		this.elMarco = frame;
	}
	
	public void run()
	{
		try{
				Thread.sleep(20);
		}catch( InterruptedException ex ){
				Thread.currentThread().interrupt();
		}
		while( !Thread.currentThread().isInterrupted() ){									
			//System.out.println("xRay =  " + pane.getXray() );
			pane.setXray( pane.getXray()-2 );
			pane.setC1pos( pane.getC1pos()-2 );
			try{
				Thread.sleep(10);
			}catch( InterruptedException ex ){
				Thread.currentThread().interrupt();
			}
			pane.repaint();
			//System.out.println("cocheY: " + pane.getYpos() );
			//System.out.println("cocheY: " + pane.getC1pos() );
			
			if( (pane.getC1pos() == 266 ) && (pane.getYpos() >=18 && pane.getYpos() <= 40) )
			{
				//System.out.println("----------------choque!-------------------");			
				gameOver(-10);
												
				//pane
				elMarco.btn_inicio.setText("Reiniciar");
				elMarco.btn_parar.setText("Salir");
				elMarco.btn_inicio.setEnabled(true);
				break;				
			}

			if( (pane.getC1pos() == -26 ) && (pane.getYpos() >=58 && pane.getYpos() <= 86) )
			{
				//System.out.println("#################choque!###################");
				gameOver(50);
				//pane.reiniciar( pane.getGraphics() );
				elMarco.btn_inicio.setText("Reiniciar");
				elMarco.btn_parar.setText("Salir");
				elMarco.btn_inicio.setEnabled(true);
				break;
			}			

			if( pane.getXray() < -65 ){
				pane.setXray(0);
			}

			if( pane.getC1pos() < -400 ){
				pane.setC1pos( 550 );
			}
		}
	}

	public void gameOver( int altura )
	{
		pane.coche = new ImageIcon( MarcoAplicacion.class.getResource("bomba.gif")).getImage();				
		pane.setYpos(altura);
		
		try {
        }catch (Exception ex) {
        }
        
        //espera a que termine de ejecutarse la imagen gif al menos una vez						
		for( int i=0; i<27; i++ ){
			pane.repaint();								
			try{
				Thread.sleep(39);
			}catch( InterruptedException ex ){
				Thread.currentThread().interrupt();
			}
		}
	}
}
