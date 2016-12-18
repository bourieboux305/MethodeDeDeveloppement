import java.awt.Graphics;
import java.io.*;

import javax.imageio.*;


public class Pig extends GraphicsEntity {

	public Pig() {
		this.x = Math.random() * 500 + 200; // position aleatoire pour le cochon
		this.y = 480;

		try {
			this.image = ImageIO.read(new File("rsc/pig.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, (int)x-22,(int)y-22,44,44,this);

	}

}
