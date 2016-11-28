package animals;

import java.awt.Graphics2D;
import java.awt.Image;

public interface Birds {
	public void deplacement();
	public void affichage(Graphics2D g);
	public double getX();
	public void setX(double x);
	public double getY();
	public void setY(double y);
	public double getVelocityX();
	public void setVelocityX(double velocityX);
	public double getVelocityY();
	public void setVelocityY(double velocityY);
}
