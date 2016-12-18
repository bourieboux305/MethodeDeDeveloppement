import java.awt.*;


/**
 * 
 * La classe graphique de laquelle heritent les composents graphique qu on utilise
 * cochon, trou noir..  
 *
 */
public abstract class GraphicsEntity extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected double x;
	protected double y;

	Image image;
	
	public abstract void paint(Graphics g);

	public double getx() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double gety() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
