import java.awt.Dimension;

import javax.swing.JFrame;

public class VisualizationBase {

	protected static VisualizationWindow VISUALIZATION_WINDOW;
	protected static VisualizationGUI VISUALIZATION_GUI;
	public static int SLEEP_TIMER = 0;
	public static int CHANGE_TIMER = 100;
	public static int SORT_COUNT = 1000;
	public static boolean DRAW_SET_UPDATES = true;
	public static boolean DRAW_GET_UPDATES = true;
	public static boolean DRAW_COMPARE_UPDATES = true;
	public static boolean DRAW_SCREEN_UPDATES_WHILE_SORTING = true;
	public static Dimension WINDOW_SIZE = new Dimension(700, 400);
	public static Sort.algorithms CURRENT_ALGORITHM = Sort.algorithms.SELECTION;
	public static ElementArray.directions DIRECTION = ElementArray.directions.FORWARD;
	public static ElementArray.orders ORDER = ElementArray.orders.RANDOM;
	public static ElementArray.uniqueness UNIQUENESS = ElementArray.uniqueness.ALL;
	
	public static void main(String[] args) {
		
		VISUALIZATION_GUI = new VisualizationGUI();
		VISUALIZATION_GUI.setTitle("Sorting Visualization");
		VISUALIZATION_GUI.setVisible(true);
		VISUALIZATION_GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Continue to implement algorithms and see why the values are so high, on the fly value updating
		// Fix the GUI for sorting states, should set condition and then simply redraw the field
		// Check all that is calling repaint(), trim down those calls
		// I don't like the SortExecutor class to stop sorting threads, it's using the depreciated Thread.stop() command, the class also acts like a liaison
		// Remove sortExecutor?
		// bools to disable drawing and such for better runtime representations (implement them!)
		// Time slow down after running multiple algorithms, why?
		// No unique should not be an acceptable option in algorithm dialog
		
	}

}
