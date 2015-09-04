
public class SortMerge extends Sort{

	public SortMerge(ElementArray inputArray) {
		super(inputArray);
		
	}

	@Override
	public void sort() {
		
		if (array.size() != 1) {
			
			int middle = array.size() / 2;
			new SortMerge(array.subList(0, middle));
			
		}
		
	}

}
