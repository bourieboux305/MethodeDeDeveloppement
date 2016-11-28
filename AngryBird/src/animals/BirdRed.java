package animals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class BirdRed implements Birds{

	private double x,y;//coordennée x et y
	private double velocityX,velocityY;
	
	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
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
		this.x=x;
		this.y=y;
		this.velocityX=0;
		this.velocityY=0;
	}


	@Override
	public void deplacement() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void affichage(Graphics2D g) {
		g.setColor(Color.RED);
		 g.fillOval((int) x - 20, (int) y - 20, 40, 40);
	}

}
