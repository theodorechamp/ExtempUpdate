import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;


@SuppressWarnings("serial")
public class SelectDialog extends JFrame implements ActionListener{
	JCheckBox AlJazeera, BBC, NPR;
	JTextField fieldLocation;
	JButton enter, exit;
	boolean proceed = false;
	TransferInformation ti;
	public SelectDialog()
	{
		StringBuilder defaults = null;
		try {
			defaults = new StringBuilder(loadValues());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
				
		this.setSize(552, 130);
		this.setTitle("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JLabel select = new JLabel("Select Sources");
		
		AlJazeera = new JCheckBox("Al Jazeera");
		//if(defaults.charAt(0) == 't'){
		//	AlJazeera.setSelected(true);
		//}
		BBC = new JCheckBox("BBC");
		//if(defaults.charAt(1) == 't'){
		//	BBC.setSelected(true);
		//}
		NPR = new JCheckBox("NPR");
		//if(defaults.charAt(2) == 't'){
		//	NPR.setSelected(true);
		//}
		
		JLabel labelLocation = new JLabel("Folder:");
		//try {
		//	fieldLocation = new JTextField(loadLocation(), 30);
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		enter = new JButton("Enter");
		enter.addActionListener(this);
		exit = new JButton("Exit");
		exit.addActionListener(this);
		
		JPanel panelNorth = new JPanel();
		panelNorth.add(select);
		
		JPanel panelCenter = new JPanel();
		panelCenter.add(AlJazeera);
		panelCenter.add(BBC);
		panelCenter.add(NPR);
		
		JPanel panelSouth = new JPanel();
		panelSouth.add(labelLocation);
		panelSouth.add(fieldLocation);
		panelSouth.add(enter);
		panelSouth.add(exit);
		
		this.add(panelNorth, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == enter){
			ti = new TransferInformation();
			ti.setAlJazeera(AlJazeera.isSelected());
			ti.setBBC(BBC.isSelected());
			ti.setNPR(NPR.isSelected());
			ti.setLocation(fieldLocation.getText());
			saveFileLocation();
			saveValues();
			proceed = true;
		}
		else if (e.getSource() == exit){
			System.exit(0);
		}
	}
	
	public boolean getProceed(){
		return proceed;
	}
	
	public TransferInformation getti(){
		return ti;
	}
	
	public void saveFileLocation(){
		File f = new File("savedLocation.txt");
		PrintWriter out = null;
		
		try{out = new PrintWriter(f);}
		catch(Exception e){System.out.println(e);}
		
		out.write(fieldLocation.getText());
		out.close();
	}
	
	public String loadLocation() throws IOException{
		BufferedReader in = null;
		try{
			File f  = new File("savedLocation.txt");
			in = new BufferedReader(new FileReader(f));
		}
		catch(Exception e){
			System.out.println(e);
		}
		String str = in.readLine();
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public void saveValues()
	{
		String str = "";
		if(AlJazeera.isSelected()){
			str += "t";
		}
		else{
			str += "f";
		}
		
		if(BBC.isSelected()){
			str += "t";
		}
		else{
			str += "f";
		}
		
		if(NPR.isSelected()){
			str += "t";
		}
		else{
			str += "f";
		}
		
		File f = new File("savedValues.txt");
		PrintWriter out = null;
		
		try{out = new PrintWriter(f);}
		catch(Exception e){System.out.println(e);}
		
		out.write(str);
		out.close();
	}
	
	public String loadValues() throws IOException{
		BufferedReader in = null;
		File f  = new File("savedValues.txt");
		if(!(f.exists())){
			return "fff";
		}
		try{
			in = new BufferedReader(new FileReader(f));
		}
		catch(Exception e){
			System.out.println(e);
		}
		String str = in.readLine();
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
