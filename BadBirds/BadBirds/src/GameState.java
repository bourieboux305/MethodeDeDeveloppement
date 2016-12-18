import java.awt.*;

/**
 * 
 * @author : Amine & Anja 
 *
 * Note: Classe Singleton
 */
public abstract class GameState extends Panel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	abstract public void init();
	abstract public void pause();
	abstract public void resume();
	abstract public void update(Graphics g);
	/**
	 * dessine le contenu de l'écran dans un buffer puis copie le buffer a l'écran
	 */
	abstract public void paint(Graphics g);
	
	public void changeState(Kernel kern, GameState state) {
		kern.changeState(state);
	}
	
	/**
	 * Constructeur.
	 * ProtegŽ pour assurer la propriété Singleton des instances de cette classe
	 */
	protected GameState() {
		
	}

}
