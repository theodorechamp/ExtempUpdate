import java.awt.BorderLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class FinishDialog extends JFrame{
	JButton buttonExit;
	public FinishDialog(){
		this.setSize(400, 100);
		this.setTitle("Done");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JLabel labelFinished = new JLabel("Your files have been updated successfully");
		
		JPanel panelText = new JPanel();
		panelText.add(labelFinished);
		
		
		this.add(panelText, BorderLayout.NORTH);
		this.setVisible(true);
	}
}
