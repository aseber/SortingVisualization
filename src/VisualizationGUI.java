import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class VisualizationGUI extends JFrame {

	protected VisualizationWindow mainWindow;
	private JLabel openCounter = new JLabel("| Nodes Open: 0 ");
	private JLabel closedCounter = new JLabel("| Nodes Closed: 0 ");
	private JLabel pathLengthCounter = new JLabel("| Path Length: 0 ");
	private JButton runButton = new JButton("Start Simulation");
	
	private static final long serialVersionUID = -6664286942946303464L;
	
	VisualizationGUI() {
		
		initializeGUI();
		VisualizationBase.VISUALIZATION_WINDOW.createBoxField();
		
	}
	
	public void setOpenCounter(int var) {
		
		openCounter.setText("| Nodes Open: " + var + " ");
		
	}
	
	public void resetOpenCounter() {
		
		setOpenCounter(0);
		
	}
	
	public void setClosedCounter(int var) {
		
		closedCounter.setText("| Nodes Closed: " + var + " ");
		
	}
	
	public void resetClosedCounter() {
		
		setClosedCounter(0);
		
	}
	
	public void setPathLengthCounter(int var) {
		
		pathLengthCounter.setText("| Path Length: " + var + " ");
		
	}
	
	public void resetPathLengthCounter() {
		
		setPathLengthCounter(0);
		
	}
	
	public void setRunButtonState(boolean paused) {
		
		if (paused) {
			
			runButton.setText("Start Simulation");
			
		}
		
		else {
			
			runButton.setText("Pause Simulation");
			
		}
		
	}
	
	private void initializeGUI() {
		
		VisualizationWindow mainWindow = new VisualizationWindow();
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenuGroup = new JMenu("File");
		JMenu fileResetBoxes = new JMenu("Reset Boxes");
		JMenuItem fileResetAllBoxes = new JMenuItem("Reset All Boxes");
		JMenuItem fileResetSearchedBoxes = new JMenuItem("Reset Searched Boxes");
		JMenuItem fileResetBarrierBoxes = new JMenuItem("Reset Barrier Boxes");
		JMenuItem fileResetShortBoxes = new JMenuItem("Reset Shortest Path Boxes");
		JMenuItem fileResetStartBox = new JMenuItem("Reset Start Box");
		JMenuItem fileResetEndBox = new JMenuItem("Reset End Box");
		JMenuItem fileResetQueuedBoxes = new JMenuItem("Reset Queued Boxes");
		JMenuItem fileResetSomeBoxes = new JMenuItem("Reset Boxes...");
		JMenuItem fileExit = new JMenuItem("Exit program");
		JMenu settingsMenuGroup = new JMenu("Settings");
		JMenuItem settingsChangeAlgorithm = new JMenuItem("Modify Algorithm");
		JMenuItem settingsChangeWindow = new JMenuItem("Modify Window");
		JMenuItem settingsChangeSleepTimer = new JMenuItem("Change Sleep Timer");
		
		VisualizationBase.VISUALIZATION_WINDOW = mainWindow;
		
		this.mainWindow = mainWindow;
		
		//FormLayout layout = new FormLayout();
		
		setLayout(new FormLayout("5px, 1px:grow, 5px", "25px, 1px:grow, 5px, 20px, 5px"));
		
		//PanelBuilder builder = new PanelBuilder(layout);
		
		add(menuBar, CC.xywh(1, 1, 3, 1, CC.FILL, CC.FILL));
			menuBar.add(fileMenuGroup);
				fileMenuGroup.add(fileResetBoxes);
					fileResetBoxes.add(fileResetAllBoxes);
					fileResetBoxes.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
						
							fileResetAllBoxes();
							
						}
						
					});
					fileResetBoxes.add(fileResetSearchedBoxes);
					fileResetSearchedBoxes.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
						
							fileResetSearchedBoxes();
							
						}
						
					});
					fileResetBoxes.add(fileResetBarrierBoxes);
					fileResetBarrierBoxes.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
						
							fileResetBarrierBoxes();
							
						}
						
					});
					fileResetBoxes.add(fileResetShortBoxes);
					fileResetShortBoxes.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
						
							fileResetShortBoxes();
							
						}
						
					});
					fileResetBoxes.add(fileResetStartBox);
					fileResetStartBox.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
						
							fileResetStartBox();
							
						}
						
					});
					fileResetBoxes.add(fileResetEndBox);
					fileResetEndBox.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
						
							fileResetEndBox();
							
						}
						
					});
					fileResetBoxes.add(fileResetQueuedBoxes);
					fileResetQueuedBoxes.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
						
							fileResetQueuedBoxes();
							
						}
						
					});
					fileResetBoxes.add(fileResetSomeBoxes);
					fileResetSomeBoxes.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg0) {
						
							fileResetSomeBoxes();
							
						}
						
					});
				
				fileMenuGroup.add(fileExit);
				fileExit.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
					
						fileExit();
						
					}
					
				});
			menuBar.add(settingsMenuGroup);
				settingsMenuGroup.add(settingsChangeAlgorithm);
				settingsChangeAlgorithm.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
					
						settingsChangeAlgorithm();
						
					}
					
				});
				settingsMenuGroup.add(settingsChangeWindow);
				settingsChangeWindow.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
					
						settingsChangeWindow();
						
					}
					
				});
				settingsMenuGroup.add(settingsChangeSleepTimer);
				settingsChangeSleepTimer.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
					
						settingsChangeSleepTimer();
						
					}
					
				});
				
			menuBar.add(openCounter);
			menuBar.add(closedCounter);
			menuBar.add(pathLengthCounter);
				
		add(mainWindow, CC.xywh(1, 2, 3, 1, CC.FILL, CC.FILL));
			
		add(runButton, CC.xywh(2, 4, 1, 1, CC.FILL, CC.FILL));
		
		pack();
		
		int sizeX = (int) (VisualizationBase.ROW_COUNT*VisualizationBase.size.getWidth() + 17);
		int sizeY = (int) (VisualizationBase.COLUMN_COUNT*VisualizationBase.size.getHeight() + 95);
		setSize(sizeX, sizeY);
		
	}
	
	private void fileResetAllBoxes() {
		
		VisualizationBase.VISUALIZATION_WINDOW.resetBoxField();
		
	}
	
	private void fileResetSearchedBoxes() {
		
		VisualizationBase.VISUALIZATION_WINDOW.clearBoxFieldFlag(Box.BOX_SEARCHED_FLAG);
		
	}
	
	private void fileResetBarrierBoxes() {
			
		VisualizationBase.VISUALIZATION_WINDOW.clearBoxFieldFlag(Box.BOX_BARRIER_FLAG);
			
	}
	
	private void fileResetShortBoxes() {
		
		VisualizationBase.VISUALIZATION_WINDOW.clearBoxFieldFlag(Box.BOX_SHORTEST_PATH_FLAG);
		
	}
	
	private void fileResetStartBox() {
		
		VisualizationBase.VISUALIZATION_WINDOW.clearBoxFieldFlag(Box.BOX_START_FLAG);
		
	}
	
	private void fileResetEndBox() {
		
		VisualizationBase.VISUALIZATION_WINDOW.clearBoxFieldFlag(Box.BOX_END_FLAG);
		
	}
	
	private void fileResetQueuedBoxes() {
		
		VisualizationBase.VISUALIZATION_WINDOW.clearBoxFieldFlag(Box.BOX_QUEUED_FLAG);
		
	}
	
	private void fileResetSomeBoxes() {
		
		ClearBoxDialog BoxGUI = new ClearBoxDialog(this);
		BoxGUI.setModalityType(ModalityType.APPLICATION_MODAL);
		BoxGUI.setVisible(true);
		
	}
	
	private void fileExit() {
		
		System.exit(0);
		
	}
	
	private void settingsChangeAlgorithm() {
		
		AlgorithmDialog AlgorithmGUI = new AlgorithmDialog(this);
		AlgorithmGUI.setModalityType(ModalityType.APPLICATION_MODAL);
		AlgorithmGUI.setVisible(true);
		
	}
	
	private void settingsChangeWindow() {
		
		WindowDialog WindowGUI = new WindowDialog(this);
		WindowGUI.setModalityType(ModalityType.APPLICATION_MODAL);
		WindowGUI.setVisible(true);
		
	}
	
	private void settingsChangeSleepTimer() {
		
		SleepTimeDialog SleepTimeGUI = new SleepTimeDialog(this);
		SleepTimeGUI.setModalityType(ModalityType.APPLICATION_MODAL);
		SleepTimeGUI.setVisible(true);
		
	}
	
}
