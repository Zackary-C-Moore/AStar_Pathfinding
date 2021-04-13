import java.awt.Color;

//Zackary Moore

public class AStar
{
	public static final int rows = 12;
	public static final int cols = 23;
	public static final char openCharacter = '-';
	public static final Color openColor = Color.gray;
	private static final Node grid[][] = new Node[rows][cols];
	private static Node startLocation = null;
	private static Node endLocation = null;
	//used as a lookup table
	//pos 0 = wall pos 1 = start pos 2 = end
	private static final Color[] possibleColors = {Color.black, Color.cyan, Color.magenta};
	private static final char[] possibleCharacters = {'W','S','E'};
	private static char characterToPlace = possibleCharacters[0];
	private static Color colorToPlace = possibleColors[0];
	private static final Node blankNode = new Node();
	public static void main(String[] args) 
	{
		setupButtonsAndArray();
		//You can do either way but the first way requires that I create a setBackGroundColor() function in NodeButton
		//grid[7][15].getNodeButton().setBackGroundColor(Color.red);
		//grid[7][15].getNodeButton().getButton().setBackground(Color.red);
		grid[0][0].setFValue(100);
		grid[0][0].setGValue(25);
		grid[0][0].setHValue(300);
		//This could also work to get the button to display the correct h value
		//But I think it is easier to have .setHValue() in the Node class call
		//setHDisplay in the NodeButton class.  The logic is, every time I wan to 
		//change a nodes value then display should be changed as well.
		//grid[0][0].getNodeButton().setHDisplay(String.valueOf(grid[0][0].getHValue()));
	}
	//Might want to move this logic to GUI and then create a function here
	//to pass the information back to add to 2d array.
	public static void setupButtonsAndArray()
	{
		GUI gui = new GUI();
		for(int r = 0; r < rows; r++)
		{
			for(int c = 0; c < cols; c++)
			{
				grid[r][c] = new Node();
				grid[r][c].setCol(c);
				grid[r][c].setRow(r);
				grid[r][c].setNodeButton(gui.addButtons());
				grid[r][c].getNodeButton().setBackGroundColor(openColor);
				//You can do either way but the first way requires that I create a setName() function in NodeButton
				grid[r][c].getNodeButton().setName(r,c);
				//grid[r][c].getNodeButton().getButton().setName(r + "," + c);
				//Add action listener to all of my buttons.
				grid[r][c].getNodeButton().getButton().addActionListener(new NodeButtonActionListener());
			}
		}
		gui.showFrame();
	}
	
	//Debugging purposes so I can make sure the GUI and the 2d array are identical.
	public static void displayBoard()
	{
		for(int r = 0; r < rows; r++)
		{ 
			for(int c = 0; c < cols; c++)
			{
				System.out.print(grid[r][c].getValue());
			}
			System.out.println();
		}
		if(startLocation != null)
			System.out.println(startLocation.getRow() + ", " + startLocation.getCol());
		else
			System.out.println("NOTHING");
		
		if(endLocation != null)
			System.out.println(endLocation.getRow() + ", " + endLocation.getCol());
		else
			System.out.println("NOTHING");
	}
	
	public static void updateBoardAfterClickEvent(int r, int c)
	{
		//If I am placing something over the start (changing it from start to a wall or start to end)
		//Make sure to set start to null so the simulation cannot take place until there is a start
		if(startLocation == grid[r][c])
		{
			startLocation = null;
		}
		//If I am placing something over the end (changing it from end to a wall or end to start)
		//make sure to set end to null so the simulation cannot take place until there is an end.
		else if(endLocation == grid[r][c])
		{
			endLocation = null;
		}
		
		//If I am placing the start location
		//check to make sure there is not already a start defined.
		if(characterToPlace == possibleCharacters[1])
		{
			eraseStart();
			startLocation = grid[r][c];
			grid[r][c].setHValue(characterToPlace);
		}
		//If I am place the end location
		//check to make sure there is not already an end defined.
		else if(characterToPlace == possibleCharacters[2])
		{
			eraseEnd();
			endLocation = grid[r][c];
			grid[r][c].setHValue(characterToPlace);
		}

		grid[r][c].setValue(characterToPlace);
		grid[r][c].getNodeButton().setBackGroundColor(colorToPlace);
		displayBoard();
	}
	
	public static void setCharacterColorToPlace(int x)
	{
		//0 = wall 1 = start 2 = end
		characterToPlace = possibleCharacters[x];
		colorToPlace = possibleColors[x];
	}
	

	public static void eraseStart()
	{
		//if there was already a start location clear the old one
		if(startLocation != null)
		{
			//change the old start location to nothing.
			startLocation.setValue(openCharacter);
			startLocation.getNodeButton().setBackGroundColor(openColor);
		}
		
	}
	
	public static void eraseEnd()
	{
		//if there was already an end location clear the old one.
		if(endLocation != null)
		{
			//change the old end location to nothing
			endLocation.setValue(openCharacter);
			endLocation.getNodeButton().setBackGroundColor(openColor);
		}
	}

}
