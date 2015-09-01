//import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;

//import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class ElementEvent implements Runnable { // class that executes the region flashing thing
	
	//private static Color transparant = new Color(255, 255, 255, 0);
	//private static AlphaComposite compositeApply = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
	//private static AlphaComposite compositeRemove = AlphaComposite.getInstance(AlphaComposite.CLEAR, 1f);
	
	HashFunction hf = Hashing.md5();
	
	private Element element = null;
	private Graphics g = null;
	private Color color = null;
	
	public ElementEvent(Element inputElement, Graphics inputG, Color inputColor) {
		
		element = inputElement;
		g = inputG;
		color = inputColor;
		
	}
	
	public void drawBeginning() {
		
		element.drawElement(g, color);
		VisualizationBase.VISUALIZATION_WINDOW.repaint(element);
		
	}
	
	private void drawEnd() {
		
		element.drawElement(g, Color.WHITE);
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
