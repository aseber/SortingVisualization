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
import javax.swing.JTextField;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;


public class WindowDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2295277117437760048L;
	JTextField boxCountField;
	JTextField boxSizeField;

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
		JLabel boxCountLabel = new JLabel();
		JLabel boxSizeLabel = new JLabel();
		boxCountField = new JTextField();
		boxSizeField = new JTextField();
		JButton CancelButton = new JButton();
		JButton AcceptButton = new JButton();
			
		setTitle("Window Size Selector");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		AlgorithmDialogGUI.setLayout(new BorderLayout());
		
		contentPanel.setLayout(new FormLayout("3px, 150px, 5px, 100px, 5px", "5px, 25px, 5px, 25px, 5px, 50px"));
		
		boxCountLabel.setText("Window X Size");
		contentPanel.add(boxCountLabel, CC.xywh(2, 2, 1, 1));
		
		boxCountField.setText(Integer.toString(VisualizationBase.ROW_COUNT));
		contentPanel.add(boxCountField, CC.xywh(4, 2, 1, 1));
		
		boxSizeLabel.setText("Box Row/Column Size");
		contentPanel.add(boxSizeLabel, CC.xywh(2, 4, 1, 1));
		
		boxSizeField.setText(Integer.toString((int) (VisualizationBase.size.getWidth())));
		contentPanel.add(boxSizeField, CC.xywh(4, 4, 1, 1));
		
		CancelButton.setText("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				CancelButtonMouseCLicked();
				
			}
			
		});
		contentPanel.add(CancelButton, CC.xywh(2, 6, 1, 1));
		
		AcceptButton.setText("Accept");
		AcceptButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				AcceptButtonMouseCLicked();
				
			}
			
		});
		contentPanel.add(AcceptButton, CC.xywh(4, 6, 1, 1));
		
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
		
		if (VisualizationBase.VISUALIZATION_WINDOW.pathfinder != null) {
			
			if (VisualizationBase.VISUALIZATION_WINDOW.pathfinder.isRunning()) {
				
				VisualizationBase.VISUALIZATION_WINDOW.pathfinder.end();
				
			}
			
		}
		
		VisualizationBase.ROW_COUNT = Integer.parseInt(boxCountField.getText());
		VisualizationBase.COLUMN_COUNT = Integer.parseInt(boxCountField.getText());
		int sizeInt = Integer.parseInt(boxSizeField.getText());
		VisualizationBase.size.setSize(sizeInt, sizeInt);
		Dimension windowSize = new Dimension((int) (VisualizationBase.ROW_COUNT*VisualizationBase.size.getWidth()), (int) (VisualizationBase.COLUMN_COUNT*VisualizationBase.size.getHeight()));
		VisualizationBase.VISUALIZATION_WINDOW.setWindowSize(windowSize);
		Box.clearBoxes();
		VisualizationBase.VISUALIZATION_WINDOW.createBoxField();
		VisualizationBase.VISUALIZATION_WINDOW.invalidate();
		VisualizationBase.VISUALIZATION_WINDOW.repaint();
		
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
