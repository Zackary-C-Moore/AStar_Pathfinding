import javax.swing.*;

public class GUI 
{
	private int rows = AStar.getRows();
	private int cols = AStar.getCols();
	private int xFrameSize = 1890;
	private int yFrameSize = 1035;
	private JFrame frame;
	private JPanel buttonPanel;
	private int xPos = 0;
	private int yPos = 0;
	private int colCount = 0;
	private ButtonGroup radioButtonGroup;
	
	private String radioButtonNameWall = "radioButtonWall";
	private String radioButtonNameEnd = "radioButtonEnd";
	private String radioButtonNameStart = "radioButtonStart";
	
	private String jbuttonNameFindPath = "findPathBtn";
	private String jbuttonNameNextStep = "nextStepBtn";
	private String jbuttonNameRandomMaze = "randomMazeBtn";
	private String jbuttonNameClear = "clearBtn";
	
	

	
	
	GUI()
	{
		
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
		
		setupRadioButtons();
		setupRunButtons();
	}
	
	public void setupRadioButtons()
	{
		//create radio buttons
		JRadioButton wallRadioButton = new JRadioButton();
		JRadioButton endRadioButton = new JRadioButton();
		JRadioButton startRadioButton = new JRadioButton();
		
		//create the group for my radio buttons
		radioButtonGroup = new ButtonGroup();
		
		//set position and size of radio buttons
		wallRadioButton.setBounds(10,960,80,30);
		endRadioButton.setBounds(90,960,80,30);
		startRadioButton.setBounds(170,960,80,30);
		
		//set the text for my radio buttons
		wallRadioButton.setText("Wall");
		endRadioButton.setText("End");
		startRadioButton.setText("Start");
		
		//set the name for my radio buttons
		wallRadioButton.setName(radioButtonNameWall);
		endRadioButton.setName(radioButtonNameEnd);
		startRadioButton.setName(radioButtonNameStart);
		
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
	
	public void setupRunButtons()
	{
		JButton findPathBtn = new JButton("Find Path");
		JButton stepBtn = new JButton("Next Step");
		JButton clearBtn = new JButton("Clear");
		JButton randomMaze = new JButton("Random");
		
		findPathBtn.setName(jbuttonNameFindPath);
		stepBtn.setName(jbuttonNameNextStep);
		randomMaze.setName(jbuttonNameRandomMaze);
		clearBtn.setName(jbuttonNameClear);
		
		
		findPathBtn.setBounds(300,965,100,30);
		stepBtn.setBounds(405,965,100,30);
		randomMaze.setBounds(510,965,100,30);
		clearBtn.setBounds(620,965,100,30);
		
		findPathBtn.addActionListener(new JButtonActionListener());
		stepBtn.addActionListener(new JButtonActionListener());
		randomMaze.addActionListener(new JButtonActionListener());
		clearBtn.addActionListener(new JButtonActionListener());
		
		
		frame.add(findPathBtn);
		frame.add(stepBtn);
		frame.add(clearBtn);
		frame.add(randomMaze);
		
		
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
		frame.setResizable(false); 
		frame.setVisible(true);
	}
	
	public void deleteFrame()
	{
		frame.dispose();
	}
	
	//GETTERS
	public String getRadioButtonNameWall()
	{
		return radioButtonNameWall;
	}
	public String getRadioButtonNameEnd()
	{
		return radioButtonNameEnd;
	}
	public String getRadioButtonNameStart()
	{
		return radioButtonNameStart;
	}
	
	public String getJButtonNameFindPath()
	{
		return jbuttonNameFindPath;
	}
	public String getJButtonNameNextStep()
	{
		return jbuttonNameNextStep;
	}
	public String getJButtonNameRandomMaze()
	{
		return jbuttonNameRandomMaze;
	}
	public String getJButtonNameClear()
	{
		return jbuttonNameClear;
	}
}
