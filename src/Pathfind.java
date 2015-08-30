import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public abstract class Pathfind extends Thread {

	protected Node startNode;
	protected Node endNode;
	protected double expandedCounter;
	protected final double EXPANDED_COUNTER_HARD_CAP = 100000;
	protected boolean running = true;
	protected boolean pause = false;
	protected boolean pathFound = false;
	protected BestPathComparator comparator = new BestPathComparator();
	protected PriorityQueue<Node> open = new PriorityQueue<Node>(10, comparator);
	protected PriorityQueue<Node> closed = new PriorityQueue<Node>(10, comparator);
	protected Node endOfPath = null;
	
	public Pathfind(Node startNode, Node endNode, double f) {
	
		this.startNode = startNode;
		this.endNode = endNode;
		addNodeToOpen(startNode, f);
		
	}
	
	//static abstract class BestPathComparator implements Comparator<Node> {};
	
	static class BestPathComparator implements Comparator<Node> {
		
		public int compare(Node node, Node node2) {
			
			return Double.compare(node.getF(), node2.getF());
			
		}
		
	}
	
	@Override
	public final void run() {
		
		VisualizationBase.VISUALIZATION_GUI.setRunButtonState(pause);
		searchForPath();
		
	}
	
	public abstract void searchForPath();
	
	public final void togglePause() {
		
		synchronized(this) {
			
			if (pause) {
			
				pause = false;
				VisualizationBase.VISUALIZATION_GUI.setRunButtonState(pause);
				this.notify();
				
			} else {
				
				pause = true;
				VisualizationBase.VISUALIZATION_GUI.setRunButtonState(pause);
				
			}
			
		}
			
	}
	
	protected final boolean isRunning() {
		
		return running;
		
	}
	
	public final void end() {
		
		running = false;
		
	}
	
	protected final void addNodeToOpen(Node node, double f) {
		
		node.setF(f);
		open.add(node);
		node.box.setFlag(Box.BOX_QUEUED_FLAG);
		
	}
	
	protected final void addNodeToClosed(Node node) {
		
		closed.add(node);
		
		double f = node.box.euclideanDistance(endNode.box)*0.85;
		double fStandard = startNode.box.euclideanDistance(endNode.box);
		int value = (int) Math.round(Math.max(0, Math.min(200, 200*(f/fStandard))));
		
		node.box.setColor(new Color(value, 200 - value, 0));
		
		node.box.setFlag(Box.BOX_SEARCHED_FLAG);
		
	}
	
	protected final void returnPath(Node endNode) {
		
		Node currentNode = endOfPath;
		
		do {
			
			currentNode.box.setFlag(Box.BOX_SHORTEST_PATH_FLAG);
			currentNode = currentNode.parentNode;
			
		}
		
		while (!currentNode.box.equals(startNode.box));
		
	}
	
	protected final boolean isPathFound() {
		
		return pathFound;
		
	}
	
	public final Node path() {
		
		if (pathFound) {
			
			return endNode;
			
		}
		
		return null;
		
	}
	
	public final Box[] boxesAlongPath() {
		
		ArrayList<Box> boxesList = new ArrayList<Box>();
		
		if (isPathFound()) {
		
			Node currentNode = endOfPath;
			
			do {
				
				boxesList.add(currentNode.box);
				currentNode = currentNode.parentNode;
				
			}
			
			while (!currentNode.box.equals(startNode.box));
		
		}
		
		Box[] boxes = new Box[boxesList.size()];
		boxesList.toArray(boxes);
		return boxes;
		
	}
	
	protected final double getExpandedCounter() {
		
		return expandedCounter;
		
	}
	
	protected final boolean isExpandedCounterExceeded() {
		
		return getExpandedCounter() > EXPANDED_COUNTER_HARD_CAP;
		
	}
	
}
