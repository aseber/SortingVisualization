import java.util.ArrayList;


public class SortMerge extends Sort{

	private ArrayList<Element> helperArrayList = new ArrayList<Element>();
	private ElementArray helper;
	
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
		
		//System.out.println("Split: " + (high - low + 1));
		
		if (low < high) {
			
			int middle = low + (high - low) / 2;
			//System.out.println("Split left: " + (middle - low));
			//System.out.println("Split right: " + (high - middle + 1));
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
		
		while (i < newMid && j < high) {
			
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
		
		/*System.out.println("i diff: " + (middle - i));
		System.out.println("j diff: " + (high - j));
		
		while (i <= middle) {
		
			System.out.println("i run");
			array.set(k, i);
			i++;
			k++;
			
		}

		while (j <= high) {
			
			System.out.println("j run");
			array.set(k, j);
			j++;
			k++;
			
		}
		
		/*if (i < middle) {
			
			while (i <= middle) {
				
				array.set(k, i);
				
			}
			
		}
		
		else if (j < high) {
			
			for (int index = k; index < high; index++) {
				
				//array.set(index, i + index);
				
			}
			
		}*/
		
	}
	
}
