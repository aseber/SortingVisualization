import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ElementArray {
	
	// ElementArrays are an immutable data type that contains an ArrayList of elements.
	// The constructor should add all values to the array
	// and subsequent functions can modify the elementArray list, but not the copy
	
	public static final int FORWARD_DIRECTION = 0;
	public static final int REVERSE_DIRECTION = 1;
	
	public static final int SORTED_ORDER = 2;
	public static final int RANDOM_ORDER = 3;
	public static final int ALMOST_SORTED_ORDER = 4;
	
	public static final double ALL_UNIQUE = 1.0;
	public static final double FEW_UNIQUE = 0.75;
	private static final double NO_UNIQUE = 0.0;
	
	private ArrayList<Element> elementArray = null;
	private List<Element> elementArrayCopy = null;
	private long accesses;
	private long compares;
	private long sets;
	
	@SuppressWarnings("unchecked")
	public ElementArray(int size, int direction, int order, double uniques) { // Do we ever set the element index correctly?
		
		elementArray = new ArrayList<Element>();
		accesses = 0;
		compares = 0;
		sets = 0;
		
		ArrayList<Element> basicElementArray = new ArrayList<Element>();
		
		if (uniques <= NO_UNIQUE || uniques > ALL_UNIQUE) {
			
			throw new IllegalArgumentException("Invalid uniques passed to ElementArray constructor: " + uniques);
			
		}
		
		int intervalIncrement = 0;
		
		if (uniques == ALL_UNIQUE) {
			
			intervalIncrement = 1;
			
		}
		
		else {
		
			intervalIncrement = (int) Math.ceil(size * (1.0 - uniques));
			
		}
		
		if (direction == FORWARD_DIRECTION) {
			
			for (int index = 0; index < size; index++) {
				
				int value = (int) Math.floor((index) / intervalIncrement) * intervalIncrement + 1;
				
				basicElementArray.add(new Element(value, index)); // Creates the forward Array, very fast and simple
				
			}
			
		}
		
		else if (direction == REVERSE_DIRECTION) {
			
			for (int index = 0; index < size; index++) {
				
				int value = (int) Math.floor((index) / intervalIncrement) * intervalIncrement + 1;
				
				basicElementArray.add(new Element(value, (size - 1) - index)); // Creates the reverse Array, very fast and simple
				
			}
			
		}
		
		else {
			
			throw new IllegalArgumentException("Invalid direction flag passed to ElementArray constructor: " + direction);
			
		}
		
		if (order == SORTED_ORDER) {
			
			for (Element currentElement : basicElementArray) {
				
				elementArray.add(currentElement);
				
			}
			
		}

		else if (order == RANDOM_ORDER) { // Randomize by taking the forward array and doing 10*size random swaps
			
			ElementArray sortingElementArray = new ElementArray(size, direction, SORTED_ORDER, uniques);
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
			
			ElementArray sortingElementArray = new ElementArray(size, direction, SORTED_ORDER, uniques);
			int RANDOMIZE_COUNTER = (int) Math.ceil(size / 50);
			Random random = new Random();
			
			for (int i = 0; i < RANDOMIZE_COUNTER; i++) {
				
				sortingElementArray.swap(random.nextInt(size), random.nextInt(size));
				
			}
			
			for (Element currentElement : sortingElementArray.getElements()) {
				
				elementArray.add(currentElement);
				
			}
			
		}
		
		else {
			
			throw new IllegalArgumentException("Invalid order flag passed to ElementArray constructor: " + order);
			
		}
		
		elementArrayCopy = Collections.unmodifiableList((ArrayList<Element>) elementArray.clone());
		
	}
	
	public Element get(int index) {
		
		accesses++;
		Element element = elementArray.get(index);
		//VisualizationBase.VISUALIZATION_WINDOW.registerEvent(element, Color.RED, 5000); // Slow
		return element;
		
	}
	
	private Element getWithoutIncrement(int index) {
		
		return elementArray.get(index);
		
	}
	
	public int compare(int indexE1, int indexE2) {
		
		Element E1 = this.get(indexE1);
		Element E2 = this.get(indexE2);
		return compare(E1, E2);
		
	}
	
	public int compare(Element E1, int indexE2) {
		
		Element E2 = this.get(indexE2);
		return compare(E1, E2);
		
	}
	
	public int compare(int indexE1, Element E2) {
		
		Element E1 = this.get(indexE1);
		return compare(E1, E2);
		
	}
	
	public int compare(Element E1, Element E2) {
		
		compares++;
		//VisualizationBase.VISUALIZATION_WINDOW.registerEvent(E1, Color.GREEN, 5000);
		//VisualizationBase.VISUALIZATION_WINDOW.registerEvent(E2, Color.GREEN, 5000);
		return E1.compare(E2);
		
	}

	public void swap(int indexE1, int indexE2) {
		
		Element E1 = this.get(indexE1);
		Element E2 = this.get(indexE2);
		set(indexE1, E2);
		set(indexE2, E1);
		
	}
	
	public void set(int index, Element E1) {
		
		sets++;
		E1.setIndex(index);
		elementArray.set(index, E1);
		//VisualizationBase.VISUALIZATION_WINDOW.registerEvent(E1, Color.BLUE, 5000);
		VisualizationBase.VISUALIZATION_WINDOW.repaint(E1);
		
	}
	
	public long getAccesses() {
		
		return accesses;
		
	}
	
	public long getCompares() {
		
		return compares;
		
	}
	
	public long getSets() {
		
		return sets;
		
	}
	
	public List<Element> getElements() {
		
		return Collections.unmodifiableList(elementArray);
		
	}
	
	public Element getClosestElement(int xPos) {
		
		int count = elementArray.size();
		double interval = (VisualizationBase.WINDOW_SIZE.getWidth() / ((double) elementArray.size()));
		int index = MyUtils.clampInt(count - 1, (int) Math.round(xPos / interval), 0);
		return getWithoutIncrement(index);
		
	}
	
	public int size() {
		
		return elementArray.size();
		
	}
	
	public void reset() {	// Resets the elementArray by first creating an empty arrayList and then getting all the values from the
							// immutable list
		
		elementArray = new ArrayList<Element>();
		accesses = 0;
		compares = 0;
		sets = 0;
		
		for (Element currentElement : elementArrayCopy) {
			
			System.out.println(currentElement.getIndex());
			elementArray.add(currentElement);
			
		}
		
		VisualizationBase.VISUALIZATION_WINDOW.repaintAllElements();
		
	}
	
}
