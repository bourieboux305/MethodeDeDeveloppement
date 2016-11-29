package animals;

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

import affichage.*;

public class BirdRed extends Panel implements Birds{

	private double birdX,birdY;//coordennée x et y
	private double velocityX,velocityY;
	private boolean selecting=true;
	private int mouseX,mouseY;
	private boolean stop=false;
	private Image image;
	
	public double getBirdX() {
		return birdX;
	}


	public void setBirdX(double birdX) {
		this.birdX = birdX;
	}


	public double getBirdY() {
		return birdY;
	}


	public void setBirdY(double birdY) {
		this.birdY = birdY;
	}
	
	public double getVelocityX() {
		return velocityX;
	}


	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}


	public double getVelocityY() {
		return velocityY;
	}


	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	public BirdRed(double x,double y){
		this.birdX=x;
		this.birdY=y;
		this.velocityX=0;
		this.velocityY=0;
		try{
			image=ImageIO.read(new File("resources/angry-bird.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}


	@Override
	public void deplacement(double gravity) {
		this.birdX+=this.velocityX;
		this.birdY+=this.velocityY;
		this.velocityY+=gravity;
	}


	@Override
	public void affichage(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawImage(image, (int)birdX-20,(int)birdY-20,40,40,this);
		if(selecting) g.drawLine((int) birdX, (int) birdY, mouseX, mouseY);
	}
	
	 public boolean isSelecting() {
		return selecting;
	}


	public void setSelecting(boolean selecting) {
		this.selecting = selecting;
	}


	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}


	public int getMouseY() {
		return mouseY;
	}


	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	
	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	public void crashGround(){
		if(birdX < 20 || birdX > 780 || birdY < 0 || birdY > 480)
			stop=true;
	}
	
	// calcule la distance entre deux points
    static double distance(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }
	
	public void crashPig(Pig pig){
		  if(distance(birdX, birdY, pig.getPigX(), pig.getPigY()) < 35)
			  stop=true;
	}
}
