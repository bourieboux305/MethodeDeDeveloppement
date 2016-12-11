package affichage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Environement extends Panel{
	
	Image image;
	private int hauteurGround=520;
	
	public int getHauteurGround() {
		return hauteurGround;
	}

	public void setHauteurGround(int hauteurGround) {
		this.hauteurGround = hauteurGround;
	}

	public void background(Graphics2D g){
		g.setColor(Color.orange);
        g.fillRect(0, 0, 800, 600);
	}
	
	public void backgroundImage(Graphics2D g){
		
		try{
			image=ImageIO.read(new File("resources/background.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		g.drawImage(image, 0,0,800,600,this);
	}
	
	public void ground(Graphics2D g){
		 g.setColor(Color.BLACK);
	     g.drawLine(0, hauteurGround, 800, hauteurGround);
	     g.drawLine(100, hauteurGround, 100, hauteurGround-100);
	}
	
	
}
