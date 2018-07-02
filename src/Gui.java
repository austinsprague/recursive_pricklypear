import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gui JFrame displays the buttons for picking colors and drawing ranges to
 * alter
 * 
 * @author Sprague
 *
 */
public class Gui extends JFrame {

	/** Private JPanel for the Gui */
	private JPanel panel;
	
	/** Private Subject fractal generator */
	private Subject fracGen;
	
	/** Private JButton for the Gui's picking color for the main cactus color */
	private JButton openCactusColor;
	
	/** Private JButton for the Gui's picking color for the fruit or last cactus color */
	private JButton openPearColor;
	
	/** Private JSpinner for the Gui's picking range of Radius of cactus*/
	private JSpinner spinnerR;
	
	/** Private JSpinner for the Gui's picking range of Depth of cactus*/
	private JSpinner spinnerD;
	
	/** Private JSpinner for the Gui's picking range of Rotation of cactus*/
	private JSpinner spinnerO;

	/** Private serial ID*/
	private static final long serialVersionUID = 1L;

	/**
	 * Gui constructor
	 * 
	 * @param fracGen
	 *            Subject i.e Fractal Generator
	 */
	public Gui(Subject fracGen) {
		this.fracGen = fracGen;

		setTitle("Cactus Color Picker GUI");

		// COLOR AND DRAW BUTTONS
		JButton buttonDraw = new JButton("Draw");
		openCactusColor = new JButton("Cactus Color");
		openPearColor = new JButton("Pear Color");

		// SET THE COLOR OF THE BUTTONS
		openCactusColor.setBackground(new Color(51, 153, 0));
		openCactusColor.setOpaque(true);
		openCactusColor.setBorderPainted(false);
		openPearColor.setBackground(new Color(209, 55, 202));
		openPearColor.setOpaque(true);
		openPearColor.setBorderPainted(false);

		// NUMBER SPINNERS FOR DEPTH, RATIO, ROTATION
		SpinnerModel modelD = new SpinnerNumberModel(4, 2, 10, 1);
		spinnerD = new JSpinner(modelD);
		((DefaultEditor) spinnerD.getEditor()).getTextField().setEditable(false);

		SpinnerModel modelR = new SpinnerNumberModel(45, 40, 70, 1);
		spinnerR = new JSpinner(modelR);
		((DefaultEditor) spinnerR.getEditor()).getTextField().setEditable(false);

		SpinnerModel modelO = new SpinnerNumberModel(45, 20, 80, 1);
		spinnerO = new JSpinner(modelO);
		((DefaultEditor) spinnerO.getEditor()).getTextField().setEditable(false);

		// ACTION LISTENERS FOR THE BUTTONS
		setColor(openPearColor);
		setColor(openCactusColor);
		sendDataToDraw(buttonDraw);

		// ADD THE BUTTONS TO THE PANEL WITH LABELS FOR THE SPINNERS
		panel = new JPanel();
		panel.add(openPearColor);
		panel.add(openCactusColor);
		panel.add(new JLabel("Depth"));
		panel.add(spinnerD);
		panel.add(new JLabel("Ratio"));
		panel.add(spinnerR);
		panel.add(new JLabel("Rotation"));
		panel.add(spinnerO);
		panel.add(buttonDraw);

		// ADD THE PANEL TO THE FRAME
		add(panel);

		// SET THE SIZE OF THE FRAME
		setSize(700, 100);

		// SET THE STARTING LOCATION OF THE FRAME
		Dimension screenSize = getToolkit().getScreenSize();
//		setLocation((getWidth() - screenSize.width), (getHeight() - screenSize.height));
		setLocation((screenSize.width - getWidth()) / 2,0);

		// FRAME OPTIONS
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	/**
	 * Private action listener for the Color JButtons
	 * 
	 * @param button
	 *            Color reference buttons
	 */
	private void setColor(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JColorChooser clr = new JColorChooser();
				Color color = clr.showDialog(panel, "Choose Color", Color.white);
				button.setBackground(color);
			}
		});
	}

	/**
	 * Private action listener for the "Draw" button that sets the data to the
	 * Subject
	 * 
	 * @param button
	 *            Button for draw
	 */
	private void sendDataToDraw(JButton button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				int depth = (int) spinnerD.getValue();
				int ratio = (int) spinnerR.getValue();
				double rotation = (int) spinnerO.getValue() * Math.PI / 180;
				Color cactusColor = openCactusColor.getBackground();
				Color pearColor = openPearColor.getBackground();

				fracGen.setData(depth, ratio, rotation, cactusColor, pearColor);
			}
		});
	}

}
