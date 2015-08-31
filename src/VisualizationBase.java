import java.awt.Dimension;

import javax.swing.JFrame;

public class VisualizationBase {

	public static final String SELECTION = "SELECTION";
	public static final String INSERTION = "INSERTION";
	public static final String QUICK = "QUICK";
	public static final String MERGE = "MERGE";
	public static final String HEAP = "HEAP";
	public static final String RADIXLSD = "RADIXLSD";
	public static final String RADIXMSD = "RADIXMSD";
	public static final String STDSORT = "STDSORT";
	public static final String STDSTABLESORT = "STDSTABLESORT";
	public static final String SHELL = "SHELL";
	public static final String BUBBLE = "BUBBLE";
	public static final String SHAKER = "SHAKER";
	public static final String GNOME = "GNOME";
	public static final String BITONIC = "BITONIC";
	public static final String BOGO = "BOGO";
	
	protected static VisualizationWindow VISUALIZATION_WINDOW;
	protected static VisualizationGUI VISUALIZATION_GUI;
	public static int SLEEP_TIMER = 0;
	public static int SORT_COUNT = 5000;
	public static Dimension WINDOW_SIZE = new Dimension(700, 400);
	public static String CURRENT_ALGORITHM = INSERTION;
	
	public static void main(String[] args) {
		
		VISUALIZATION_GUI = new VisualizationGUI();
		VISUALIZATION_GUI.setTitle("Sorting Visualization");
		VISUALIZATION_GUI.setVisible(true);
		VISUALIZATION_GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
