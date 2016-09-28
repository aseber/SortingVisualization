package SortStrategies;

public class SortShell extends Sort {

	int gapSize = 51;
	Element key;
	int i;
	int j;
	
	public SortShell(ElementArray inputArray) {
		super(inputArray);
		
	}

	@Override
	public void sort() {
		
		synchronized (this) {
			
			do {
				
				for (i = gapSize; i < array.size(); i++) {
					
					key = array.get(i);
					
					for (j = i; j >= gapSize && array.compare(key, j - gapSize) < 0; j -= gapSize) {
						
						array.set(j, array.get(j - gapSize));
						checkWait();
						
					}
					
					array.set(j, key);
					
				}
				
				System.out.println(gapSize);
				
				gapSize -= 5;
				
			} while (gapSize >= 1);
			
		}
		
	}

	/*private ArrayList<ElementSystem.ElementArray> createGaps(int count) {
		
		ArrayList<ArrayList<ElementSystem.Element>> elementLists = new ArrayList<ArrayList<ElementSystem.Element>>();
		ArrayList<ElementSystem.ElementArray> gaps = new ArrayList<ElementSystem.ElementArray>();
		int gapSize = (array.size() / gapCount);
		int gapIndex = 0;
		
		for (int i = 0; i < count; i++) {
			
			elementLists.add(new ArrayList<ElementSystem.Element>());
			
		}
		
		for (ElementSystem.Element currentElement : array.getElements()) {
			
			elementLists.get(gapIndex).add(new ElementSystem.Element(currentElement.getValue(), currentElement.getIndex(), false));
			gapIndex++;
			gapIndex %= count;
			
		}
		
		gapIndex = 0;
		
		for (ArrayList<ElementSystem.Element> elementList : elementLists) {
		
			System.out.println("Gap size: " + elementList.size());
			gaps.add(new ElementSystem.ElementArray(elementList, array.counter, gapIndex * gapSize));
			gapIndex++;
			
		}
		
		return gaps;
		
		
	}*/
	
}
