
public class SortInsertion extends Sort {

	int i;
	int j;
	Element key;
	
	public SortInsertion(ElementArray inputArray) {
		super(inputArray);
		
	}

	@Override
	public void sort() {
		
		for (i = 1; i < array.size(); i++) {
			
			key = array.get(i);
			
			for (j = i - 1; j >= 0 && array.compare(key, j) < 0; j--) {
				
				array.set(j + 1, array.get(j));
				
			}
			
			array.set(j + 1, key);
			
		}
		
	}

}
