import java.awt.Color;

public class Main {

	public static void main(String[] args) {

		FractalGenerator fracGen = new FractalGenerator();
		Display display = new Display(fracGen);
		Gui gui = new Gui(fracGen);
	}

}