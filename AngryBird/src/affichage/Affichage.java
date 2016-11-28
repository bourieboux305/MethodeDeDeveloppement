package affichage;
import animals.*;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class Affichage extends Panel implements Runnable {
	public static Image buffer;
	private Birds bird;

	public Birds getBird() {
		return bird;
	}

	public void setBird(Birds bird) {
		this.bird = bird;
	}

	private int x,y;
	
	public Affichage(int x,int y){
		this.x=x;
		this.y=y;
		new Thread(this).start();
	}
	
	public void backGround(Graphics2D g){
		g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 600);
	}
	
	public void paint(Graphics g2){
		if(buffer == null) buffer = createImage(x, y);
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        this.backGround(g);
        bird.affichage(g);
		g2.drawImage(buffer, 0, 0, null);
	}
	
	// évite les scintillements
    public void update(Graphics g) {
        paint(g);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
    
    public void run() {
        while(true) {
            // un pas de simulation toutes les 10ms
            try { Thread.currentThread().sleep(1000); } catch(InterruptedException e) { }
            	System.out.println(bird.getX());
            	bird.setX(bird.getX()+10);
                // redessine
                repaint();
        }
    }
}
