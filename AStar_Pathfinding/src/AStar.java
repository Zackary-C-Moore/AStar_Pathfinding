import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

//Zackary Moore

public class AStar
{
	private static int numRows = Details.getNumRows();
	private static int numCols = Details.getNumCols();
	private static final Node grid[][] = new Node[numRows][numCols];
	private static Node startLocation = null;
	private static Node endLocation = null;
	private static char characterToPlace = Details.getWallCharacter();
	private static Color colorToPlace = Details.getWallColor();
	
	
	public static void main(String[] args)
	{
		setupButtonsAndArray();
		//You can do either way but the first way requires that I create a setBackGroundColor() function in NodeButton
		//grid[7][15].getNodeButton().setBackGroundColor(Color.red);
		//grid[7][15].getNodeButton().getButton().setBackground(Color.red);
		//grid[0][0].setFValue(20);
		//grid[0][0].setGValue(25);
		//grid[0][0].setHValue(30);
		//This could also work to get the button to display the correct h value
		//But I think it is easier to have .setHValue() in the Node class call
		//setHDisplay in the NodeButton class.  The logic is, every time I wan to 
		//change a nodes value then display should be changed as well.
		//grid[0][0].getNodeButton().setHDisplay(String.valueOf(grid[0][0].getHValue()));
		//determineAdjacentNodes(grid[5][5]);
	}
	//Might want to move this logic to GUI and then create a function here
	//to pass the information back to add to 2d array.
	public static void setupButtonsAndArray()
	{
		GUI.setupFrame();
		for(int r = 0; r < numRows; r++)
		{
			for(int c = 0; c < numCols; c++)
			{
				grid[r][c] = new Node();
				grid[r][c].setCol(c);
				grid[r][c].setRow(r);
				grid[r][c].setNodeButton(GUI.addButtons(r,c));
			}
		}
		GUI.showFrame();
	}
	
