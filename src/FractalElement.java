import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Fractal Element class that is the one circle of the cactus
 * 
 * @author Sprague
 *
 */
public class FractalElement {

	/** Private Point of the circle */
	private Point point;

	/** Private double radius of the circle */
	private double radius;

	/** Private Color of the circle */
	private Color color;

	/**
	 * FractalElement constructor to set the attributes
	 * 
	 * @param point
	 *            Point x,y of the circle
	 * @param radius
	 *            Double of the radius of the circle
	 * @param color
	 *            Color of the circle
	 */
	public FractalElement(Point point, double radius, Color color) {
		this.point = point;
		this.radius = radius;
		this.color = color;

	}

	/**
	 * Draw the Fractal Element on the panel with it's attributes
	 * 
	 * @param g
	 *            Graphics context
	 * @param panel
	 *            JPanel panel
	 */
	public void draw(Graphics g, JPanel panel) {

		g.setColor(color);
		int startingX = (panel.getWidth() / 2) + (int) (point.getX() - radius);
		int startingY = (panel.getHeight() / 2) + (int) (point.getY() - radius);
		g.fillOval(startingX, startingY, (int) (radius * 2), (int) (radius * 2));
	}

}
