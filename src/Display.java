import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Display class of Cactus drawing implementing Observer, extending JFrame
 *
 * @author Sprague
 *
 */
public class Display extends JFrame implements Observer {

	/**Private Serial ID*/
	private static final long serialVersionUID = 1L;

	/** Private JPanel */
	private JPanel panel;

	/** Private Subject of Fractal Generator */
	private Subject fracGen;

	/**
	 * Public Display constructor
	 * 
	 * @param fracGen
	 *            Subject of FractalGenerator
	 */
	public Display(Subject fracGen) {
		this.fracGen = fracGen;
		fracGen.register(this);

		setTitle("Prickly Pear Cactus");

		panel = new GPanel();
		this.add(panel);
		setSize(600, 400);
		
		JPanel nPanel = new GPanel();
		panel.add(nPanel);
		panel.setLayout(null);
		nPanel.setSize(50,50);
		nPanel.setBackground(Color.BLACK);
		nPanel.setLocation(130, 30);
		
		
		

		Dimension screenSize = getToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	/**
	 * Update to panel to "repaint" the drawing
	 */
	public void update() {
		panel.repaint();
	}

	/**
	 * Private GPanel class extending JPanel to Override the paintComponent
	 * 
	 * @author Sprague
	 *
	 */

	private class GPanel extends JPanel {

		/** Private Default serial ID */
		private static final long serialVersionUID = 1L;

		/**
		 * Overriding JPanel's paintComponent, passing in Graphics, and iterating
		 * through each FractalElement to draw
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (FractalElement fractal : fracGen.getData()) {
				fractal.draw(g, this);
			}
		}
	}
}
