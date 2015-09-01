import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class VisualizationWindow extends JPanel implements ComponentListener, MouseMotionListener, KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3756454707674613931L;
	Point mouse = new Point();
	boolean drawDebugInformation = false;
	ElementArray elementArray = null;
	BufferedImage image;
	
	public VisualizationWindow() {
		
		setFocusable(true);
		addComponentListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setIgnoreRepaint(true);
		setBackground(Color.BLACK);
	
		image = new BufferedImage(VisualizationBase.WINDOW_SIZE.width, VisualizationBase.WINDOW_SIZE.height, BufferedImage.TYPE_INT_ARGB);
		image.getGraphics().setColor(Color.BLACK);
		image.getGraphics().drawRect(0, 0, VisualizationBase.WINDOW_SIZE.width, VisualizationBase.WINDOW_SIZE.height);
		
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, VisualizationBase.WINDOW_SIZE.width, VisualizationBase.WINDOW_SIZE.height);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		int mouseX = (int) mouse.getX();
		int mouseY = (int) mouse.getY();
		
		g.drawImage(image, 0, 0, this.getBackground(), null);
		
		int windowSizeX = VisualizationBase.WINDOW_SIZE.width;
		int windowSizeY = VisualizationBase.WINDOW_SIZE.height;
			
		g.setColor(new Color(200, 200, 200, 100));
		g.fillRect(0, windowSizeY - 20, windowSizeX, 20);
		
		g.setColor(Color.WHITE);

		String display1 = "Algorithm: " + VisualizationBase.CURRENT_ALGORITHM;
		int offsetX = 5;
		g.drawString(display1, offsetX, 12);
		
		String display2 = "Data Size: " + VisualizationBase.SORT_COUNT;
		offsetX += g.getFontMetrics().stringWidth(display1) + 5;
		g.drawString(display2, offsetX, 12);
		
		if (drawDebugInformation) {
	
			String display3 = "Mouse: [" + mouseX + ", " + mouseY + "]";
			offsetX += g.getFontMetrics().stringWidth(display2) + 5;
			g.drawString(display3, offsetX, 12);
			
			if (elementArray != null) {
				
				Element curElement = elementArray.getClosestElement(mouseX);

				String display4 = "Index: " + curElement.getIndex();
				offsetX += g.getFontMetrics().stringWidth(display3) + 5;
				g.drawString(display4, offsetX, 12);
				String display5 = "Value: " + curElement.getValue();
				offsetX += g.getFontMetrics().stringWidth(display4) + 5;
				g.drawString(display5, offsetX, 12);
				
			}
			
			g.setColor(Color.RED);
			g.drawLine(mouseX, 0, mouseX, windowSizeY);
			g.drawLine(0, mouseY, windowSizeX, mouseY);
			g.setColor(Color.WHITE);
		
		}
		
		//repaint();
		
	}
	
	public void repaint(Element element) {

		element.drawElement(image.getGraphics());
		repaint();
		
	}
	
	public void repaintAllElements() {
		
		if (elementArray == null) {
			
			return;
			
		}
		
		for (Element element : elementArray.getElements()) {
			
			element.drawElement(image.getGraphics());
			
		}
		
		repaint();
		
	}
	
	public boolean isMouseInApplet() {

		int mouseX = mouse.x;
		int mouseY = mouse.y;
		int sizeX = VisualizationBase.WINDOW_SIZE.width;
		int sizeY = VisualizationBase.WINDOW_SIZE.height;
		
		if (mouseX >= 0 && mouseY >= 0 && mouseX <= sizeX && mouseY <= sizeY) {
		
			return true;
		
		}
	
		return false;
	
	}

	public void createElementsArray() {
		
		elementArray = new ElementArray(VisualizationBase.SORT_COUNT, ElementArray.REVERSE_DIRECTION, ElementArray.RANDOM_ORDER, ElementArray.FEW_UNIQUE);
		repaintAllElements();
		
	}
	
	public void resetElementArray() { // Sets ALL flags to default
		
		elementArray = null;
		
	}
	
	public void setWindowSize(Dimension d) {
		
		image = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		Dimension newDimension = new Dimension(d.width + 17, d.height + 94);
		this.setSize(d);
		VisualizationBase.VISUALIZATION_GUI.setSize(newDimension);
		image.getGraphics().setColor(Color.BLACK);
		image.getGraphics().drawRect(0, 0, image.getWidth(), getHeight());
		
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
		int sizeX = VisualizationBase.WINDOW_SIZE.width;
		int sizeY = VisualizationBase.WINDOW_SIZE.height;
		int mouseX = e.getX();
		int mouseY = e.getY();
		mouse.setLocation(MyUtils.clampInt(sizeX, mouseX, 0), MyUtils.clampInt(sizeY, mouseY, 0));
		repaint();
		
	}
	
	public void mouseDragged(MouseEvent e) { // This should draw line between last two points and all boxes that intersect it are colored.
		
		int sizeX = VisualizationBase.WINDOW_SIZE.width;
		int sizeY = VisualizationBase.WINDOW_SIZE.height;
		int mouseX = e.getX();
		int mouseY = e.getY();
		mouse.setLocation(MyUtils.clampInt(sizeX, mouseX, 0), MyUtils.clampInt(sizeY, mouseY, 0));
		repaint();
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			
			
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			new Thread(new SortExecutor(elementArray)).start();
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			elementArray.reset();
			
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_ALT) {
			
			drawDebugInformation = !drawDebugInformation;
			e.consume();
			repaint();
			
		}
		
	}
	
	public void componentResized(ComponentEvent e) {
		
		int WINDOW_WIDTH = e.getComponent().getWidth();
		int WINDOW_HEIGHT = e.getComponent().getHeight();
		VisualizationBase.WINDOW_SIZE.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		image = new BufferedImage(VisualizationBase.WINDOW_SIZE.width, VisualizationBase.WINDOW_SIZE.height, BufferedImage.TYPE_INT_ARGB);
		image.getGraphics().setColor(Color.BLACK);
		image.getGraphics().drawRect(0, 0, VisualizationBase.WINDOW_SIZE.width, VisualizationBase.WINDOW_SIZE.height);
		repaintAllElements();
		
	}
	
	public void keyTyped(KeyEvent e) {}
	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
	
}