	//Debugging purposes so I can make sure the GUI and the 2d array are identical.
	public static void displayBoard()
	{
		for(int r = 0; r < numRows; r++)
		{ 
			for(int c = 0; c < numCols; c++)
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
		//clear the node of any values it might already have
		clearNode(grid[r][c]);
		
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
		if(characterToPlace == Details.getStartCharacter())
		{
			eraseStart();
			startLocation = grid[r][c];
			//if I am placing the start or end node I want to have it display this instead
			grid[r][c].getNodeButton().setButtonDisplay("Start");
		}
		//If I am place the end location
		//check to make sure there is not already an end defined.
		else if(characterToPlace == Details.getEndCharacter())
		{
			eraseEnd();
			endLocation = grid[r][c];
			//if I am placing the start or end node I want to have it display this instead
			grid[r][c].getNodeButton().setButtonDisplay("End");
		}

		grid[r][c].setValue(characterToPlace);
		grid[r][c].getNodeButton().setBackGroundColor(colorToPlace);
		displayBoard();
	}
	
	public static void setCharacterColorToPlace(char v, Color c)
	{
		//0 = wall 1 = start 2 = end
		characterToPlace = v;
		colorToPlace = c;
	}
	

	public static void eraseStart()
	{
		//if there was already a start location clear the old one
		if(startLocation != null)
		{
			//change the old start location to nothing.
			clearNode(startLocation);
		}
		
	}
	
	public static void eraseEnd()
	{
		//if there was already an end location clear the old one.
		if(endLocation != null)
		{
			//change the old end location to nothing
			clearNode(endLocation);
		}
	}
	
	public static void resetBoard()
	{
		for(int r = 0; r < numRows; r++)
		{ 
			for(int c = 0; c < numCols; c++)
			{
				clearNode(grid[r][c]);
				startLocation = null;
				endLocation = null;
			}
		}
		
		displayBoard();
	}
	
	public static void clearNode(Node node)
	{
		node.setValue('-');
		node.getNodeButton().setBackGroundColor(Details.getOpenColor());
		node.getNodeButton().clearButtonText();
	}
	
	
	public static void loadMazeIntoArray(int r, int c, char val)
	{
		//delete all the information in the node
		clearNode(grid[r][c]);
		Color color = null;
		
		//figure out what color to set the button
		//wall
		if(val == Details.getWallCharacter())
		{
			color = Details.getWallColor();
		}
		//start location
		else if(val == Details.getStartCharacter())
		{
			color = Details.getStartColor();
			startLocation = grid[r][c];
			grid[r][c].getNodeButton().setButtonDisplay("Start");
		}
		//end location
		else if(val == Details.getEndCharacter())
		{
			color = Details.getEndColor();
			endLocation = grid[r][c];
			grid[r][c].getNodeButton().setButtonDisplay("End");
		}
		else if(val == Details.getOpenCharacter())
		{
			color = Details.getOpenColor();
		}
		else
		{
			System.out.println("Error: Reading file bad character read");
		}
		
		//set the value to what was read in the text file.
		grid[r][c].setValue(val);
		//set the color to the corresponding color with this value
		grid[r][c].getNodeButton().setBackGroundColor(color);
		
		
	}
	
	public static void determineAdjacentNodes(Node node, boolean checkStart)
	{
		boolean up = false;
		boolean left = false;
		boolean right = false;
		boolean down = false;
		Node possibleAdjNode = null;
		int r;
		int c;
		
		if(checkStart)
		{
			node = startLocation;
		}
		
		r = node.getRow();
		c = node.getCol();
		
		if(node.getRow() > 0)
		{
			//can check UP
			up = true;
			possibleAdjNode = grid[r - 1][c];
			if(possibleAdjNode.isOpenSpot())
			{
				possibleAdjNode.getNodeButton().setBackGroundColor(Color.BLUE);
			}
		}
		if(node.getRow() < Details.getNumRows() - 1)
		{
			//can check DOWN
			down = true;
			possibleAdjNode = grid[r + 1][c];
			if(possibleAdjNode.isOpenSpot())
			{
				possibleAdjNode.getNodeButton().setBackGroundColor(Color.BLUE);
			}
		}
		if(node.getCol() > 0)
		{
			//can check LEFT
			left = true;
			possibleAdjNode = grid[r][c - 1];
			if(possibleAdjNode.isOpenSpot())
			{
				possibleAdjNode.getNodeButton().setBackGroundColor(Color.BLUE);
			}
		}
		if(node.getCol() < Details.getNumCols() - 1)
		{
			//can check RIGHT
			right = true;
			possibleAdjNode = grid[r][c + 1];
			if(possibleAdjNode.isOpenSpot())
			{
				possibleAdjNode.getNodeButton().setBackGroundColor(Color.BLUE);
			}
		}
		
		//check diagonals
		if(up && left)
		{
			possibleAdjNode = grid[r - 1][c - 1];
			if(possibleAdjNode.isOpenSpot())
			{
				possibleAdjNode.getNodeButton().setBackGroundColor(Color.BLUE);
			}
		}
		if(up && right)
		{
			possibleAdjNode = grid[r - 1][c + 1];
			if(possibleAdjNode.isOpenSpot())
			{
				possibleAdjNode.getNodeButton().setBackGroundColor(Color.BLUE);
			}
		}
		if(down && left)
		{
			possibleAdjNode = grid[r + 1][c - 1];
			if(possibleAdjNode.isOpenSpot())
			{
				possibleAdjNode.getNodeButton().setBackGroundColor(Color.BLUE);
			}
		}
		if(down && right)
		{
			possibleAdjNode = grid[r + 1][c + 1];
			if(possibleAdjNode.isOpenSpot())
			{
				possibleAdjNode.getNodeButton().setBackGroundColor(Color.BLUE);
			}
		}	
	}
	
	//GETTERS
	public static Node getNodeFromArray(int r, int c)
	{
		return grid[r][c];
	}

}
