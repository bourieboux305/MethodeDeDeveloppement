package animals;

import java.awt.Color;
import java.awt.Graphics2D;

public class AntiGravitation extends Decorator{


	public AntiGravitation(Birds bird){
		this.bird=bird;
		this.birdX=bird.getBirdX();
		this.birdY=bird.getBirdY();
		this.velocityX=bird.getVelocityX();
		this.velocityY=bird.getVelocityY();
		this.image=bird.image;
	}
	
	public void deplacement(double gravity){
		gravity=0;
		bird.velocityX=this.velocityX;
		bird.velocityY=this.velocityY;
		bird.deplacement(gravity);
		this.birdX=bird.getBirdX();
		this.birdY=bird.getBirdY();
		this.velocityX=bird.getVelocityX();
		this.velocityY=bird.getVelocityY();
	}
	
}
