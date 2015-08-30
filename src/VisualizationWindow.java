import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class VisualizationWindow extends JPanel implements ComponentListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	
	Pathfind pathfinder = null;
	
	private static final long serialVersionUID = -453107461013635372L;
	int WINDOW_WIDTH, WINDOW_HEIGHT;
	static boolean mouse1Down, mouse3Down, shiftDown, spaceDown;
	int escapeState = -1;
	Point mouse = new Point();
	Point oldMouse = new Point();
	boolean drawDebugInformation = false;
	boolean drawFunInformation = false;
	int drawSize = 1;
	BufferedImage image;
	
	public VisualizationWindow() {
		
		setFocusable(true);
		addComponentListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		addMouseWheelListener(this);
		setIgnoreRepaint(true);
		setBackground(Color.CYAN);
		
		WINDOW_WIDTH = getWidth();
		WINDOW_HEIGHT = getHeight();
	
		int sizeX = (int) (VisualizationBase.ROW_COUNT*VisualizationBase.size.getWidth()) + 1;
		int sizeY = (int) (VisualizationBase.COLUMN_COUNT*VisualizationBase.size.getHeight()) + 1;
		image = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		int mouseX = (int) mouse.getX();
		int mouseY = (int) mouse.getY();
		
		//g.drawLine(0, mouseX, mouseX, WINDOW_HEIGHT);
		//g.drawLine(mouseY, 0, WINDOW_WIDTH, mouseY);
		
		g.drawImage(image, 0, 0, this.getBackground(), null);
		
		g.setColor(Color.BLACK);
		//System.out.println(mouseX);
		g.drawArc(mouseX - drawSize, mouseY - drawSize, drawSize*2, drawSize*2, 0, 360);
		
		if (drawDebugInformation) {
			
			Box curBox = Box.getBoxFromPosition(mouse);
			Point newPoint = new Point((int) (curBox.physicalPosition.getX() + VisualizationBase.size.getWidth()), (int) (curBox.physicalPosition.getY() + VisualizationBase.size.getHeight()));
			Box[] boxes = Box.squaresInCircle(newPoint, drawSize);
			
			for (Box box : boxes) {
				
				box.setColor(new Color(255, 0, 150));
				box.setFlag(Box.BOX_SEARCHED_FLAG);
				
			}
			
			g.setColor(Color.BLACK);
			g.drawLine((int) oldMouse.getX(), (int) oldMouse.getY(), mouseX, mouseY); // Just need to find intersecting			
			g.setColor(Color.RED);
			g.drawLine(mouseX, 0, mouseX, WINDOW_HEIGHT);
			g.drawLine(0, mouseY, WINDOW_WIDTH, mouseY);
			g.setColor(Color.BLACK);
			g.drawString("Mouse: [" + mouseX + ", " + mouseY + "]", mouseX + 15, mouseY + 15);
			g.drawString("Window: [" + WINDOW_WIDTH + ", " + WINDOW_HEIGHT + "]", mouseX + 15, mouseY + 30);
			g.drawString("Box: [" + Box.findClosestIndex(mouseX, VisualizationBase.ROW_COUNT, WINDOW_WIDTH) + ", " + Box.findClosestIndex(mouseY, VisualizationBase.COLUMN_COUNT, WINDOW_HEIGHT) + "]", mouseX + 15, mouseY + 45);
			g.drawString("Box flag: " + Box.getBoxFromPosition(mouseX, mouseY).getFlag(), mouseX + 15, mouseY + 60);
			repaint();
		
		}
		
		oldMouse.setLocation(mouseX, mouseY);
		
	}
	
	public void setDrawSize(int value) {
		
		drawSize = value;
		repaint();
		
	}
	
	public void repaint(Box box) {

		drawBox(image.getGraphics(), box);
		repaint();
		
	}
	
	public void repaint(Box[] boxes) {
		
		for (Box box : boxes) {
			
			repaint(box);
			
		}
		
	}
	
	public void repaintAll() {
		
		repaint(Box.getAllBoxes());
		
	}
	
	private void drawBox(Graphics g, Box currentBox) {
		
		int x = (int) currentBox.physicalPosition.getX();
		int y = (int) currentBox.physicalPosition.getY();
		int sizeX = (int) VisualizationBase.size.getWidth();
		int sizeY = (int) VisualizationBase.size.getHeight();
		Color color = currentBox.getActiveColor();
		
		g.setColor(color);
		g.fillRect(x, y, sizeX, sizeY);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, sizeX, sizeY);
		
		/*Graphics2D g2d = (Graphics2D) g;
		
		BufferedImage boxImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
		Graphics2D boxGraphic = boxImage.createGraphics();
		Rectangle wholeRectangle = new Rectangle(0, 0, 10, 10);
		Rectangle drawRectangle = new Rectangle(0, 0, 10, 10);
		Rectangle fillRectangle = new Rectangle(1, 1, 9, 9);
		
		boxGraphic.setColor(Color.WHITE);
		boxGraphic.fill(wholeRectangle);
		boxGraphic.setColor(Color.BLACK);
		boxGraphic.draw(drawRectangle);
		boxGraphic.setColor(currentBox.getActiveColor());
		boxGraphic.fill(fillRectangle);
		
		AffineTransform t = new AffineTransform();
		t.translate(currentBox.x, currentBox.y);
		t.scale(currentBox.sizeX/10, currentBox.sizeY/10);
		g2d.drawImage(boxImage, t, null);*/

	}
	
	public boolean isMouseInApplet() {

		if (0 <= mouse.getX() && WINDOW_WIDTH >= mouse.getX() && 0 <= mouse.getY() && WINDOW_HEIGHT >= mouse.getY()) {
		
			return true;
		
		}
	
		return false;
	
	}

	public void createBoxField() {
		
		Box.clearBoxes();
		
		for (int row = 0; row < VisualizationBase.ROW_COUNT; row++) {
			
			for (int column = 0; column < VisualizationBase.COLUMN_COUNT; column++) {
			
				int x = (int) (row*(VisualizationBase.size.getWidth()));
				int y = (int) (column*(VisualizationBase.size.getHeight()));
				new Box(x, y, row, column, Box.BOX_STANDARD_FLAG);
			
			}
		
		}
		
		repaintAll();
		
	}
	
	public void resetBoxField() { // Sets ALL flags to default
		
		Box.resetStartBox();
		Box.resetEndBox();
		
		for (int row = 0; row < VisualizationBase.ROW_COUNT; row++) {
			
			for (int column = 0; column < VisualizationBase.COLUMN_COUNT; column++) {
			
				Box.getBoxFromIndex(row, column).setFlag(Box.BOX_STANDARD_FLAG);
			
			}
		
		}
		
	}
	
	public void clearBoxFieldFlag(int flag) { // Removes ALL flags besides barrier types
		
		if (flag == Box.BOX_STANDARD_FLAG) {return;}
		if (flag == Box.BOX_START_FLAG) {Box.resetStartBox(); return;}
		if (flag == Box.BOX_END_FLAG) {Box.resetEndBox();  return;}
		if (flag < Box.BOX_STANDARD_FLAG || flag > Box.BOX_QUEUED_FLAG) {return;}
		
		Box[] boxes = Box.getAllBoxes();
		
		for (Box box : boxes) {
			
			if (box.getFlag() == flag) {
				
				box.setFlag(Box.BOX_STANDARD_FLAG);
				
			}
			
		}
		
	}
	
	public void clearBoxFieldFlags(int[] flags) {
		
		for (int flag : flags) {
			
			clearBoxFieldFlag(flag);
			
		}
		
	}
	
	public static int clampInt(int a, int b, int c) {
	
		return Math.max(c, Math.min(a, b));
	
	}
	
	public void setWindowSize(Dimension d) {
		
		int sizeX = (int) (VisualizationBase.ROW_COUNT*VisualizationBase.size.getWidth()) + 1;
		int sizeY = (int) (VisualizationBase.COLUMN_COUNT*VisualizationBase.size.getHeight()) + 1;
		image = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
		Dimension newDimension = new Dimension((int) (d.getWidth() + 17), (int) (d.getHeight() + 95));
		this.setSize(d);
		VisualizationBase.VISUALIZATION_GUI.setSize(newDimension);
		image.getGraphics().setColor(Color.CYAN);
		image.getGraphics().drawRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		
	}
	
	/*public void getMouseInWindow(boolean clamp) {

		int x = (int)(MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().getX());
		int y = (int)(MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().getY());
		
		if (clamp) {
		
			x = clampInt(WINDOW_WIDTH, x, 0);
			y = clampInt(WINDOW_HEIGHT, y, 0);
			
		}
		
		mouse.setLocation(x, y);*/
		
	//}//
	
	public void mousePressed(MouseEvent e) {
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			
			mouse1Down = true;
			
		}
		
		else if (e.getButton() == MouseEvent.BUTTON3) {
			
			mouse3Down = true;
			
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			
			mouse1Down = false;
			
		}
		
		else if (e.getButton() == MouseEvent.BUTTON3) {
			
			mouse3Down = false;
			
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
		int mouseX = e.getX();
		int mouseY = e.getY();
			
		Box box = Box.getBoxFromPosition(mouseX, mouseY);
		Point newPoint = new Point((int) (box.physicalPosition.getX() + VisualizationBase.size.getWidth()), (int) (box.physicalPosition.getY() + VisualizationBase.size.getHeight()));
		Box[] boxes = Box.squaresInCircle(newPoint, drawSize);
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			
			if (shiftDown) {
				
				box.setFlag(Box.BOX_START_FLAG);
				
				if (escapeState != 0) {
				
					escapeState = 1;
					
				}
				
			}
			
			else {
				
				Box.setFlags(boxes, Box.BOX_BARRIER_FLAG);
				
			}
			
		}
		
		if (e.getButton() == MouseEvent.BUTTON3) {
			
			if (shiftDown) {
				
				box.setFlag(Box.BOX_END_FLAG);
				
				if (escapeState != 0) {
				
					escapeState = 1;
				
				}
				
			}
			
			else {
				
				Box.setFlags(boxes, Box.BOX_STANDARD_FLAG);
				
			}
			
		}
		
	}
	
	public void mouseMoved(MouseEvent e) {
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		mouse.setLocation(clampInt(WINDOW_WIDTH, mouseX, 0), clampInt(WINDOW_HEIGHT, mouseY, 0));
		
		Box box = Box.getBoxFromPosition(mouseX, mouseY);
		box.setSelected();
		
		Box oldBox = Box.getBoxFromPosition(oldMouse);
		
		if (drawFunInformation) {
		
			if (box != oldBox) {
			
				int[] flags = {Box.BOX_SEARCHED_FLAG, Box.BOX_SHORTEST_PATH_FLAG, Box.BOX_QUEUED_FLAG};
				clearBoxFieldFlags(flags);
				
				Pathfind path = new AStarPathfind(new Node(Box.getBoxFromIndex(VisualizationBase.ROW_COUNT/2, VisualizationBase.COLUMN_COUNT/2), null), new Node(Box.getBoxFromPosition(mouseX, mouseY), null));
				path.start();
				
			}
		
		}
		
	}
	
	public void mouseDragged(MouseEvent e) { // This should draw line between last two points and all boxes that intersect it are colored.
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		mouse.setLocation(clampInt(WINDOW_WIDTH, mouseX, 0), clampInt(WINDOW_HEIGHT, mouseY, 0));
			
		ArrayList<Box> boxesList = new ArrayList<Box>(5);
		Box box = Box.getBoxFromPosition(mouseX, mouseY);
		Box oldBox = Box.getBoxFromPosition(oldMouse);
		
		boxesList.add(box);
		
		if (box != oldBox) { // Far enough apart that we care about interpolating between the points.
			
			Box[] boxes = Box.squaresBetweenPoints(new Point(mouseX, mouseY), oldMouse, drawSize);
			
			for (Box currentBox : boxes) {
			
				boxesList.add(currentBox);
				
			}
			
		}
		
		if (mouse1Down) {
		
			for (Box currentBox : boxesList) {
				
				currentBox.setFlag(Box.BOX_BARRIER_FLAG);
				
			}
			
			box.setSelected();
		
		}
		
		else if (mouse3Down) {
		
			for (Box currentBox : boxesList) {
				
				currentBox.setFlag(Box.BOX_STANDARD_FLAG);
				
			}

			box.setSelected();
			
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			
			shiftDown = true;
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			spaceDown = true;
			
			try {
				
				if (pathfinder.isRunning()) {
					
					pathfinder.togglePause();
					
				} else {
					
					if (Box.startBox != null && Box.endBox != null) {
					
						int[] flags = {Box.BOX_SEARCHED_FLAG, Box.BOX_SHORTEST_PATH_FLAG, Box.BOX_QUEUED_FLAG};
						clearBoxFieldFlags(flags);
						
						if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.ASTAR) {
						
							pathfinder = new AStarPathfind(new Node(Box.startBox, null), new Node(Box.endBox, null));
							
						} else if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.DIJKSTRA) {
						
							pathfinder = new DijkstrasPathfind(new Node(Box.startBox, null), new Node(Box.endBox, null));
							
						} else if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.CUSTOM) {
							
							pathfinder = new CustomPathfind(new Node(Box.startBox, null), new Node(Box.endBox, null));
							
						}
						
						pathfinder.start();
						escapeState = 0;
					
					}
					
				}
				
			} catch (NullPointerException e2) {
				
				if (Box.startBox != null && Box.endBox != null) {
				
					int[] flags = {Box.BOX_SEARCHED_FLAG, Box.BOX_SHORTEST_PATH_FLAG, Box.BOX_QUEUED_FLAG};
					clearBoxFieldFlags(flags);
					
					if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.ASTAR) {
						
						pathfinder = new AStarPathfind(new Node(Box.startBox, null), new Node(Box.endBox, null));
						
					} else if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.DIJKSTRA) {
					
						pathfinder = new DijkstrasPathfind(new Node(Box.startBox, null), new Node(Box.endBox, null));
						
					} else if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.CUSTOM) {
						
						pathfinder = new CustomPathfind(new Node(Box.startBox, null), new Node(Box.endBox, null));
						
					}
					
					pathfinder.start();
					escapeState = 0;
				
				}
				
			}
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			if (pathfinder != null) {
				
				pathfinder.end();
				
			}
			
			if (escapeState == 0) {
				
				int[] flags = {Box.BOX_SEARCHED_FLAG, Box.BOX_SHORTEST_PATH_FLAG, Box.BOX_QUEUED_FLAG};
				clearBoxFieldFlags(flags);
				escapeState = 1;
				
			} else if (escapeState == 1) {
				
				int[] flags = {Box.BOX_SEARCHED_FLAG, Box.BOX_SHORTEST_PATH_FLAG, Box.BOX_START_FLAG, Box.BOX_END_FLAG, Box.BOX_QUEUED_FLAG};
				clearBoxFieldFlags(flags);
				escapeState = 2;
			
			} else {
				
				int[] flags = {Box.BOX_SEARCHED_FLAG, Box.BOX_BARRIER_FLAG, Box.BOX_SHORTEST_PATH_FLAG, Box.BOX_START_FLAG, Box.BOX_END_FLAG, Box.BOX_QUEUED_FLAG};
				clearBoxFieldFlags(flags);
				
			}
			
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_ALT) {
			
			drawDebugInformation = !drawDebugInformation; // Must show the currently selected block and highlight it
			repaint();
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			
			shiftDown = false;
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			spaceDown = false;
		
		}
		
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			
			drawFunInformation = !drawFunInformation;
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			
			setDrawSize(clampInt(50, drawSize + 5, 1));
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			if (drawSize > 1) {
				
				setDrawSize(clampInt(50, drawSize - 5, 1));
				
			}
			
		}
		
	}
	
	public void componentResized(ComponentEvent e) {
		
		WINDOW_WIDTH = e.getComponent().getWidth();
		WINDOW_HEIGHT = e.getComponent().getHeight();
		
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}//repaintAll();}
	public void componentShown(ComponentEvent e) {}//repaintAll();}	

	public void mouseWheelMoved(MouseWheelEvent e) {

		setDrawSize(clampInt(50, drawSize - e.getWheelRotation(), 1));
		
	}
	
}