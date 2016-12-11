package affichage;
import animals.*;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class Affichage extends Panel implements Runnable, MouseListener, MouseMotionListener {
	public static Image buffer;
	private Birds bird;
	private Pig pig;
	private Environement env;

	public Environement getEnv() {
		return env;
	}

	public void setEnv(Environement env) {
		this.env = env;
	}

	public Birds getBird() {
		return bird;
	}

	public void setBird(Birds bird) {
		this.bird = bird;
	}

	public Pig getPig() {
		return pig;
	}

	public void setPig(Pig pig) {
		this.pig = pig;
	}

	private int x,y;
	
	public Affichage(int x,int y){
		this.x=x;
		this.y=y;
		addMouseListener(this);
        addMouseMotionListener(this);
		new Thread(this).start();
	}
	
	
	public void paint(Graphics g2){
		if(buffer == null) buffer = createImage(x, y);
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        env.background(g);
        env.ground(g);
        bird.affichage(g);
        pig.affichage(g);
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
            try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }
                // redessine
            	if(!bird.isStop() && !bird.isSelecting()){
            		bird.deplacement(0.1);
            		bird.crashGround(env);
            		bird.crashPig(pig);
            	}
                repaint();
        }
    }
    
    public void mouseClicked(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) {
    	if(bird.isSelecting()) {
            bird.setVelocityX((bird.getBirdX()-bird.getMouseX())/20); 
            bird.setVelocityY((bird.getBirdY()-bird.getMouseY())/20);
            bird.setSelecting(false);
        }
    }
    public void mouseDragged(MouseEvent e) { mouseMoved(e); }
    public void mouseMoved(MouseEvent e) {
    	bird.setMouseX(e.getX());
    	bird.setMouseY(e.getY());
    	repaint();
    };
}
