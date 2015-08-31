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
	//public static final int FEW_UNIQUE_ORDER = 3;
	
	public static final int FORWARD_DIRECTION = 4;
	public static final int REVERSE_DIRECTION = 5;
	
	private ArrayList<Element> elementArray = null;
	private List<Element> elementArrayCopy = null;
	private long accesses;
	private long compares;
	private long swaps;
	
	public ElementArray(int size, int direction, int order, double uniques) { // Do we ever set the element index correctly?
		
		elementArray = new ArrayList<Element>();
		accesses = 0;
		compares = 0;
		swaps = 0;
		
		ArrayList<Element> basicElementArray = new ArrayList<Element>();
		
		if (direction == FORWARD_DIRECTION) {
			
			for (int index = 0; index < VisualizationBase.SORT_COUNT; index++) {
				
				basicElementArray.add(new Element(index + 1, index)); // Create the forward Array, very fast and simple
				
			}
			
		}
		
		else if (direction == REVERSE_DIRECTION) {
			
			for (int index = 0; index < size; index++) {
				
				basicElementArray.add(new Element(index + 1, (size - 1) - index)); // Create the forward Array, very fast and simple
				
			}
			
		}
		
		else {
			
			throw new IllegalArgumentException("Invalid direction flag sent to Element Array: " + direction);
			
		}
		
		if (order == NO_CHANGE) {
			
			for (Element currentElement : basicElementArray) {
				
				elementArray.add(currentElement);
				
			}
			
		}

		else if (order == RANDOM_ORDER) { // Randomize by taking the forward array and doing 10*size random swaps
			
			ElementArray sortingElementArray = new ElementArray(size, direction, NO_CHANGE, 1.0);
			int RANDOMIZE_COUNTER = size * 10;
			Random random = new Random();
			
			for (int i = 0; i < RANDOMIZE_COUNTER; i++) {
				
				sortingElementArray.swap(random.nextInt(size), random.nextInt(size));
				
			}
			
			for (Element currentElement : sortingElementArray.getElements()) {
				
				elementArray.add(currentElement);
				
			}
			
		}
		
		else if (order == ALMOST_SORTED_ORDER) { // Randomize by taking the forward array and doing size / 50 random swaps
			
			ElementArray sortingElementArray = new ElementArray(size, direction, NO_CHANGE, 1.0);
			int RANDOMIZE_COUNTER = (int) Math.ceil(size / 50);
			Random random = new Random();
			
			for (int i = 0; i < RANDOMIZE_COUNTER; i++) {
				
				sortingElementArray.swap(random.nextInt(size), random.nextInt(size));
				
			}
			
			for (Element currentElement : sortingElementArray.getElements()) {
				
				elementArray.add(currentElement);
				
			}
			
		}
		
		/*else if (order == FEW_UNIQUE_ORDER) { // Unimplemented thus far
			
			ElementArray sortingElementArray = new ElementArray(size, direction, RANDOM_ORDER, 1.0);
			int RANDOMIZE_COUNTER = size * 10;
			Random random = new Random();
			
			for (int i = 0; i < RANDOMIZE_COUNTER; i++) {
				
				sortingElementArray.swap(random.nextInt(size), random.nextInt(size));
				
			}
			
			for (Element currentElement : sortingElementArray.getElements()) {
				
				elementArray.add(currentElement);
				
			}
			
		}*/
		
		else {
			
			throw new IllegalArgumentException("Invalid order flag sent to Element Array: " + order);
			
		}
		
		elementArrayCopy = Collections.unmodifiableList(elementArray);
		
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
		
		int count = elementArray.size();
		double interval = (VisualizationBase.WINDOW_SIZE.getWidth() / ((double) VisualizationBase.SORT_COUNT));
		int index = MyUtils.clampInt(count - 1, (int) Math.round(xPos / interval), 0);
		return getWithoutIncrement(index);
		
	}
	
	public void reset() {	// Resets the elementArray by first creating an empty arrayList and then getting all the values from the
							// immutable list
		
		elementArray = new ArrayList<Element>();
		
		for (Element currentElement : elementArrayCopy) {
			
			elementArray.add(currentElement);
			
		}
		
	}
	
}
