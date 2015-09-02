import java.awt.Color;
import java.awt.Graphics;

public class ElementEvent implements Runnable { // class that executes the region flashing thing
	
	private Element element = null;
	private Graphics g = null;
	private Color color = null;
	
	public ElementEvent(Element inputElement, Graphics inputG, Color inputColor) {
		
		element = inputElement;
		g = inputG;
		color = inputColor;
		
	}
	
	public void drawBeginning() {
		
		element.setColor(color);
		element.drawElement(g);
		VisualizationBase.VISUALIZATION_WINDOW.repaint(element);
		
	}
	
	private void drawEnd() {
		
		element.setColor(Color.WHITE);
		element.drawElement(g);
		VisualizationBase.VISUALIZATION_WINDOW.repaint(element);
		
	}
	
	public void run() {
		
		drawEnd();
		ElementEventDriver.removeEvent(this);
		
	}
	
	public Element getElement() {
		
		return element;
		
	}
	
	public Color getColor() {
		
		return color;
		
	}
	
	@Override
	public int hashCode() {
		
		return element.getIndex();
		
	}
	
	@Override
	public boolean equals(Object o) {
		
		ElementEvent otherEvent = (ElementEvent) o;
		
		if (element.getIndex() == otherEvent.getElement().getIndex()) {
			
			return true;
			
		}
		
		return false;
		
	}
	
}
