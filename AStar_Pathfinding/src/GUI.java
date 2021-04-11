import java.awt.Color;

import javax.swing.*;

public class GUI 
{
	private int rows = 10;
	private int cols = 19;
	private int xFrameSize = 1910;
	private int yFrameSize = 1080;
	private JFrame frame;
	private JPanel buttonPanel;
	private int xPos = 0;
	private int yPos = 0;
	private int colCount = 0;
	GUI()
	{
		setupFrame();
	}
	public void setupFrame()
	{
		frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(xFrameSize,yFrameSize);
		

		
		//Create a new panel to be placed on the frame
		buttonPanel = new JPanel();
		//needed so I can place the buttons where I want.
		buttonPanel.setLayout(null);
	}
	
	public NodeButton addButtons()
	{
		//Create a new button
		NodeButton nodeBtn = new NodeButton();
		
		//If the colCount == cols then I need to make a new row.
		if(colCount == cols)
		{
			colCount = 0;
			xPos = 0;
			//add to yPos the height of the button so they are evenly spaced.
			yPos += nodeBtn.getButtonSize().height;
		}
		
		
		//Set the position of the button
		nodeBtn.getButton().setBounds(xPos,yPos,nodeBtn.getButtonSize().width, nodeBtn.getButtonSize().height);
		//Get the button from NodeButton and place it on my panel
		buttonPanel.add(nodeBtn.getButton());
		//add to xPos the width of the button so they are evenly spaced.
		xPos += nodeBtn.getButtonSize().width;
		
		colCount++;
		
		//Return the button to AStar to add to array.
		return nodeBtn;
	}
	
	public void showFrame()
	{
		//Place the panel on the frame.
		frame.add(buttonPanel);
		//frame.setResizable(false); 
		frame.setVisible(true);
	}
}
