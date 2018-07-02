import java.awt.Color;

/**
 * Fractal Generator class implementing Subject
 * Stores attributes for cactus, ArrayList of Observers and fractals
 * @author Sprague
 *
 */
public class FractalGenerator implements Subject {

	/** int ranging from 2-10 */
	private int recursionDepth; 
	
	/** int ranging from 40-70 */
	private int ratio; 
	
	/** double of the rotation */
	private double rotation;
	
	/** Color of the main part of cactus */
	private Color cactusColor;
	
	/** Color of the fruit or last part of cactus */
	private Color pearColor;
	
	/** ArrayList of Observer or displays */
	private ArrayList<Observer> displays;
	
	/** ArrayList of FractalElement or fractals */
	private ArrayList<FractalElement> fractals;

	/**
	 * FractalGenerator constructor, creating displays and fractals ArrayList
	 */
	public FractalGenerator() {
		displays = new ArrayList<Observer>();
		fractals = new ArrayList<FractalElement>();
	}

	/**
	 * Register method to add Observer to the Observer ArrayList
	 * 
	 * @param observer
	 *            Observer to be added to the ArrayList
	 */
	public void register(Observer observer) {
		displays.add(observer);

	};

	/**
	 * Public to remove the Observer from the ArrayList
	 * 
	 * @param observer
	 *            Observer to be removed from ArrayList
	 */
	public void remove(Observer observer) {
		displays.remove(displays.indexOf(observer));

	};

	/**
	 * Private recursive method to build each Fractal Element
	 * 
	 * @param location
	 *            Point which the fractal element will exist
	 * @param depth
	 *            int of depth of cactus
	 * @param orientation
	 *            double of the orientation of fractal
	 * @param radius
	 *            double of the radius of fractal
	 */
	private void generateFractal(Point location, int depth, double orientation, double radius) {
		if (depth == 0 || radius < 0.5) {
			return;
		}

		// Create the next element
		FractalElement newFractal;
		if (depth == 1) {
			newFractal = new FractalElement(location, radius, pearColor);
		} else {
			newFractal = new FractalElement(location, radius, cactusColor);
		}
		fractals.add(newFractal);

		double newRadius = radius * ratio / 100;

		// Left side
		double leftOrientation = orientation + rotation;
		double leftDeltaX = Math.cos(leftOrientation) * (radius + newRadius);
		double leftDeltaY = Math.sin(leftOrientation) * (radius + newRadius);
		Point leftCenter = new Point(location.getX() + leftDeltaX, location.getY() - leftDeltaY);
		generateFractal(leftCenter, depth - 1, leftOrientation, newRadius);

		// Right side
		double rightOrientation = orientation - rotation;
		double rightDeltaX = Math.cos(rightOrientation) * (radius + newRadius);
		double rightDeltaY = Math.sin(rightOrientation) * (radius + newRadius);
		Point rightCenter = new Point(location.getX() + rightDeltaX, location.getY() - rightDeltaY);
		generateFractal(rightCenter, depth - 1, rightOrientation, newRadius);

	}

	/**
	 * Public to retrieve ArrayList of FractalElements
	 * 
	 * @return fractals ArrayList of FractalElement
	 */
	public ArrayList<FractalElement> getData() {
		fractals.clear();
		double startingRadius = 80;
		Point startingLocation = new Point(0, 0);

		generateFractal(startingLocation, recursionDepth, Math.PI / 2, startingRadius);
		
		return fractals;
	}

	/**
	 * Public setData
	 * @param recursionDepth int depth of the cactus
	 * @param ratio int of the ratio of the parent child relationship
	 * @param rotation double of the rotation
	 * @param cactusColor Color of the main part of the cactus
	 * @param pearColor Color of the fruit of the cactus, or the very last FractalElement
	 * 
	 */
	public void setData(int recursionDepth, int ratio, double rotation, Color cactusColor, Color pearColor) {
		this.recursionDepth = recursionDepth;
		this.ratio = ratio;
		this.pearColor = pearColor;
		this.cactusColor = cactusColor;
		this.rotation = rotation;
		notifyObservers();
	}

	/**
	 * Public notifyObservers, will itereate the Observer ArrayList and call update
	 */
	public void notifyObservers() {
		for (Observer display : displays) {
			display.update();
		}
	}

}
