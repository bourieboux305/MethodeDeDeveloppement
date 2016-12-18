import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author 
 *
 */
public class BadBirds 
{
	// met le jeu dans une fenêtre
	public static void main(String args[]) {
		Frame frame = new Frame("Bad Birds");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});

		Kernel kern = Kernel.getInstance();

		GameState playState = PlayState.getInstance();
		kern.changeState(playState);

		frame.add(playState);
		frame.setPreferredSize(kern.getPreferredSize());
		frame.pack();
		frame.setVisible(true);
	}
}
