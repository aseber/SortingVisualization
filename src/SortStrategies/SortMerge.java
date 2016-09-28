package SortStrategies;

public class SortMerge extends Sort{
	
	public SortMerge(ElementArray inputArray) {
		super(inputArray);
		
	}

	@Override
	public void sort() {
		
		synchronized (this) {
		
			split(0, array.size() - 1);
			
		}
		
	}
	
	public void split(int low, int high) {
		
		if (low < high) {
			
			int middle = low + (high - low) / 2;
			split(low, middle);
			split(middle + 1, high);
			merge(low, middle, high);
			
		}
		
	}
	
	public void merge(int low, int middle, int high) {
		
		int i = low;
		int j = middle + 1;
		int newMid = middle;
		Element key;
		
		while (i <= newMid && j <= high) {
			
			checkWait();
			
			if (array.compare(i, j) > 0) {
				
				key = array.get(j);
				
				for (int index = j - 1; index >= i; index--) {
					
					array.set(index + 1, index);
					
				}
				
				array.set(i, key);
				
				j++;
				newMid++;
				
			}
			
			i++;
			
		}
		
	}
	
}
