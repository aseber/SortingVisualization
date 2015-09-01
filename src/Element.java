import java.awt.Color;
import java.awt.Graphics;

public class Element {
	
	// Elements encompass the concept of values and the methods required to evaluate and draw them.
	// White color refers to no operation happening
	// Red color refers to an array access
	// Blue color refers to a swap
	// Green color refers to a compare
	
	private int value;
	private int index;
	
	public Element(int inputValue, int arrayIndex) {
		
		value = inputValue;
		index = arrayIndex;
		VisualizationBase.VISUALIZATION_WINDOW.repaint(this);
		
	}
	
	public void drawElement(Graphics g) {
		
		int topOffset = 20;
		int bottomOffset = 20;
		
		double width = VisualizationBase.WINDOW_SIZE.getWidth();
		double total_height = VisualizationBase.WINDOW_SIZE.getHeight();
		double reduced_height = VisualizationBase.WINDOW_SIZE.getHeight() - topOffset - bottomOffset;
		double count_double = (double) VisualizationBase.SORT_COUNT;
		
		int x = (int) Math.round(width * ((double) index / (count_double)));
		int y = ((int) total_height - topOffset) - (int) Math.round(reduced_height * ((double) value / count_double));
		int deltaX = (int) Math.round(width / count_double) + 1;
		int deltaY = (int) total_height - y - bottomOffset;
		
		g.clearRect(x, topOffset, deltaX, (int) reduced_height);
		g.setColor(Color.WHITE);
		g.fillRect(x, y, deltaX, deltaY);
		
	}
	
	public int getValue() {
		
		return value;
		
	}
	
	public int getIndex() {
		
		return index;
		
	}
	
	public void setIndex(int newIndex) {
		
		index = newIndex;
		
	}
	
	public int compare(Element otherElement) {
		
		int thisValue = this.getValue();
		int otherValue = otherElement.getValue();
		int difference = thisValue - otherValue;
		
		return MyUtils.clampInt(1, difference, -1);
		
	}
	
	@Override
	public boolean equals(Object o) {
		
		Element otherElement = (Element) o;
		
		if (this.getValue() == otherElement.getValue()) {
			
			return true;
			
		}
		
		return false;
		
	}
	
}