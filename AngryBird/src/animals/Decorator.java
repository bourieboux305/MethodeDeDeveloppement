package animals;

public abstract class Decorator extends Birds{

	protected Birds bird;
	
	public abstract void deplacement(double gravity);
}
