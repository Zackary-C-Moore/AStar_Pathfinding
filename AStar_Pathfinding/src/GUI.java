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
	private ButtonGroup radioButtonGroup;
	private JRadioButton wallRadioButton;
	private JRadioButton endRadioButton;
	private JRadioButton startRadioButton;
	GUI()
	{
		setupFrame();
		setupRadioButtons();
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
	
	public void setupRadioButtons()
	{
		//create radio buttons
		wallRadioButton = new JRadioButton();
		endRadioButton = new JRadioButton();
		startRadioButton = new JRadioButton();
		
		//create the group for my radio buttons
		radioButtonGroup = new ButtonGroup();
		
		//set position and size of radio buttons
		wallRadioButton.setBounds(10,1000,120,50);
		endRadioButton.setBounds(130,1000,120,50);
		startRadioButton.setBounds(250,1000,120,50);
		
		//set the text for my radio buttons
		wallRadioButton.setText("Wall");
		endRadioButton.setText("End");
		startRadioButton.setText("Start");
		
		//add action listener to my radio buttons
		wallRadioButton.addActionListener(new RadioButtonActionListener());
		endRadioButton.addActionListener(new RadioButtonActionListener());
		startRadioButton.addActionListener(new RadioButtonActionListener());
		
		//select wall radio button by default
		wallRadioButton.setSelected(true);
		
		//add radio buttons to the frame
		frame.add(wallRadioButton);
		frame.add(endRadioButton);
		frame.add(startRadioButton);
		
		//Put all my radio buttons in a group so only one can be selected at a time.
		radioButtonGroup.add(wallRadioButton);
		radioButtonGroup.add(endRadioButton);
		radioButtonGroup.add(startRadioButton);
	}
	//function called by AStar to create new NodeButtons and
	//1. add them to my form - done below
	//2. add them to my 2d array - done in AStar
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
		
		
		//Set the position and size of the button
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
