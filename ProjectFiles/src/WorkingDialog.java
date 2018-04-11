import java.awt.BorderLayout;
import static javax.swing.ScrollPaneConstants.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class WorkingDialog extends JFrame{
	JProgressBar progressBar;
	JLabel labelWorking;
	String text;
	String articleList;
	//JLabel labelTitle;
	String title;
	JTextArea textArea;
	public WorkingDialog(int arg0){
		this.setSize(384, 262);
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		text = "Working...";
		labelWorking = new JLabel(text);

		textArea = new JTextArea(10, 30);
		articleList = "Articles:";
		textArea.setText(articleList);
		textArea.setEditable(false);
		JScrollPane sp = new JScrollPane(textArea);
		sp.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		progressBar = new JProgressBar(0, arg0);

		JPanel panelProgress = new JPanel();
		panelProgress.add(progressBar);
		
		JPanel panelTextArea = new JPanel();
		panelTextArea.add(sp);
		
		JPanel panelWorking = new JPanel();
		panelWorking.add(labelWorking);
		
		this.add(panelWorking, BorderLayout.NORTH);
		this.add(panelProgress, BorderLayout.CENTER);
		this.add(panelTextArea, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void updateText(String str){
		text = "Working... " + str;
		System.out.println(text);
		labelWorking.setText(text);
	}
	public void updateTitle(String str){
		articleList += "\n" + str;
		textArea.setText(articleList);
	}
	
	public void updateProgressBar(int newNumber)
	{
		progressBar.setValue(newNumber);
	}
	
}
