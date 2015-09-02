import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;


public class WindowDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2295277117437760048L;
	JTextField windowXSizeField;
	JTextField windowYSizeField;
	JRadioButton drawSets;
	JRadioButton drawCompares;
	JRadioButton drawGets;
	JRadioButton drawScreenUpdatesWhileSorting;

	public WindowDialog(Frame owner) {
		
		super(owner);
		initComponents();
		
	}
	
	public WindowDialog(Dialog owner) {
		
		super(owner);
		initComponents();
		
	}
	
	private void initComponents() {
		
		JPanel AlgorithmDialogGUI = new JPanel();
		JPanel contentPanel = new JPanel();
		windowXSizeField = new JTextField();
		windowYSizeField = new JTextField();
		drawSets = new JRadioButton();
		drawCompares = new JRadioButton();
		drawGets = new JRadioButton();
		drawScreenUpdatesWhileSorting = new JRadioButton();
		JButton CancelButton = new JButton();
		JButton AcceptButton = new JButton();
			
		setTitle("Window Settings");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		AlgorithmDialogGUI.setLayout(new BorderLayout());
		
		contentPanel.setLayout(new FormLayout("3px, 150px, 5px, 150px, 5px", "5px, 25px, 5px, 25px, 5px, 25px, 5px, 25px, 5px, 25px"));
		
		windowXSizeField.setText(Integer.toString(VisualizationBase.WINDOW_SIZE.width));
		contentPanel.add(windowXSizeField, CC.xywh(2, 2, 1, 1));
		
		windowYSizeField.setText(Integer.toString(VisualizationBase.WINDOW_SIZE.height));
		contentPanel.add(windowYSizeField, CC.xywh(4, 2, 1, 1));
		
		drawSets.setText("Draw set updates");
		drawSets.setSelected(false);
		contentPanel.add(drawSets, CC.xywh(2, 4, 2, 1));
		
		drawCompares.setText("Draw compare updates");
		drawCompares.setSelected(false);
		contentPanel.add(drawCompares, CC.xywh(2, 4, 2, 1));
		
		drawGets.setText("Draw get updates");
		drawGets.setSelected(false);
		contentPanel.add(drawGets, CC.xywh(2, 6, 2, 1));
		
		drawScreenUpdatesWhileSorting.setText("Draw screen updates while sorting");
		drawScreenUpdatesWhileSorting.setSelected(false);
		contentPanel.add(drawScreenUpdatesWhileSorting, CC.xywh(2, 8, 2, 1));
		
		CancelButton.setText("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				CancelButtonMouseCLicked();
				
			}
			
		});
		contentPanel.add(CancelButton, CC.xywh(2, 10, 1, 1));
		
		AcceptButton.setText("Accept");
		AcceptButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				AcceptButtonMouseCLicked();
				
			}
			
		});
		contentPanel.add(AcceptButton, CC.xywh(4, 10, 1, 1));
		
		AlgorithmDialogGUI.add(contentPanel, BorderLayout.NORTH);
		contentPane.add(AlgorithmDialogGUI, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	private void CancelButtonMouseCLicked() {
		
		this.dispose();
		
	}
	
	private void AcceptButtonMouseCLicked() {
		
		VisualizationBase.WINDOW_SIZE.setSize(Integer.parseInt(windowXSizeField.getText()), Integer.parseInt(windowYSizeField.getText()));
		VisualizationBase.VISUALIZATION_WINDOW.setSize(VisualizationBase.WINDOW_SIZE);
		
		
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
