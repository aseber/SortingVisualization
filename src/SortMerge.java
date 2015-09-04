
public class SortMerge extends Sort{

	public SortMerge(ElementArray inputArray) {
		super(inputArray);
		
	}

	@Override
	public void sort() {
		
		synchronized (this) {
		
			if (array.size() > 2) {
				
				int middleIndex = array.size() / 2;
				new SortMerge(array.subList(0, middleIndex)).sort();
				new SortMerge(array.subList(middleIndex, array.size() - 1)).sort();
				merge();
				
			}
		
			else {
				
				merge();
				
			}
			
		}
		
	}

	public void merge() {
		
		int E0 = array.getOffset();
		int E1 = array.getOffset() + array.size() / 2;
		
		for (int i = 0; i < array.size(); i++) {
			
			
			
		}
		
	}
	
}
