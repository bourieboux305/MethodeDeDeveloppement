import java.awt.Graphics;

/*
 * Une classe abstraite que l entite concrete (bird) va utiliser 
 * et implementer les methodes
 * 
 */
public abstract class PhysicsEntity extends GraphicsEntity {

	double velocityX;
	double velocityY;
	
	
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


	public abstract void computePhysics(double gravity);

}
