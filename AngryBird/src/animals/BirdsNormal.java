package animals;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BirdsNormal extends Birds {
	
	public BirdsNormal(int x,int y){
		this.setBirdX(x);
		this.setBirdY(y);
		try{
			image=ImageIO.read(new File("resources/angry-bird.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}
