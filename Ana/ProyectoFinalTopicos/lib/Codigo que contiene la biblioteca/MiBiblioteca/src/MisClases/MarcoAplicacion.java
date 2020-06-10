package MisClases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.File;

//cambiar apariencia de las ventanas
import javax.swing.UIManager;

public class MarcoAplicacion extends JFrame
{
	private PanelDibujo area;
	private Thread t;
	public JButton btn_inicio;
	public JButton btn_parar;
	private AnimaRayas a;

    UIManager.LookAndFeelInfo apariencias[];
    int pausado = 0;
    private Image logo;
	public MarcoAplicacion()
	{		
		super("Game go Go");
		setIconImage( new ImageIcon( MarcoAplicacion.class.getResource("logo.png")).getImage() );
		apariencias = UIManager.getInstalledLookAndFeels();
        try{
            UIManager.setLookAndFeel(apariencias[1].getClassName());           
            SwingUtilities.updateComponentTreeUI( this );
        } catch( Exception ex )
        {
            ex.printStackTrace();
        }
        setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,300);
		setLocationRelativeTo(null);
		iniciarComponentes();
		setVisible(true);
	}
	
	public void iniciarComponentes()
	{		
        try {

        } catch (Exception e) {
            System.out.println("Error al reproducir el archivo!");
        }

		JLabel mensaje1 = new JLabel("Bienvenidos al Juego", SwingConstants.CENTER);
		mensaje1.setForeground( Color.RED );
		mensaje1.setFont( new Font( "Comic MS Sans", Font.BOLD, 16 ) );		
		add( mensaje1, BorderLayout.NORTH );
		area = new PanelDibujo( this.getSize() );
		
		btn_inicio = new JButton("iniciar");
		btn_inicio.setFont( new Font( "Comic MS Sans", Font.BOLD, 14 ) );		
		btn_inicio.setForeground( Color.WHITE );
		btn_inicio.setBackground( Color.BLUE );
		
		addKeyListener( new KeyAdapter()
			{
				public void keyPressed( KeyEvent evt )
				{	
					//System.out.println("La tecla oprimida es: " + evt.getKeyCode()  );
					if( evt.getKeyCode() == 38 ){
						if( area.getYpos() > 20 )
							area.setYpos( area.getYpos()-4 );
						area.repaint();
					}else if( evt.getKeyCode() == 40 ){
						if( area.getYpos() < 85 )
							area.setYpos( area.getYpos()+4 );
						area.repaint();
					}			
				}
			}							
		 );
		
		btn_inicio.addActionListener( new ManejadorRayas(this) );
		
		JPanel pane_botones = new JPanel();
		pane_botones.setLayout( new FlowLayout( FlowLayout.CENTER ) );
		pane_botones.add(btn_inicio);
		btn_parar = new JButton("Pausar");
		btn_parar.setFont( new Font( "Comic MS Sans", Font.BOLD, 14 ) );		
		btn_parar.setForeground( Color.WHITE );
		btn_parar.setBackground( Color.RED );
		
		btn_parar.addActionListener( new ActionListener()
			{
				public void actionPerformed( ActionEvent evento )
				{
					if( btn_parar.getText().equals("Salir") )
						dispose();
					
					if( btn_parar.getText().equals("Reanudar") )
					{									
						if(pausado == 1)
           				{
           				
           					try {				
             	  				pausado = 0;
             	  				btn_parar.setText("Pausar");
            				}catch (Exception ex) {
            				}
           				}	

						t = new Thread(a);								
						t.start();
					}
					else{
					
					t.interrupt();
					try {				
               			pausado = 1;
               			btn_parar.setText("Reanudar");
            		}catch (Exception ex) {
            		}
					}
				}			
			}		
		);
	
		pane_botones.add( btn_parar );
				
		add(pane_botones, BorderLayout.SOUTH);
		add( area, BorderLayout.CENTER );
	}

	private class ManejadorRayas implements ActionListener
	{
		MarcoAplicacion m;
		public ManejadorRayas( MarcoAplicacion marco )
		{
			this.m = marco;
		}

		public void actionPerformed( ActionEvent e )
		{
			if( btn_inicio.getText().equals("Reiniciar") ){			
				a.pane.reiniciar( a.pane.getGraphics() );
				btn_parar.setText("Pausar");
			}
			
			m.setFocusable(true);
			btn_inicio.setFocusable(false);
			btn_parar.setFocusable(false);
			
			btn_inicio.setEnabled(false);
			
			a = new AnimaRayas(area, m);					
			
            	
           	if(pausado == 1)
           	{
           		try {				
             	  pausado = 0;
            	}catch (Exception ex) {
               	}
           	}	

			t = new Thread(a);								
			t.start();						
		}		
	}

	private String obtenerRutaGood( String rut )
    {
        String nueva="";
        char [] a = rut.toCharArray();
        for( int i=0; i<rut.length(); i++ )
        {
            if( a[i] == '\\' )
            {
                a[i] = '/';               
            }
            
        }
        nueva = String.valueOf(a);
        //System.out.println("nueva"+ nueva);
        return nueva;
    }
}
