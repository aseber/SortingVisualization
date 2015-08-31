import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ElementArray {
	
	// ElementArrays are an immutable data type that contains an ArrayList of elements.
	// The constructor should add all values to the array
	// Afterwards, the only methods you can do are get(index), swap(indexE1, indexE2), compare(indexE1, indexE2),
	// getAccesses(), getCompares(), getSwaps(), getElements(), findClosestElement(xPos), and reset()
	
	public static final int NO_CHANGE = 0;
	public static final int RANDOM_ORDER = 1;
	public static final int ALMOST_SORTED_ORDER = 2;
	public static final int FEW_UNIQUE_ORDER = 3;
	public static final int REVERSE_ORDER = 4;
	public static final int FORWARD_ORDER = 5;
	
	private ArrayList<Element> elementArray = new ArrayList<Element>();
	private ArrayList<Element> elementArrayCopy = new ArrayList<Element>();
	private long accesses;
	private long compares;
	private long swaps;
	
	@SuppressWarnings("unchecked")
	public ElementArray(ArrayList<Element> list, int flag) {
		
		accesses = 0;
		compares = 0;
		swaps = 0;
		
		if (flag == NO_CHANGE) {
			
			elementArray = (ArrayList<Element>) list.clone();
			elementArrayCopy = (ArrayList<Element>) list.clone();
			
		}
		
		else if (flag == RANDOM_ORDER) {
			
			ElementArray sortingElementArray = new ElementArray(list, NO_CHANGE);
			int count = VisualizationBase.SORT_COUNT;
			int RANDOMIZE_COUNTER = count * 10;
			Random random = new Random();
			
			for (int i = 0; i < RANDOMIZE_COUNTER; i++) {
				
				sortingElementArray.swap(random.nextInt(count), random.nextInt(count));
				
			}
			
			for (Element currentElement : sortingElementArray.getElements()) {
				
				elementArray.add(currentElement);
				elementArrayCopy.add(currentElement);
				
			}
			
		}
		
		else if (flag == ALMOST_SORTED_ORDER) {
			
			ElementArray sortingElementArray = new ElementArray(list, NO_CHANGE);
			int count = VisualizationBase.SORT_COUNT;
			int RANDOMIZE_COUNTER = (int) Math.ceil(count / 50);
			Random random = new Random();
			
			for (int i = 0; i < RANDOMIZE_COUNTER; i++) {
				
				sortingElementArray.swap(random.nextInt(count), random.nextInt(count));
				
			}
			
			for (Element currentElement : sortingElementArray.getElements()) {
				
				elementArray.add(currentElement);
				elementArrayCopy.add(currentElement);
				
			}
			
		}
		
		else if (flag == FEW_UNIQUE_ORDER) { // Unimplemented thus far
			
			elementArray = (ArrayList<Element>) list.clone();
			elementArrayCopy = (ArrayList<Element>) list.clone();
			
		}
		
		else if (flag == REVERSE_ORDER) { // Unimplemented thus far
			
			ArrayList<Element> sortingElementArray = (ArrayList<Element>) list.clone();
			
			
			
			elementArray = (ArrayList<Element>) sortingElementArray.clone();
			elementArrayCopy = (ArrayList<Element>) list.clone();
			
		}
		
		else if (flag == FORWARD_ORDER) { // Unimplemented thus far
			
			ArrayList<Element> sortingElementArray = (ArrayList<Element>) list.clone();
			
			
			
			elementArray = (ArrayList<Element>) sortingElementArray.clone();
			elementArrayCopy = (ArrayList<Element>) list.clone();
			
		}
		
		else {
			
			throw new IllegalArgumentException("Invalid flag sent to Element Array: " + flag);
			
		}
		
	}
	
	public Element get(int index) {
		
		accesses++;
		return elementArray.get(index);
		
	}
	
	public Element getWithoutIncrement(int index) {
		
		return elementArray.get(index);
		
	}
	
	public int compare(int indexE1, int indexE2) {
		
		compares++;
		Element e1 = this.get(indexE1);
		Element e2 = this.get(indexE2);
		
		return e1.compare(e2);
		
	}
	
	public void swap(int indexE1, int indexE2) {
		
		swaps++;
		Element E1 = this.get(indexE1);
		Element E2 = this.get(indexE2);
		E1.setIndex(indexE2);
		E2.setIndex(indexE1);
		elementArray.set(indexE2, E1);
		elementArray.set(indexE1, E2);
		
	}
	
	public long getAccesses() {
		
		return accesses;
		
	}
	
	public long getCompares() {
		
		return compares;
		
	}
	
	public long getSwaps() {
		
		return swaps;
		
	}
	
	public List<Element> getElements() {
		
		return Collections.unmodifiableList(elementArray);
		
	}
	
	public Element getClosestElement(int xPos) {
		
		int count = VisualizationBase.SORT_COUNT;
		double interval = (VisualizationBase.WINDOW_SIZE.getWidth() / ((double) VisualizationBase.SORT_COUNT));
		int index = MyUtils.clampInt(count - 1, (int) Math.round(xPos / interval), 0);
		return getWithoutIncrement(index);
		
	}
	
	@SuppressWarnings("unchecked")
	public void reset() {
		
		elementArray = (ArrayList<Element>) elementArrayCopy.clone();
		
	}
	
}
