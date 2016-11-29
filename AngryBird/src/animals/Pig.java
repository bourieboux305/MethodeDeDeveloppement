package animals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pig extends Panel {
	
	private double pigX,pigY;
	private Image image;
	
	public Pig(){
		try{
			image=ImageIO.read(new File("resources/Pig.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	};

	public Pig(double x,double y){
		this.pigX=x;
		this.pigY=y;
	}
	
	public double getPigX() {
		return pigX;
	}

	public void setPigX(double pigX) {
		this.pigX = pigX;
	}

	public double getPigY() {
		return pigY;
	}

	public void setPigY(double pigY) {
		this.pigY = pigY;
	}

	public void radomPig(){
		pigX=Math.random() * 500 + 200;
		pigY=480;
	}
	
	
	public void affichage(Graphics2D g){
		g.drawImage(image, (int)pigX-22,(int)pigY-22,44,44,this);
	}
}
