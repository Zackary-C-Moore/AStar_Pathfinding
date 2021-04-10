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
		NodeButton nodeBtn = new NodeButton();
		
		if(colCount == cols)
		{
			colCount = 0;
			xPos = 0;
			yPos += nodeBtn.getButtonSize().height;
		}
		
		
		
		nodeBtn.getButton().setBounds(xPos,yPos,nodeBtn.getButtonSize().width, nodeBtn.getButtonSize().height);
		//Get the button from NodeButton and place it on my panel
		buttonPanel.add(nodeBtn.getButton());
		
		//nodeBtn.setButtonDisplay("10", "10", "10");
		
		xPos += nodeBtn.getButtonSize().width;
		
		colCount++;

		
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