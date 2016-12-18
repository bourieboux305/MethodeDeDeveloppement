import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BackgroundScene extends GraphicsEntity {

	public BackgroundScene () {
		this.x = Math.random() * 500 + 200; // position aléatoire pour le cochon
		this.y = 480;

		try {
			this.image = ImageIO.read(new File("rsc/background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		
		g.drawImage(image, 0,0,800,600, this);

	}

}
