package animals;

public class SpeedX2 extends Decorator{

	public SpeedX2(Birds bird){
		this.bird=bird;
		this.birdX=bird.getBirdX();
		this.birdY=bird.getBirdY();
		this.velocityX=bird.getVelocityX();
		this.velocityY=bird.getVelocityY();
		this.image=bird.image;
	}
	
	public void deplacement(double gravity){
		bird.velocityX=this.velocityX;
		bird.velocityY=this.velocityY;
		bird.deplacement(gravity/2);
		bird.deplacement(gravity);
		this.birdX=bird.getBirdX();
		this.birdY=bird.getBirdY();
		this.velocityX=bird.getVelocityX();
		this.velocityY=bird.getVelocityY();
	}
	
}
