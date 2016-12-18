import java.awt.*;
import java.awt.event.*;


/**
 * 
 * @author : Amine & Anja
 *
 *	L etat du jeu c est l interface sur laquelle on est situes et l etat sur lequel le jeu est figŽ
 */
public final class PlayState extends GameState implements Runnable, MouseListener, MouseMotionListener
{
	/**
	 * Instance unique  de cette classe
	 */
	private static volatile PlayState instance = null;

	/**
	 *  Boolean vrai lorsque le joueur a touché un bord ou le cochon
	 */
	boolean gameOver;                           

	Pig pig;
	
	BackgroundScene bg;
	
	Bird bird;
	
	/**
	 *  Gravité
	 */
	double gravity;                             
	/**
	 *  position de la souris lors de la sélection
	 */
	int mouseX, mouseY;                         
	/**
	 *  message a afficher en haut de l'écran
	 */
	String message;                             
	/**
	 *  nombre de fois ou le joueur a gagné
	 */
	int score;                                  
	/**
	 *  vrai lorsque le joueur sélectionne l'angle et la vitesse
	 */
	boolean selecting;                          
	/**
	 *  image pour le rendu hors écran
	 */
	Image buffer;


	private PlayState() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		new Thread(this).start();

	}

	/**
	 * Renvoie l'instance unique de cette classe
	 * @return
	 */
	public final static PlayState getInstance() {
		if (null == PlayState.instance) {
			// Portion de code thread-safe, prémunie contre la data-race
			synchronized(PlayState.class) {
				if (null == PlayState.instance) {
					PlayState.instance = new PlayState();
				}
			}
		}
		return PlayState.instance;
	}

	@Override
	public void init() {
		this.gameOver = false;
		this.selecting = true;
		this.pig = new Pig();
		this.bg   = new BackgroundScene();
		this.bird = new Bird();
		this.gravity = 0.1;
		this.message = "Choisissez l'angle et la vitesse";
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	/**
	 *  Boucle principale du jeu.
	 *  Calcule la position de l'oiseau en vol, effectue l'affichage et 
	 *  teste les conditions de victoire
	 */
	public void run() {
		while(true) {
			// un pas de simulation toutes les 10ms
			try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }
			if(! this.gameOver && ! this.selecting) {

				// moteur physique
				this.bird.computePhysics(this.gravity);
			
				// conditions de victoire
				this.computeVictoryConditions();
				
				// redessine
				repaint();


			}
		}
	}

	/**
	 *  test des conditions de victoire
	 */
	private void computeVictoryConditions() {
		if(Utilities.distance(bird.getx(), bird.gety(), pig.getx(), pig.gety()) < 35) {
			stop();
			message = "Gagné : cliquez pour recommencer.";
			score++;
		} else if(bird.getx() < 20 || bird.getx() > 780 || bird.gety() < 0 || bird.gety() > 480) {
			stop();
			message = "Perdu : cliquez pour recommencer.";
		}		
	}

	@Override
	public void update(Graphics g) {		
		this.paint(g);
	}

	@Override
	public void paint(Graphics g) {
		if(null == this.buffer) 
			buffer = this.createImage(800, 600);
		Graphics2D g2 = (Graphics2D) buffer.getGraphics();

		// fond
		bg.paint(g2);

		// décore
		g2.setColor(Color.BLACK);
		g2.drawLine(0, 500, 800, 500);
		g2.drawLine(100, 500, 100, 400);

		// oiseau
		if(this.selecting) g2.drawLine((int) bird.getx(), (int) bird.gety(), mouseX, mouseY); // montre l'angle et la vitesse
		bird.paint(g2);
		
		// cochon
		pig.paint(g2);
		
		// messages
		g2.setColor(Color.BLACK);
		g2.drawString(message, 300, 100);
		g2.drawString("Score: " + score, 20, 20);

		// affichage a l'écran sans scintillement
		g.drawImage(buffer, 0, 0, null);
	}

	/**
	 *  Fin de partie
	 */
	void stop() {
		bird.stop();
		
		this.gameOver = true;
	}

	// InputManager

	// gestion des événements souris
	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) {
		if(this.gameOver) {
			this.init();
		} else if(this.selecting) {
			this.bird.setVelocityX((this.bird.getx() - this.mouseX) / 20.0);
			this.bird.setVelocityY((this.bird.gety() - this.mouseY) / 20.0);
			this.message = "L'oiseau prend sont envol";
			this.selecting = false;
		}
		this.repaint();
	}
	public void mouseDragged(MouseEvent e) { this.mouseMoved(e); }
	public void mouseMoved(MouseEvent e) { 
		this.mouseX = e.getX();
		this.mouseY = e.getY();
		this.repaint();
	}

}
