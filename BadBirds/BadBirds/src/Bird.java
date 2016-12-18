import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Bird extends PhysicsEntity {

	public Bird () {
		this.x = 100;
		this.y = 400;
		this.velocityX = 0;
		this.velocityY = 0;
		
		try {
			this.image = ImageIO.read(new File("rsc/angry-bird.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void computePhysics(double gravity) {
		x         += velocityX;
		y         += velocityY;
		velocityY += gravity;		
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, (int)x-20,(int)y-20,40,40,this);

	}

	public void stop() {
		this.velocityX = 0;
		this.velocityY = 0;		
	}

}
