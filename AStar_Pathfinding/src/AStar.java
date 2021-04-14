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
	public static final int numRows = 12;
	public static final int numCols = 23;
	public static final char openCharacter = '-';
	public static final Color openColor = Color.gray;
	private static final Node grid[][] = new Node[numRows][numCols];
	private static Node startLocation = null;
	private static Node endLocation = null;
	//used as a lookup table
	//pos 0 = wall pos 1 = start pos 2 = end
	private static final Color[] possibleColors = {Color.black, Color.cyan, Color.magenta};
	private static final char[] possibleCharacters = {'W','S','E'};
	private static char characterToPlace = possibleCharacters[0];
	private static Color colorToPlace = possibleColors[0];
	
	
	public static void main(String[] args)
	{
		setupButtonsAndArray();
		//You can do either way but the first way requires that I create a setBackGroundColor() function in NodeButton
		//grid[7][15].getNodeButton().setBackGroundColor(Color.red);
		//grid[7][15].getNodeButton().getButton().setBackground(Color.red);
		//grid[0][0].setFValue(100);
		//grid[0][0].setGValue(25);
		//grid[0][0].setHValue(300);
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
		if(characterToPlace == possibleCharacters[1])
		{
			eraseStart();
			startLocation = grid[r][c];
			//if I am placing the start or end node I want to have it display this instead
			grid[r][c].getNodeButton().setButtonDisplay("Start");
		}
		//If I am place the end location
		//check to make sure there is not already an end defined.
		else if(characterToPlace == possibleCharacters[2])
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
		node.getNodeButton().setBackGroundColor(openColor);
		node.getNodeButton().clearButtonText();
	}
	
	
	public static void loadMazeIntoArray(int r, int c, char val)
	{
//		//erase start and finish if this is the first time calling this function
//		//a reading loop will call this and we need a to set the 
//		if(r == 0)
//		{
//			startLocation = null;
//			endLocation = null;
//		}
		//delete all the information in the node
		clearNode(grid[r][c]);
		Color color = null;
		
		//figure out what color to set the button
		if(val == possibleCharacters[0])
		{
			color = possibleColors[0];
		}
		else if(val == possibleCharacters[1])
		{
			color = possibleColors[1];
			startLocation = grid[r][c];
		}
		else if(val == possibleCharacters[2])
		{
			color = possibleColors[2];
			endLocation = grid[r][c];
		}
		else if(val == openCharacter)
		{
			color = openColor;
		}
		else
		{
			System.out.println("Error: Reading file");
		}
		
		//set the value to what was read in the text file.
		grid[r][c].setValue(val);
		//set the color to the corresponding color with this value
		grid[r][c].getNodeButton().setBackGroundColor(color);
		
		
	}
	
	//GETTERS
	public static int getRows()
	{
		return numRows;
	}
	public static int getCols()
	{
		return numCols;
	}
	public static char getOpenCharacter()
	{
		return openCharacter;
	}
	public static Color getOpenColor()
	{
		return openColor;
	}

}
