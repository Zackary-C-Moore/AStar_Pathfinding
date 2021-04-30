import java.awt.Color;
import java.util.ArrayList;

import jdk.dynalink.beans.StaticClass;


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
	//possible nodes to evaluate
	//starts as startLocation
	private static ArrayList<Node> openSet = new ArrayList<Node>();
	//start as empty
	//the set of nodes that have already been evaluated
	private static ArrayList<Node> closedSet = new ArrayList<Node>();
	private static boolean firstLoad = true;
	
	
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
			System.out.println("Start: " + startLocation.getRow() + ", " + startLocation.getCol());
		else
			System.out.println("NOTHING");
		
		if(endLocation != null)
			System.out.println("End: " + endLocation.getRow() + ", " + endLocation.getCol());
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
	
	public static void determineAdjacentNodes(Node currentNode)
	{
		boolean up = false;
		boolean left = false;
		boolean right = false;
		boolean down = false;
		Node possibleAdjNode = null;
		int r;
		int c;
		int tempG;
		
		
		r = currentNode.getRow();
		c = currentNode.getCol();
		
		if(currentNode.getRow() > 0)
		{
			//can check UP
			up = true;
			possibleAdjNode = grid[r - 1][c];
			if(!possibleAdjNode.isWall())
			{
				//possibleAdjNode.getNodeButton().setBackGroundColor(Color.BLUE);
				adjacentNodeEvaluation(currentNode, possibleAdjNode);
			}
		}
		if(currentNode.getRow() < Details.getNumRows() - 1)
		{
			//can check DOWN
			down = true;
			possibleAdjNode = grid[r + 1][c];
			if(!possibleAdjNode.isWall())
			{
				adjacentNodeEvaluation(currentNode, possibleAdjNode);
			}
		}
		if(currentNode.getCol() > 0)
		{
			//can check LEFT
			left = true;
			possibleAdjNode = grid[r][c - 1];
			if(!possibleAdjNode.isWall())
			{
				adjacentNodeEvaluation(currentNode, possibleAdjNode);
			}
		}
		if(currentNode.getCol() < Details.getNumCols() - 1)
		{
			//can check RIGHT
			right = true;
			possibleAdjNode = grid[r][c + 1];
			if(!possibleAdjNode.isWall())
			{
				adjacentNodeEvaluation(currentNode, possibleAdjNode);
			}
		}
		
		//check diagonals
		if(up && left)
		{
			possibleAdjNode = grid[r - 1][c - 1];
			if(!possibleAdjNode.isWall())
			{
				adjacentNodeEvaluation(currentNode, possibleAdjNode);
			}
		}
		if(up && right)
		{
			possibleAdjNode = grid[r - 1][c + 1];
			if(!possibleAdjNode.isWall())
			{
				adjacentNodeEvaluation(currentNode, possibleAdjNode);
			}
		}
		if(down && left)
		{
			possibleAdjNode = grid[r + 1][c - 1];
			if(!possibleAdjNode.isWall())
			{
				adjacentNodeEvaluation(currentNode, possibleAdjNode);
			}
		}
		if(down && right)
		{
			possibleAdjNode = grid[r + 1][c + 1];
			if(!possibleAdjNode.isWall())
			{
				adjacentNodeEvaluation(currentNode, possibleAdjNode);
			}
		}	
	}
	
	public static boolean adjacentNodeInClosedSet(Node adjNode)
	{
		for(int i = 0; i < closedSet.size(); i++)
		{
			if(adjNode == closedSet.get(i))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean adjacentNodeInOpenSet(Node adjNode)
	{
		for(int i = 0; i < openSet.size(); i++)
		{
			if(adjNode == openSet.get(i))
			{
				return true;
			}
		}
		return false;
	}
	
	public static void adjacentNodeEvaluation(Node currentNode, Node adjNode)
	{
		int tempG = 0;
		boolean newPath = false;
		//if we have not already evaluated this node: it is not in the closed set
		if(!adjacentNodeInClosedSet(adjNode) && !adjNode.isWall())
		{
			//Calculating G value
			tempG = currentNode.getGValue() + 1;
			
			//we need to see if this node is already in the open list
			//We could have already seen this node and not ruled it out yet
			if(adjacentNodeInOpenSet(adjNode))
			{
				//if this node has already been seen: in our openset
				//we want to see which one has a lower g value
				if(tempG < adjNode.getGValue())
				{
					//set the g value because we have found a better g value for this node
					adjNode.setGValue(tempG);
					newPath = true;
				}
			}
			//this adjacent node is not in the open list
			else 
			{
				//add this to our list of nodes to explore and set its g value
				adjNode.setGValue(tempG);
				openSet.add(adjNode);
				newPath = true;
			}
			
			if(newPath)
			{
				//adjNode.getNodeButton().setBackGroundColor(Color.blue);
				//Calculating H value
				adjNode.setHValue(calculateHeuristic(adjNode));
				//Calculating F value
				adjNode.setFValue(adjNode.getGValue() + adjNode.getHValue());
				//determine who called this node
				adjNode.setPreviousNode(currentNode);
				//currentNode.setPreviousNode(adjNode);

			}
			
		}
	}
	
	public static int calculateHeuristic(Node adjNode)
	{
		//calculate the distance from one of the adjNodes to the end.
		int xStart = adjNode.getCol();
		int yStart = adjNode.getRow();
		int xEnd = endLocation.getCol();
		int yEnd = endLocation.getRow();
		
		int dist = (int) Math.sqrt((xEnd - xStart) * (xEnd - xStart) + (yEnd - yStart)*(yEnd - yStart));
		
		return dist;
	}
	
	public static void findPath()
	{
		int indexToExplore;
		Node nodeToExplore = null;
		if(firstLoad)
		{
			openSet.add(startLocation);
			firstLoad = false;
		}
		
		
		//System.out.println("NodeToExplore: " + nodeToExplore.getRow() + ", " + nodeToExplore.getCol());
		//Keep cycling through until openSet is empty or until I find the end.
		//while(!openSet.isEmpty())
		//{
			indexToExplore = 0;
			nodeToExplore = openSet.get(indexToExplore);
			//colorOpenSet();
			
			for(int i = 0; i < openSet.size(); i++)
			{
				//find the lowest f value
				//assume the first one in the list is the lowest from the start
				//System.out.println("F Values: " + openSet.get(i).getFValue());
				if(openSet.get(i).getFValue() < openSet.get(indexToExplore).getFValue())
				{
					//System.out.println("F Chosen -- " + openSet.get(i).getFValue());
					indexToExplore = i;
					nodeToExplore = openSet.get(i);
				}
			}
			nodeToExplore.getNodeButton().setBackGroundColor(Color.white);
			
			//we found the end
			if(nodeToExplore == endLocation)
			{
				//We found the end
				System.out.println("Found end");
				showPath(nodeToExplore);
				//break;
			}
			//we have not found the end yet
			else
			{
				openSet.remove(indexToExplore);
				closedSet.add(nodeToExplore);
				determineAdjacentNodes(nodeToExplore);
				
				colorOpenSet();
				colorClosedSet();
				showPath(nodeToExplore);
			}
		//}
		//there is no solution 
		if(openSet.isEmpty())
		{
			//no solution
			System.out.println("No Path Found");
		}
	}
	
	public static void showPath(Node start)
	{
		endLocation.getNodeButton().setBackGroundColor(Details.getEndColor());
		while(start.getPreviousNode() != null)
		{
			start = start.getPreviousNode();
			start.getNodeButton().setBackGroundColor(Color.blue);
		}
	}
	
	public static void colorClosedSet()
	{
		//System.out.println("Open Set");
		for(int i = 0; i < closedSet.size(); i++)
		{
			closedSet.get(i).getNodeButton().setBackGroundColor(Color.RED);
			//System.out.println(openSet.get(i).getRow() + ", " + openSet.get(i).getCol());
		}
	}
	
	public static void colorOpenSet()
	{
		//System.out.println("Open Set");
		for(int i = 0; i < openSet.size(); i++)
		{
			openSet.get(i).getNodeButton().setBackGroundColor(Color.ORANGE);
			//System.out.println(openSet.get(i).getRow() + ", " + openSet.get(i).getCol());
		}
	}
	
	//GETTERS
	public static Node getNodeFromArray(int r, int c)
	{
		return grid[r][c];
	}

}
