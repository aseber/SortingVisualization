public class SortExecutor { // Simple class that allows me to move the processing to a new thread so the UI doesn't lag.
													// Also lets me test the algorithms speed
	private Sort sort;
	private Thread sortingThread;
	
	public void run(ElementArray inputArray, Sort.algorithms sortingAlgorithm) {

		if (sort != null) {
			
			if (sort.isRunning()) {
			
				if (!sort.isPaused()) {
				
					sort.pause();
				
				}
				
				else {
					
					sort.unpause();
					
				}
				
			}
			
			else {
				
				inputArray.resetCounters();
				sort = VisualizationBase.CURRENT_ALGORITHM.run(inputArray);
				sortingThread = new Thread(sort);
				sortingThread.start();
				
			}
			
		}
		
		else {
			
			sort = VisualizationBase.CURRENT_ALGORITHM.run(inputArray);
			sortingThread = new Thread(sort);
			sortingThread.start();
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		
		if (sort != null) {
			
			sort.stopSorting();
			sortingThread.stop();
			
		}
		
	}
	
	public boolean isSorting() {
		
		if (sort != null) {
			
			return sort.isRunning();
			
		}
	
		return false;
		
	}
	
}
