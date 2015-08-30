import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;


public class AlgorithmDialog extends JDialog implements KeyListener {

	Container contentPane;
	JPanel AlgorithmDialogGUI;
	JPanel customPanel;
	JRadioButton selectionButton;
	JRadioButton insertionButton;
	JRadioButton quickButton;
	JRadioButton mergeButton;
	JRadioButton heapButton;
	JRadioButton radixlsdButton;
	JRadioButton radixmsdButton;
	JRadioButton stdsortButton;
	JRadioButton stdstablesortButton;
	JRadioButton shellButton;
	JRadioButton bubbleButton;
	JRadioButton shakerButton;
	JRadioButton gnomeButton;
	JRadioButton bitonicButton;
	JRadioButton bogoButton;
	JRadioButton[] buttons = {selectionButton, insertionButton, quickButton, mergeButton, heapButton, radixlsdButton, radixmsdButton, stdsortButton, stdstablesortButton, shellButton, bubbleButton, shakerButton, gnomeButton, bitonicButton, bogoButton};
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7388554549130678395L;

	public AlgorithmDialog(Frame owner) {
		
		super(owner);
		initComponents();
		
	}
	
	public AlgorithmDialog(Dialog owner) {
		
		super(owner);
		initComponents();
		
	}
	
	private void initComponents() {
		
		AlgorithmDialogGUI = new JPanel();
		JPanel contentPanel = new JPanel();
		customPanel = new JPanel();
		JPanel contentPanel2 = new JPanel();
		selectionButton = new JRadioButton("Selection");
		insertionButton = new JRadioButton("Insertion");
		quickButton = new JRadioButton("Quick");
		mergeButton = new JRadioButton("Merge");
		heapButton = new JRadioButton("Heap");
		radixlsdButton = new JRadioButton("Radix LSD");
		radixmsdButton = new JRadioButton("Radix MSD");
		stdsortButton = new JRadioButton("std::sort");
		stdstablesortButton = new JRadioButton("std::stablesort");
		shellButton = new JRadioButton("Shell");
		bubbleButton = new JRadioButton("Bubble");
		shakerButton = new JRadioButton("Shaker");
		gnomeButton = new JRadioButton("Gnome");
		bitonicButton = new JRadioButton("Bitonic");
		bogoButton = new JRadioButton("Bogo");
		JButton CancelButton = new JButton();
		JButton AcceptButton = new JButton();
		
		ActionListener radioActionListener = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				JRadioButton source = (JRadioButton) e.getSource();
				
				for (JRadioButton button : buttons) {
					
					if (source != button) {
						
						button.setSelected(false);
						
					}
					
				}
				
			}
			
		};
		
		setTitle("Algorithm Selector");
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		AlgorithmDialogGUI.setLayout(new BorderLayout());
		
		contentPanel.setLayout(new FormLayout("3px, 100px, 5px, 100px, 5px, 100px, 5px", "5px, 25px, 5px, 25px, 5px, 25px, 5px, 25px, 5px, 25px"));
		
		selectionButton.addActionListener(radioActionListener);
		if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.ASTAR) {astarButton.setSelected(true);}
		contentPanel.add(astarButton, CC.xywh(2, 1, 1, 1));
		
		dijkstrasButton.addActionListener(radioActionListener);
		if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.DIJKSTRA) {dijkstrasButton.setSelected(true);}
		contentPanel.add(dijkstrasButton, CC.xywh(4, 1, 1, 1));
		
		customButton.addActionListener(radioActionListener);
		if (VisualizationBase.CURRENT_ALGORITHM == VisualizationBase.CUSTOM) {customButton.setSelected(true);}
		contentPanel.add(customButton, CC.xywh(6, 1, 1, 1));
		
		contentPanel2.setLayout(new FormLayout("3px, 50px, 5px, 100px, 5px, 100px, 5px, 50px", "5px, 50px"));
		
		CancelButton.setText("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				CancelButtonMouseCLicked();
				
			}
			
		});
		contentPanel2.add(CancelButton, CC.xywh(4, 2, 1, 1));
		
		AcceptButton.setText("Accept");
		AcceptButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				AcceptButtonMouseCLicked();
				
			}
			
		});
		contentPanel2.add(AcceptButton, CC.xywh(6, 2, 1, 1));
		
		AlgorithmDialogGUI.add(contentPanel, BorderLayout.NORTH);
		AlgorithmDialogGUI.add(customPanel, BorderLayout.CENTER);
		if (VisualizationBase.CURRENT_ALGORITHM != VisualizationBase.CUSTOM) {customPanel.setVisible(false);}
		AlgorithmDialogGUI.add(contentPanel2, BorderLayout.SOUTH);
		contentPane.add(AlgorithmDialogGUI, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(true);
		
	}

	private void CancelButtonMouseCLicked() {
		
		this.dispose();
		
	}
	
	private void AcceptButtonMouseCLicked() {
		
		if (astarButton.isSelected()) {
			
			VisualizationBase.CURRENT_ALGORITHM = VisualizationBase.ASTAR;
			
		} else if (dijkstrasButton.isSelected()) {
			
			VisualizationBase.CURRENT_ALGORITHM = VisualizationBase.DIJKSTRA;
			
		} else if (customButton.isSelected()) {
			
			VisualizationBase.CURRENT_ALGORITHM = VisualizationBase.CUSTOM;
			
			double g = GValueSlider.getValue();
			double h = HValueSlider.getValue();
			
			VisualizationBase.gModifier = g/1000;
			VisualizationBase.hModifier = h/1000;
			
		}
		
		this.dispose();
		
	}
	
	public void keyPressed(KeyEvent key) {
		
		if (key.getKeyCode() == KeyEvent.VK_ENTER) { 
			
			AcceptButtonMouseCLicked();
	
		}
		
		if (key.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			CancelButtonMouseCLicked();
			
		}
		
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}
	
}