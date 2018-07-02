import java.awt.Color;

/**
 * Interface of Subject
 * @author Sprague
 *
 */
public interface Subject {
	/**
	 * Register the observer 
	 * @param observer Observer
	 */
	public void register(Observer observer);

	/**
	 * Remove the Observer
	 * @param observer Observer to be removed
	 */
	public void remove(Observer observer);

	/**
	 * Notify the list of Observers
	 */
	public void notifyObservers();

	/**
	 * Get the ArrayList of FractalElements
	 * @return ArrayList of FractalElements
	 */
	public ArrayList<FractalElement> getData();

	/**
	 * Set the attribute data needed to build the cactus
	 * @param recursionDepth int of how deep the cactus will be
	 * @param ratio int of the parent child ratio
	 * @param rotation double of the rotation of the children
	 * @param cactusColor Color of the main part of the cactus
	 * @param pearColor Color of the fruit of the cactus
	 */
	public void setData(int recursionDepth, int ratio, double rotation, Color cactusColor, Color pearColor);
}
