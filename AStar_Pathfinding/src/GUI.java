import javax.swing.*;

public class GUI 
{
	
	GUI()
	{
		setupFrame();
	}
	public static void setupFrame()
	{
		JFrame frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1910,1080);
		
		
		//Create a new panel to be placed on the frame
		JPanel buttonPanel = new JPanel();
		//needed so I can place the buttons where I want.
		buttonPanel.setLayout(null);
		
		addButtons(frame,buttonPanel);
		
		//frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public static void addButtons(JFrame frame, JPanel buttonPanel)
	{
		NodeButton nodeBtn;
		
		int xPos = 0;
		int yPos = 0;
		int colCount = 0;
		for(int i = 0; i < 190; i++)
		{
			//Create a new node button to be placed on the panel
			nodeBtn = new NodeButton();
			
			if(colCount == 19)
			{
				colCount = 0;
				xPos = 0;
				yPos += nodeBtn.getButtonSize().height;
			}
			
			
			nodeBtn.getButton().setBounds(xPos,yPos,nodeBtn.getButtonSize().width, nodeBtn.getButtonSize().height);
			//Get the button from NodeButton and place it on my panel
			buttonPanel.add(nodeBtn.getButton());
			
			//Place the panel on the frame.
			frame.add(buttonPanel);
			
			xPos += nodeBtn.getButtonSize().width;
			
			colCount++;
		}
	}
}
