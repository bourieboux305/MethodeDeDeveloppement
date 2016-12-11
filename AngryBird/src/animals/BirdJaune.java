package animals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BirdJaune extends Birds{
	
	public BirdJaune(int x,int y){
		this.setBirdX(x);
		this.setBirdY(y);
		this.setVelocityX(0);
		this.setVelocityY(0);
		try{
			image=ImageIO.read(new File("resources/angry-yellow.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}	
}
