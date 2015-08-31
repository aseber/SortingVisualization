import java.awt.Color;
import java.util.HashSet;


public class SortExecutor implements Runnable { // Simple class that allows me to move the processing to a new thread so the UI doesn't lag.
													// Also lets me test the algorithms speed
	
	Sort sort;
	
	public void run() {

		long startTime = System.currentTimeMillis();
		
		if (Box.beginningAndEndExist()) {
			
			int[] flags = {Box.BOX_SEARCHED_FLAG, Box.BOX_SHORTEST_PATH_FLAG, Box.BOX_QUEUED_FLAG};
			VisualizationBase.VISUALIZATION_WINDOW.clearBoxFieldFlags(flags);
			PathfindRegion regionPathfind = null;
			HashSet<Box> boxesAlongRegionPath = null;
			
			if (VisualizationBase.hierarchicalPathfinding) {
				
				regionPathfind = new PathfindRegion(new RegionNode(Box.startBox.getRegion(), null), new RegionNode(Box.endBox.getRegion(), null));
				regionPathfind.start();
				HashSet<Region> regionsOnPath = regionPathfind.regionsAlongPath();
				HashSet<Region> expandedRegionsOnPath = new HashSet<Region>();
				
				for (Region currentRegion : regionsOnPath) {
					
					expandedRegionsOnPath.addAll(currentRegion.getNeighboringRegions());
					expandedRegionsOnPath.add(currentRegion);
					
				}
				
				boxesAlongRegionPath = new HashSet<Box>();
				
				for (Region currentRegion : expandedRegionsOnPath) {
				
					VisualizationBase.VISUALIZATION_WINDOW.registerChange(currentRegion, 2000, new Color(0, 0, 255, 125));
					boxesAlongRegionPath.addAll(currentRegion.getBoxes());
					
				}
				
			}
				
			if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.ASTAR) {
				
				pathfinder = new AStarPathfind(new BoxNode(Box.startBox, null), new BoxNode(Box.endBox, null));
				
			} else if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.DIJKSTRA) {
			
				pathfinder = new DijkstrasPathfind(new BoxNode(Box.startBox, null), new BoxNode(Box.endBox, null));
				
			} else if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.CUSTOM) {
				
				pathfinder = new CustomPathfind(new BoxNode(Box.startBox, null), new BoxNode(Box.endBox, null));
				
			}
			
			if (VisualizationBase.hierarchicalPathfinding) {
				
				pathfinder.setAvailableRegion(boxesAlongRegionPath);
				
				if (regionPathfind.isPathFound()) {
					
					pathfinder.start();
					pathfinder.waitForFinish();
					long endTime = System.currentTimeMillis();
					VisualizationBase.VISUALIZATION_GUI.setRunTimeCounter(endTime - startTime);
					
				}
				
				else {
					
					System.out.println("Pathfind not attempted as region pathfinding did not find a result.");
					
				}
				
				return;
				
			}
			
			pathfinder.start();
			pathfinder.waitForFinish();
			long endTime = System.currentTimeMillis();
			VisualizationBase.VISUALIZATION_GUI.setRunTimeCounter(endTime - startTime);
			
		}
		
	}

}
