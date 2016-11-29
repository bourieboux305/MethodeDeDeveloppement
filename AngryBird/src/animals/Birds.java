package animals;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;

import affichage.*;
public interface Birds {
	public void deplacement(double gravity);
	public void affichage(Graphics2D g);
	public double getVelocityX();
	public void setVelocityX(double velocityX);
	public double getVelocityY();
	public void setVelocityY(double velocityY);
	public boolean isSelecting();
	public void setSelecting(boolean selecting);
	public int getMouseX();
	public void setMouseX(int mouseX);
	public int getMouseY();
	public void setMouseY(int mouseY);
	public boolean isStop();
	public void setStop(boolean stop);
	public void crashGround();
	public void crashPig(Pig pig);
	public double getBirdX();
	public void setBirdX(double birdX);
	public double getBirdY();
	public void setBirdY(double birdY);
}
