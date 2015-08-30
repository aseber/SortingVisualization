public class DijkstrasPathfind extends Pathfind {
	
	public DijkstrasPathfind(Node startNode, Node endNode) {
	
		super(startNode, endNode, 0);
		startNode.setG(0);
		
	}
	
	public void searchForPath() {
		
		Node currentNode;
		
		do {
			
			synchronized (this) {
			
				try {
					
					while (pause) {
						
						this.wait();
						
					}
					
					if (VisualizationBase.sleepTimer > 0) {
					
						sleep(VisualizationBase.sleepTimer);
						
					}
					
				} catch (InterruptedException e) {}
				
			}
			
			currentNode = open.poll();
			
			if (currentNode.box.equals(endNode.box)) {
				
				break;
				
			}
			
			addNodeToClosed(currentNode);
			VisualizationBase.VISUALIZATION_GUI.setOpenCounter(open.size());
			VisualizationBase.VISUALIZATION_GUI.setClosedCounter(closed.size());
			Node[] neighboringNodes = currentNode.findNeighboringNodes(1);
			expandedCounter++;
			
			for (Node neighbor : neighboringNodes) {
				
				if (neighbor.box.getFlag() != Box.BOX_SEARCHED_FLAG && neighbor.box.getFlag() != Box.BOX_BARRIER_FLAG) {
					
					double tentitive_g = currentNode.getG() + neighbor.box.euclideanDistance(currentNode.box);
					
					if (neighbor.box.getFlag() != Box.BOX_QUEUED_FLAG | tentitive_g < neighbor.getG()) {
						
						neighbor.setParent(currentNode);
						neighbor.setG(tentitive_g);
						
						if (neighbor.box.getFlag() != Box.BOX_QUEUED_FLAG) {
							
							addNodeToOpen(neighbor, neighbor.box.euclideanDistance(startNode.box));
							
						}
						
					}
					
				}
				
			}
			
		}
		
		while (!open.isEmpty() && !isExpandedCounterExceeded() && running);
		
		if (currentNode.box.equals(endNode.box)) {
			
			pathFound = true;
			endOfPath = currentNode;
			System.out.println("Path found! Retracing our steps and highlighting the path.");
			Box[] boxes = boxesAlongPath();
			Box.setFlags(boxes, Box.BOX_SHORTEST_PATH_FLAG);
			VisualizationBase.VISUALIZATION_GUI.setPathLengthCounter(boxes.length);
			
			
		} else if (isExpandedCounterExceeded()) {
			
			System.out.println("Path could not be produced as counter expanded past the hard cap.");
			VisualizationBase.VISUALIZATION_GUI.setRunButtonState(true);
			
		} else {
			
			System.out.println("Path could not be produced as there are no more nodes to be searched.");
			VisualizationBase.VISUALIZATION_GUI.setRunButtonState(true);
			
		}
		
		running = false;
		
	}

}
