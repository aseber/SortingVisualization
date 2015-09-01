
public abstract class Sort {

	ElementArray array = null;
	boolean running = false;
	boolean sorted = false;
	
	public Sort(ElementArray inputArray) {
		
		array = inputArray;
		
	}
	
	public abstract void sort(); 
	
	public final void start() {
		
		running = true;
		long start_time = System.currentTimeMillis();
		sort();
		long end_time = System.currentTimeMillis();
		VisualizationBase.VISUALIZATION_GUI.setAccessCounter(array.getAccesses());
		VisualizationBase.VISUALIZATION_GUI.setCompareCounter(array.getCompares());
		VisualizationBase.VISUALIZATION_GUI.setSetCounter(array.getSets());
		VisualizationBase.VISUALIZATION_GUI.setRunTimeCounter(end_time - start_time);
		running = false;
		sorted = true;
		
	}
	
	public final boolean isRunning() {
		
		return running;
		
	}
	
	public final boolean isSorted() {
		
		return sorted;
		
	}
	
}
