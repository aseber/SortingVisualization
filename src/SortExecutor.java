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
				sort = createSort(inputArray, sortingAlgorithm);
				sortingThread = new Thread(sort);
				sortingThread.start();
				
			}
			
		}
		
		else {
			
			sort = createSort(inputArray, sortingAlgorithm);
			sortingThread = new Thread(sort);
			sortingThread.start();
			
		}
		
	}

	private Sort createSort(ElementArray inputArray, Sort.algorithms sortingAlgorithm) {
		
		if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.BOGO) {
			
			//return new SortBogo(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.HEAP) {
		
			//return new SortHeap(inputArray);
		
		}
	
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.GNOME) {
			
			//return new SortGnome(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.MERGE) {
			
			//return new SortMerge(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.SHELL) {
			
			//return new SortShell(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.QUICK) {
			
			//return new SortQuick(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.BUBBLE) {
			
			return new SortBubble(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.SHAKER) {
			
			return new SortShaker(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.BITONIC) {
			
			//return new SortBitonic(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.STDSORT) {
			
			//return new SortStd(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.RADIXLSD) {
			
			//return new SortRadixLSD(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.RADIXMSD) {
			
			//return new SortRadixMSD(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.INSERTION) {
			
			return new SortInsertion(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.SELECTION) {
			
			return new SortSelection(inputArray);
		
		}
		
		else if (VisualizationBase.CURRENT_ALGORITHM == Sort.algorithms.STDSTABLESORT) {
			
			//return new SortStdStable(inputArray);
		
		}
		
		else {
			
			throw new IllegalArgumentException("Bad algorithm supplied to SortExecutor!");
			
		}
		
		return null;
		
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
