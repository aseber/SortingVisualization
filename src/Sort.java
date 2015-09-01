
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
		sort();
		
	}
	
	public final boolean isRunning() {
		
		return running;
		
	}
	
	public final boolean isSorted() {
		
		return sorted;
		
	}
	
}
