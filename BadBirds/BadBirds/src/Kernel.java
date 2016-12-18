import java.awt.*;
import java.util.*;

/**
 * Classe implementant le moteur de jeu.
 * Le moteur de jeu implemente lui-meme le gestionnaire des états de jeu.
 * Cette classe delegue a l'état en cours toutes les taches qui le concernent.
 * Concue en tant que classe Singleton, pour s'assurer que le jeu demarre une seule fois 
 * @author 
 *
 */
public final class Kernel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Unique instance du moteur de jeu.
	 */
    private static volatile Kernel instance = null;
	/**
	 * 
	 */
	private Deque<GameState> states;

	
	/**
	 * Booleen vrai lorsque le moteur de jeu (Kernel) est en marche.
	 */
	private boolean running;                               

	private Kernel () {
		this.states = new ArrayDeque<GameState>();
		this.running = true;
	}
	
	/**
	 * Renvoie l'instance unique de cette classe
	 * @return
	 */
	public final static Kernel getInstance() {
        if (null == Kernel.instance) {
           // Portion de code thread-safe, prémunie contre la data-race
           synchronized(Kernel.class) {
             if (null == Kernel.instance) {
               Kernel.instance = new Kernel();
             }
           }
        }
        return Kernel.instance;
    }



	/**
	 *  evite les scintillements
	 * @param g
	 */
	public void update(Graphics g) {
		states.peek().update(g);
	}

	// taille de la fenêtre
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

    // State Manager
    
    /**
     * Detruit l'état courant et le remplace par l'état state. 
     * Demarre le nouvel état state.
     * @param state
     */
	public void changeState(GameState state) {
		if ( ! states.isEmpty())
			states.pop();
		states.push(state);
		states.peek().init();
	}
	
	/**
	 * Empile le nouvel etat de jeu state sur l'etat courant (qui va etre mis en pause).
	 * Demarre le nouvel état.
	 * @param state
	 */
	public void pushState(GameState state) {
		if ( ! states.isEmpty())
			states.peek().pause();
		states.push(state);
		states.peek().init();
	}
	
	/**
	 * Depile l'etat courant. Re-demarre l'etat precedent.
	 * @param state
	 */
	public void popState(GameState state) {
		if ( ! states.isEmpty())
			states.pop();
		if ( ! states.isEmpty())
			states.peek().resume();
	}
}
